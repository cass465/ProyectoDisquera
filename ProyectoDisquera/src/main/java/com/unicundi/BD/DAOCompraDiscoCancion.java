/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.BD;

import com.unicundi.utilitarios.UCancion;
import com.unicundi.utilitarios.UCompraDiscoCancion;
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
public class DAOCompraDiscoCancion implements Serializable {

    public void registrar(UCompraDiscoCancion compraCancion) {
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "INSERT INTO compras.compra_disco_cancion(id_compra_disco, id_cancion) VALUES ('"
                        + compraCancion.getIdCompraDisco() + "','"
                        + compraCancion.getIdCancion() + "');";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<UCompraDiscoCancion> buscarPorCompraDisco(int idCompraDisco) {
        List<UCompraDiscoCancion> canciones = new ArrayList<UCompraDiscoCancion>();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT cancion.nombre, cancion.duracion "
                        + "FROM musica.cancion, compras.compra_disco, compras.compra_disco_cancion "
                        + "WHERE compra_disco_cancion.id_compra_disco = compra_disco.id "
                        + "AND compra_disco_cancion.id_cancion = cancion.id "
                        + "AND compra_disco_cancion.id_compra_disco = " + idCompraDisco + ";";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    String cancion = resultado.getString("nombre");
                    String duracion = resultado.getString("duracion");
                    
                    canciones.add(new UCompraDiscoCancion(cancion, duracion));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return canciones;
    }
}
