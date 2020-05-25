/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.BD;

import com.unicundi.utilitarios.UCompraCancion;
import com.unicundi.utilitarios.UUsuario;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author cass465
 */
public class DAOCompraCancion implements Serializable{
    
    public void registrar(UCompraCancion compra) {
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "INSERT INTO compras.compra_cancion(id_usuario, id_cancion, valor_compra, fecha_compra) VALUES ('"
                        + compra.getIdUsuario()+ "','"
                        + compra.getIdCancion()+ "','"
                        + compra.getValorCompra()+ "','"
                        + compra.getFechaCompra()+ "');";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public List<UCompraCancion> listarPorUsuario(UUsuario usuario){
        List<UCompraCancion> compras = new ArrayList<UCompraCancion>();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT artista.genero, artista.nombre AS nombre_artista, artista.apellido AS apellido_artista, compra_cancion.*, cancion.* "
                        + "FROM musica.artista, compras.compra_cancion, musica.disco, musica.cancion "
                        + "WHERE compra_cancion.id_cancion = cancion.id "
                        + "AND cancion.id_disco = disco.id AND disco.id_artista = artista.id "
                        + "AND compra_cancion.id_usuario = " + usuario.getId() + ";";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    float valorCompra = (float) resultado.getDouble("valor_compra");
                    Date fechaCompra = resultado.getDate("fecha_compra");
                    String genero = resultado.getString("genero");
                    String nombreCompletoArtista = resultado.getString("nombre_artista") + " " + resultado.getString("apellido_artista");
                    String cancion = resultado.getString("nombre");
                    String duracion = resultado.getString("duracion");
                    
                    compras.add(new UCompraCancion(valorCompra, fechaCompra, genero, nombreCompletoArtista, cancion, duracion));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return compras;
    }
}
