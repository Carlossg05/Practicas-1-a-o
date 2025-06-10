package com.akihabara.market;

import com.akihabara.market.dao.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

 //Clase principal para probar la conexi�n a la base de datos.
 //Nota: Este c�digo es temporal y ser� eliminado m�s adelante.

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando comprobaci�n de conexi�n a la base de datos...");

        // Crear una instancia de DatabaseConnection
        DatabaseConnection dbConnection = new DatabaseConnection();

        // Obtener la conexi�n
        Connection connection = dbConnection.getConexion();

        // Verificar si la conexi�n se ha establecido correctamente
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    System.out.println("La conexi�n a la base de datos est� activa y es v�lida.");
                } else {
                    System.out.println("La conexi�n a la base de datos est� cerrada.");
                }
            } catch (SQLException e) {
                System.err.println("Error al verificar el estado de la conexi�n: " + e.getMessage());
            } finally {
                // Asegurarse de cerrar la conexi�n despu�s de la prueba
                dbConnection.cerrarConexion();
            }
        } else {
            System.out.println("No se pudo obtener una conexi�n a la base de datos. Revisa los mensajes de error anteriores.");
        }

        System.out.println("Comprobaci�n de conexi�n finalizada.");
    }
}