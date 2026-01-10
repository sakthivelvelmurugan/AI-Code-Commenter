/**
 * A utility class for making POST requests using HttpClient.
 */
package com.aicodecommenter.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientUtil {

    /**
     * Makes a POST request to the specified URL with the provided JSON body and API key.
     *
     * @param url The URL to post the request to.
     * @param jsonBody The JSON body to include in the request.
     * @param apiKey The API key for authorization.
     * @return The response body from the POST request.
     * @throws Exception If an error occurs during the POST request.
     */
    public static String post(String url, String jsonBody, String apiKey) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response =
                HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}