package com.akihabara.market.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Clase para gestionar la conexi�n a la base de datos MySQL para el proyecto AkihabaraMarket.

public class DatabaseConnection {

    // 1. Variables constantes para los datos de conexi�n
    private static final String DB_URL = "jdbc:mysql://localhost:3306/akihabara_market_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "userAkihabara";
    private static final String PASSWORD = "curso";

    // 2. Propiedad de conexi�n
    private Connection conexion;

     //Constructor de la clase DatabaseConnection.
     //Carga el driver de MySQL y establece la conexi�n con la base de datos.
    
    public DatabaseConnection() {
        try {
            // Cargar el driver de MySQL en memoria
            System.out.println("Intentando cargar el driver de MySQL...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Se ha cargado en memoria el driver de MySQL.");

            // Establecer la conexi�n con la base de datos
            System.out.println("Intentando establecer conexi�n con la base de datos...");
            this.conexion = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Se ha establecido con �xito la conexi�n a la base de datos.");

        } catch (ClassNotFoundException e) {
            // Manejo de excepci�n si el driver no se encuentra
            System.err.println("Error: El driver de MySQL no se ha encontrado. Aseg�rate de que el JAR est� en el classpath.");
            System.err.println("Causa del error: " + e.getMessage());
        } catch (SQLException e) {
            // Manejo de excepci�n si ocurre un error al conectar con la base de datos
            System.err.println("Error: No se pudo establecer la conexi�n con la base de datos.");
            System.err.println("Causa del error: " + e.getMessage());
        }
    }

    public Connection getConexion() {
        return conexion;
    }

 
     //Cierra la conexi�n a la base de datos si est� activa.
    
    public void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Se ha cerrado la conexi�n con la base de datos.");
            } catch (SQLException e) {
                // Manejo de excepci�n si ocurre un error al cerrar la conexi�n
                System.err.println("Error al intentar cerrar la conexi�n con la base de datos.");
                System.err.println("Causa del error: " + e.getMessage());
            }
        } else {
            System.out.println("No hay conexi�n activa para cerrar.");
        }
    }
}