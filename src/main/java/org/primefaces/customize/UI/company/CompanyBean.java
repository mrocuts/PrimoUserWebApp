/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.customize.UI.company;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.primo.bean.AddMarkersView;
import com.primo.bean.MarkersController;
import com.primo.model.Contacto;
import com.primo.ws.dominio.DominioWSClient;
import com.primo.model.Dominio;
import com.primo.model.Empresa;
import com.primo.model.Sucursal;
import com.primo.ws.PrimoMsg;
import com.primo.ws.company.CompanyWSClient;
import com.primo.ws.contacto.ContactoWSClient;
import com.primo.ws.sucursal.SucursalWSClient;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import org.primefaces.customize.UI.utils.UIMessageManagement;
import org.primefaces.customize.controllers.security.UserSessionManager;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author OvalleGA
 */
@ManagedBean(name="CompanyBean")
@SessionScoped
public class CompanyBean {

    private MapModel emptyModel;
    private String title;
    private String coordToCenter="4.71098860,-74.07209200";
    private String zoom="12";
    private Marker markerSelect;
    private final String ApiKey="AIzaSyClXdX5gQMy9ehI3s7F9UW7qCZQxVJVRqA";
    private double lat;
    private double lng;
    
    /** Atributos de Clase **/
    private Empresa company;
    private Dominio myDominio;
    private String company_identification;
    private String company_name;
    private Date company_fundation_date;
    private UploadedFile company_logo_file;
    
    private String company_address;
    private int company_rate_primos;
    private int company_ranking_zone;
    private int company_ranking_service;
    private String company_status;
    private int company_client_attend;
    private int company_new_client;
    private int company_recurrent_client;
    private boolean company_is_register;

    private String company_sucursal_name;
    private String company_sucursal_address;
    
    private String company_contact_id;
    private String company_contact_name;
    private String company_contact_surname;
    private String company_contact_address;
    private String company_contact_phone;
    private String company_contact_email;
    private Date company_contact_birthdate;
    
    private Map<String,String> myListDepartamento;
    private Map<String,String> myListCiudad;
    private Dominio myDominioContact;
    private Blob myBlobImagen;
    
