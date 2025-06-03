package com.akihabara.market.model;

public class Main {
    public static void main(String[] args) {
        com.akihabara.market.model.ProductoOtaku producto1 = new com.akihabara.market.model.ProductoOtaku(
            "Figura de Son Goku Super Saiyajin",
            "Figuras de Acción",
            35.99,
            50,
            "Figura detallada de Son Goku de Dragon Ball Z."
        );

        System.out.println("Producto 1:");
        System.out.println(producto1.toString());

        System.out.println("\n--------------------\n");

        com.akihabara.market.model.ProductoOtaku producto2 = new com.akihabara.market.model.ProductoOtaku();
        producto2.setNombre("Manga Attack on Titan Vol. 1");
        producto2.setCategoria("Manga");
        producto2.setPrecio(9.99);
        producto2.setStock(120);
        producto2.setDescripcion("Primer volumen de la aclamada serie de manga Attack on Titan.");

        System.out.println("Producto 2:");
        System.out.println(producto2.toString());
    }
}