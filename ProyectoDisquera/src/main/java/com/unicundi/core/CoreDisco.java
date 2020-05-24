/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.core;

import com.unicundi.BD.DAODisco;
import com.unicundi.utilitarios.UDisco;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author cass465
 */
public class CoreDisco implements Serializable{
    
    public List<UDisco> listar(){
        return new DAODisco().listar();
    }
}
