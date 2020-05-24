/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.core;

import com.unicundi.BD.DAOCompraDisco;
import com.unicundi.utilitarios.UCompraDisco;
import com.unicundi.utilitarios.UDisco;
import com.unicundi.utilitarios.UUsuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author cass465
 */
public class CoreCompraDisco implements Serializable {

    public void registrar(List<UDisco> discos) {
        int nCompras = 0;
        for (UDisco disco : discos) {
            if (disco.isSeleccionado()) {
                int idUsuario = ((UUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getId();
                int idDisco = disco.getId();
                float valorCompra = disco.getPrecio();
                Date fechaCompra = new Date();

                UCompraDisco compra = new UCompraDisco(0, idUsuario, idDisco, valorCompra, fechaCompra);
                new DAOCompraDisco().registrar(compra);
                nCompras++;
            }
        }
        if (nCompras > 0) {
            FacesMessage mensaje = new FacesMessage("DISCOS COMPRADOS: " + nCompras);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "NO HA SELECCIONADO DISCOS", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
    }
}
