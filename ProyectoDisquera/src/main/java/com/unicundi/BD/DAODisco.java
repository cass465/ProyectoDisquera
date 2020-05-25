/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.BD;

import com.unicundi.utilitarios.UDisco;
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
public class DAODisco implements Serializable{
    
    public List<UDisco> listar(){
        List<UDisco> discos = new ArrayList<UDisco>();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT artista.genero, artista.nombre AS nombre_artista, artista.apellido AS apellido_artista, disco.* "
                        + "FROM musica.artista, musica.disco "
                        + "WHERE disco.id_artista = artista.id "
                        + "AND estado = true";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    int numeroCanciones = resultado.getInt("n_canciones");
                    float precio = (float) resultado.getDouble("precio");
                    int idArtista = resultado.getInt("id_artista");
                    String genero = resultado.getString("genero");
                    String nombreCompletoArtista = resultado.getString("nombre_artista") + " " + resultado.getString("apellido_artista");
                    
                    discos.add(new UDisco(id, nombre, numeroCanciones, precio, idArtista, nombreCompletoArtista, genero));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return discos;
    }
}
