/*
 * PACKAGE
 */
package com.primo.ws.vehiculo;

/*
 * IMPORTS
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.primo.constants.ws.PrimoURI;
import com.primo.model.Vehiculo;
import com.primo.ws.PrimoMsg;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

/**
 * Clase cliente que contiene los servicios del objeto Vehiculo
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 04/04/2020
 */
public class VehiculoWSClient {
    
    /**
     * Método que trae la información de los vehiculos por garaje
     * @param myIdGaraje
     * @return List<>
     */    
    public static List<Vehiculo> getVehiclesGarage(BigInteger myIdGaraje){
        //Atributos de Metodo
        Client myClient = ClientBuilder.newClient();
        Gson myGson = new Gson();

        //Traer la información de los Vehiculos por Garaje
        String myURL = PrimoURI.REG_DIR_VH +"/"+ myIdGaraje;
        String myStringList = myClient.target(myURL).request(MediaType.APPLICATION_JSON).get(String.class);
        List<Vehiculo> myListVehiculo = myGson.fromJson(myStringList, new TypeToken<List<Vehiculo>>(){}.getType());
        myClient.close();

        //Retornar la información de los Vehiculos por Garaje
        return myListVehiculo;
    }

    /**
     * Método que registra la información del Vehocuño
     * @param myVehiculo
     * @throws IOException
     * @throws Exception 
     */
    public static void registrarVehiculo(Vehiculo myVehiculo) throws IOException, Exception{
	//Atributos de Método
        SSLContext ctx = SSLContext.getDefault();
        HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();
        ClientBuilder builder = ClientBuilder.newBuilder().sslContext(ctx);
	Client myClient = builder.hostnameVerifier(hostnameVerifier).build();
	WebTarget resourceTarget= myClient.target(PrimoURI.REG_DIR_VH);
	Invocation.Builder invocationBuilder = resourceTarget.request();
        Response response= invocationBuilder.post(Entity.json(myVehiculo));
        PrimoMsg respuesta=response.readEntity(PrimoMsg.class);
        if(!respuesta.isSucces()){
            throw new Exception(respuesta.getResponse());
        }
    }

    /**
     * Método que actualiza la información del Vehocuño
     * @param myVehiculo
     * @throws IOException
     * @throws Exception 
     */
    public static void actualizarVehiculo(Vehiculo myVehiculo) throws IOException, Exception{
	//Atributos de Método
        SSLContext ctx = SSLContext.getDefault();
        HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();
        ClientBuilder builder = ClientBuilder.newBuilder().sslContext(ctx);
	Client myClient = builder.hostnameVerifier(hostnameVerifier).build();
	WebTarget resourceTarget= myClient.target(PrimoURI.REG_MOD_VH);
	Invocation.Builder invocationBuilder = resourceTarget.request();
        Response response= invocationBuilder.put(Entity.json(myVehiculo));
        PrimoMsg respuesta=response.readEntity(PrimoMsg.class);
        if(!respuesta.isSucces()){
            throw new Exception(respuesta.getResponse());
        }
    }

    /**
     * Método que sube al cliente al vehiculo
     * @param myIdGaraje
     * @param myVehiculo
     * @throws IOException
     * @throws Exception 
     */
    public static void subirAlVehiculo(BigInteger myIdGaraje,Vehiculo myVehiculo) throws IOException, Exception{
	//Atributos de Método
        SSLContext ctx = SSLContext.getDefault();
        HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();
        ClientBuilder builder = ClientBuilder.newBuilder().sslContext(ctx);
	Client myClient = builder.hostnameVerifier(hostnameVerifier).build();
	WebTarget resourceTarget= myClient.target(PrimoURI.REG_SUB_VH + myIdGaraje);
	Invocation.Builder invocationBuilder = resourceTarget.request();
        Response response= invocationBuilder.put(Entity.json(myVehiculo));
        PrimoMsg respuesta=response.readEntity(PrimoMsg.class);
        if(!respuesta.isSucces()){
            throw new Exception(respuesta.getResponse());
        }
    }
}