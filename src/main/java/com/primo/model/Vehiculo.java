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
public class Vehiculo implements Serializable {
    
    /** Atributos de Clase **/
    private BigInteger idVehiculo;
    private BigInteger intAnio;
    private String strPlaca;
    private String strObservacion;
    private String strSerial;
    private String strColor;
    private boolean bitActivo;
    private boolean bitSubido;
    private BigInteger intPuertas;
    private String strMotor;
    private String strTransmision;
    private TipoVehiculo myTipoVehiculo;
    private Modelo myModelo;
    private Garaje myGaraje;

    /**
     * @return the idVehiculo
     */
    public BigInteger getIdVehiculo() {
        return idVehiculo;
    }

    /**
     * @param idVehiculo the idVehiculo to set
     */
    public void setIdVehiculo(BigInteger idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    /**
     * @return the intAnio
     */
    public BigInteger getIntAnio() {
        return intAnio;
    }

    /**
     * @param intAnio the intAnio to set
     */
    public void setIntAnio(BigInteger intAnio) {
        this.intAnio = intAnio;
    }

    /**
     * @return the strObservacion
     */
    public String getStrObservacion() {
        return strObservacion;
    }

    /**
     * @param strObservacion the strObservacion to set
     */
    public void setStrObservacion(String strObservacion) {
        this.strObservacion = strObservacion;
    }

    /**
     * @return the strSerial
     */
    public String getStrSerial() {
        return strSerial;
    }

    /**
     * @param strSerial the strSerial to set
     */
    public void setStrSerial(String strSerial) {
        this.strSerial = strSerial;
    }

    /**
     * @return the strColor
     */
    public String getStrColor() {
        return strColor;
    }

    /**
     * @param strColor the strColor to set
     */
    public void setStrColor(String strColor) {
        this.strColor = strColor;
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
     * @return the intPuertas
     */
    public BigInteger getIntPuertas() {
        return intPuertas;
    }

    /**
     * @param intPuertas the intPuertas to set
     */
    public void setIntPuertas(BigInteger intPuertas) {
        this.intPuertas = intPuertas;
    }

    /**
     * @return the strMotor
     */
    public String getStrMotor() {
        return strMotor;
    }

    /**
     * @param strMotor the strMotor to set
     */
    public void setStrMotor(String strMotor) {
        this.strMotor = strMotor;
    }

    /**
     * @return the strTransmision
     */
    public String getStrTransmision() {
        return strTransmision;
    }

    /**
     * @param strTransmision the strTransmision to set
     */
    public void setStrTransmision(String strTransmision) {
        this.strTransmision = strTransmision;
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

    /**
     * @return the myModelo
     */
    public Modelo getMyModelo() {
        return myModelo;
    }

    /**
     * @param myModelo the myModelo to set
     */
    public void setMyModelo(Modelo myModelo) {
        this.myModelo = myModelo;
    }

    /**
     * @return the myGaraje
     */
    public Garaje getMyGaraje() {
        return myGaraje;
    }

    /**
     * @param myGaraje the myGaraje to set
     */
    public void setMyGaraje(Garaje myGaraje) {
        this.myGaraje = myGaraje;
    }

    /**
     * @return the strPlaca
     */
    public String getStrPlaca() {
        return strPlaca;
    }

    /**
     * @param strPlaca the strPlaca to set
     */
    public void setStrPlaca(String strPlaca) {
        this.strPlaca = strPlaca;
    }

    /**
     * @return the bitSubido
     */
    public boolean isBitSubido() {
        return bitSubido;
    }

    /**
     * @param bitSubido the bitSubido to set
     */
    public void setBitSubido(boolean bitSubido) {
        this.bitSubido = bitSubido;
    }
}