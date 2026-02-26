package com.aicodecommenter.service;

import com.aicodecommenter.api.HttpClientUtil;
import com.google.gson.*;

public class AIService {

    private static final String API_KEY = "YOUR_API_KEY";
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";

    public static String generateComments(String lang, String code) {
        try {
            // Use PromptBuilder to build the prompt
            Language language = Language.valueOf(lang.toUpperCase());
            String prompt = PromptBuilder.buildPrompt(language, code);

            JsonObject body = new JsonObject();
            body.addProperty("model", "openai/gpt-3.5-turbo");

            JsonObject msg = new JsonObject();
            msg.addProperty("role", "user");
            msg.addProperty("content", prompt);

            body.add("messages", new JsonArray());
            body.getAsJsonArray("messages").add(msg);

            // Use HttpClientUtil to make the HTTP request
            String response = HttpClientUtil.post(API_URL, body.toString(), API_KEY);

            JsonObject json = JsonParser.parseString(response).getAsJsonObject();

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