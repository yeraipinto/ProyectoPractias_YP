package services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.*;
import config.ConfigLoader;

/**
 * Clase LlmService
 * ----------------
 * Servicio que permite conectar con un modelo de lenguaje (LLM)
 * a través de la API de OpenRouter (https://openrouter.ai).
 * Se utiliza para generar texto en base a un prompt proporcionado.
 */

public class LlmService {
	// Datos de la IA: URL, API KEY, MODEL (OpenRouter)
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";
    private static final String API_KEY = ConfigLoader.getLLMApiKey(); // Cogemos el API key de nuestro archivo config.properties
    private static final String MODEL = "mistralai/mistral-7b-instruct:free";

    /**
     * Método que envía un prompt al modelo de lenguaje y devuelve la respuesta textual.
     * @param promptUsuario Instrucción o pregunta escrita por el usuario
     * @return Texto generado por el modelo IA o mensaje de error
     */
    public String enviarPrompt(String promptUsuario) {
        try {
        	// Construimos el cuerpo de la solicitud JSON con modelo y mensajes
            JsonObject body = new JsonObject();
            body.addProperty("model", MODEL);

            // Creamos el mensaje del usuario como un array de objetos 'message'
            JsonArray messages = new JsonArray();
            JsonObject userMsg = new JsonObject();
            userMsg.addProperty("role", "user");
            userMsg.addProperty("content", promptUsuario);
            messages.add(userMsg);
            body.add("messages", messages);

            // Construimos la petición HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                    .build();

            // Enviamos la petición y obtenemos la respuesta como texto
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // Parseamos la respuesta JSON para extraer solo el texto generado
            JsonObject respuesta = JsonParser.parseString(response.body()).getAsJsonObject();
            return respuesta.getAsJsonArray("choices")		// Obtenemos array de respuestas
                            .get(0).getAsJsonObject()		// Elegimos la primera
                            .getAsJsonObject("message")		// Obtenemos el objeto mensaje
                            .get("content").getAsString();	// Devolvemos el texto generado

        } catch (Exception e) {
        	// Mensaje de error en caso de que no haya respuesta de la IA
            return "Error al comunicarse con la IA: " + e.getMessage();
        }
    }
}