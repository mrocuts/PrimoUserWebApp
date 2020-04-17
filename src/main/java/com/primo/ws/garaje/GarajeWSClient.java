/*
 * PACKAGE
 */
package com.primo.ws.garaje;

/*
 * IMPORTS
 */
import com.primo.constants.ws.PrimoURI;
import com.primo.model.Garaje;
import java.math.BigInteger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 * Clase cliente que contiene los servicios del objeto Garaje
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 03/04/2020
 */
public class GarajeWSClient {
    
    /**
     * Método que trae la información del Garaje de un Usuario
     * @param myIdUsuario
     * @return Garaje
     */    
    public static Garaje getGarageUser(BigInteger myIdUsuario){
        //Atributos de Metodo
        Client myClient = ClientBuilder.newClient();

        //Traer la información del Garaje
        String myURL = PrimoURI.REG_DIR_GR +"/"+ myIdUsuario;
        Garaje myGaraje = myClient.target(myURL).request(MediaType.APPLICATION_JSON).get(Garaje.class);
        myClient.close();

        //Retornar la información del Garaje
        return myGaraje;
    }
}