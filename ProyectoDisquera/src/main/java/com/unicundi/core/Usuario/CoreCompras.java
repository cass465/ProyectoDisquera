/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.core.Usuario;

import com.unicundi.BD.DAOCancion;
import com.unicundi.BD.DAOCompraCancion;
import com.unicundi.BD.DAOCompraDisco;
import com.unicundi.utilitarios.UCancion;
import com.unicundi.utilitarios.UCompraCancion;
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
public class CoreCompras implements Serializable{

    public List<UDisco> listarDiscosDisponibles() {
        return new DAOCompraDisco().listarDiscosDisponibles();
    }
    
    public List<UCancion> listarCancionesDisponibles() {
        return new DAOCompraCancion().listarCancionesDisponibles();
    }
    
    public List<UCancion> buscarPorDisco(int idDisco){
        return new DAOCancion().buscarPorDisco(idDisco);
    }
    
    public void registrar(List<UDisco> discosAgregados, List<UCancion> cancionesAgregadas) {
        int nCompras = 0;
        int idUsuario = ((UUsuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getId();
        Date fechaCompra = new Date();
        
        for (UDisco disco : discosAgregados){
            int idDisco = disco.getId();
            int valorCompra = disco.getPrecio();
            UCompraDisco compra = new UCompraDisco(0, idUsuario, idDisco, valorCompra, fechaCompra);
            new DAOCompraDisco().registrar(compra);
            nCompras++;
        }
        
        for (UCancion cancion : cancionesAgregadas){
            int idCancion = cancion.getId();
            int valorCompra = cancion.getPrecio();
            UCompraCancion compra = new UCompraCancion(0, idUsuario, idCancion, valorCompra, fechaCompra);
            new DAOCompraCancion().registrar(compra);
            nCompras++;
        }
        
        if (nCompras > 0) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "ÉXITO", "ELEMENTOS COMPRADOS: " + nCompras);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "ATENCIÓN!!", "LISTA DE COMPRAS VACIA");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
    }
    
    public int calcularTotal(List<UDisco> discosAgregados, List<UCancion> cancionesAgregadas){
        int total = 0;
        for (UDisco disco : discosAgregados){
            total += disco.getPrecio();
        }
        
        for (UCancion cancion : cancionesAgregadas){
            total += cancion.getPrecio();
        }
        
        return total;
    }
    
    public List<UCompraDisco> listarDiscosComprados(){
        UUsuario usuario = (UUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return new DAOCompraDisco().listarPorUsuario(usuario);
    }
    
    public List<UCompraCancion> listarCancionesCompradas(){
        UUsuario usuario = (UUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return new DAOCompraCancion().listarPorUsuario(usuario);
    }
}
