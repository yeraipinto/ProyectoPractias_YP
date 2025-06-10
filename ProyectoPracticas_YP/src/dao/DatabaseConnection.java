package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import config.ConfigLoader;

/**
 * Clase DatabaseConnection
 * ------------------------
 * Esta clase proporciona una única función: establecer una conexión con la base de datos.
 * Usa el patrón de utilidad (clase estática) y lee los parámetros desde el archivo `config.properties`
 * usando la clase `ConfigLoader`.
 */

public class DatabaseConnection {
	/**
     * Método estático que devuelve una conexión JDBC activa.
     * Usa los parámetros (URL, usuario, contraseña) definidos en config.properties.
     * 
     * @return Connection objeto que representa la conexión a la base de datos
     * @throws SQLException si no se puede establecer la conexión
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            ConfigLoader.getDBUrl(),		// URL de la BBDD
            ConfigLoader.getDBUser(),		// Usuario de la BBDD
            ConfigLoader.getDBPassword()	// Contraseña de la BBDD
        );
    }
}