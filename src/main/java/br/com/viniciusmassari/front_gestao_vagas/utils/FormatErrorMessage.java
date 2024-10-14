package br.com.viniciusmassari.front_gestao_vagas.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FormatErrorMessage {
    // iremos receber o getResponseBodyAsString
    public static String formatErrorMessage(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(message);
            if (rootNode.isArray()) {
                return formatArrayErrorMessage(rootNode);
            }
            return rootNode.asText();

        } catch (Exception e) {
            return message;
        }
    }

    private static String formatArrayErrorMessage(JsonNode arrayNode) {
        StringBuilder formattedMessage = new StringBuilder();
        for (JsonNode node : arrayNode) {
            // concatenando erros. Pegamos apenas o que está dentro do campo "message"
            formattedMessage.append("- ").append(node.get("message").asText()).append("\n");
        }
        return formattedMessage.toString();
    }
}
