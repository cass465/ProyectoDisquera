/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.BD;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author cass465
 */
public class BDConector implements Serializable {

    private final String JDBC_DRIVER = "org.postgresql.Driver";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/Disquera";
    private String user;
    private String pass;
    private Connection conexion = null;

    public static boolean acceso = false;

    public BDConector() {
        this.user = "postgres";
        this.pass = "XVV254";
    }

    public Connection open() {
        try {
            Class.forName(JDBC_DRIVER);
            try {
                conexion = DriverManager.getConnection(DB_URL, user, pass);
                acceso = true;
            } catch (SQLException e) {
                acceso = false;
                e.printStackTrace();
                System.out.println("No se pudo conectar a la BD");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver no encontrado");
        }
        return conexion;
    }

    public void close() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println("No se pudo cerrar la conexion");
        }
    }
}
