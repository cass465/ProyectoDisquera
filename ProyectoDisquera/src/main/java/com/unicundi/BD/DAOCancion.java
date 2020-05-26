/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.BD;

import com.unicundi.utilitarios.UArtista;
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
public class DAOCancion implements Serializable {

    public void registrar(UCancion cancion) {

        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                System.out.println("REGISTRAR");
                String query = "INSERT INTO musica.cancion(nombre,duracion,precio,id_disco,estado) VALUES ('"
                        + cancion.getNombre() + "','"
                        + cancion.getDuracion() + "','"
                        + cancion.getPrecio() + "','"
                        + cancion.getIdDisco() + "','"
                        + true + "');";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void registrarDisco(UCancion cancion, String nombreDisco) {
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "INSERT INTO musica.cancion(nombre,duracion,precio,id_disco,estado) VALUES ('"
                        + cancion.getNombre() + "','"
                        + cancion.getDuracion() + "','"
                        + cancion.getPrecio()
                        + "', (SELECT id FROM musica.disco WHERE disco.nombre='" + nombreDisco + "') ,'"
                        + true + "');";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<UCancion> listar() {
        ArrayList<UCancion> canciones = new ArrayList<UCancion>();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT * FROM musica.cancion;";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    String nombre = new DAODisco().obtenerNombre(resultado.getInt("id_disco"));
                    canciones.add(new UCancion(resultado.getInt("id"),
                            resultado.getString("nombre"),
                            resultado.getString("duracion"),
                            resultado.getInt("precio"),
                            resultado.getInt("id_disco"),
                            resultado.getBoolean("estado"), nombre));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return canciones;
    }

    public UCancion obtenerExistente(UCancion cancion) {
        UCancion cancionAux = new UCancion();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT * FROM musica.cancion  WHERE nombre='" + cancion.getNombre() + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    cancionAux = new UCancion(resultado.getInt("id"),
                            resultado.getString("nombre"),
                            resultado.getString("duracion"),
                            resultado.getInt("precio"),
                            resultado.getInt("id_disco"),
                            resultado.getBoolean("estado"),
                            resultado.getString("nombre"));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cancionAux;
    }

    public void modificar(UCancion cancion) {

        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {

                String query = "UPDATE musica.cancion SET nombre='" + cancion.getNombre()
                        + "',duracion = '" + cancion.getDuracion()
                        + "', precio='" + cancion.getPrecio()
                        + "', id_disco= (SELECT id  from musica.disco where nombre = '" + cancion.getNombreDisco()
                        + "') , estado='" + cancion.isEstado()
                        + "' WHERE  id='" + cancion.getId() + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void cambiarEstadoArtista(int id, boolean estado) {
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "UPDATE musica.cancion SET estado= '" + estado
                        + "' FROM musica.disco WHERE id_artista= '" + id
                        + "'  and cancion.id_disco=disco.id";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void cambiarEstadoDisco(int id, boolean estado) {
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {

                String query = "UPDATE musica.cancion SET estado='" + estado + "' WHERE  id_disco='" + id + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<UCancion> buscarPorDisco(int idDisco) {
        List<UCancion> canciones = new ArrayList<UCancion>();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT * FROM musica.cancion WHERE cancion.id_disco = " + idDisco + ";";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    String duracion = resultado.getString("duracion");
                    int precio = resultado.getInt("precio");
                    boolean estado = resultado.getBoolean("estado");

                    canciones.add(new UCancion(id, nombre, duracion, precio, idDisco, estado, null));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return canciones;
    }
}
