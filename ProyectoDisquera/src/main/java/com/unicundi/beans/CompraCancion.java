/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author cass465
 */
@Named(value = "compraCancion")
@RequestScoped
public class CompraCancion implements Serializable{

    /**
     * Creates a new instance of CompraCancion
     */
    public CompraCancion() {
    }
    
}
