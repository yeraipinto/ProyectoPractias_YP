package view;

import java.util.*;
import model.ProductoOtaku;
import model.ClienteOtaku;

/**
 * Clase InterfazConsola
 * ----------------------
 * Representa la interfaz de usuario por consola.
 * Gestiona tanto productos como clientes.
 * Contiene menús, lectura de datos y visualización de resultados.
 */

public class InterfazConsola {
    private Scanner scanner = new Scanner(System.in);

    // Muestra el menú principal con acceso a productos, clientes o salida
    public void mostrarMenuPrincipal() {
        System.out.println("\n===== AKIHABARA MARKET - MENU PRINCIPAL =====");
        System.out.println("1. Gestionar productos");
        System.out.println("2. Gestionar clientes");
        System.out.println("3. Salir");
        System.out.print("Selecciona una opción: ");
    }

    // Submenú para operaciones de productos
    public void mostrarMenuProductos() {
        System.out.println("\n===== GESTIÓN DE PRODUCTOS =====");
        System.out.println("1. Añadir nuevo producto");
        System.out.println("2. Consultar producto por ID");
        System.out.println("3. Ver todos los productos");
        System.out.println("4. Actualizar producto existente");
        System.out.println("5. Eliminar producto");
        System.out.println("6. Generar descripción con IA");
        System.out.println("7. Sugerir categoría con IA");
        System.out.println("8. Volver al menú principal");
        System.out.print("Selecciona una opción: ");
    }

    // Submenú para operaciones de clientes
    public void mostrarMenuClientes() {
        System.out.println("\n===== GESTIÓN DE CLIENTES =====");
        System.out.println("1. Añadir nuevo cliente");
        System.out.println("2. Consultar cliente por ID");
        System.out.println("3. Ver todos los clientes");
        System.out.println("4. Actualizar cliente existente");
        System.out.println("5. Eliminar cliente");
        System.out.println("6. Volver al menú principal");
        System.out.print("Selecciona una opción: ");
    }

    // Solicita los datos para crear un nuevo producto
    public ProductoOtaku leerDatosProductoNuevo() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());
        System.out.print("Stock: ");
        int stock = Integer.parseInt(scanner.nextLine());
        return new ProductoOtaku(nombre, categoria, precio, stock);
    }

    // Solicita nuevos valores para actualizar un producto existente
    public ProductoOtaku leerDatosActualizados(ProductoOtaku p) {
        System.out.println("Actualizando producto: " + p.getNombre());
        System.out.print("Nuevo nombre (actual: " + p.getNombre() + "): ");
        String nombre = scanner.nextLine();
        System.out.print("Nueva categoría (actual: " + p.getCategoria() + "): ");
        String categoria = scanner.nextLine();
        System.out.print("Nuevo precio (actual: " + p.getPrecio() + "): ");
        double precio = Double.parseDouble(scanner.nextLine());
        System.out.print("Nuevo stock (actual: " + p.getStock() + "): ");
        int stock = Integer.parseInt(scanner.nextLine());
        p.setNombre(nombre);
        p.setCategoria(categoria);
        p.setPrecio(precio);
        p.setStock(stock);
        return p;
    }

    // Solicita los datos para crear un nuevo cliente
    public ClienteOtaku leerDatosClienteNuevo() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        return new ClienteOtaku(nombre, email, telefono);
    }

    // Solicita nuevos valores para actualizar un cliente existente
    public ClienteOtaku leerDatosActualizadosCliente(ClienteOtaku c) {
        System.out.println("Actualizando cliente: " + c.getNombre());
        System.out.print("Nuevo nombre (actual: " + c.getNombre() + "): ");
        String nombre = scanner.nextLine();
        System.out.print("Nuevo email (actual: " + c.getEmail() + "): ");
        String email = scanner.nextLine();
        System.out.print("Nuevo teléfono (actual: " + c.getTelefono() + "): ");
        String telefono = scanner.nextLine();
        c.setNombre(nombre);
        c.setEmail(email);
        c.setTelefono(telefono);
        return c;
    }

    // Solicita ID de producto
    public int leerIdProducto() {
        System.out.print("Introduce el ID del producto: ");
        return Integer.parseInt(scanner.nextLine());
    }

    // Solicita ID de cliente
    public int leerIdCliente() {
        System.out.print("Introduce el ID del cliente: ");
        return Integer.parseInt(scanner.nextLine());
    }

    // Muestra la información de un producto
    public void mostrarProducto(ProductoOtaku p) {
        if (p != null) {
            System.out.println("[ID: " + p.getId() + "] " + p.getNombre() + " | " + p.getCategoria() + " | " + p.getPrecio() + "€ | Stock: " + p.getStock());
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    // Muestra la información de un cliente
    public void mostrarCliente(ClienteOtaku c) {
        if (c != null) {
            System.out.println("[ID: " + c.getId() + "] " + c.getNombre() + " | " + c.getEmail() + " | Tel: " + c.getTelefono());
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    // Muestra lista completa de productos
    public void mostrarListaProductos(List<ProductoOtaku> lista) {
        for (ProductoOtaku p : lista) {
            mostrarProducto(p);
        }
    }

    // Muestra lista completa de clientes
    public void mostrarListaClientes(List<ClienteOtaku> lista) {
        for (ClienteOtaku c : lista) {
            mostrarCliente(c);
        }
    }

    // Muestra un mensaje simple por consola
    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    // Lee y valida una opción numérica del menú
    public int leerOpcion() {
        while (true) {
            try {
                String entrada = scanner.nextLine().trim();
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Introduce un número del menú: ");
            }
        }
    }

    // Lee una cadena de texto
    public String leerCadena() {
        return scanner.nextLine();
    }
}