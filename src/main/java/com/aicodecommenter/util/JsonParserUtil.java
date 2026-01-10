/** 
 * This class provides utility methods for parsing JSON responses.
 */
package com.aicodecommenter.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonParserUtil {

    /**
     * Extracts comments from a JSON response.
     * 
     * @param jsonResponse the JSON response containing comments
     * @return extracted comments as a string, or "No comments generated." if no comments found
     */
    public static String extractComments(String jsonResponse) {
        try {
            JsonObject root = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonArray choices = root.getAsJsonArray("choices");
            if (choices != null && choices.size() > 0) {
                JsonObject firstChoice = choices.get(0).getAsJsonObject();
                JsonObject message = firstChoice.has("message") ? firstChoice.getAsJsonObject("message") : null;
                if (message != null && message.has("content")) {
                    return message.get("content").getAsString().trim();
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // optional: log for debugging
        }
        return "No comments generated.";
    }
}
