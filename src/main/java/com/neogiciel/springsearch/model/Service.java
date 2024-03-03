package com.neogiciel.springsearch.model;

import java.io.Serializable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
//import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SERVICE")
public class Service implements Serializable{
   
    @Id //@GeneratedValue
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "IDSERVICE")
    public long id;
    
    @Column(name="LABEL")
    public String label;

    /*
     * Constructeur
    */
    public Service(){
    }

    /*
     * Constructeur
    */
    public Service(String label){
        this.label = label;
    }

    /*
     * toJSON
     */
    public JSONObject toJSON(Service s) {

        JSONObject obj = new JSONObject();
        obj.put("idservice", s.id);
        obj.put("label", s.label);
        return obj;
    }    


    /*
     * totListeJSON
     */
    public JSONArray totListeJSON( List<Service> liste ) {

        JSONArray jsonArray = new JSONArray();

        for (int i= 0; i < liste.size(); i++ ){
            Service s = liste.get(i);
            JSONObject obj = new JSONObject();
            obj.put("idservice", s.id);
            obj.put("label", s.label);
            jsonArray.put(obj);
        }
        return jsonArray;
    } 


}
