/*
 * PACKAGE
 */
package com.primo.ws.modelo;

/*
 * IMPORTS
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.primo.constants.ws.PrimoURI;
import com.primo.model.Modelo;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 * Clase cliente que contiene los servicios del objeto Modelo
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 05/04/2020
 */
public class ModeloWSClient {

    /**
     * Método que encarga de traer la información de las modelos por marca
     * @param myIdMarca
     * @return myListModelo
     * @throws IOException 
     */
    public static List<Modelo> traerModeloMarca(BigInteger myIdMarca) throws IOException{
        
        //Atributos de Metodo
        Gson myGson = new Gson();
        
        Client myClient = ClientBuilder.newClient();
        String myURL = PrimoURI.REG_DIR_MOD + "/" + myIdMarca;
        String myStringList = myClient.target(myURL).request(MediaType.APPLICATION_JSON).get(String.class);
        List<Modelo> myListModelo = myGson.fromJson(myStringList, new TypeToken<List<Modelo>>(){}.getType());
        myClient.close();
        return myListModelo;
    }        
}
