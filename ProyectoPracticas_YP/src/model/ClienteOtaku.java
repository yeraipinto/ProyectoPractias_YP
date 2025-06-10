package model;

/**
 * Clase ClienteOtaku
 * ------------------
 * Representa un cliente de Akihabara Market.
 * Contiene atributos básicos como nombre, email y teléfono,
 * y permite su encapsulación a través de métodos getter y setter.
 */

public class ClienteOtaku {
    private int id;             // Identificador único del cliente (clave primaria en BD)
    private String nombre;      // Nombre del cliente
    private String email;       // Correo electrónico del cliente
    private String telefono;    // Teléfono de contacto del cliente

    /**
     * Constructor con ID (para clientes ya registrados en base de datos).
     */
    public ClienteOtaku(int id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    /**
     * Constructor sin ID (para nuevos clientes antes de ser insertados en la base de datos).
     */
    public ClienteOtaku(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    // Getter
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }

    // Setter
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
