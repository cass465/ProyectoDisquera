/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author cass465
 */
@Named(value = "index")
@SessionScoped
public class Index implements Serializable {

    /**
     * Creates a new instance of Index
     */
    public Index() {
    }
}
