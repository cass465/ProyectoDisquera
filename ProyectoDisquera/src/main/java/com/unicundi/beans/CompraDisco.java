/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans;

import com.unicundi.core.CoreComprarDisco;
import com.unicundi.core.CoreDisco;
import com.unicundi.utilitarios.UDisco;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author cass465
 */
@Named(value = "compraDisco")
@ViewScoped
public class CompraDisco implements Serializable{
    private List<UDisco> discos;
    
    /**
     * Creates a new instance of CompraDisco
     */
    public CompraDisco() {
        this.discos = new CoreDisco().listar();
    }
    
    public void comprar(){
        new CoreComprarDisco().registrar(discos);
    }

    public List<UDisco> getDiscos() {
        return discos;
    }

    public void setDiscos(List<UDisco> discos) {
        this.discos = discos;
    }
}
