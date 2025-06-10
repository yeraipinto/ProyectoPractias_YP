package controller;

import dao.ClienteDAO;
import dao.ProductoDAO;
import model.ClienteOtaku;
import model.ProductoOtaku;
import services.LlmService;
import view.InterfazConsola;

import java.util.List;

/**
 * Clase Main
 * ----------
 * Punto de entrada del programa en modo consola para Akihabara Market.
 * Permite navegar entre dos submenús: gestión de productos y gestión de clientes.
 * Utiliza la clase InterfazConsola para interacción con el usuario.
 */

public class Main {
    public static void main(String[] args) {
        // Inicializamos las clases de vista, DAOs y servicio IA
        InterfazConsola vista = new InterfazConsola();
        ProductoDAO productoDAO = new ProductoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        LlmService ia = new LlmService();

        boolean salir = false;

        // Bucle principal del menú
        while (!salir) {
            vista.mostrarMenuPrincipal(); // Muestra menú principal
            int opcion = vista.leerOpcion();

            switch (opcion) {
                // === Gestión de productos ===
                case 1 -> {
                    boolean volver = false;
                    while (!volver) {
                        vista.mostrarMenuProductos(); // Submenú de productos
                        int sub = vista.leerOpcion();
                        switch (sub) {
                            case 1 -> { // Añadir producto
                                ProductoOtaku nuevo = vista.leerDatosProductoNuevo();
                                productoDAO.agregarProducto(nuevo);
                                vista.mostrarMensaje("Producto añadido correctamente.");
                            }
                            case 2 -> { // Consultar por ID
                                int id = vista.leerIdProducto();
                                ProductoOtaku p = productoDAO.obtenerProductoPorId(id);
                                vista.mostrarProducto(p);
                            }
                            case 3 -> { // Ver todos los productos
                                List<ProductoOtaku> lista = productoDAO.obtenerTodosLosProductos();
                                vista.mostrarListaProductos(lista);
                            }
                            case 4 -> { // Actualizar producto
                                int id = vista.leerIdProducto();
                                ProductoOtaku p = productoDAO.obtenerProductoPorId(id);
                                if (p != null) {
                                    ProductoOtaku actualizado = vista.leerDatosActualizados(p);
                                    productoDAO.actualizarProducto(actualizado);
                                    vista.mostrarMensaje("Producto actualizado.");
                                } else {
                                    vista.mostrarMensaje("Producto no encontrado.");
                                }
                            }
                            case 5 -> { // Eliminar producto
                                int id = vista.leerIdProducto();
                                if (productoDAO.eliminarProducto(id)) {
                                    vista.mostrarMensaje("Producto eliminado.");
                                } else {
                                    vista.mostrarMensaje("No se pudo eliminar el producto.");
                                }
                            }
                            case 6 -> { // Descripción IA
                                int id = vista.leerIdProducto();
                                ProductoOtaku p = productoDAO.obtenerProductoPorId(id);
                                if (p != null) {
                                    String prompt = "Genera una descripción atractiva para el producto otaku: " + p.getNombre() + " de la categoría " + p.getCategoria() + ".";
                                    String respuesta = ia.enviarPrompt(prompt);
                                    vista.mostrarMensaje("\nDescripción IA: " + respuesta);
                                } else {
                                    vista.mostrarMensaje("Producto no encontrado.");
                                }
                            }
                            case 7 -> { // Sugerencia de categoría IA
                                System.out.print("Introduce el nombre del nuevo producto: ");
                                String nombreNuevo = vista.leerCadena();
                                String prompt = "Para un producto otaku llamado '" + nombreNuevo + "', sugiere una categoría adecuada de esta lista: Figura, Manga, Póster, Llavero, Ropa, Videojuego, Otro.";
                                String respuesta = ia.enviarPrompt(prompt);
                                vista.mostrarMensaje("\nCategoría sugerida: " + respuesta);
                            }
                            case 8 -> volver = true; // Volver al menú principal
                            default -> vista.mostrarMensaje("Opción no válida.");
                        }
                    }
                }

                // === Gestión de clientes ===
                case 2 -> {
                    boolean volver = false;
                    while (!volver) {
                        vista.mostrarMenuClientes(); // Submenú de clientes
                        int sub = vista.leerOpcion();
                        switch (sub) {
                            case 1 -> { // Añadir cliente
                                ClienteOtaku nuevo = vista.leerDatosClienteNuevo();
                                clienteDAO.agregarCliente(nuevo);
                                vista.mostrarMensaje("Cliente añadido correctamente.");
                            }
                            case 2 -> { // Consultar por ID
                                int id = vista.leerIdCliente();
                                ClienteOtaku c = clienteDAO.obtenerClientePorId(id);
                                vista.mostrarCliente(c);
                            }
                            case 3 -> { // Ver todos los clientes
                                List<ClienteOtaku> lista = clienteDAO.obtenerTodosLosClientes();
                                vista.mostrarListaClientes(lista);
                            }
                            case 4 -> { // Actualizar cliente
                                int id = vista.leerIdCliente();
                                ClienteOtaku c = clienteDAO.obtenerClientePorId(id);
                                if (c != null) {
                                    ClienteOtaku actualizado = vista.leerDatosActualizadosCliente(c);
                                    clienteDAO.actualizarCliente(actualizado);
                                    vista.mostrarMensaje("Cliente actualizado.");
                                } else {
                                    vista.mostrarMensaje("Cliente no encontrado.");
                                }
                            }
                            case 5 -> { // Eliminar cliente
                                int id = vista.leerIdCliente();
                                if (clienteDAO.eliminarCliente(id)) {
                                    vista.mostrarMensaje("Cliente eliminado.");
                                } else {
                                    vista.mostrarMensaje("No se pudo eliminar el cliente.");
                                }
                            }
                            case 6 -> volver = true; // Volver al menú principal
                            default -> vista.mostrarMensaje("Opción no válida.");
                        }
                    }
                }

                case 3 -> { // Salida del programa
                    salir = true;
                    vista.mostrarMensaje("¡Gracias por usar Akihabara Market!");
                }

                default -> vista.mostrarMensaje("Opción no válida.");
            }
        }
    }
}
