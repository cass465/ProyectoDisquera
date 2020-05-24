/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.BD;

import com.unicundi.utilitarios.UCompraDisco;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
