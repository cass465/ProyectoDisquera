/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.BD;

import com.unicundi.utilitarios.UCompraDisco;
import com.unicundi.utilitarios.UDisco;
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
    
    public List<UDisco> listarDiscosDisponibles() {
        List<UDisco> discos = new ArrayList<UDisco>();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT artista.genero, artista.nombre AS nombre_artista, artista.apellido AS apellido_artista, disco.* "
                        + "FROM musica.artista, musica.disco "
                        + "WHERE disco.id_artista = artista.id "
                        + "AND disco.estado = true;";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    int numeroCanciones = resultado.getInt("n_canciones");
                    int precio = resultado.getInt("precio");
                    int idArtista = resultado.getInt("id_artista");
                    String genero = resultado.getString("genero");
                    String nombreArtista = resultado.getString("nombre_artista") + " " + resultado.getString("apellido_artista");

                    discos.add(new UDisco(id, nombre, numeroCanciones, precio, idArtista, nombreArtista, genero));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return discos;
    }
    
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
                    int id = resultado.getInt("id");
                    int idDisco = resultado.getInt("id_disco");
                    int valorCompra = resultado.getInt("valor_compra");
                    Date fechaCompra = resultado.getDate("fecha_compra");
                    String genero = resultado.getString("genero");
                    String nombreCompletoArtista = resultado.getString("nombre_artista") + " " + resultado.getString("apellido_artista");
                    String disco = resultado.getString("nombre");
                    int numeroCanciones = resultado.getInt("n_canciones");
                    
                    compras.add(new UCompraDisco(id, idDisco, valorCompra, fechaCompra, genero, nombreCompletoArtista, disco, numeroCanciones));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return compras;
    }
}
