package com.aicodecommenter.util;

import com.google.gson.*;

public class JsonParserUtil {

    public static String extractComments(String jsonResponse) {
        try {
            JsonArray choices = JsonParser.parseString(jsonResponse)
                    .getAsJsonObject()
                    .getAsJsonArray("choices");

            if (choices != null && choices.size() > 0) {
                JsonObject message = choices.get(0)
                        .getAsJsonObject()
                        .getAsJsonObject("message");

                if (message != null && message.has("content")) {
                    return message.get("content").getAsString().trim();
                }
            }
        } catch (Exception ignored) {}

        return "No comments generated.";
    }
}