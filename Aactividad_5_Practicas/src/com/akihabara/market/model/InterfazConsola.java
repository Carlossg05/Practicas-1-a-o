// InterfazConsola.java
package com.akihabara.market.model;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.akihabara.market.model.ProductoOtaku;

public class InterfazConsola {
    private Scanner scanner;

    public InterfazConsola() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenuPrincipal() {
        System.out.println("\n--- Menú Principal Akihabara Market ---");
        System.out.println("1. Añadir producto");
        System.out.println("2. Consultar producto por ID");
        System.out.println("3. Listar todos los productos");
        System.out.println("4. Listar productos por nombre");
        System.out.println("5. Listar productos por categoría");
        System.out.println("6. Actualizar producto");
        System.out.println("7. Eliminar producto");
        System.out.println("8. Salir del programa");
        System.out.print("Elige una opción: ");
        return leerOpcionEntera();
    }

    public int leerOpcionEntera() {
        int opcion = -1;
        try {
            opcion = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Por favor, introduce un número.");
        } finally {
            scanner.nextLine(); // Limpiar el buffer del scanner
        }
        return opcion;
    }

    public ProductoOtaku pedirDatosNuevoProducto() {
        System.out.println("\n--- Añadir Nuevo Producto ---");
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Categoría del producto: ");
        String categoria = scanner.nextLine();

        double precio = 0.0;
        boolean precioValido = false;
        while (!precioValido) {
            System.out.print("Precio del producto: ");
            try {
                precio = scanner.nextDouble();
                precioValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número decimal para el precio.");
            } finally {
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }

        int stock = 0;
        boolean stockValido = false;
        while (!stockValido) {
            System.out.print("Stock del producto: ");
            try {
                stock = scanner.nextInt();
                stockValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número entero para el stock.");
            } finally {
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }

        return new ProductoOtaku(0, nombre, categoria, precio, stock); // El ID es 0, se asignará en la DAO
    }

    public int pedirIdProducto() {
        int id = -1;
        boolean idValido = false;
        while (!idValido) {
            System.out.print("Introduce el ID del producto: ");
            try {
                id = scanner.nextInt();
                idValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número entero para el ID.");
            } finally {
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
        return id;
    }

    public String pedirNombreProducto() {
        System.out.print("Introduce el nombre del producto: ");
        return scanner.nextLine();
    }

    public String pedirCategoriaProducto() {
        System.out.print("Introduce la categoría del producto: ");
        return scanner.nextLine();
    }

    public ProductoOtaku pedirDatosActualizacionProducto(ProductoOtaku productoExistente) {
        System.out.println("\n--- Actualizar Producto (ID: " + productoExistente.getId() + ") ---");
        System.out.println("Dejar en blanco para mantener el valor actual.");

        System.out.print("Nuevo nombre (" + productoExistente.getNombre() + "): ");
        String nombre = scanner.nextLine();
        if (!nombre.trim().isEmpty()) {
            productoExistente.setNombre(nombre);
        }

        System.out.print("Nueva categoría (" + productoExistente.getCategoria() + "): ");
        String categoria = scanner.nextLine();
        if (!categoria.trim().isEmpty()) {
            productoExistente.setCategoria(categoria);
        }

        System.out.print("Nuevo precio (" + productoExistente.getPrecio() + "): ");
        String precioStr = scanner.nextLine();
        if (!precioStr.trim().isEmpty()) {
            try {
                productoExistente.setPrecio(Double.parseDouble(precioStr));
            } catch (NumberFormatException e) {
                mostrarMensajeError("Precio no válido. Se mantendrá el precio actual.");
            }
        }

        System.out.print("Nuevo stock (" + productoExistente.getStock() + "): ");
        String stockStr = scanner.nextLine();
        if (!stockStr.trim().isEmpty()) {
            try {
                productoExistente.setStock(Integer.parseInt(stockStr));
            } catch (NumberFormatException e) {
                mostrarMensajeError("Stock no válido. Se mantendrá el stock actual.");
            }
        }
        return productoExistente;
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarMensajeExito(String mensaje) {
        System.out.println("✅ " + mensaje);
    }

    public void mostrarMensajeError(String mensaje) {
        System.err.println("❌ " + mensaje);
    }

    public void mostrarProducto(ProductoOtaku producto) {
        if (producto != null) {
            System.out.println("\n--- Detalles del Producto ---");
            System.out.println("ID: " + producto.getId());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Categoría: " + producto.getCategoria());
            System.out.println("Precio: " + String.format("%.2f€", producto.getPrecio()));
            System.out.println("Stock: " + producto.getStock());
            System.out.println("----------------------------");
        } else {
            mostrarMensaje("Producto no encontrado.");
        }
    }

    public void mostrarListaProductos(List<ProductoOtaku> productos) {
        if (productos == null || productos.isEmpty()) {
            mostrarMensaje("No hay productos para mostrar.");
            return;
        }
        System.out.println("\n--- Lista de Productos ---");
        System.out.printf("%-5s %-30s %-20s %-10s %-8s\n", "ID", "Nombre", "Categoría", "Precio", "Stock");
        System.out.println("----- ------------------------------ -------------------- ---------- --------");
        for (ProductoOtaku producto : productos) {
            System.out.printf("%-5d %-30s %-20s %-10.2f %-8d\n",
                    producto.getId(),
                    producto.getNombre(),
                    producto.getCategoria(),
                    producto.getPrecio(),
                    producto.getStock());
        }
        System.out.println("--------------------------------------------------------------------------------");
    }
}