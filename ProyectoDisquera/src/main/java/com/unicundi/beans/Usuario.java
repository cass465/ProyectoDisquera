/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans;

import com.unicundi.utilitarios.UDisco;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author cass465
 */
@Named(value = "usuario")
@ViewScoped
public class Usuario implements Serializable {
    private List<UDisco> discos;
    
    /**
     * Creates a new instance of Usuario
     */
    public Usuario(){
    }
    
}
