package model;

/**
 * Clase ProductoOtaku
 * -------------------
 * Representa un objeto de tipo producto otaku dentro del sistema Akihabara Market.
 * Esta clase sigue el patrón POJO (Plain Old Java Object), sin lógica adicional.
 * Sirve como modelo de datos para ser almacenado en la base de datos y utilizado en la lógica de negocio.
 */

public class ProductoOtaku {
    private int id;				// Identificador único (autogenerado en la BD)
    private String nombre;		// Nombre del producto
    private String categoria;	// Categoría (Figura, Manga, Póster, etc.)
    private double precio;		// Precio en euros
    private int stock;			// Unidades disponibles en inventario

    /**
     * Constructor completo (incluye ID).
     * Usado cuando se recupera un producto desde la base de datos.
     */
    public ProductoOtaku(int id, String nombre, String categoria, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * Constructor sin ID (usado al insertar un nuevo producto).
     * El ID será asignado por la base de datos automáticamente.
     */
    public ProductoOtaku(String nombre, String categoria, double precio, int stock) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    // Getter
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    // Setter
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setStock(int stock) { this.stock = stock; }
}