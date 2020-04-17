/*
 * PACKAGE
 */
package com.primo.bean;

/*
 * IMPORTS
 */
import com.primo.model.Garaje;
import com.primo.model.Marca;
import com.primo.model.Modelo;
import com.primo.model.TipoVehiculo;
import com.primo.model.Vehiculo;
import com.primo.ws.marca.MarcaWSClient;
import com.primo.ws.modelo.ModeloWSClient;
import com.primo.ws.tipoVehiculo.TipoVehiculoWSClient;
import com.primo.ws.vehiculo.VehiculoWSClient;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.primefaces.customize.UI.utils.UIMessageManagement;

/**
 * Bean que maneja la interfaz del Vehiculo
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 05/04/2020
 */

@ViewScoped
@ManagedBean(name = "VehiculoBean")
public class VehiculoBean {
    
    /** Atributos de Método **/
    HttpSession mySession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    private Vehiculo myVehiculo;
    private Marca myMarca;
    private List<SelectItem> myListMarca;
    private List<SelectItem> myListModelo;
    private Garaje myGaraje;
    private BigDecimal dblAnio;
    private BigDecimal dblPuertas;
    private Vehiculo myVehiculoModificar;
    private List<Vehiculo> myListVehiculo;
    
    /**
     * @return the myVehiculo
     */
    public Vehiculo getMyVehiculo() {
        return myVehiculo;
    }

