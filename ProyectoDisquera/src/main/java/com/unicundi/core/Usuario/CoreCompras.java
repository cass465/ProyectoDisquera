/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.core.Usuario;

import com.unicundi.BD.DAOCancion;
import com.unicundi.BD.DAOCompraCancion;
import com.unicundi.BD.DAOCompraDisco;
import com.unicundi.BD.DAOCompraDiscoCancion;
import com.unicundi.utilitarios.UCancion;
import com.unicundi.utilitarios.UCompraCancion;
import com.unicundi.utilitarios.UCompraDisco;
import com.unicundi.utilitarios.UCompraDiscoCancion;
import com.unicundi.utilitarios.UDisco;
import com.unicundi.utilitarios.UUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Clase que maneja la logica de las compras
 * @author Camilo Sanabria
 * @version 1.0.0
 */
public class CoreCompras implements Serializable {

    /**
     * Lista los discos disponibles 
     * @return Todos los discos disponibles
     */
    public List<UDisco> listarDiscosDisponibles() {
        return new DAOCompraDisco().listarDiscosDisponibles();
    }

    /**
     * Lista las canciones disponibles
     * @return Todas las canciones disponibles
     */
    public List<UCancion> listarCancionesDisponibles() {
        return new DAOCompraCancion().listarCancionesDisponibles();
    }

    /**
     * Lista las canciones del disco especificado
     * @param idDisco Id del disco al que se desea listar sus canciones
     * @return Lista de canciones del disco
     */
    public List<UCancion> buscarPorDisco(int idDisco) {
        return new DAOCancion().buscarPorDisco(idDisco);
    }
    
    /**
     * Lista las compras que relacionan la compra de los discos con sus canciones
     * @param idCompraDisco Id de las compras de las canciones que tienen el disco
     * @return Lista de compras de canciones de disco
     */
    public List<UCompraDiscoCancion> buscarPorCompraDisco(int idCompraDisco) {
        return new DAOCompraDiscoCancion().buscarPorCompraDisco(idCompraDisco);
    }

    /**
     * Registra las compras realizadas por el usuario
     * @param discosAgregados Lista de discos que se compran
     * @param cancionesAgregadas Lista de canciones que se compran
     */
    public void registrar(List<UDisco> discosAgregados, List<UCancion> cancionesAgregadas) {
        int nCompras = 0;
        int idUsuario = ((UUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getId();
        Date fechaCompra = new Date();

        for (UDisco disco : discosAgregados) {
            int idDisco = disco.getId();
            int valorCompra = disco.getPrecio();
            int numeroCanciones = disco.getCancionesDisco().size();
            UCompraDisco compra = new UCompraDisco(0, idUsuario, idDisco, valorCompra, fechaCompra, numeroCanciones);
            new DAOCompraDisco().registrar(compra);
            int idCompraDisco = new DAOCompraDisco().obtenerIdMayor();
            
            List<UCancion> cancionesDisco = disco.getCancionesDisco();
            for (UCancion cancionDisco : cancionesDisco){
                int idCancion = cancionDisco.getId();
                UCompraDiscoCancion compraCancion = new UCompraDiscoCancion(0, idCompraDisco, idCancion);
                new DAOCompraDiscoCancion().registrar(compraCancion);
            }
            
            nCompras++;
        }

        for (UCancion cancion : cancionesAgregadas) {
            int idCancion = cancion.getId();
            int valorCompra = cancion.getPrecio();
            UCompraCancion compra = new UCompraCancion(0, idUsuario, idCancion, valorCompra, fechaCompra);
            new DAOCompraCancion().registrar(compra);
            nCompras++;
        }

        if (nCompras > 0) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "ÉXITO", "ELEMENTOS COMPRADOS: " + nCompras);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "ATENCIÓN!!", "LISTA DE COMPRAS VACIA");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
    }

    /**
     * Lista los discos comprados
     * @return Todos los discos comprados
     */
    public List<UCompraDisco> listarDiscosComprados() {
        UUsuario usuario = (UUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return new DAOCompraDisco().listarPorUsuario(usuario);
    }

    /**
     * Lista las canciones compradas
     * @return Todas las canciones compradas
     */
    public List<UCompraCancion> listarCancionesCompradas() {
        UUsuario usuario = (UUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return new DAOCompraCancion().listarPorUsuario(usuario);
    }

    /**
     * Lista las canciones de disco que se van a remover de la compra
     * cuando se agrega el disco
     * @param cancionesAgregadas Lista de todas las canciones agregadas
     * @param disco Disco al que pertenecen las canciones a remover
     * @return Lista de canciones que deben ser removidas
     */
    public List<UCancion> listarCancionesARemover(List<UCancion> cancionesAgregadas, UDisco disco) {
        //Recorre las canciones agregadas y se listan las que tienen que salir
        List<UCancion> removerCanciones = new ArrayList<UCancion>();
        for (UCancion cancionAgregada : cancionesAgregadas) {
            if (cancionAgregada.getIdDisco() == disco.getId()) {
                removerCanciones.add(cancionAgregada);
            }
        }
        return removerCanciones;
    }

    /**
     * Valida que una cancion no se pueda agregar si
     * el disco ya esta agregado en la compra
     * @param discosAgregados Todos los discos agregados
     * @param cancion Cancion que se valida
     * @return Si es aceptada la cancion para agregar o no
     */
    public boolean validarDiscoAgregado(List<UDisco> discosAgregados, UCancion cancion) {
        boolean aceptado = true;
        for (UDisco discoAgregado : discosAgregados) {
            if (discoAgregado.getId() == cancion.getIdDisco()) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "NO SE PUEDE AGREGAR LA CANCIÓN PORQUE EL DISCO YA ESTÁ AGREGADO");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                aceptado = false;
                break;
            }
        }
        return aceptado;
    }

    /**
     * Obtiene el disco disponible de la cancion especificada
     * @param discosDisponibles Todos los discos disponibles
     * @param cancion Cancion por la que se desea buscar el disco
     * @return Disco encontrado de la cancion
     */
    public UDisco obtenerDiscoPorCancion(List<UDisco> discosDisponibles, UCancion cancion) {
        UDisco discoCompleto = new UDisco();
        for (UDisco disco : discosDisponibles) {
            if (disco.getId() == cancion.getIdDisco()) {
                discoCompleto = disco;
            }
        }
        return discoCompleto;
    }
    
    /**
     * Cuenta el numero de canciones del disco especificado
     * @param cancionesAgregadas Todas las canciones que han sido agregadas a la compra
     * @param idDisco id de disco al que se desea contar sus canciones
     * @return Numero de canciones del disco
     */
    public int contarCancionesAgregadasDeDisco(List<UCancion> cancionesAgregadas, int idDisco) {
        int nCanciones = 0;
        for (UCancion cancion : cancionesAgregadas) {
            if (cancion.getIdDisco() == idDisco) {
                nCanciones++;
            }
        }
        return nCanciones;
    }

    /**
     * Calcula el precio total a pagar segun los discos y canciones agregadas
     * @param discosAgregados Todos los discos agregados
     * @param cancionesAgregadas Todas las canciones agregadas
     * @return Valor total a pagar
     */
    public int calcularTotal(List<UDisco> discosAgregados, List<UCancion> cancionesAgregadas) {
        int total = 0;
        for (UDisco disco : discosAgregados) {
            total += disco.getPrecio();
        }

        for (UCancion cancion : cancionesAgregadas) {
            total += cancion.getPrecio();
        }

        return total;
    }
}
