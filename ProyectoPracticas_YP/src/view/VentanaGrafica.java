package view;

import dao.ClienteDAO;
import dao.ProductoDAO;
import model.ClienteOtaku;
import model.ProductoOtaku;
import services.LlmService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Clase Ventana Gráfica
 * ---------------------
 * Representa la interfaz gráfica principal de la aplicación Akihabara Market.
 * Incluye gestión (CRUD), de productos y clientes con pestañas, y funcionalidades de IA.
 */

public class VentanaGrafica extends JFrame {

    // Componentes y servicios
    private JPanel contentPane;
    private JTable tablaProductos, tablaClientes;
    private DefaultTableModel modeloProductos, modeloClientes;
    private ProductoDAO productoDAO;
    private ClienteDAO clienteDAO;
    private LlmService ia;

    // Constructor principal
    public VentanaGrafica() {
        setTitle("Akihabara Market - Interfaz Gráfica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 500);
        setLocationRelativeTo(null);

        // Inicialización de DAOs y servicio IA
        productoDAO = new ProductoDAO();
        clienteDAO = new ClienteDAO();
        ia = new LlmService();

        // Configuración del panel principal
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        // Pestañas principales (productos y clientes)
        JTabbedPane tabs = new JTabbedPane();
        contentPane.add(tabs, BorderLayout.CENTER);

        // Botón para salir del programa
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "¡Gracias por usar Akihabara Market!");
            System.exit(0);
        });
        contentPane.add(btnSalir, BorderLayout.SOUTH);

        // ===================== PRODUCTOS =====================
        JPanel panelProductos = new JPanel(new BorderLayout());
        tabs.addTab("Productos", panelProductos);

        modeloProductos = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre", "Categoría", "Precio", "Stock" });
        tablaProductos = new JTable(modeloProductos);
        panelProductos.add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

        JPanel barraProductos = new JPanel();
        panelProductos.add(barraProductos, BorderLayout.NORTH);

        JButton btnAddProducto = new JButton("Añadir Producto");
        JButton btnUpdateProducto = new JButton("Actualizar Producto");
        JButton btnDeleteProducto = new JButton("Eliminar Producto");
        JButton btnConsultProducto = new JButton("Consultar por ID");
        JButton btnDescIA = new JButton("Descripción IA");
        JButton btnCategoriaIA = new JButton("Sugerir Categoría IA");

        barraProductos.add(btnAddProducto);
        barraProductos.add(btnUpdateProducto);
        barraProductos.add(btnDeleteProducto);
        barraProductos.add(btnConsultProducto);
        barraProductos.add(btnDescIA);
        barraProductos.add(btnCategoriaIA);

        // ===================== CLIENTES =====================
        JPanel panelClientes = new JPanel(new BorderLayout());
        tabs.addTab("Clientes", panelClientes);

        modeloClientes = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre", "Email", "Teléfono" });
        tablaClientes = new JTable(modeloClientes);
        panelClientes.add(new JScrollPane(tablaClientes), BorderLayout.CENTER);

        JPanel barraClientes = new JPanel();
        panelClientes.add(barraClientes, BorderLayout.NORTH);

        JButton btnAddCliente = new JButton("Añadir Cliente");
        JButton btnUpdateCliente = new JButton("Actualizar Cliente");
        JButton btnDeleteCliente = new JButton("Eliminar Cliente");
        JButton btnConsultCliente = new JButton("Consultar por ID");

        barraClientes.add(btnAddCliente);
        barraClientes.add(btnUpdateCliente);
        barraClientes.add(btnDeleteCliente);
        barraClientes.add(btnConsultCliente);

        // === Acciones Productos ===
        btnAddProducto.addActionListener(e -> mostrarFormularioProducto(null));
        btnUpdateProducto.addActionListener(e -> {
            int fila = tablaProductos.getSelectedRow();
            if (fila >= 0) {
                int id = (int) modeloProductos.getValueAt(fila, 0);
                ProductoOtaku p = productoDAO.obtenerProductoPorId(id);
                mostrarFormularioProducto(p);
            }
        });
        btnDeleteProducto.addActionListener(e -> {
            int fila = tablaProductos.getSelectedRow();
            if (fila >= 0) {
                int id = (int) modeloProductos.getValueAt(fila, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar producto ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    productoDAO.eliminarProducto(id);
                    cargarProductos();
                }
            }
        });
        btnConsultProducto.addActionListener(e -> {
            String entrada = JOptionPane.showInputDialog(this, "Introduce el ID del producto:");
            try {
                int id = Integer.parseInt(entrada);
                ProductoOtaku p = productoDAO.obtenerProductoPorId(id);
                if (p != null) {
                    JOptionPane.showMessageDialog(this, p.getNombre() + " | " + p.getCategoria() + " | €" + p.getPrecio() + " | Stock: " + p.getStock());
                }
            } catch (Exception ignored) {}
        });
        btnDescIA.addActionListener(e -> {
            int fila = tablaProductos.getSelectedRow();
            if (fila >= 0) {
                int id = (int) modeloProductos.getValueAt(fila, 0);
                ProductoOtaku p = productoDAO.obtenerProductoPorId(id);
                String prompt = "Genera una descripción atractiva para el producto otaku: " + p.getNombre() + " de la categoría " + p.getCategoria() + ".";
                String respuesta = ia.enviarPrompt(prompt);
                mostrarTextoIA("Descripción IA", respuesta);
            }
        });
        btnCategoriaIA.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Introduce el nombre del nuevo producto:");
            if (nombre != null && !nombre.isBlank()) {
                String prompt = "Para un producto otaku llamado '" + nombre + "', sugiere una categoría adecuada de esta lista: Figura, Manga, Póster, Llavero, Ropa, Videojuego, Otro.";
                String respuesta = ia.enviarPrompt(prompt);
                mostrarTextoIA("Categoría sugerida", respuesta);
            }
        });

        // === Acciones Clientes ===
        btnAddCliente.addActionListener(e -> mostrarFormularioCliente(null));
        btnUpdateCliente.addActionListener(e -> {
            int fila = tablaClientes.getSelectedRow();
            if (fila >= 0) {
                int id = (int) modeloClientes.getValueAt(fila, 0);
                ClienteOtaku c = clienteDAO.obtenerClientePorId(id);
                mostrarFormularioCliente(c);
            }
        });
        btnDeleteCliente.addActionListener(e -> {
            int fila = tablaClientes.getSelectedRow();
            if (fila >= 0) {
                int id = (int) modeloClientes.getValueAt(fila, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar cliente ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    clienteDAO.eliminarCliente(id);
                    cargarClientes();
                }
            }
        });
        btnConsultCliente.addActionListener(e -> {
            String entrada = JOptionPane.showInputDialog(this, "Introduce el ID del cliente:");
            try {
                int id = Integer.parseInt(entrada);
                ClienteOtaku c = clienteDAO.obtenerClientePorId(id);
                if (c != null) {
                    JOptionPane.showMessageDialog(this, c.getNombre() + " | " + c.getEmail() + " | " + c.getTelefono());
                }
            } catch (Exception ignored) {}
        });

        // Carga inicial de datos
        cargarProductos();
        cargarClientes();
    }

    // Cargar productos en la tabla
    private void cargarProductos() {
        modeloProductos.setRowCount(0);
        for (ProductoOtaku p : productoDAO.obtenerTodosLosProductos()) {
            modeloProductos.addRow(new Object[]{p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio(), p.getStock()});
        }
    }

    // Cargar clientes en la tabla
    private void cargarClientes() {
        modeloClientes.setRowCount(0);
        for (ClienteOtaku c : clienteDAO.obtenerTodosLosClientes()) {
            modeloClientes.addRow(new Object[]{c.getId(), c.getNombre(), c.getEmail(), c.getTelefono()});
        }
    }

    // Formulario para agregar o editar productos
    private void mostrarFormularioProducto(ProductoOtaku producto) {
        boolean esNuevo = (producto == null);
        JTextField nombre = new JTextField(esNuevo ? "" : producto.getNombre());
        JTextField categoria = new JTextField(esNuevo ? "" : producto.getCategoria());
        JTextField precio = new JTextField(esNuevo ? "" : String.valueOf(producto.getPrecio()));
        JTextField stock = new JTextField(esNuevo ? "" : String.valueOf(producto.getStock()));

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.add(new JLabel("Nombre:")); panel.add(nombre);
        panel.add(new JLabel("Categoría:")); panel.add(categoria);
        panel.add(new JLabel("Precio (€):")); panel.add(precio);
        panel.add(new JLabel("Stock:")); panel.add(stock);

        JButton limpiar = new JButton("Limpiar");
        limpiar.addActionListener(e -> { nombre.setText(""); categoria.setText(""); precio.setText(""); stock.setText(""); });

        Object[] opciones = { esNuevo ? "Agregar" : "Actualizar", "Cancelar", limpiar };
        int res = JOptionPane.showOptionDialog(this, panel, esNuevo ? "Nuevo Producto" : "Editar Producto",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

        if (res == 0) {
            try {
                double p = Double.parseDouble(precio.getText());
                int s = Integer.parseInt(stock.getText());
                if (esNuevo) {
                    productoDAO.agregarProducto(new ProductoOtaku(nombre.getText(), categoria.getText(), p, s));
                } else {
                    producto.setNombre(nombre.getText());
                    producto.setCategoria(categoria.getText());
                    producto.setPrecio(p);
                    producto.setStock(s);
                    productoDAO.actualizarProducto(producto);
                }
                cargarProductos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos.");
            }
        }
    }

    // Formulario para agregar o editar clientes
    private void mostrarFormularioCliente(ClienteOtaku cliente) {
        boolean esNuevo = (cliente == null);
        JTextField nombre = new JTextField(esNuevo ? "" : cliente.getNombre());
        JTextField email = new JTextField(esNuevo ? "" : cliente.getEmail());
        JTextField telefono = new JTextField(esNuevo ? "" : cliente.getTelefono());

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("Nombre:")); panel.add(nombre);
        panel.add(new JLabel("Email:")); panel.add(email);
        panel.add(new JLabel("Teléfono:")); panel.add(telefono);

        JButton limpiar = new JButton("Limpiar");
        limpiar.addActionListener(e -> { nombre.setText(""); email.setText(""); telefono.setText(""); });

        Object[] opciones = { esNuevo ? "Agregar" : "Actualizar", "Cancelar", limpiar };
        int res = JOptionPane.showOptionDialog(this, panel, esNuevo ? "Nuevo Cliente" : "Editar Cliente",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

        if (res == 0) {
            if (esNuevo) {
                clienteDAO.agregarCliente(new ClienteOtaku(nombre.getText(), email.getText(), telefono.getText()));
            } else {
                cliente.setNombre(nombre.getText());
                cliente.setEmail(email.getText());
                cliente.setTelefono(telefono.getText());
                clienteDAO.actualizarCliente(cliente);
            }
            cargarClientes();
        }
    }

    // Mostrar respuesta de la IA en un cuadro de texto formateado
    private void mostrarTextoIA(String titulo, String contenido) {
        JTextArea area = new JTextArea(contenido, 10, 50);
        area.setWrapStyleWord(true);
        area.setLineWrap(true);
        area.setEditable(false);
        JOptionPane.showMessageDialog(this, new JScrollPane(area), titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}