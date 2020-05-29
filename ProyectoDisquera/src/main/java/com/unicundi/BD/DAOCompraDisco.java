/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.BD;

import com.unicundi.utilitarios.UCancion;
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
 * Clase que maneja las acciones de compras de disco en Base de Datos
 * @author Camilo Sanabria
 * @version 1.0.0
 */
public class DAOCompraDisco implements Serializable {
    
    /**
     * Lista todos los discos disponibles
     * @return Todos los discos disponibles
     */
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
                    List<UCancion> cancionesDisco = new DAOCancion().buscarPorDisco(id);
                    
                    discos.add(new UDisco(id, nombre, numeroCanciones, precio, idArtista, nombreArtista, genero, cancionesDisco));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return discos;
    }
    
    /**
     * Regitra la compra de disco especificado
     * @param compra Compra de disco a registrar
     */
    public void registrar(UCompraDisco compra) {
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "INSERT INTO compras.compra_disco(id_usuario, id_disco, valor_compra, fecha_compra, n_canciones) VALUES ('"
                        + compra.getIdUsuario()+ "','"
                        + compra.getIdDisco()+ "','"
                        + compra.getValorCompra()+ "','"
                        + compra.getFechaCompra()+ "','"
                        + compra.getNumeroCanciones()+ "');";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Lista las compras de discos filtrado por usuario
     * @param usuario Usuario al que se le consultan las compras de disco
     * @return Lista de compras de usuario encontradas
     */
    public List<UCompraDisco> listarPorUsuario(UUsuario usuario){
        List<UCompraDisco> compras = new ArrayList<UCompraDisco>();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT artista.genero, artista.nombre AS nombre_artista, artista.apellido AS apellido_artista, "
                        + "disco.nombre, compra_disco.* "
                        + "FROM musica.artista, compras.compra_disco, musica.disco "
                        + "WHERE compra_disco.id_disco = disco.id "
                        + "AND disco.id_artista = artista.id "
                        + "AND compra_disco.id_usuario = " + usuario.getId() + ";";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String genero = resultado.getString("genero");
                    String nombreCompletoArtista = resultado.getString("nombre_artista") + " " + resultado.getString("apellido_artista");
                    String disco = resultado.getString("nombre");
                    int valorCompra = resultado.getInt("valor_compra");
                    Date fechaCompra = resultado.getDate("fecha_compra");
                    int numeroCanciones = resultado.getInt("n_canciones");
                    
                    compras.add(new UCompraDisco(id, valorCompra, fechaCompra, genero, nombreCompletoArtista, disco, numeroCanciones));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return compras;
    }
    
    /**
     * Obtiene el mayor id de todas las compras de discos
     * @return Id mayor de compras de discos
     */
    public int obtenerIdMayor(){
        int idCompraDisco = 0;
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT MAX(compra_disco.id) AS id_compra_disco FROM compras.compra_disco";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    idCompraDisco = resultado.getInt("id_compra_disco");
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return idCompraDisco;
    }
}
