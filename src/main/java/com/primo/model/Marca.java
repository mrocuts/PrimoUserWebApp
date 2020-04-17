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
 * Clase que representa el Objeto Marca
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 01/04/2020
 */
public class Marca implements Serializable {
    
    /** Atributos de Clase **/
    private BigInteger idMarca;
    private String strDescripcion;
    private boolean bitActivo;
    private TipoVehiculo myTipoVehiculo;

    /**
     * @return the idMarca
     */
    public BigInteger getIdMarca() {
        return idMarca;
    }

    /**
     * @param idMarca the idMarca to set
     */
    public void setIdMarca(BigInteger idMarca) {
        this.idMarca = idMarca;
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

    /**
     * @return the bitActivo
     */
    public boolean isBitActivo() {
        return bitActivo;
    }

    /**
     * @param bitActivo the bitActivo to set
     */
    public void setBitActivo(boolean bitActivo) {
        this.bitActivo = bitActivo;
    }

    /**
     * @return the myTipoVehiculo
     */
    public TipoVehiculo getMyTipoVehiculo() {
        return myTipoVehiculo;
    }

    /**
     * @param myTipoVehiculo the myTipoVehiculo to set
     */
    public void setMyTipoVehiculo(TipoVehiculo myTipoVehiculo) {
        this.myTipoVehiculo = myTipoVehiculo;
    }
}
