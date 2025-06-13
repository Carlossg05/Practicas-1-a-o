// src/main/java/com/akihabara/market/Main.java
package com.akihabara.market;

import com.akihabara.market.dao.DatabaseConnection;
import com.akihabara.market.dao.ProductoDAO;
import com.akihabara.market.model.ProductoOtaku;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS ---");

        DatabaseConnection dbConnection = new DatabaseConnection();
        if (dbConnection.getConexion() == null) {
            System.out.println("❌ Fallo de conexión a DB. Terminando pruebas.");
            return;
        }

        ProductoDAO productoDAO = new ProductoDAO();

        // 1. Agregar productos
        System.out.println("\n--- AGREGANDO PRODUCTOS ---");
        ProductoOtaku p1 = new ProductoOtaku("Figura de Son Goku SSJ", "Figura de PVC de alta calidad.", new BigDecimal("45.99"), 50, "Figura");
        productoDAO.agregarProducto(p1);
        ProductoOtaku p2 = new ProductoOtaku("Manga Attack on Titan Vol. 1", "Primer volumen del aclamado manga.", new BigDecimal("8.50"), 120, "Manga");
        productoDAO.agregarProducto(p2);
        ProductoOtaku p3 = new ProductoOtaku("Sudadera Akatsuki", "Sudadera de algodón con el logo Akatsuki.", new BigDecimal("35.00"), 75, "Merchandising");
        productoDAO.agregarProducto(p3);
        ProductoOtaku p4 = new ProductoOtaku("DVD Akira", "Película clásica de animación japonesa.", new BigDecimal("15.99"), 30, "Anime");
        productoDAO.agregarProducto(p4);

        // 2. Listar todos
        System.out.println("\n--- PRODUCTOS ACTUALES ---");
        productoDAO.obtenerTodosLosProductos().forEach(System.out::println);

        // 3. Obtener por ID y Actualizar
        System.out.println("\n--- OBTENER Y ACTUALIZAR ---");
        ProductoOtaku fetchedP1 = productoDAO.obtenerProductoPorId(p1.getId());
        if (fetchedP1 != null) {
            System.out.println("Obtenido: " + fetchedP1);
            fetchedP1.setPrecio(new BigDecimal("49.99"));
            fetchedP1.setStock(45);
            productoDAO.actualizarProducto(fetchedP1);
        } else {
            System.out.println("Producto ID " + p1.getId() + " no encontrado.");
        }

        // 4. Buscar por nombre y categoría
        System.out.println("\n--- BÚSQUEDAS ---");
        System.out.println("Por nombre 'Titan':");
        productoDAO.buscarProductosPorNombre("Titan").forEach(System.out::println);
        System.out.println("Por categoría 'Figura':");
        productoDAO.buscarProductoPorCategoria("Figura").forEach(System.out::println);


        // 5. Eliminar
        System.out.println("\n--- ELIMINANDO PRODUCTO ---");
        productoDAO.eliminarProducto(p3.getId());

        // 6. Listar todos de nuevo
        System.out.println("\n--- PRODUCTOS RESTANTES ---");
        productoDAO.obtenerTodosLosProductos().forEach(System.out::println);

        // Cerrar conexión
        productoDAO.cerrarConexionBaseDeDatos();
        System.out.println("\n--- PRUEBAS COMPLETADAS ---");
    }
}