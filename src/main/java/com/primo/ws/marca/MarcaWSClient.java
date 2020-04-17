/*
 * PACKAGE
 */
package com.primo.ws.marca;

/*
 * IMPORTS
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.primo.constants.ws.PrimoURI;
import com.primo.model.Marca;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 * Clase cliente que contiene los servicios del objeto Marca
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 05/04/2020
 */
public class MarcaWSClient {

    /**
     * Método que encarga de traer la información de las marcas por tipo vehiculo
     * @param myIdTipoVehiculo
     * @return myListMarca
     * @throws IOException 
     */
    public static List<Marca> traerMarcaTipoVehiculo(BigInteger myIdTipoVehiculo) throws IOException{
        
        //Atributos de Metodo
        Gson myGson = new Gson();
        
        Client myClient = ClientBuilder.newClient();
        String myURL = PrimoURI.REG_DIR_MAR + "/" + myIdTipoVehiculo;
        String myStringList = myClient.target(myURL).request(MediaType.APPLICATION_JSON).get(String.class);
        List<Marca> myListMarca = myGson.fromJson(myStringList, new TypeToken<List<Marca>>(){}.getType());
        myClient.close();
        return myListMarca;
    }    
}