    /**
     * @param myVehiculo the myVehiculo to set
     */
    public void setMyVehiculo(Vehiculo myVehiculo) {
        this.myVehiculo = myVehiculo;
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

    /**
     * @return the myListMarca
     */
    public List<SelectItem> getMyListMarca() {
        return myListMarca;
    }

    /**
     * @param myListMarca the myListMarca to set
     */
    public void setMyListMarca(List<SelectItem> myListMarca) {
        this.myListMarca = myListMarca;
    }

    /**
     * @return the myListModelo
     */
    public List<SelectItem> getMyListModelo() {
        return myListModelo;
    }

    /**
     * @param myListModelo the myListModelo to set
     */
    public void setMyListModelo(List<SelectItem> myListModelo) {
        this.myListModelo = myListModelo;
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
     * @return the dblAnio
     */
    public BigDecimal getDblAnio() {
        return dblAnio;
    }

    /**
     * @param dblAnio the dblAnio to set
     */
    public void setDblAnio(BigDecimal dblAnio) {
        this.dblAnio = dblAnio;
    }

    /**
     * @return the dblPuertas
     */
    public BigDecimal getDblPuertas() {
        return dblPuertas;
    }

    /**
     * @param dblPuertas the dblPuertas to set
     */
    public void setDblPuertas(BigDecimal dblPuertas) {
        this.dblPuertas = dblPuertas;
    }

    /**
     * @return the myVehiculoModificar
     */
    public Vehiculo getMyVehiculoModificar() {
        return myVehiculoModificar;
    }

    /**
     * @param myVehiculoModificar the myVehiculoModificar to set
     */
    public void setMyVehiculoModificar(Vehiculo myVehiculoModificar) {
        this.myVehiculoModificar = myVehiculoModificar;
    }

    /**
     * @return the myListVehiculo
     */
    public List<Vehiculo> getMyListVehiculo() {
        return myListVehiculo;
    }

    /**
     * @param myListVehiculo the myListVehiculo to set
     */
    public void setMyListVehiculo(List<Vehiculo> myListVehiculo) {
        this.myListVehiculo = myListVehiculo;
    }
    
    /**
     * Constructor de la Clase VehiculoBean
     */
    public VehiculoBean(){
        //Atributos de Método
        TipoVehiculo myTipoVehiculo = new TipoVehiculo();
        Modelo myModelo = new Modelo();
        
        //Inicializar información
        this.myGaraje = (Garaje)mySession.getAttribute("myGaraje");
        this.myVehiculo = new Vehiculo();
        this.myMarca = new Marca();
        this.myListMarca = new ArrayList<>();
        this.myListModelo = new ArrayList<>();
        this.dblAnio = new BigDecimal(0);
        this.dblPuertas = new BigDecimal(0);
        
        myVehiculo.setMyTipoVehiculo(myTipoVehiculo);
        myVehiculo.setMyModelo(myModelo);
        myVehiculo.setMyGaraje(myGaraje);
        myMarca.setIdMarca(BigInteger.ZERO);
        
        this.myVehiculoModificar = (Vehiculo)mySession.getAttribute("myVehiculoAct");
        
        //Traer la lista de los vehiculos
        this.myListVehiculo = VehiculoWSClient.getVehiclesGarage(myGaraje.getIdGaraje());
    }
    
    /**
     * Metodo que obtiene la informacion de la lista de los tipos de Vehiculo
     * @return List<>
     */
    public List<SelectItem> obtenerListaTipoVehiculo() {
        //Atributos de Método
        List<SelectItem> myListSelectItems = new ArrayList<>();
                    
        try {
            //Traer la Lista de los Tipos de Vehiculo
            List<TipoVehiculo> myListTipoVehiculo = TipoVehiculoWSClient.traerTiposVehiculo();
            
            //Recorrer la lista de los dominios
            myListTipoVehiculo.stream().map((myTipoVehiculoTemp) -> {
                //Crear el Item
                SelectItem item = new SelectItem();
                item.setLabel(myTipoVehiculoTemp.getStrDescripcion());
                item.setDescription(myTipoVehiculoTemp.getStrDescripcion());
                item.setValue(myTipoVehiculoTemp.getIdTipoVehiculo());
                return item;                
            }).forEachOrdered((item) -> {
                //Adicionarlo en la lista
                myListSelectItems.add(item);
            });
        } catch (IOException ex) {
            Logger.getLogger(TipoVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Retornar la lista de los tipos de Vehiculo
        return myListSelectItems;
    }

    /**
     * Metodo que obtiene la informacion de la lista de las marcas por tipo de Vehiculo
     * @return List<>
     */
    public List<SelectItem> obtenerListaMarca() {
        try {
            //Traer la Lista de las marcas por tipo de vehiculo
            List<Marca> myListMarcaTemp = MarcaWSClient.traerMarcaTipoVehiculo(myVehiculo.getMyTipoVehiculo().getIdTipoVehiculo());
            
            myListMarcaTemp.stream().map((myMarcaTemp) -> {
                //Crear el Item
                SelectItem item = new SelectItem();
                item.setLabel(myMarcaTemp.getStrDescripcion());
                item.setDescription(myMarcaTemp.getStrDescripcion());
                item.setValue(myMarcaTemp.getIdMarca());
                return item;                
            }).forEachOrdered((item) -> {
                //Adicionarlo en la lista
                myListMarca.add(item);
            });
        } catch (IOException ex) {
            Logger.getLogger(Marca.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Retornar la lista de las marcas de acuerdo al tipo de vehiculo
        return myListMarca;
    }

    /**
     * Metodo que obtiene la informacion de la lista de los modelos por marca
     * @return List<>
     */
    public List<SelectItem> obtenerListaModelo() {
        try {
            //Traer la Lista de las modelo por marca
            List<Modelo> myListModeloTemp = ModeloWSClient.traerModeloMarca(myMarca.getIdMarca());
            
            myListModeloTemp.stream().map((myModeloTemp) -> {
                //Crear el Item
                SelectItem item = new SelectItem();
                item.setLabel(myModeloTemp.getStrDescripcion());
                item.setDescription(myModeloTemp.getStrDescripcion());
                item.setValue(myModeloTemp.getIdModelo());
                return item;                
            }).forEachOrdered((item) -> {
                //Adicionarlo en la lista
                myListModelo.add(item);
            });
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Retornar la lista de los modelos por marca
        return myListModelo;
    }
    
    /**
     * Mètodo que registra la información del Vehiculo
     * @return 
     */
    public String registrarVehiculo(){
        try {
            //Construir el objeto Vehiculo
            this.myVehiculo.setIntAnio(dblAnio.toBigInteger());
            this.myVehiculo.setBitActivo(true);
            this.myVehiculo.setIntPuertas(dblPuertas.toBigInteger());
            
            //Verificar si es el primer vehiculo que se guarda
            if(myListVehiculo.isEmpty()){
                this.myVehiculo.setBitSubido(true);
            }
            else{
                this.myVehiculo.setBitSubido(false);
            }
                
            VehiculoWSClient.registrarVehiculo(myVehiculo);
            UIMessageManagement.putInfoMessage("Vehiculo Creado");
            return "/vehiculo/registerVehicle.xhtml?faces-redirect=true";
        } 
        catch (Exception ex) {
            UIMessageManagement.putException(ex);
            return "/vehiculo/registerVehicle.xhtml?faces-redirect=true";
        }
    }    

    /**
     * Mètodo que actualiza la información del Vehiculo
     * @return 
     */
    public String actualizarVehiculo(){
        try {
            VehiculoWSClient.actualizarVehiculo(myVehiculoModificar);
            UIMessageManagement.putInfoMessage("Vehiculo Actualizado");
            return "/vehiculo/modifyVehicle.xhtml?faces-redirect=true";
        } 
        catch (Exception ex) {
            UIMessageManagement.putException(ex);
            return "/vehiculo/modifyVehicle.xhtml?faces-redirect=true";
        }
    }    

    /**
     * Mètodo que se encarga de subir al vehiculo
     * @return 
     */
    public String subirAlVehiculo(){
        //Atributos de Metodo
        String myMensaje = "";
        try {
            //Verificar que exista más de un vehiculo y que no este montado
            if(myListVehiculo.size() > 1 && !myVehiculoModificar.isBitSubido()){
                VehiculoWSClient.subirAlVehiculo(myGaraje.getIdGaraje(),myVehiculoModificar);
                myMensaje = "Se ha subido al Vehiculo";
            }
            else if(myListVehiculo.size() == 1 || myVehiculoModificar.isBitSubido()){
                myMensaje = "Ya esta montado en el Vehiculo";
            }
            
            //Retornar a la pantalla
            UIMessageManagement.putInfoMessage(myMensaje);
            return "/vehiculo/modifyVehicle.xhtml?faces-redirect=true";
        } 
        catch (Exception ex) {
            UIMessageManagement.putException(ex);
            return "/vehiculo/modifyVehicle.xhtml?faces-redirect=true";
        }
    }    
}