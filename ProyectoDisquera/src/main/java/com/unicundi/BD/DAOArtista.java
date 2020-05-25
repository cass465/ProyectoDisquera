/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.BD;

import com.unicundi.utilitarios.UArtista;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author cass465
 */
public class DAOArtista implements Serializable {

    public void registrar(UArtista artista) {
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                System.out.println("REGISTRAR");
                String query = "INSERT INTO musica.artista(nombre, apellido,genero,estado) VALUES ('"
                        + artista.getNombre() + "','"
                        + artista.getApellido() + "','"
                        + artista.getGenero() + "','"
                        + true + "');";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<UArtista> listar() {
        String estado;
        ArrayList<UArtista> artistas = new ArrayList<UArtista>();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT * FROM musica.artista ;";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    artistas.add(new UArtista(resultado.getInt("id"),
                            resultado.getString("nombre"),
                            resultado.getString("apellido"),
                            resultado.getString("genero"),
                            resultado.getBoolean("estado")));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return artistas;
    }

    public ArrayList<UArtista> listarActivos() {
        String estado;
        ArrayList<UArtista> artistas = new ArrayList<UArtista>();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT * FROM musica.artista   WHERE estado= true ;";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    artistas.add(new UArtista(resultado.getInt("id"),
                            (resultado.getString("nombre") + " " + resultado.getString("apellido")),
                            resultado.getString("apellido"),
                            resultado.getString("genero"),
                            resultado.getBoolean("estado")));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return artistas;
    }

    public String obtenerNombre(int id) {
        String nombre = null;

        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {

                String query = "SELECT * FROM musica.artista  WHERE  id='" + id + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    nombre = resultado.getString("nombre") + " " + resultado.getString("apellido");
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nombre;
    }

    public UArtista obtenerExistente(UArtista artista) {
       UArtista artistaAux= new UArtista();

        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {

                String query = "SELECT * FROM musica.artista  WHERE  nombre='" + artista.getNombre()
                        + "' AND apellido='" + artista.getApellido() + "' ;";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                   artistaAux= new UArtista(resultado.getInt("id"),
                            resultado.getString("nombre"),
                            resultado.getString("apellido"),
                            resultado.getString("genero"),
                            resultado.getBoolean("estado"));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ARTISTA AUX"+ artistaAux.getApellido());
        return artistaAux;
    }

    public void modificar(UArtista artista) {

        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {

                String query = "UPDATE musica.artista SET nombre='" + artista.getNombre()
                        + "', apellido= '" + artista.getApellido()
                        + "', genero='" + artista.getGenero()
                        + "', estado='" + artista.isEstado()
                        + "' WHERE  id='" + artista.getId() + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void eliminar(UArtista artista) {

        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {

                String query = "DELETE FROM musica.artista  WHERE  id='" + artista.getId() + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
