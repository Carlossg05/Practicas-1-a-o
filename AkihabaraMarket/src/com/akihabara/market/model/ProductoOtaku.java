package com.akihabara.market.model;

/**
 * Esta clase representa un producto otaku, como figuras, mangas o ropa.
 * Guarda información como el nombre, la categoría, el precio, el stock y una descripción del producto.
 */
public class ProductoOtaku {

    // Nombre del producto
    private String nombre;

    // Categoría del producto (ej. figuras, mangas, ropa)
    private String categoria;

    // Precio del producto
    private double precio;

    // Cantidad disponible del producto
    private int stock;

    // Descripción del producto
    private String descripcion;

    /**
     * Constructor vacío. Se usa cuando quieres crear un producto sin datos al principio.
     */
    public ProductoOtaku() {
    }

    /**
     * Constructor con todos los datos. Se usa para crear un producto completo desde el inicio.
     *
     * @param nombre Nombre del producto
     * @param categoria Tipo o categoría del producto
     * @param precio Precio del producto
     * @param stock Cantidad disponible
     * @param descripcion Descripción del producto
     */
    public ProductoOtaku(String nombre, String categoria, double precio, int stock, String descripcion) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
    }

    // Métodos para obtener y cambiar los valores de los atributos

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Muestra los datos principales del producto como texto.
     */
    @Override
    public String toString() {
        return "ProductoOtaku " + "nombre = " + nombre + ", precio = " + precio + ", descripcion = " + descripcion;
    }
}
