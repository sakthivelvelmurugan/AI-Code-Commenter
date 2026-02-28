package com.aicodecommenter.service;

import com.aicodecommenter.api.HttpClientUtil;
import com.aicodecommenter.util.JsonParserUtil;
import com.google.gson.*;

public class AIService {

    private static final String API_KEY = "xxxx"; // Replace with your actual API key
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";

    private static Language convertToLanguage(String lang) {
        if ("C++".equals(lang)) {
            return Language.CPP;
        }
        return Language.valueOf(lang.toUpperCase());
    }

    public static String generateComments(String lang, String code) {
        try {
            // Use PromptBuilder to build the prompt
            Language language = convertToLanguage(lang);
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

            // Use JsonParserUtil to extract comments from the response
            return JsonParserUtil.extractComments(response);

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}