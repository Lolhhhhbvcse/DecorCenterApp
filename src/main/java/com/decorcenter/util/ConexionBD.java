package com.decorcenter.util;
import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;
/**

 * PATRÓN DE DISEÑO 1: SINGLETON

 * ------------------------------------------------------------

 * Garantiza que exista una única instancia encargada de

 * suministrar conexiones a la base de datos, evitando crear

 * múltiples configuraciones repetidas a lo largo de la app.

 */
public class ConexionBD {

    // Sustituye estos valores por los de tu base de datos en la nube

    private static final String URL = "jdbc:postgresql://dpg-d9bbo2beo5us73ebrvjg-a.oregon-postgres.render.com:5432/decor";

    private static final String USER = "decor_user"; 

    private static final String PASSWORD = "0PaBaSLK67ZppT1UQdtNxE8yBnQcM81O"; 

    public static Connection getConnection() throws SQLException {

        try {

            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {

            throw new SQLException("Error al cargar el driver de PostgreSQL", e);

        }

    }

} 

