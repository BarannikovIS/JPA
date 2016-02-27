/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.City;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Иван
 */
@Local
public interface CityLocal {
    void createCity(City city);
    void updateCity(City city);
    void deleteCity(int id);

    List<City> getCities();
    City getCityById(int id);
    City getCityByName(String name);
    List<City> getCitiesByCountryId(int id);
}
