/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.BD;

import com.unicundi.utilitarios.UCancion;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cass465
 */
public class DAOCancion implements Serializable{
    
    public List<UCancion> listar(){
        List<UCancion> canciones = new ArrayList<UCancion>();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT artista.genero, artista.nombre AS nombre_artista, artista.apellido AS apellido_artista, cancion.* "
                        + "FROM musica.artista, musica.cancion, musica.disco "
                        + "WHERE disco.id_artista = artista.id "
                        + "AND cancion.id_disco = disco.id "
                        + "AND cancion.estado = true;";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    String duaracion = resultado.getString("duracion");
                    float precio = (float) resultado.getDouble("precio");
                    int idDisco = resultado.getInt("id_disco");
                    String genero = resultado.getString("genero");
                    String nombreCompletoArtista = resultado.getString("nombre_artista") + " " + resultado.getString("apellido_artista");
                    
                    canciones.add(new UCancion(id, nombre, duaracion, precio, idDisco, genero, nombreCompletoArtista));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return canciones;
    }
    
    public List<UCancion> buscarPorDisco(int idDisco){
        List<UCancion> canciones = new ArrayList<UCancion>();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT * FROM musica.cancion WHERE cancion.id_disco = " + idDisco + " AND cancion.estado = true;";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    String duaracion = resultado.getString("duracion");
                    float precio = (float) resultado.getDouble("precio");
                    
                    canciones.add(new UCancion(id, nombre, duaracion, precio, idDisco, true));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return canciones;
    }
}
