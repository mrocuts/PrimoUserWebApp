/*
 * PACKAGE
 */
package com.primo.ws.tipoVehiculo;

/*
 * IMPORTS
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.primo.constants.ws.PrimoURI;
import com.primo.model.TipoVehiculo;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 * Clase cliente que contiene los servicios del objeto Tipo de Vehiculo
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 05/04/2020
 */
public class TipoVehiculoWSClient {

    /**
     * Método que encarga de traer la información de los tipos de vehiculo
     * @return myListTipoVehiculo
     * @throws IOException 
     */
    public static List<TipoVehiculo> traerTiposVehiculo() throws IOException{
        
        //Atributos de Metodo
        Gson myGson = new Gson();
        
        Client myClient = ClientBuilder.newClient();
        String myURL = PrimoURI.REG_DIR_TVH;
        String myStringList = myClient.target(myURL).request(MediaType.APPLICATION_JSON).get(String.class);
        List<TipoVehiculo> myListTipoVehiculo = myGson.fromJson(myStringList, new TypeToken<List<TipoVehiculo>>(){}.getType());
        myClient.close();
        return myListTipoVehiculo;
    }
}