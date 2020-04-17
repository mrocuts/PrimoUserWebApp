/*
 * PACKAGE
 */
package com.primo.bean;

/*
 * IMPORTS
 */
import com.primo.model.Garaje;
import com.primo.model.Vehiculo;
import com.primo.ws.vehiculo.VehiculoWSClient;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Bean que maneja la interfaz del Garaje
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 04/04/2020
 */

@RequestScoped
@ManagedBean(name = "GarajeBean")
public class GarajeBean {
    
    /** Atributos de Método **/
    HttpSession mySession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    private List<Vehiculo> myListVehiculo;
    
    public GarajeBean(){
        //Traer la información del Garaje
        Garaje myGaraje = (Garaje)mySession.getAttribute("myGaraje");
        
        //Traer la lista de los vehiculos
        this.myListVehiculo = VehiculoWSClient.getVehiclesGarage(myGaraje.getIdGaraje());
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
     * Método que carga el vehiculo en sesión y lo envía a actualizar
     * @param myVehiculo
     * @return String
     */
    public String cargarVehiculo(Vehiculo myVehiculo){
        //Cargar la información del Vehiculo
        mySession.setAttribute("myVehiculoAct", myVehiculo);
        
        //Ir a la pagina de Actualizaciòn
        return "modificarVehiculo";
    } 
}