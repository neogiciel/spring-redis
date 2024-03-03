package com.neogiciel.springsearch.redis;

import java.util.List;

import com.neogiciel.springsearch.model.Personne;
import com.neogiciel.springsearch.model.Service;
import com.neogiciel.springsearch.model.ServicePersonne;

public interface RedisManager {
    
    /*
     * Table Personne
     */
    public List<Personne> getListPersonnes();
    
}
