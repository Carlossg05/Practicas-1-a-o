import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import com.google.gson.*;


public class Main {


    public static void main(String[] args) {
        String apiKey = "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";  // Sustituye esto por tu clave
        String prompt = "Dime quién fué Rasputín";


        try {
            HttpClient client = HttpClient.newHttpClient();


            JsonObject message = new JsonObject();
            message.addProperty("role", "user");
            message.addProperty("content", prompt);


            JsonArray messages = new JsonArray();
            messages.add(message);


            JsonObject body = new JsonObject();
            body.addProperty("model", "mistralai/mistral-7b-instruct:free");
            body.add("messages", messages);


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://openrouter.ai/api/v1/chat/completions"))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                    .build();


            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            // Extraer el texto generado
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            String resultado = json
                    .getAsJsonArray("choices")
                    .get(0)
                    .getAsJsonObject()
                    .getAsJsonObject("message")
                    .get("content")
                    .getAsString();


            System.out.println("Respuesta del LLM:\n" + resultado);


        } catch (Exception e) {
            System.out.println("Error al comunicar con OpenRouter: " + e.getMessage());
        }
    }
}
