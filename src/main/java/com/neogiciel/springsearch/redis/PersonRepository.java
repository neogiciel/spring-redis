package com.neogiciel.springsearch.redis;

import org.springframework.data.repository.CrudRepository;

import com.neogiciel.springsearch.model.Personne;
 

public interface PersonRepository extends CrudRepository<Personne, Long> {
    
}

