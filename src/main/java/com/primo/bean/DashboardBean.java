/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primo.bean;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.primo.bean.AddMarkersView;
import com.primo.bean.MarkersController;
import com.primo.model.Garaje;
import com.primo.model.Vehiculo;
import com.primo.ws.vehiculo.VehiculoWSClient;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author OvalleGA
 */
@ManagedBean(name="DashboardBean")
@SessionScoped
public class DashboardBean {

    /** Atributos de Clase **/
    private MapModel emptyModel;
    private String title;
    private String coordToCenter="4.71098860,-74.07209200";
    private String zoom="12";
    private Marker markerSelect;
    private final String ApiKey="AIzaSyClXdX5gQMy9ehI3s7F9UW7qCZQxVJVRqA";
    private double lat;
    private double lng;
    HttpSession mySession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    private List<Vehiculo> myListVehiculo;
    
    /**
     * Constructor de la Clase DashboardBean
     */
    public DashboardBean(){
        emptyModel = new DefaultMapModel();
        for(Marker m: MarkersController.getInstance().getMarkers()){
            emptyModel.addOverlay(m);
        }
        
        //Traer la información del Garaje
        Garaje myGaraje = (Garaje)mySession.getAttribute("myGaraje");
        
        //Traer la lista de los vehiculos
        this.myListVehiculo = VehiculoWSClient.getVehiclesGarage(myGaraje.getIdGaraje());        
    }

    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public void setEmptyModel(MapModel emptyModel) {
        this.emptyModel = emptyModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoordToCenter() {
        return coordToCenter;
    }

    public void setCoordToCenter(String coordToCenter) {
        this.coordToCenter = coordToCenter;
    }

    public String getZoom() {
        return zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    public Marker getMarkerSelect() {
        return markerSelect;
    }

    public void setMarkerSelect(Marker markerSelect) {
        this.markerSelect = markerSelect;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
    
    public void addMarker() {
        Marker markerToAdd = new Marker(new org.primefaces.model.map.LatLng(lat, lng), title);
        markerToAdd.setId("1");
        markerToAdd.setDraggable(true);
        emptyModel.addOverlay(markerToAdd);
        MarkersController.getInstance().putMarker(markerToAdd);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
    }
    
    public void onMarkerSelect(OverlaySelectEvent event){
        System.out.println("Evento: "+event);
        markerSelect = (Marker) event.getOverlay();
        System.out.println("Marcador: "+markerSelect);
    }
    
    public void onMarkerDrag(MarkerDragEvent event) {
        markerSelect = event.getMarker();     
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + markerSelect.getLatlng().getLat() + ", Lng:" + markerSelect.getLatlng().getLng()));
        LatLng coords=new LatLng(markerSelect.getLatlng().getLat(), markerSelect.getLatlng().getLng());
        markerSelect.setTitle(getAddress(coords));
    }
    public void putAdress(){
        try {
            System.out.println("Click ");
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(ApiKey)
                    .build();
            GeocodingResult[] results =  GeocodingApi.geocode(context,
                    "").await();
            zoom="16";
            coordToCenter = (results[0].geometry.location).toString();
            System.out.println("Coordenadas: "+coordToCenter);
            String[] data=coordToCenter.split(",");
            Marker markerToAdd = new Marker(new org.primefaces.model.map.LatLng(Double.parseDouble(data[0]), Double.parseDouble(data[1])), "");
            markerToAdd.setDraggable(true);
            emptyModel.addOverlay(markerToAdd);
            //company_address=company_sucursal_name+" - "+company_sucursal_address;
            lat=markerToAdd.getLatlng().getLat();
            lng=markerToAdd.getLatlng().getLng();
            MarkersController.getInstance().putMarker(markerToAdd);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + markerToAdd.getLatlng().getLat() + ", Lng:" + markerToAdd.getLatlng().getLng()));
        } catch (ApiException ex) {
            Logger.getLogger(DashboardBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(DashboardBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DashboardBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getAddress(LatLng coords){
        try {
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(ApiKey)
                    .build();
            GeocodingResult[] results= GeocodingApi.reverseGeocode(context, coords).await();
            for(Object o : results){
                System.out.println(o.toString());
            }
            System.out.println("Direccion: "+results[0].formattedAddress);
            return results[0].formattedAddress;
        } catch (ApiException ex) {
            Logger.getLogger(AddMarkersView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(AddMarkersView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddMarkersView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
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
}