package com.akihabara.market.model;

/**
 * Esta clase contiene el m�todo principal que ejecuta el programa.
 * Crea dos productos otaku y muestra su informaci�n por pantalla.
 */
public class Main {

    /**
     * M�todo principal que se ejecuta al iniciar el programa.
     * Aqu� se crean dos objetos de tipo ProductoOtaku y se muestran sus datos.
     */
    public static void main(String[] args) {

        // Crear un producto usando el constructor con todos los datos
        com.akihabara.market.model.ProductoOtaku producto1 = new com.akihabara.market.model.ProductoOtaku(
            "Figura de Son Goku Super Saiyajin", // Nombre
            "Figuras de Acci�n",                  // Categor�a
            35.99,                                // Precio
            50,                                   // Stock
            "Figura detallada de Son Goku de Dragon Ball Z." // Descripci�n
        );

        // Mostrar informaci�n del primer producto
        System.out.println("Producto 1:");
        System.out.println(producto1.toString());

        System.out.println("\n---------------------------------------------------------------\n");

        // Crear un producto vac�o y luego asignar los datos uno por uno
        com.akihabara.market.model.ProductoOtaku producto2 = new com.akihabara.market.model.ProductoOtaku();
        producto2.setNombre("Manga Attack on Titan Vol. 1");
        producto2.setCategoria("Manga");
        producto2.setPrecio(9.99);
        producto2.setStock(120);
        producto2.setDescripcion("Primer volumen de la aclamada serie de manga Attack on Titan.");

        // Mostrar informaci�n del segundo producto
        System.out.println("Producto 2:");
        System.out.println(producto2.toString());
    }
}
