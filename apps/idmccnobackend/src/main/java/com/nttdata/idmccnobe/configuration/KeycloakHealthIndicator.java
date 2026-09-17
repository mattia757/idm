package com.nttdata.idmccnobe.configuration;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("keycloak")
@ConditionalOnProperty(name = "keycloak.base-url")
public class KeycloakHealthIndicator implements HealthIndicator {

    private final HttpClient httpClient;
    private final URI discoveryUri;

    public KeycloakHealthIndicator(
            @Value("${keycloak.base-url}") String baseUrl,
            @Value("${keycloak.realm}") String realm) {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .build();
        this.discoveryUri = URI.create(stripTrailingSlash(baseUrl)
                + "/realms/" + realm + "/.well-known/openid-configuration");
    }

    @Override
    public Health health() {
        HttpRequest request = HttpRequest.newBuilder(discoveryUri)
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();
        try {
            HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                return Health.up()
                        .withDetail("realm", discoveryUri.toString())
                        .withDetail("statusCode", response.statusCode())
                        .build();
            }
            return Health.down()
                    .withDetail("realm", discoveryUri.toString())
                    .withDetail("statusCode", response.statusCode())
                    .build();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return Health.down(ex)
                    .withDetail("realm", discoveryUri.toString())
                    .build();
        } catch (Exception ex) {
            return Health.down(ex)
                    .withDetail("realm", discoveryUri.toString())
                    .build();
        }
    }

    private static String stripTrailingSlash(String value) {
        return value.endsWith("/") ? value.substring(0, value.length() - 1) : value;
    }
}
