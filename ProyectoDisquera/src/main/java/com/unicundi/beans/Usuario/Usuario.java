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
 * Managed Bean de la vista usuario.xhtml
 * @author Camilo Sanabria
 * @version 1.0.0
 */
@Named(value = "usuario")
@ViewScoped
public class Usuario implements Serializable {
    /**
     * Nombre de usuario que se mostrara en la vista
     */
    private String username;
    
    /**
     * Lista de discos comprados por el usuario
     */
    private List<UCompraDisco> discos;
    
    /**
     * Lista de canciones compradas por el usuario
     */
    private List<UCompraCancion> canciones;
    
    /**
     * Lista de canciones compradas en el disco consultado
     */
    private List<UCompraDiscoCancion> cancionesDisco;
    
    /**
     * Lista de discos al realizar algun filtro
     */
    private List<UCompraDisco> filtroDiscos;
    
    /**
     * Lista de canciones al realizar algun filtro
     */
    private List<UCompraCancion> filtroCanciones;
    
    /**
     * Constructor del Managed Bean Usuario
     */
    public Usuario(){
        this.username = ((UUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getUsername();
        this.discos = new CoreCompras().listarDiscosComprados();
        this.canciones = new CoreCompras().listarCancionesCompradas();
    }
    
    /**
     * Obtiene las canciones del disco comprado especificado
     * @param compra Objeto compra del disco
     */
    public void buscarCancionesPorDisco(UCompraDisco compra) {
        this.cancionesDisco = new CoreCompras().buscarPorCompraDisco(compra.getId());
        //Abrir modal de canciones de disco comprado
        RequestContext.getCurrentInstance().execute("PF('cancionesDiscoDialog').show();");
    }

    /**
     * Obtiene el username de usuario
     * @return username de usuario
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el username de usuario
     * @param username Valor para asignar al username de usuario
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Obtene los discos comprados
     * @return Lista de discos comprados
     */
    public List<UCompraDisco> getDiscos() {
        return discos;
    }
    
    /**
     * Establece los discos comprados
     * @param discos Lista para asignar a los discos
     */
    public void setDiscos(List<UCompraDisco> discos) {
        this.discos = discos;
    }
    
    /**
     * Obtiene las canciones compradas
     * @return Lista de canciones compradas
     */
    public List<UCompraCancion> getCanciones() {
        return canciones;
    }

    /**
     * Establece las canciones compradas
     * @param canciones Lista para asignar a las canciones
     */
    public void setCanciones(List<UCompraCancion> canciones) {
        this.canciones = canciones;
    }

    /**
     * Obtiene las canciones del disco consultado
     * @return Lista de canciones segun el disco
     */
    public List<UCompraDiscoCancion> getCancionesDisco() {
        return cancionesDisco;
    }

    /**
     * Establece las canciones segun el disco
     * @param cancionesDisco Lista de canciones para asignar
     */
    public void setCancionesDisco(List<UCompraDiscoCancion> cancionesDisco) {
        this.cancionesDisco = cancionesDisco;
    }

    /**
     * Obtiene la lista de filtro de discos comprados
     * @return Lista de compras de disco
     */
    public List<UCompraDisco> getFiltroDiscos() {
        return filtroDiscos;
    }
    
    /**
     * Establece la lista de filtro de compras de discos
     * @param filtroDiscos Lista para asignar al filtro
     */
    public void setFiltroDiscos(List<UCompraDisco> filtroDiscos) {
        this.filtroDiscos = filtroDiscos;
    }

    /**
     * Obtiene la lista de filtro de canciones compradas
     * @return 
     */
    public List<UCompraCancion> getFiltroCanciones() {
        return filtroCanciones;
    }

    /**
     * Establece la lista de filtro de compras de canciones
     * @param filtroCanciones Lista para asignar al filtro
     */
    public void setFiltroCanciones(List<UCompraCancion> filtroCanciones) {
        this.filtroCanciones = filtroCanciones;
    }
    
}
