package com.neogiciel.springsearch.model;

import java.io.Serializable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PERSONNE")
public class Personne implements Serializable {
   
    @Id //@GeneratedValue
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    public long id;
    
    @Column(name="NOM")
    public String nom;

    @Column(name="PRENOM")
    public String prenom;
    
    @Column(name="AGE")
    public int age;

    /*
     * Constructeur
    */
    public Personne(){
    }

    /*
     * Constructeur
    */
    public Personne(String nom,String prenom,int age){
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    /*
     * toJSON
     */
    public JSONObject toJSON(Personne p) {

        JSONObject obj = new JSONObject();
        obj.put("id", p.id);
        obj.put("nom", p.prenom);
        obj.put("prenon", p.nom);
        obj.put("age", p.age);
            
        return obj;
    }    


    /*
     * totListeJSON
     */
    public JSONArray totListeJSON( List<Personne> liste ) {

        JSONArray jsonArray = new JSONArray();

        for (int i= 0; i < liste.size(); i++ ){
            Personne p = liste.get(i);
            JSONObject obj = new JSONObject();
            obj.put("id", p.id);
            obj.put("nom", p.prenom);
            obj.put("prenon", p.nom);
            obj.put("age", p.age);
            jsonArray.put(obj);
        }
        return jsonArray;
    } 


}
