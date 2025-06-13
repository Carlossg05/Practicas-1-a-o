// LlmService.java
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class LlmService {

    private static final String OPENROUTER_API_URL = "https://openrouter.ai/api/v1/chat/completions";
    private final String apiKey;

    public LlmService() {
        this.apiKey = loadApiKey();
    }

    private String loadApiKey() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
            return properties.getProperty("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");
        } catch (IOException e) {
            System.err.println("Error loading API key from config.properties: " + e.getMessage());
            System.err.println("Please make sure config.properties exists and contains OPENROUTER_API_KEY=your_key_here");
            return null; // Or throw a more specific exception
        }
    }

    public String sugerirNombreProducto(String tipo, String franquicia) {
        if (apiKey == null || apiKey.isEmpty()) {
            return "Error: API Key no cargada. No se puede sugerir un nombre.";
        }

        String prompt = String.format("Sugiere un nombre llamativo y original para un producto otaku del tipo '%s' basado en la franquicia '%s'.", tipo, franquicia);
        String requestBody = String.format("{\"model\":\"google/gemini-flash-1.5\",\"messages\":[{\"role\":\"user\",\"content\":\"%s\"}]}", prompt);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(OPENROUTER_API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                // Parse the JSON response to extract the suggested name
                // This is a simplified parsing. In a real application, consider using a JSON library like Jackson or Gson.
                String responseBody = response.body();
                int contentStartIndex = responseBody.indexOf("\"content\":\"") + "\"content\":\"".length();
                int contentEndIndex = responseBody.indexOf("\"", contentStartIndex);
                if (contentStartIndex != -1 && contentEndIndex != -1) {
                    return responseBody.substring(contentStartIndex, contentEndIndex).replace("\\n", " ");
                } else {
                    return "Error al parsear la respuesta de la IA.";
                }
            } else {
                return "Error al conectar con OpenRouter. Código de estado: " + response.statusCode() + " | Cuerpo: " + response.body();
            }
        } catch (IOException | InterruptedException e) {
            return "Error de comunicación con la API de OpenRouter: " + e.getMessage();
        }
    }
}