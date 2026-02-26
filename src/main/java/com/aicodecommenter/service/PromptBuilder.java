package com.aicodecommenter.service;

public class PromptBuilder {

    public static String buildPrompt(Language language, String code) {
        return "Generate clear, line-by-line comments for the following code,each comment should explain the purpose of the line or block of code in a concise manner. and never change the original code. every time you must return the original code.\n\n"
                + language.name() + " code:\n\n" + code;
    }
}
