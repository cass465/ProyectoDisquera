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

/**
 * Clase que permite hacer operaciones en BD para el disco
 *
 * @author Yeison Cifuentes
 * @version 1.0.0
 */
public class DAODisco implements Serializable {

    /**
     * Inserta el disco
     *
     * @param disco
     */
    public void registrar(UDisco disco) {
        String[] returnId = {"id"};
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                System.out.println("REGISTRAR");
                String query = "INSERT INTO musica.disco(nombre,n_canciones,precio,id_artista,estado) VALUES ('"
                        + disco.getNombre() + "','"
                        + 1 + "','"
                        + disco.getPrecio() + "','"
                        + disco.getIdArtista() + "','"
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
     * Lista todos los diacos
     *
     * @return
     */
    public ArrayList<UDisco> listar() {
        ArrayList<UDisco> discos = new ArrayList<UDisco>();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT * FROM musica.disco;";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    String nombre = new DAOArtista().obtenerNombre(resultado.getInt("id_artista"));
                    System.out.println(nombre);
                    discos.add(new UDisco(resultado.getInt("id"),
                            resultado.getString("nombre"),
                            resultado.getInt("n_canciones"),
                            resultado.getInt("precio"),
                            resultado.getInt("id_artista"),
                            resultado.getBoolean("estado"),
                            nombre));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return discos;
    }

    /**
     * Listar discos activos
     *
     * @return
     */
    public ArrayList<UDisco> listarActivos() {
        ArrayList<UDisco> discos = new ArrayList<UDisco>();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT * FROM musica.disco WHERE estado=true ;";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    String nombre = new DAOArtista().obtenerNombre(resultado.getInt("id_artista"));
                    System.out.println(nombre);
                    discos.add(new UDisco(resultado.getInt("id"),
                            resultado.getString("nombre"),
                            resultado.getInt("n_canciones"),
                            resultado.getInt("precio"),
                            resultado.getInt("id_artista"),
                            resultado.getBoolean("estado"),
                            nombre));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return discos;
    }

    /**
     * Obtener el nombre del disco filtrando por el id
     *
     * @param id
     * @return
     */
    public String obtenerNombre(int id) {
        String nombre = null;

        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {

                String query = "SELECT * FROM musica.disco  WHERE  id='" + id + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    nombre = resultado.getString("nombre");
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nombre;
    }

    /**
     * Obtener el estado de disco apartir del nombre
     *
     * @param nombre
     * @return
     */
    public boolean obtenerEstado(String nombre) {
        boolean estado = true;

        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {

                String query = "SELECT * FROM musica.disco  WHERE  nombre='" + nombre + "';";
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
        return estado;
    }

    /**
     * obtener disco
     *
     * @param disco
     * @return
     */
    public UDisco obtenerExistente(UDisco disco) {
        UDisco discoAux = new UDisco();

        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {

                String query = "SELECT * FROM musica.disco  WHERE nombre='" + disco.getNombre() + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    discoAux = new UDisco(resultado.getInt("id"),
                            resultado.getString("nombre"),
                            resultado.getInt("n_canciones"),
                            resultado.getInt("precio"),
                            resultado.getInt("id_artista"),
                            resultado.getBoolean("estado"),
                            resultado.getString("nombre"));
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return discoAux;
    }

    /**
     * Modificar disco
     *
     * @param disco
     */
    public void modificar(UDisco disco) {

        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {

                String query = "UPDATE musica.disco SET nombre='" + disco.getNombre()
                        + "', n_canciones= '" + 2
                        + "', precio='" + disco.getPrecio()
                        + "', id_artista= (SELECT id  from musica.artista where CONCAT(CONCAT(nombre, ' '), apellido) = '" + disco.getNombreArtista()
                        + "') , estado='" + disco.isEstado()
                        + "' WHERE  id='" + disco.getId() + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Cambiar el estado si el artista se activa o inactiva
     *
     * @param id
     * @param estado
     */
    public void cambiarEstadoArtista(int id, boolean estado) {
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {

                String query = "UPDATE musica.disco SET estado='" + estado + "' WHERE  id_artista='" + id + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Actualiza el numero de canciones del disco
     */
    public void actualizarNCanciones() {
        Connection conexion = new BDConector().open();

        if (conexion != null) {
            try {

                String query = "UPDATE musica.disco SET n_canciones=(SELECT  COUNT(id_disco)  "
                        + "     FROM musica.cancion "
                        + "     WHERE cancion.id_disco=disco.id AND cancion.estado=true);";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Actualiza el precio de disco
     */
    public void actualizarPrecio() {
        Connection conexion = new BDConector().open();

        if (conexion != null) {
            try {
                String query = "UPDATE musica.disco SET precio=(SELECT  SUM(precio)-(SUM(precio)*0.1) "
                        + "     FROM musica.cancion "
                        + "     WHERE cancion.id_disco=disco.id AND cancion.estado=true );";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
