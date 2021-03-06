/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.BD;

import com.unicundi.utilitarios.UUsuario;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que maneja las acciones de usuario en Base de Datos
 * @author Camilo Sanabria
 * @version 1.0.0
 */
public class DAOUsuario implements Serializable {

    /**
     * Inicia la sesion del usuario 
     * @param usuario Datos de usuario que desea ingresar
     * @return Datos de usuario logueado
     */
    public UUsuario iniciarSesion(UUsuario usuario) {
        UUsuario usuarioLogueado = null;
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT * FROM usuarios.usuario WHERE usuario.username = '" + usuario.getUsername()
                        + "' AND usuario.contrasenia = '" + usuario.getContrasenia() + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {

                    int id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    String apellido = resultado.getString("apellido");
                    String username = resultado.getString("username");
                    String contrasenia = resultado.getString("contrasenia");
                    int idRol = resultado.getInt("id_rol");

                    usuarioLogueado = new UUsuario(id, nombre, apellido, username, contrasenia, idRol);

                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return usuarioLogueado;
    }

    /**
     * Busca un usuario filtrado por username para validar el registro
     * @param username Username a buscar
     * @return Datos de usuario encontrado
     */
    public UUsuario buscarPorUsername(String username){
        UUsuario usuarioEncontrado = null;
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT * FROM usuarios.usuario WHERE usuario.username = '" + username + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    String usernameEncontrado = resultado.getString("username");
                    
                    usuarioEncontrado = new UUsuario(0, null,  null, username, null, 0);

                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return usuarioEncontrado;
    }
    
    /**
     * Registra el usuario especificado en Base de Datos
     * @param usuario Datos de usuario que se desean registrar
     */
    public void registrar(UUsuario usuario) {
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "INSERT INTO usuarios.usuario(nombre, apellido, username, contrasenia, id_rol) VALUES ('"
                        + usuario.getNombre() + "','"
                        + usuario.getApellido() + "','"
                        + usuario.getUsername() + "','"
                        + usuario.getContrasenia()+ "','"
                        + usuario.getIdRol()+ "');";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
