package com.aicodecommenter.service;

import com.google.gson.*;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;

public class AIService {

    // ✅ Put your key directly for now (college project)
    private static final String API_KEY = "xxx";

    // ✅ OpenRouter endpoint
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";

    public static String generateComments(String language, String code) {

        try {
            String prompt = """
            Add clear and professional comments to the following %s code.
            Keep it simple and clean.

            Code:
            %s
            """.formatted(language, code);

            // ---------- Request Body ----------
            JsonObject body = new JsonObject();
            body.addProperty("model", "openai/gpt-3.5-turbo");

            JsonArray messages = new JsonArray();
            JsonObject msg = new JsonObject();
            msg.addProperty("role", "user");
            msg.addProperty("content", prompt);
            messages.add(msg);

            body.add("messages", messages);

            // ---------- HTTP Request ----------
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body.toString(), StandardCharsets.UTF_8))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            // ---------- Response Parsing ----------
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

            // 🔐 SAFETY CHECK
            if (!json.has("choices")) {
                if (json.has("error")) {
                    return "API Error: " +
                            json.getAsJsonObject("error")
                                .get("message")
                                .getAsString();
                }
                return "Unexpected API response.";
            }

            JsonArray choices = json.getAsJsonArray("choices");

            if (choices.size() == 0) {
                return "No response from AI.";
            }

            JsonObject message =
                    choices.get(0)
                           .getAsJsonObject()
                           .getAsJsonObject("message");

            return message.get("content").getAsString();

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
