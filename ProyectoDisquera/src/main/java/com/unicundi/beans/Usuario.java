/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans;

import com.unicundi.core.CoreCompraCancion;
import com.unicundi.core.CoreCompraDisco;
import com.unicundi.utilitarios.UCompraCancion;
import com.unicundi.utilitarios.UCompraDisco;
import com.unicundi.utilitarios.UUsuario;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

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
    
    /**
     * Creates a new instance of Usuario
     */
    public Usuario(){
        this.username = ((UUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getUsername();
        this.discos = new CoreCompraDisco().listar();
        this.canciones = new CoreCompraCancion().listar();
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
    
}
