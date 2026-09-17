/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.idmccnobe.util;

import java.util.Map;
import kong.unirest.HttpRequest;
import kong.unirest.HttpRequestWithBody;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.RequestBodyEntity;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

/**
 *
 * @author DelorenziVa
 */
public class UnirestClient {
    public static HttpResponse<JsonNode> call(String method, String url, String realm, String contentType, Map<String, Object> requestBodyParams, String authToken) {
        HttpResponse<JsonNode> response;
        if (requestBodyParams != null) {
            HttpRequestWithBody request = Unirest.request(method, url)
                    .routeParam("realm", realm)
                    .header("Content-Type", contentType);
            if (authToken != null) {
                request.header("Authorization", "Bearer " + authToken);
            }
            RequestBodyEntity requestBody = null;
            if (contentType.equals(Constants.CONTENT_TYPE_JSON)) {
                JSONObject bodyParamsJSON = new JSONObject(requestBodyParams);
                requestBody = request.body(bodyParamsJSON);
            }
            if (contentType.equals(Constants.CONTENT_TYPE_FORM)) {
                String bodyParamsForm = "";
                for (Map.Entry<String, Object> entry : requestBodyParams.entrySet()) {
                    bodyParamsForm = bodyParamsForm.concat("&" + entry.getKey() + "=" + entry.getValue());
                }
                requestBody = request.body(bodyParamsForm.substring(1));
            }
            response = requestBody.asJson();
        } else {
            HttpRequest request = Unirest.request(method, url)
                    .routeParam("realm", realm)
                    .header("Content-Type", contentType);
            if (authToken != null) {
                request.header("Authorization", "Bearer " + authToken);
            }
            response = request.asJson();
        }
        return response;
    }
}
