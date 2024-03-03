package com.neogiciel.springsearch;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.metrics.StartupStep.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neogiciel.springsearch.model.Personne;
import com.neogiciel.springsearch.model.ServicePersonne;
import com.neogiciel.springsearch.redis.RedisManager;
import com.neogiciel.springsearch.service.BddManager;
import com.neogiciel.springsearch.util.Trace;

@RestController
@RequestMapping("/cache")
public class ApiController {
    
    //BddManager
    @Autowired
    BddManager bdd; 

    // Auto-wiring the CacheManager within your service
    @Autowired
    private CacheManager cacheManager;
    
  
    /*
     * test
    */
    @GetMapping(value = "/test",produces="application/json") 
    @Cacheable(value = "myCache", key = "'api-test'")
    public String test() {
        Trace.info("Appel REST test cache");
        return getJSON("test", "test").toString();
        //return Map.of("test", "test");
    }

    /*
     * test
    */
    @GetMapping(value = "/invalidate",produces="application/json") 
    //@CacheEvict(value = "myCache", allEntries = true)
    public String invalidate() {
        Trace.info("Appel REST invalidate cache");
        refreshCache();
        return getJSON("invalidate", "ok").toString();
        //return Map.of("test", "test");
    }


    /*
     * listepersonne
    */
    @GetMapping(value = "/listepersonne",produces="application/json") 
    @Cacheable(value = "myCache", key = "'api-getListPersonnes_'")
    public String listepersonne() {
        Trace.info("Appel REST listepersonne");
        List<Personne> liste = bdd.getListPersonnes();
        //return Map.of("liste", "nb = "+liste.size());

        if(liste.size()> 0){
            JSONArray jsonArray = new Personne().totListeJSON(liste);
            return jsonArray.toString();
        }
        return getJSON("nb", "0").toString();

        
    }
  
    /*
     * personne
    */
    @GetMapping(value = "/personne/{id}",produces="application/json") 
    @Cacheable(value = "myCache", key = "'api-getPersonneFromId_' + #id")
    //public ResponseEntity<Object> personne(@PathVariable int id) {
    public String personne(@PathVariable int id) {
        Trace.info("Appel REST personne = "+id);
        Personne personne = (Personne) bdd.getPersonneFromId(id);
        Trace.info("id = "+personne.id);
        Trace.info("prenom = "+personne.prenom);
        Trace.info("nom = "+personne.nom);
        Trace.info("age = "+personne.age);

        return personne.toJSON(personne).toString();

        //return personne.toString();
        //return ResponseEntity.ok(personne);
         
    }

     /*
      * getJSON
      */
     public JSONObject getJSON(String value,String key){
        JSONObject obj = new JSONObject();
        obj.put(value, key);
        return obj;
     }

    /*
     * refreshCache
     */
    public void refreshCache() {

        Cache cache = cacheManager.getCache("myCache");
        if (cache != null) {
            cache.clear(); // Clears all entries from the cache, effectively refreshing it
        }

    }

    
  

}
