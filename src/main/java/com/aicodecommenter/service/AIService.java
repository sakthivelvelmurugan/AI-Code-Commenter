package com.aicodecommenter.service;

import com.google.gson.*;
import java.net.URI;
import java.net.http.*;

public class AIService {

    private static final String API_KEY = "YOUR_API_KEY";
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";

    public static String generateComments(String lang, String code) {
        try {
            String prompt = "Add clear and professional comments to the following "
                    + lang + " code.\n\nCode:\n" + code;

            JsonObject body = new JsonObject();
            body.addProperty("model", "openai/gpt-3.5-turbo");

            JsonObject msg = new JsonObject();
            msg.addProperty("role", "user");
            msg.addProperty("content", prompt);

            body.add("messages", new JsonArray());
            body.getAsJsonArray("messages").add(msg);

            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                    .build();

            HttpResponse<String> res = HttpClient.newHttpClient()
                    .send(req, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() != 200)
                return "API Error: HTTP " + res.statusCode() + " - " + res.body();

            JsonObject json = JsonParser.parseString(res.body()).getAsJsonObject();

            if (json.has("error"))
                return "API Error: " + json.getAsJsonObject("error")
                        .get("message").getAsString();

            return json.getAsJsonArray("choices")
                    .get(0).getAsJsonObject()
                    .getAsJsonObject("message")
                    .get("content").getAsString();

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}