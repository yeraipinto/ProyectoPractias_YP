package dao;

import model.ClienteOtaku;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para ClienteDAO
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteDAOTest {

    private static ClienteDAO dao;
    private static int clienteIdInsertado;

    @BeforeAll
    public static void setup() {
        dao = new ClienteDAO();
    }

    @Test
    @Order(1)
    public void testAgregarCliente() {
        ClienteOtaku nuevo = new ClienteOtaku("Test Usuario", "test@correo.com", "123456789");
        dao.agregarCliente(nuevo);

        // Verificamos si se añadió buscándolo por lista y comparando último insertado
        List<ClienteOtaku> clientes = dao.obtenerTodosLosClientes();
        assertFalse(clientes.isEmpty());

        ClienteOtaku ultimo = clientes.get(clientes.size() - 1);
        assertEquals("Test Usuario", ultimo.getNombre());
        assertEquals("test@correo.com", ultimo.getEmail());
        assertEquals("123456789", ultimo.getTelefono());

        clienteIdInsertado = ultimo.getId();
    }

    @Test
    @Order(2)
    public void testObtenerClientePorId() {
        ClienteOtaku cliente = dao.obtenerClientePorId(clienteIdInsertado);
        assertNotNull(cliente);
        assertEquals("Test Usuario", cliente.getNombre());
    }

    @Test
    @Order(3)
    public void testActualizarCliente() {
        ClienteOtaku cliente = dao.obtenerClientePorId(clienteIdInsertado);
        assertNotNull(cliente);

        cliente.setNombre("Usuario Modificado");
        cliente.setEmail("modificado@correo.com");
        cliente.setTelefono("987654321");

        boolean actualizado = dao.actualizarCliente(cliente);
        assertTrue(actualizado);

        ClienteOtaku actualizadoRecibido = dao.obtenerClientePorId(clienteIdInsertado);
        assertEquals("Usuario Modificado", actualizadoRecibido.getNombre());
    }

    @Test
    @Order(4)
    public void testEliminarCliente() {
        boolean eliminado = dao.eliminarCliente(clienteIdInsertado);
        assertTrue(eliminado);

        ClienteOtaku cliente = dao.obtenerClientePorId(clienteIdInsertado);
        assertNull(cliente);
    }
}
