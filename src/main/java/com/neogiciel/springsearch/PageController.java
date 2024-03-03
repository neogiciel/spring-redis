package com.neogiciel.springsearch;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.neogiciel.springsearch.model.Personne;
import com.neogiciel.springsearch.model.Service;
import com.neogiciel.springsearch.model.ServicePersonne;
import com.neogiciel.springsearch.redis.RedisManager;
import com.neogiciel.springsearch.service.BddManager;
import com.neogiciel.springsearch.util.Trace;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;


@Controller
public class PageController {

    //BddManager
    @Autowired
    BddManager bdd; 

    //RedisManager
    //@Autowired
    //RedisManager redisBdd; 
  
  
    /*
     * Page
    */
    @GetMapping
    @RequestMapping("/page")
    public String page(Model model) {
        Trace.info("Affiche la page");
        return "page";
    }

    /*
     * listepersonne
    */
    @GetMapping
    @RequestMapping("/listepersonne")
    public String listepersonne(Model model) {

        List<Personne> liste = bdd.getListPersonnes();
        model.addAttribute("liste", liste);
        return "listepersonne";
    }

    /*
     * listepersonnenocache
    */
    @GetMapping
    @RequestMapping("/listepersonnenocache")
    public String listepersonnenocache(Model model) {

        List<Personne> liste = bdd.getListPersonnesNoCache();
        model.addAttribute("liste", liste);
        return "listepersonne";
    }

    /*
     * listepersonneredis
    */
    @GetMapping
    @RequestMapping("/listepersonneredis")
    public String listepersonneredis(Model model) {

        List<Personne> liste = bdd.getListPersonnes();
        model.addAttribute("liste", liste);
        return "listepersonne";
    }



    /*
     * addpersonne
    */
    @GetMapping
    @RequestMapping("/addpersonne")
    public String addpersonne(Model model) {

        return "addpersonne";
    }

    /*
     * addserviceb
    */
    @PostMapping
    @RequestMapping("/addpersonneb")
    public String addpersonneb(Model model, @RequestParam("nom") String nom,@RequestParam("prenom") String prenom,@RequestParam("age") String age) {
        
        
        Personne personne = new Personne(nom, prenom,Integer.valueOf(age));
        bdd.addPersonne(personne);

        List<Personne> liste = bdd.getListPersonnes();
        model.addAttribute("liste", liste);
        
        return "listepersonne";
    }


    /*
     * supprimerpersonne
    */
    @GetMapping
    @RequestMapping("/supprimerpersonne")
    public String supprimerpersonne(Model model,@RequestParam("id") String id) {
        //Supprimer la perssonne
        bdd.deletePersonne(Integer.valueOf(id));
        //Recuperer la liste
        List<Personne> liste = bdd.getListPersonnes();
        model.addAttribute("liste", liste);
        return "listepersonne";
    }

    /*
     * listeservice
    */
    @GetMapping
    @RequestMapping("/listeservice")
    public String listeservice(Model model) {

        List<Service> liste = bdd.getListServices();
        model.addAttribute("liste", liste);
        return "listeservice";
    }

    /*
     * addservice
    */
    @GetMapping
    @RequestMapping("/addservice")
    public String addservice(Model model) {

        return "addservice";
    }

    /*
     * addserviceb
    */
    @PostMapping
    @RequestMapping("/addserviceb")
    public String addpostservice(Model model, @RequestParam("label") String label) {
        Trace.info("label ="+label);

        Service service = new Service(label);
        bdd.addService(service);
        return "addservice";
    }

    /*
     * supprimerservice
    */
    @GetMapping
    @RequestMapping("/supprimerservice")
    public String supprimerservice(Model model,@RequestParam("id") String id) {
        //Supprimer la perssonne
        bdd.deleteService(Integer.valueOf(id));
        //Recuperer la liste
        List<Service> liste = bdd.getListServices();
        model.addAttribute("liste", liste);
        return "listeservice";
    }

    /*
     * listeservicepersonne
    */
    @GetMapping
    @RequestMapping("/listeservicepersonne")
    public String listeservicepersonne(Model model) {
        Trace.info("getListPersonneFromServices ");
        List<ServicePersonne> liste = bdd.getListPersonneFromServices(1);
        Trace.info("liste  nb ="+liste.size());

        model.addAttribute("liste", liste);
        return "listeservicepersonne";
    }


  

  
}
