package com.neogiciel.springsearch.service;

import java.util.List;

import com.neogiciel.springsearch.model.Personne;
import com.neogiciel.springsearch.model.Service;
import com.neogiciel.springsearch.model.ServicePersonne;

public interface BddManager {
    
    /*
     * Table Personne
     */
    public List<Personne> getListPersonnes();
    public List<Personne> getListPersonnesNoCache();
    public List<Personne> getListPersonnesRedis();
    public List<Personne> getListPersonnesSQL();
    public Long addPersonne(Personne personne);
    public Long addPersonneSQL(Personne personne);
	public Personne getPersonneFromId(int Id);
    public Personne getPersonneFromIdSQL(int Id);
    public void deletePersonne(int id);
	public void deletePersonneSQL(int id);
	public void updatePersonne(Personne personne);
	public void updatePersonneSQL(Personne personne);

    /*
     * Table Service
     */
    public List<Service> getListServices();
    public Service getServiceFromId(int Id);
    public Long addService(Service service);
    public void deleteService(int id);
    public void updateService(Service service);

    /*
     * Table Service Persoone
     */
    public List<ServicePersonne> getListPersonneFromServices(int id);

}
