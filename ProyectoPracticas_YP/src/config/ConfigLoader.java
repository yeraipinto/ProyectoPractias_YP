package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase ConfigLoader
 * ------------------
 * Esta clase se encarga de cargar configuraciones externas desde
 * un archivo `config.properties` ubicado en la raíz del proyecto.
 * Permite acceder a variables como la URL de la base de datos, usuario,
 * contraseña o la API key para servicios externos como OpenRouter (IA).
 */

public class ConfigLoader {
	// Objeto Properties que contendrá todas las claves/valores del archivo
    private static final Properties properties = new Properties();

    // Bloque estático: se ejecuta una vez al cargar la clase, o no, en caso de error
    static {
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Error cargando configuración: " + e.getMessage());
        }
    }

    // Devuelve la URL de conexión a la base de datos
    public static String getDBUrl() {
        return properties.getProperty("DB_URL");
    }

    // Devuelve el nombre de usuario para la base de datos
    public static String getDBUser() {
        return properties.getProperty("DB_USER");
    }

    // Devuelve la contraseña de la base de datos
    public static String getDBPassword() {
        return properties.getProperty("DB_PASSWORD");
    }
    
    // Devuelve la API key para el servicio de IA (OpenRouter)
    public static String getLLMApiKey() {
        return properties.getProperty("LLM_API_KEY");
    }
}