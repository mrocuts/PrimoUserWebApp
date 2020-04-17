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
 * Clase que representa el Objeto Modelo
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 01/04/2020
 */
public class Modelo implements Serializable {
    
    /** Atributos de Clase **/
    private BigInteger idModelo;
    private String strDescripcion;
    private boolean bitActivo;
    private Marca myMarca;

    /**
     * @return the idModelo
     */
    public BigInteger getIdModelo() {
        return idModelo;
    }

    /**
     * @param idModelo the idModelo to set
     */
    public void setIdModelo(BigInteger idModelo) {
        this.idModelo = idModelo;
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
     * @return the myMarca
     */
    public Marca getMyMarca() {
        return myMarca;
    }

    /**
     * @param myMarca the myMarca to set
     */
    public void setMyMarca(Marca myMarca) {
        this.myMarca = myMarca;
    }
}