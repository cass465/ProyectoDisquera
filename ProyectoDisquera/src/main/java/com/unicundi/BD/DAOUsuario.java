/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.BD;

import com.unicundi.model.Usuario;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author cass465
 */
public class DAOUsuario implements Serializable {

    public Usuario iniciarSesion(Usuario usuario) {
        Usuario usuarioLogueado = null;
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

                    usuarioLogueado = new Usuario(id, nombre, apellido, username, contrasenia, idRol);

                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return usuarioLogueado;
    }
}
