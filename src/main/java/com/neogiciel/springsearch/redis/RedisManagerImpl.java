package com.neogiciel.springsearch.redis;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.neogiciel.springsearch.model.Personne;
import com.neogiciel.springsearch.util.Trace;


@SpringBootApplication
public class RedisManagerImpl implements RedisManager{
    
   //Redis DatBase
   @Autowired
   PersonRepository personRepository;

    /*
     * getListPersonnes
     */
    public List<Personne> getListPersonnes(){
        Trace.info("[BddManager] select * from PERSONNE");

        Iterable<Personne> actualPersons = personRepository.findAll();
        List<Personne> liste = StreamSupport.stream(actualPersons.spliterator(), false).collect(Collectors.toList());
        return liste;

	}
	


}
