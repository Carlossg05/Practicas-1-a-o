// Main.java (Controlador)
package com.akihabara.market.model;

import com.akihabara.market.dao.ProductoDAO;
import com.akihabara.market.model.ProductoOtaku;
import com.akihabara.market.view.InterfazConsola;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InterfazConsola vista = new InterfazConsola();
        ProductoDAO productoDAO = new ProductoDAO(); // Asumiendo que ProductoDAO ya est� implementado y configura la conexi�n a BD

        int opcion;
        do {
            opcion = vista.mostrarMenuPrincipal();
            switch (opcion) {
                case 1: // A�adir producto
                    vista.mostrarMensaje("Has elegido A�adir producto.");
                    ProductoOtaku nuevoProducto = vista.pedirDatosNuevoProducto();
                    if (productoDAO.insertarProducto(nuevoProducto)) {
                        vista.mostrarMensajeExito("Producto a�adido con �xito.");
                    } else {
                        vista.mostrarMensajeError("Error al a�adir el producto.");
                    }
                    break;
                case 2: // Consultar producto por ID
                    vista.mostrarMensaje("Has elegido Consultar producto por ID.");
                    int idConsulta = vista.pedirIdProducto();
                    ProductoOtaku productoEncontrado = productoDAO.obtenerProductoPorId(idConsulta);
                    vista.mostrarProducto(productoEncontrado);
                    break;
                case 3: // Listar todos los productos
                    vista.mostrarMensaje("Has elegido Listar todos los productos.");
                    List<ProductoOtaku> todosLosProductos = productoDAO.obtenerTodosLosProductos();
                    vista.mostrarListaProductos(todosLosProductos);
                    break;
                case 4: // Listar productos por nombre
                    vista.mostrarMensaje("Has elegido Listar productos por nombre.");
                    String nombreConsulta = vista.pedirNombreProducto();
                    List<ProductoOtaku> productosPorNombre = productoDAO.obtenerProductosPorNombre(nombreConsulta);
                    vista.mostrarListaProductos(productosPorNombre);
                    break;
                case 5: // Listar productos por categor�a
                    vista.mostrarMensaje("Has elegido Listar productos por categor�a.");
                    String categoriaConsulta = vista.pedirCategoriaProducto();
                    List<ProductoOtaku> productosPorCategoria = productoDAO.obtenerProductosPorCategoria(categoriaConsulta);
                    vista.mostrarListaProductos(productosPorCategoria);
                    break;
                case 6: // Actualizar producto
                    vista.mostrarMensaje("Has elegido Actualizar producto.");
                    int idActualizar = vista.pedirIdProducto();
                    ProductoOtaku productoParaActualizar = productoDAO.obtenerProductoPorId(idActualizar);
                    if (productoParaActualizar != null) {
                        ProductoOtaku datosActualizados = vista.pedirDatosActualizacionProducto(productoParaActualizar);
                        if (productoDAO.actualizarProducto(datosActualizados)) {
                            vista.mostrarMensajeExito("Producto actualizado con �xito.");
                        } else {
                            vista.mostrarMensajeError("Error al actualizar el producto.");
                        }
                    } else {
                        vista.mostrarMensajeError("No se encontr� ning�n producto con el ID: " + idActualizar);
                    }
                    break;
                case 7: // Eliminar producto
                    vista.mostrarMensaje("Has elegido Eliminar producto.");
                    int idEliminar = vista.pedirIdProducto();
                    if (productoDAO.eliminarProducto(idEliminar)) {
                        vista.mostrarMensajeExito("Producto eliminado con �xito.");
                    } else {
                        vista.mostrarMensajeError("Error al eliminar el producto o el producto no existe.");
                    }
                    break;
                case 8: // Salir del programa
                    vista.mostrarMensaje("Saliendo del programa. �Hasta pronto!");
                    break;
                default:
                    vista.mostrarMensajeError("Opci�n no v�lida. Por favor, intenta de nuevo.");
            }
        } while (opcion != 8);
    }
}