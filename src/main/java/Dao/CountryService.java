/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.City;
import Entity.Country;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Иван
 */
@Stateless
public class CountryService implements CountryLocal {
    
    @PersistenceContext(name = "persistence")
    EntityManager entityManager;
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Country> getCountries() {
        String jpa = "Select c FROM Country c";
        return entityManager.createQuery(jpa).getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Country getCountryById(int id) {
        String jpa = "Select c FROM Country c where c.id = :id";
        try {
            return (Country) entityManager.createQuery(jpa).setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Country getCountryByName(String name) {
        String jpa = "Select c FROM Country c where c.name = :name";
        try {
            return (Country) entityManager.createQuery(jpa).setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createCountry(Country country) {
        entityManager.persist(country);
        entityManager.flush();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateCountry(Country country) {
        entityManager.merge(country);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteCountry(int id) {
        Country country = entityManager.find(Country.class, id);
        if (country != null) {
            List<City> cities = getCitiesByCountryId(id);
            for (int i = 0; i < cities.size(); i++) {
                entityManager.remove(cities.get(i));
            }
            entityManager.remove(country);
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<City> getCitiesByCountryId(int id) {
        String jpa = "Select city FROM City city where city.country.id = :id";
        return entityManager.createQuery(jpa).setParameter("id", id).getResultList();
    }
}
