/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.core;

import com.unicundi.BD.DAOCancion;
import com.unicundi.utilitarios.UCancion;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author cass465
 */
public class CoreCancion implements Serializable{
    
    public List<UCancion> listar(){
        return new DAOCancion().listar();
    }
    
    public List<UCancion> buscarPorDisco(int idDisco){
        return new DAOCancion().buscarPorDisco(idDisco);
    }
}
