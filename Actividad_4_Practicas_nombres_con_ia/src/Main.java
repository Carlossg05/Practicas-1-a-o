// MainApplication.java (Example of integration)
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LlmService llmService = new LlmService();

        System.out.println("--- Añadir Nuevo Producto Otaku ---");
        System.out.print("Introduce el tipo de producto (ej. 'figura', 'camiseta'): ");
        String tipoProducto = scanner.nextLine();

        System.out.print("Introduce la franquicia (ej. 'Naruto', 'One Piece'): ");
        String franquiciaProducto = scanner.nextLine();

        System.out.println("\nGenerando nombre sugerido por IA...");
        String nombreSugerido = llmService.sugerirNombreProducto(tipoProducto, franquiciaProducto);

        System.out.println("Nombre sugerido por la IA: " + nombreSugerido);

        // In a real application, you would now use 'nombreSugerido'
        // to create and save your new product object.
        // For example:
        // Producto nuevoProducto = new Producto(nombreSugerido, tipoProducto, franquiciaProducto);
        // productRepository.save(nuevoProducto);
        System.out.println("Producto guardado con el nombre sugerido (simulado).");

        scanner.close();
    }
}