    public CompanyBean(){
        emptyModel = new DefaultMapModel();
        for(Marker m: MarkersController.getInstance().getMarkers()){
            emptyModel.addOverlay(m);
        }
        
        //Traer la información de la empresa
        List<Empresa> myListEmpresa = CompanyWSClient.getCompany(UserSessionManager.getInstance().getUser((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getIdUsuario());
        //Validar si existe la empresa
        if(myListEmpresa.size() > 0){
            this.company = myListEmpresa.get(0);
            this.company_name=company.getStrRazonSocial();
            this.company_is_register = true;
            //List<Sucursal> myListSucursal= SucursalWSClient.getSucursal(this.company.getIdEmpresa());
            //if(myListSucursal.size()>0){
                //company_sucursal_name=myListSucursal.get(0).getStrNombre();
                //company_sucursal_address=getAddress(new LatLng(Double.parseDouble(myListSucursal.get(0).getLatitud()), Double.parseDouble(myListSucursal.get(0).getLongitud())));
              //  company_address=company_sucursal_name+" - "+company_sucursal_address;
            //}
        }else{
            this.company_name = "NOMBRE DE TU EMPRESA";
            this.company_is_register = false;
        }

        //Inicializar los campos
        setDefaultValues();
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
    
    public String getCompany_sucursal_address() {
        return company_sucursal_address;
    }

    public void setCompany_sucursal_address(String company_sucursal_address) {
        this.company_sucursal_address = company_sucursal_address;
    }

    public String getCompany_sucursal_name() {
        return company_sucursal_name;
    }

    public void setCompany_sucursal_name(String company_sucursal_name) {
        this.company_sucursal_name = company_sucursal_name;
    }

    public String getCompany_contact_name() {
        return company_contact_name;
    }

    public void setCompany_contact_name(String company_contact_name) {
        this.company_contact_name = company_contact_name;
    }

    public String getCompany_contact_surname() {
        return company_contact_surname;
    }

    public void setCompany_contact_surname(String company_contact_surname) {
        this.company_contact_surname = company_contact_surname;
    }

    public String getCompany_contact_address() {
        return company_contact_address;
    }

    public void setCompany_contact_address(String company_contact_address) {
        this.company_contact_address = company_contact_address;
    }

    public String getCompany_contact_phone() {
        return company_contact_phone;
    }

    public void setCompany_contact_phone(String company_contact_phone) {
        this.company_contact_phone = company_contact_phone;
    }

    public String getCompany_contact_email() {
        return company_contact_email;
    }

    public void setCompany_contact_email(String company_contact_email) {
        this.company_contact_email = company_contact_email;
    }

    public Date getCompany_contact_birthdate() {
        return company_contact_birthdate;
    }

    public void setCompany_contact_birthdate(Date company_contact_birthdate) {
        this.company_contact_birthdate = company_contact_birthdate;
    }
    
    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public int getCompany_rate_primos() {
        return company_rate_primos;
    }

    public void setCompany_rate_primos(int company_rate_primos) {
        this.company_rate_primos = company_rate_primos;
    }

    public int getCompany_ranking_zone() {
        return company_ranking_zone;
    }

    public void setCompany_ranking_zone(int company_ranking_zone) {
        this.company_ranking_zone = company_ranking_zone;
    }

    public int getCompany_ranking_service() {
        return company_ranking_service;
    }

    public void setCompany_ranking_service(int company_ranking_service) {
        this.company_ranking_service = company_ranking_service;
    }

    public String getCompany_status() {
        return company_status;
    }

    public void setCompany_status(String company_status) {
        this.company_status = company_status;
    }

    public int getCompany_client_attend() {
        return company_client_attend;
    }

    public void setCompany_client_attend(int company_client_attend) {
        this.company_client_attend = company_client_attend;
    }

    public int getCompany_new_client() {
        return company_new_client;
    }

    public void setCompany_new_client(int company_new_client) {
        this.company_new_client = company_new_client;
    }

    public int getCompany_recurrent_client() {
        return company_recurrent_client;
    }

    public void setCompany_recurrent_client(int company_recurrent_client) {
        this.company_recurrent_client = company_recurrent_client;
    }

    public boolean isCompany_is_register() {
        return company_is_register;
    }

    public void setCompany_is_register(boolean company_is_register) {
        this.company_is_register = company_is_register;
    }
    
    /**
     * @return the myDominio
     */
    public Dominio getMyDominio() {
        return myDominio;
    }

    /**
     * @param myDominio the myDominio to set
     */
    public void setMyDominio(Dominio myDominio) {
        this.myDominio = myDominio;
    }

    /**
     * @return the company_identification
     */
    public String getCompany_identification() {
        return company_identification;
    }

    /**
     * @param company_identification the company_identification to set
     */
    public void setCompany_identification(String company_identification) {
        this.company_identification = company_identification;
    }

    /**
     * @return the company_fundation_date
     */
    public Date getCompany_fundation_date() {
        return company_fundation_date;
    }

    /**
     * @param company_fundation_date the company_fundation_date to set
     */
    public void setCompany_fundation_date(Date company_fundation_date) {
        this.company_fundation_date = company_fundation_date;
    }

    /**
     * @return the company_logo_file
     */
    public UploadedFile getCompany_logo_file() {
        return company_logo_file;
    }

    /**
     * @param company_logo_file the company_logo_file to set
     */
    public void setCompany_logo_file(UploadedFile company_logo_file) {
        this.company_logo_file = company_logo_file;
    }

    /**
     * @return the myListDepartamento
     */
    public Map<String,String> getMyListDepartamento() {
        return myListDepartamento;
    }

    /**
     * @param myListDepartamento the myListDepartamento to set
     */
    public void setMyListDepartamento(Map<String,String> myListDepartamento) {
        this.myListDepartamento = myListDepartamento;
    }

    /**
     * @return the myListCiudad
     */
    public Map<String,String> getMyListCiudad() {
        return myListCiudad;
    }

    /**
     * @param myListCiudad the myListCiudad to set
     */
    public void setMyListCiudad(Map<String,String> myListCiudad) {
        this.myListCiudad = myListCiudad;
    }

    /**
     * @return the myDominioContact
     */
    public Dominio getMyDominioContact() {
        return myDominioContact;
    }

    /**
     * @param myDominioContact the myDominioContact to set
     */
    public void setMyDominioContact(Dominio myDominioContact) {
        this.myDominioContact = myDominioContact;
    }

    /**
     * @return the company_contact_id
     */
    public String getCompany_contact_id() {
        return company_contact_id;
    }

    /**
     * @param company_contact_id the company_contact_id to set
     */
    public void setCompany_contact_id(String company_contact_id) {
        this.company_contact_id = company_contact_id;
    }

    /**
     * @return the myBlobImagen
     */
    public Blob getMyBlobImagen() {
        return myBlobImagen;
    }

    /**
     * @param myBlobImagen the myBlobImagen to set
     */
    public void setMyBlobImagen(Blob myBlobImagen) {
        this.myBlobImagen = myBlobImagen;
    }

    private void setDefaultValues(){
        company_address = "DIRECCION DE TU EMRPESA";
        company_rate_primos = 0;
        company_ranking_zone = 0;
        company_ranking_service = 0;
        company_status="close";
        company_client_attend=0;
        company_new_client=0;
        company_recurrent_client=0;

        this.myDominio = new Dominio();
        this.myDominioContact = new Dominio();
        this.company_identification = "";
        this.company_fundation_date = new Date();
    }
    
    public void change_status_company(){
        if(company_is_register){
            if(company_status.equals("open")){
                company_status = "close";
            }else{
                company_status = "open";
            }
        }else{
            UIMessageManagement.putCustomMessage(FacesMessage.SEVERITY_WARN, "Registro incompleto", "Debe completar el registro de su compañia para poder abrir.");
        }
    }
    
    public String save_company_info(){
        // SALVAR INFORMACION CON EL WS.
        return "";
    }
    
    /**
     * Metodo que obtiene la informacion de la lista de dominios de acuerdo al tipo
     * @param myIdTipoDominio
     * @return myListSelectItems
     */
    public List<SelectItem> obtenerListadoDominioTipo(String myIdTipoDominio) {
        //Atributos de Método
        List<SelectItem> myListSelectItems= new ArrayList<>();
                    
        try {
            //Traer la Lista de Dominios
            List<Dominio> myListDominio = DominioWSClient.traerDominiosPorTipo(new BigInteger(myIdTipoDominio));
            
            //Recorrer la lista de los dominios
            myListDominio.stream().map((myDominioTemp) -> {
                //Crear el Item
                SelectItem item = new SelectItem();
                item.setLabel(myDominioTemp.getStrDescripcion());
                item.setDescription(myDominioTemp.getStrDescripcion());
                item.setValue(myDominioTemp.getIdDominio());
                return item;                
            }).forEachOrdered((item) -> {
                //Adicionarlo en la lista
                myListSelectItems.add(item);
            });
        } catch (IOException ex) {
            Logger.getLogger(CompanyBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Retornar la lista de los tipos de Identificación
        return myListSelectItems;
    }
    
    /**
     * Método que guarda la información de la empresa
     */
    public void guardarEmpresa(){
        
        //Atributos de Método
        Empresa myEmpresa = new Empresa();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        try{
            //Crear el objeto Empresa
            myEmpresa.setMyUsuario(UserSessionManager.getInstance().getUser((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)));
            myEmpresa.setMyDominio(this.myDominio);
            myEmpresa.setStrIdentificacion(this.company_identification);
            myEmpresa.setStrRazonSocial(this.company_name);
            myEmpresa.setDtmFechaFundacion(format.format(this.company_fundation_date));
            myEmpresa.setImgLogo(this.myBlobImagen);
          
            CompanyWSClient.registerCompany(myEmpresa);
            
            //Traer la información de la empresa
            List<Empresa> myListEmpresa = CompanyWSClient.getCompany(UserSessionManager.getInstance().getUser((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getIdUsuario());
            
            if(myListEmpresa.size() > 0){
                Empresa myEmpresaConsulta = myListEmpresa.get(0);
                                
                //Crear la información del Contacto
                Contacto myContacto = new Contacto(this.company_contact_id, this.company_contact_name, this.company_contact_surname, 
                                                   this.company_contact_address, this.company_contact_phone, this.company_contact_email, 
                                                   this.company_contact_birthdate, myEmpresaConsulta, myDominioContact);
                ContactoWSClient.guardarContacto(myContacto);
            }
            else{
                PrimoMsg myMensaje = new PrimoMsg("Error al crear la información de la Empresa");
                throw new Exception(myMensaje.getResponse());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Método que se encarga de traer las ciudad por departamento
     * @param event
     */
    public void cargarImagen(FileUploadEvent event) {
        try {
            //Atributos de Metodo
             UploadedFile myUploadedFile = event.getFile();
            //Verificar que hay imagen y guardar
            if(!myUploadedFile.getFileName().equals("")){
                Blob myImgBlob = new SerialBlob(myUploadedFile.getContents());
                this.myBlobImagen = myImgBlob;
            }
            else{
                this.myBlobImagen = null;
            }
        } catch (Exception ex) {
            Logger.getLogger(CompanyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        company_sucursal_address=getAddress(coords);
    }
    public void putAdress(){
        try {
            System.out.println("Click "+company_sucursal_address);
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(ApiKey)
                    .build();
            GeocodingResult[] results =  GeocodingApi.geocode(context,
                    company_sucursal_address).await();
            zoom="16";
            coordToCenter = (results[0].geometry.location).toString();
            System.out.println("Coordenadas: "+coordToCenter);
            String[] data=coordToCenter.split(",");
            Marker markerToAdd = new Marker(new org.primefaces.model.map.LatLng(Double.parseDouble(data[0]), Double.parseDouble(data[1])), company_sucursal_address);
            markerToAdd.setDraggable(true);
            emptyModel.addOverlay(markerToAdd);
            company_address=company_sucursal_name+" - "+company_sucursal_address;
            lat=markerToAdd.getLatlng().getLat();
            lng=markerToAdd.getLatlng().getLng();
            MarkersController.getInstance().putMarker(markerToAdd);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + markerToAdd.getLatlng().getLat() + ", Lng:" + markerToAdd.getLatlng().getLng()));
        } catch (ApiException ex) {
            Logger.getLogger(CompanyBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(CompanyBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CompanyBean.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void saveSucursal(){
        if(company!= null){
            //Crear el objeto sucursal y guadarlo en la base de datos
            Sucursal mySucursal = new Sucursal(this.company_sucursal_name, true, ""+lat, ""+lng, company);
            try {
                mySucursal = SucursalWSClient.guardarSucursal(mySucursal);
                //Validar que se cree la sucursal
                if(mySucursal.getIdSucursal().equals(BigInteger.ZERO)){
                    PrimoMsg myMensaje = new PrimoMsg("Error al crear la Sucursal de la Empresa");
                    try {
                        throw new Exception(myMensaje.getResponse());
                    } catch (Exception ex) {
                        Logger.getLogger(CompanyBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(CompanyBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}