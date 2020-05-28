/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans.Usuario;

import com.unicundi.core.Usuario.CoreCompras;
import com.unicundi.utilitarios.UCompraCancion;
import com.unicundi.utilitarios.UCompraDisco;
import com.unicundi.utilitarios.UCompraDiscoCancion;
import com.unicundi.utilitarios.UUsuario;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author cass465
 */
@Named(value = "usuario")
@ViewScoped
public class Usuario implements Serializable {
    private String username;
    private List<UCompraDisco> discos;
    private List<UCompraCancion> canciones;
    private List<UCompraDiscoCancion> cancionesDisco;
    
    private List<UCompraDisco> filtroDiscos;
    private List<UCompraCancion> filtroCanciones;
    
    /**
     * Creates a new instance of Usuario
     */
    public Usuario(){
        this.username = ((UUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getUsername();
        this.discos = new CoreCompras().listarDiscosComprados();
        this.canciones = new CoreCompras().listarCancionesCompradas();
    }
    
    public void buscarCancionesPorDisco(UCompraDisco compra) {
        this.cancionesDisco = new CoreCompras().buscarPorCompraDisco(compra.getId());
        //Abrir modal de canciones de disco comprado
        RequestContext.getCurrentInstance().execute("PF('cancionesDiscoDialog').show();");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public List<UCompraDisco> getDiscos() {
        return discos;
    }

    public void setDiscos(List<UCompraDisco> discos) {
        this.discos = discos;
    }

    public List<UCompraCancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<UCompraCancion> canciones) {
        this.canciones = canciones;
    }

    public List<UCompraDiscoCancion> getCancionesDisco() {
        return cancionesDisco;
    }

    public void setCancionesDisco(List<UCompraDiscoCancion> cancionesDisco) {
        this.cancionesDisco = cancionesDisco;
    }

    public List<UCompraDisco> getFiltroDiscos() {
        return filtroDiscos;
    }

    public void setFiltroDiscos(List<UCompraDisco> filtroDiscos) {
        this.filtroDiscos = filtroDiscos;
    }

    public List<UCompraCancion> getFiltroCanciones() {
        return filtroCanciones;
    }

    public void setFiltroCanciones(List<UCompraCancion> filtroCanciones) {
        this.filtroCanciones = filtroCanciones;
    }
    
}
