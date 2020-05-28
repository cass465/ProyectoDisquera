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
 * Clase que permite hacer operaciones en BD para el artista
 *
 * @author Yeison Cifuentes
 * @version 1.0.0
 */
public class DAOArtista implements Serializable {

    /**
     * Registrar artista
     * @param artista 
     */
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

    /**
     * listar todos los artistas 
     * @return 
     */
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

    /**
     * Listar los artistas que estan activos
     * @return 
     */
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

    /**
     * Obtiene el nombre del arista a partir del id
     * @param id
     * @return 
     */
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
    
    /**
     * Obtiene el estado apartir del nombre
     * @param nombre
     * @return 
     */
    public boolean obtenerEstado(String nombre) {
        boolean estado=true;

        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {

                String query = "SELECT * FROM musica.artista  WHERE CONCAT(CONCAT(nombre, ' '), apellido) ='" + nombre + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    
                    estado = resultado.getBoolean("estado");
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ESTADO:"+ estado);
        return estado;
    }

    /**
     * Obtener artista 
     * @param artista
     * @return 
     */
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

    /**
     * Modificar artista
     * @param artista 
     */
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

    /**
     * Eliminar artista, no utilizado
     * @param artista 
     */
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
