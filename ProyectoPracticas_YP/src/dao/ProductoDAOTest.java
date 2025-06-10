package dao;

import model.ProductoOtaku;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para ProductoDAO
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductoDAOTest {

    private static ProductoDAO dao;
    private static int productoIdInsertado;

    @BeforeAll
    public static void init() {
        dao = new ProductoDAO();
    }

    @Test
    @Order(1)
    public void testAgregarProducto() {
        ProductoOtaku producto = new ProductoOtaku("Figura Test", "Figura", 19.99, 5);
        dao.agregarProducto(producto);

        List<ProductoOtaku> lista = dao.obtenerTodosLosProductos();
        assertFalse(lista.isEmpty());

        ProductoOtaku ultimo = lista.get(lista.size() - 1);
        assertEquals("Figura Test", ultimo.getNombre());
        assertEquals("Figura", ultimo.getCategoria());
        assertEquals(19.99, ultimo.getPrecio());
        assertEquals(5, ultimo.getStock());

        productoIdInsertado = ultimo.getId();
    }

    @Test
    @Order(2)
    public void testObtenerProductoPorId() {
        ProductoOtaku producto = dao.obtenerProductoPorId(productoIdInsertado);
        assertNotNull(producto);
        assertEquals("Figura Test", producto.getNombre());
    }

    @Test
    @Order(3)
    public void testActualizarProducto() {
        ProductoOtaku producto = dao.obtenerProductoPorId(productoIdInsertado);
        assertNotNull(producto);

        producto.setNombre("Figura Actualizada");
        producto.setCategoria("Merchandising");
        producto.setPrecio(24.99);
        producto.setStock(10);

        boolean actualizado = dao.actualizarProducto(producto);
        assertTrue(actualizado);

        ProductoOtaku actualizadoRecibido = dao.obtenerProductoPorId(productoIdInsertado);
        assertEquals("Figura Actualizada", actualizadoRecibido.getNombre());
        assertEquals("Merchandising", actualizadoRecibido.getCategoria());
    }

    @Test
    @Order(4)
    public void testEliminarProducto() {
        boolean eliminado = dao.eliminarProducto(productoIdInsertado);
        assertTrue(eliminado);

        ProductoOtaku producto = dao.obtenerProductoPorId(productoIdInsertado);
        assertNull(producto);
    }
}
