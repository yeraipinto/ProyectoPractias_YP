package util;

import model.ProductoOtaku;
import dao.ProductoDAO;

/**
 * Clase SetupDatos
 * ----------------
 * Clase auxiliar que se ejecuta una vez para poblar la base de datos con datos iniciales
 * si está vacía. Se utiliza típicamente al iniciar el proyecto por primera vez.
 */

public class SetupDatos {
	/**
     * Método main que se ejecuta desde consola.
     * Verifica si la tabla de productos está vacía y, si es así, inserta algunos productos
     * de ejemplo relacionados con el mundo otaku.
     */
    public static void main(String[] args) {
    	// Instanciamos el DAO para acceder a la tabla 'productos'
        ProductoDAO dao = new ProductoDAO();

        // Si no hay productos en la base de datos, insertamos algunos ejemplos
        if (dao.obtenerTodosLosProductos().isEmpty()) {
            dao.agregarProducto(new ProductoOtaku("Figura Banpresto de Gojo Satoru (Jujutsu Kaisen)", "Figura", 42.99, 10));
            dao.agregarProducto(new ProductoOtaku("Manga Tokyo Revengers Vol.1 (Edición Española)", "Manga", 8.95, 25));
            dao.agregarProducto(new ProductoOtaku("Póster Oficial Evangelion Unit-01 A2", "Póster", 13.50, 15));
            System.out.println("Productos iniciales insertados.");
        } else {
        	// En caso contrario, informamos que ya hay datos
            System.out.println("La base de datos ya contiene productos.");
        }
    }
}