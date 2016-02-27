/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.City;
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
public class CityService implements CityLocal{

    @PersistenceContext(name = "persistence")
    EntityManager entityManager;
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<City> getCitiesByCountryId(int id) {
        String jpa = "Select city FROM City city where city.country.id = :id";
        return entityManager.createQuery(jpa).setParameter("id", id).getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public City getCityById(int id) {
        String jpa = "Select city FROM City city where city.id = :id";
        try {
            return (City) entityManager.createQuery(jpa).setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public City getCityByName(String name) {
        String jpa = "Select city FROM City city where city.name = :name";
        try {
            return (City) entityManager.createQuery(jpa).setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<City> getCities() {
        String jpa = "Select city FROM City city ";
        return entityManager.createQuery(jpa).getResultList();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createCity(City city) {
        entityManager.persist(city);
        entityManager.flush();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateCity(City city) {
        entityManager.merge(city);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteCity(int id) {
        City city = entityManager.find(City.class, id);
        if (city != null) {
            entityManager.remove(city);
        }
    }
}
