package dao;

import model.ClienteOtaku;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase ClienteDAO 
 * ----------------
 * Encargada para operaciones CRUD con clientes en la base de datos.
 * Proporciona métodos para agregar, consultar, actualizar y eliminar clientes.
 */

public class ClienteDAO {

    /**
     * Inserta un nuevo cliente en la base de datos.
     * @param cliente Objeto ClienteOtaku con los datos a insertar.
     */
    public void agregarCliente(ClienteOtaku cliente) {
        String sql = "INSERT INTO clientes (nombre, email, telefono) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefono());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recupera un cliente de la base de datos por su ID.
     * @param id ID del cliente.
     * @return ClienteOtaku correspondiente o null si no se encuentra.
     */
    public ClienteOtaku obtenerClientePorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ClienteOtaku(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("telefono")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtiene todos los clientes almacenados en la base de datos.
     * @return Lista de objetos ClienteOtaku.
     */
    public List<ClienteOtaku> obtenerTodosLosClientes() {
        List<ClienteOtaku> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new ClienteOtaku(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("telefono")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Actualiza los datos de un cliente existente.
     * @param cliente ClienteOtaku con los nuevos datos.
     * @return true si se actualizó correctamente, false si falló.
     */
    public boolean actualizarCliente(ClienteOtaku cliente) {
        String sql = "UPDATE clientes SET nombre=?, email=?, telefono=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefono());
            stmt.setInt(4, cliente.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina un cliente de la base de datos por su ID.
     * @param id ID del cliente a eliminar.
     * @return true si se eliminó correctamente, false si falló.
     */
    public boolean eliminarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}