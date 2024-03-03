package com.neogiciel.springsearch.model;

import java.io.Serializable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "SERVICEPERSONNE")
public class ServicePersonne implements Serializable{
   
    @Id //@GeneratedValue
    @Column(name = "ID")
    public long id;

    //@Column(name = "IDSERVICE")
    //public long idService;

    //@Column(name = "IDPERSONNE")
    //public long idPersonne;
    
    @ManyToOne
	@JoinColumn(name = "IDSERVICE", referencedColumnName = "IDSERVICE", nullable = false)
    private Service service;
    
    @ManyToOne
	@JoinColumn(name = "IDPERSONNE", referencedColumnName = "ID", nullable = false)
    private Personne personne;


    /*
     * Constructeur
    */
    public ServicePersonne(){
    }

    /*
     * Personne
    */
    public Personne getPersonne(){
        return personne;
    }

    /*
     * Service
    */
    public Service getService(){
        return service;
    }


        /*
     * toJSON
     */
    public JSONObject toJSON(ServicePersonne sp) {

        JSONObject obj = new JSONObject();
        obj.put("id", sp.id);
        obj.put("idpersonne", sp.getPersonne().id);
        obj.put("nom", sp.getPersonne().nom);
        obj.put("prenon", sp.getPersonne().prenom);
        obj.put("age", sp.getPersonne().age);
        obj.put("idservice", sp.getService().id);
        obj.put("label", sp.getService().label);
            
        return obj;
    }   


    /*
     * totListeJSON
     */
    public JSONArray totListeJSON( List<ServicePersonne> liste ) {

        JSONArray jsonArray = new JSONArray();

        for (int i= 0; i < liste.size(); i++ ){
            ServicePersonne sp = liste.get(i);
            JSONObject obj = new JSONObject();
            obj.put("id", sp.id);
            obj.put("idpersonne", sp.getPersonne().id);
            obj.put("nom", sp.getPersonne().nom);
            obj.put("prenon", sp.getPersonne().prenom);
            obj.put("age", sp.getPersonne().age);
            obj.put("idservice", sp.getService().id);
            obj.put("label", sp.getService().label);
            jsonArray.put(obj);
        }
        return jsonArray;
    } 


}
