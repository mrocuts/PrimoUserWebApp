/*
 * PACKAGE
 */
package com.primo.model;

/*
 * IMPORTS
 */
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Clase que representa el Objeto Tipo de Vehiculo
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 01/04/2020
 */
public class TipoVehiculo implements Serializable {
    
    /** Atributos de Clase **/
    private BigInteger idTipoVehiculo;
    private String strDescripcion;

    /**
     * Constructor Vacio
     */
    public TipoVehiculo(){
        this.idTipoVehiculo = BigInteger.ZERO;
    }   
    
    /**
     * @return the IdTipoVehiculo
     */
    public BigInteger getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    /**
     * @param IdTipoVehiculo the IdTipoVehiculo to set
     */
    public void setIdTipoVehiculo(BigInteger idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    /**
     * @return the strDescripcion
     */
    public String getStrDescripcion() {
        return strDescripcion;
    }

    /**
     * @param strDescripcion the strDescripcion to set
     */
    public void setStrDescripcion(String strDescripcion) {
        this.strDescripcion = strDescripcion;
    }   
}