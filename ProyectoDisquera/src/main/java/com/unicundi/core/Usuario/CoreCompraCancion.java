/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.core.Usuario;

import com.unicundi.BD.DAOCompraCancion;
import com.unicundi.utilitarios.UCancion;
import com.unicundi.utilitarios.UCompraCancion;
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
public class CoreCompraCancion implements Serializable {

    public List<UCancion> listarCancionesDisponibles() {
        return new DAOCompraCancion().listarCancionesDisponibles();
    }
    
    public void registrar(UCancion cancion) {
        int idUsuario = ((UUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getId();
        int idCancion = cancion.getId();
        int valorCompra = cancion.getPrecio();
        Date fechaCompra = new Date();
        
        UCompraCancion compra = new UCompraCancion(0, idUsuario, idCancion, valorCompra, fechaCompra);

        new DAOCompraCancion().registrar(compra);

        FacesMessage mensaje = new FacesMessage("CANCIÓN COMPRADA CON ÉXITO");
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
    }
    
    public List<UCompraCancion> listar(){
        UUsuario usuario = (UUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return new DAOCompraCancion().listarPorUsuario(usuario);
    }
}
