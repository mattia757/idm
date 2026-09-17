package com.nttdata.idmccnobe.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.util.Constants;
import java.util.Date;

public class JwtToken {

    private static String getJwtSecretFromConfig() {
        String secret = System.getProperty(Constants.IDM_BE_JWT_SECRET_PROPERTY);
        if (secret == null || secret.trim().isEmpty()) {
            secret = System.getenv(Constants.IDM_BE_JWT_SECRET_ENV);
        }
        if (secret == null || secret.trim().isEmpty()) {
            throw new IllegalStateException("JWT secret non configurato");
        }
        return secret;
    }

    public static String createJWT(UserProfile userProfile, int tokenValidityMinutes) {
        String token = null;
        long expTimeMillis = System.currentTimeMillis() + (tokenValidityMinutes * 1000 * 60);
        try {
            Algorithm algorithm = Algorithm.HMAC256(getJwtSecretFromConfig());
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username", userProfile.getUsername())
                    .withClaim("idCooperativa", userProfile.getIdCooperativa())
                    .withClaim("nomeCooperativa", userProfile.getNomeCooperativa())
                    .withClaim("role", userProfile.getRole())
                    .withClaim("customerCancellation", userProfile.getCustomerCancellation())
                    .withClaim("valideDate", new Date())
                    .withExpiresAt(new Date(expTimeMillis))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }

    public static UserProfile decodeJWT(String token) {
    try {
        Algorithm algorithm = Algorithm.HMAC256(getJwtSecretFromConfig());
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();

        DecodedJWT jwt = verifier.verify(token);

        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(jwt.getClaim("username").asString());
        userProfile.setIdCooperativa(jwt.getClaim("idCooperativa").asInt());
        userProfile.setNomeCooperativa(jwt.getClaim("nomeCooperativa").asString());
        userProfile.setRole(jwt.getClaim("role").asString());
        userProfile.setCustomerCancellation(jwt.getClaim("customerCancellation").asString());
        return userProfile;
    } catch (JWTVerificationException ex) {
        return null;
    }
}

}
