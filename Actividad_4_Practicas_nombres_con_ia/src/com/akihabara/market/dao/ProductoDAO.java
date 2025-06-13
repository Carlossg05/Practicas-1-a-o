// src/main/java/com/akihabara/market/dao/ProductoDAO.java
package com.akihabara.market.dao;

import com.akihabara.market.model.ProductoOtaku;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class ProductoDAO {

    private DatabaseConnection dbConnection;

    public ProductoDAO() {
        this.dbConnection = new DatabaseConnection();
    }

    private Connection getConexion() {
        Connection conn = dbConnection.getConexion();
        if (conn == null) {
            System.err.println("Error: Conexión a DB nula.");
        }
        return conn;
    }

    public void agregarProducto(ProductoOtaku producto) {
        String sql = "INSERT INTO producto (nombre, descripcion, precio, stock, categoria) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (conn == null) return;

            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.(3, producto.getPrecio());
            ProductoOtaku psetBigDecimalroducto;
			pstmt.setInt(4, psetBigDecimalroducto.getStock());
            pstmt.setString(5, producto.getCategoria());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        producto.setId(rs.getInt(1));
                        System.out.println("Producto '" + producto.getNombre() + "' agregado. ID: " + producto.getId());
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al agregar producto: " + e.getMessage());
        }
    }

    public ProductoOtaku obtenerProductoPorId(int id) {
        String sql = "SELECT id, nombre, descripcion, precio, stock, categoria FROM producto WHERE id = ?";
        ProductoOtaku producto = null;
        try (Connection conn = getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (conn == null) return null;

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    producto = new ProductoOtaku(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"),
                                                 rs.getBigDecimal("precio"), rs.getInt("stock"), rs.getString("categoria"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener producto por ID " + id + ": " + e.getMessage());
        }
        return producto;
    }

    public List<ProductoOtaku> obtenerTodosLosProductos() {
        List<ProductoOtaku> productos = new ArrayList<>();
        String sql = "SELECT id, nombre, descripcion, precio, stock, categoria FROM producto ORDER BY id";
        try (Connection conn = getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (conn == null) return productos;

            while (rs.next()) {
                productos.add(new ProductoOtaku(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"),
                                                rs.getBigDecimal("precio"), rs.getInt("stock"), rs.getString("categoria")));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los productos: " + e.getMessage());
        }
        return productos;
    }

    public boolean actualizarProducto(ProductoOtaku producto) {
        String sql = "UPDATE producto SET nombre = ?, descripcion = ?, precio = ?, stock = ?, categoria = ? WHERE id = ?";
        try (Connection conn = getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (conn == null) return false;

            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setBigDecimal(3, producto.getPrecio());
            pstmt.setInt(4, producto.getStock());
            pstmt.setString(5, producto.getCategoria());
            pstmt.setInt(6, producto.getId());

            boolean updated = pstmt.executeUpdate() > 0;
            if (updated) {
                System.out.println("Producto ID " + producto.getId() + " actualizado.");
            } else {
                System.out.println("Producto ID " + producto.getId() + " no encontrado para actualizar.");
            }
            return updated;
        } catch (SQLException e) {
            System.err.println("Error al actualizar producto ID " + producto.getId() + ": " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM producto WHERE id = ?";
        try (Connection conn = getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (conn == null) return false;

            pstmt.setInt(1, id);
            boolean deleted = pstmt.executeUpdate() > 0;
            if (deleted) {
                System.out.println("Producto ID " + id + " eliminado.");
            } else {
                System.out.println("Producto ID " + id + " no encontrado para eliminar.");
            }
            return deleted;
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto ID " + id + ": " + e.getMessage());
            return false;
        }
    }

    public List<ProductoOtaku> buscarProductosPorNombre(String nombre) {
        List<ProductoOtaku> productos = new ArrayList<>();
        String sql = "SELECT id, nombre, descripcion, precio, stock, categoria FROM producto WHERE LOWER(nombre) LIKE LOWER(?) ORDER BY nombre";
        try (Connection conn = getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (conn == null) return productos;

            pstmt.setString(1, "%" + nombre + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(new ProductoOtaku(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"),
                                                    rs.getBigDecimal("precio"), rs.getInt("stock"), rs.getString("categoria")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar productos por nombre '" + nombre + "': " + e.getMessage());
        }
        return productos;
    }

    public List<ProductoOtaku> buscarProductoPorCategoria(String categoria) {
        List<ProductoOtaku> productos = new ArrayList<>();
        String sql = "SELECT id, nombre, descripcion, precio, stock, categoria FROM producto WHERE LOWER(categoria) = LOWER(?) ORDER BY nombre";
        try (Connection conn = getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (conn == null) return productos;

            pstmt.setString(1, categoria);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(new ProductoOtaku(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"),
                                                    rs.getBigDecimal("precio"), rs.getInt("stock"), rs.getString("categoria")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar productos por categoría '" + categoria + "': " + e.getMessage());
        }
        return productos;
    }

    public void cerrarConexionBaseDeDatos() {
        dbConnection.cerrarConexion();
    }
}