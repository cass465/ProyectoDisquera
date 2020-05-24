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
                String query = "SELECT * FROM musica.cancion;";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    String duaracion = resultado.getString("duracion");
                    float precio = (float) resultado.getDouble("precio");
                    int idDisco = resultado.getInt("id_disco");
                    
                    canciones.add(new UCancion(id, nombre, duaracion, precio, idDisco));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return canciones;
    }
}
