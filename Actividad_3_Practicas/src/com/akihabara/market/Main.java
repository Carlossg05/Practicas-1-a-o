package com.akihabara.market;

import com.akihabara.market.dao.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

 //Clase principal para probar la conexión a la base de datos.
 //Nota: Este código es temporal y será eliminado más adelante.

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando comprobación de conexión a la base de datos...");

        // Crear una instancia de DatabaseConnection
        DatabaseConnection dbConnection = new DatabaseConnection();

        // Obtener la conexión
        Connection connection = dbConnection.getConexion();

        // Verificar si la conexión se ha establecido correctamente
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    System.out.println("La conexión a la base de datos está activa y es válida.");
                } else {
                    System.out.println("La conexión a la base de datos está cerrada.");
                }
            } catch (SQLException e) {
                System.err.println("Error al verificar el estado de la conexión: " + e.getMessage());
            } finally {
                // Asegurarse de cerrar la conexión después de la prueba
                dbConnection.cerrarConexion();
            }
        } else {
            System.out.println("No se pudo obtener una conexión a la base de datos. Revisa los mensajes de error anteriores.");
        }

        System.out.println("Comprobación de conexión finalizada.");
    }
}