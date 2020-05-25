/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.BD;

import com.unicundi.utilitarios.UCompraCancion;
import com.unicundi.utilitarios.UCompraDisco;
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
public class DAOCompraDisco implements Serializable {

    public void registrar(UCompraDisco compra) {
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "INSERT INTO compras.compra_disco(id_usuario, id_disco, valor_compra, fecha_compra) VALUES ('"
                        + compra.getIdUsuario()+ "','"
                        + compra.getIdDisco()+ "','"
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
    
    public List<UCompraDisco> listarPorUsuario(UUsuario usuario){
        List<UCompraDisco> compras = new ArrayList<UCompraDisco>();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT artista.genero, artista.nombre AS nombre_artista, artista.apellido AS apellido_artista, compra_disco.*, disco.* "
                        + "FROM musica.artista, compras.compra_disco, musica.disco "
                        + "WHERE compra_disco.id_disco = disco.id "
                        + "AND disco.id_artista = artista.id "
                        + "AND compra_disco.id_usuario = " + usuario.getId() + ";";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    float valorCompra = (float) resultado.getDouble("valor_compra");
                    Date fechaCompra = resultado.getDate("fecha_compra");
                    String genero = resultado.getString("genero");
                    String nombreCompletoArtista = resultado.getString("nombre_artista") + " " + resultado.getString("apellido_artista");
                    String disco = resultado.getString("nombre");
                    int numeroCanciones = resultado.getInt("n_canciones");
                    
                    compras.add(new UCompraDisco(valorCompra, fechaCompra, genero, nombreCompletoArtista, disco, numeroCanciones));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return compras;
    }
}
