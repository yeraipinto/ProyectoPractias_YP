package services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase LlmService
 * ------------------------------------------
 * Estas pruebas requieren una clave API válida en config.properties
 * para que se comuniquen correctamente con OpenRouter.
 */

public class LlmServiceTest {
	
    private static LlmService servicio;

    @BeforeAll
    public static void setup() {
        servicio = new LlmService();
    }

    @Test
    public void testRespuestaValidaDesdeIA() {
        String prompt = "Escribe una breve descripción de un producto anime.";
        String respuesta = servicio.enviarPrompt(prompt);

        // Verificamos que haya respuesta y no sea un mensaje de error
        assertNotNull(respuesta);
        assertFalse(respuesta.isBlank(), "La respuesta no debe estar vacía.");
        assertFalse(respuesta.startsWith("Error"), "No debe comenzar con mensaje de error.");
    }
}
