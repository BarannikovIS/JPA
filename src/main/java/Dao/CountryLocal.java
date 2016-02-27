/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Country;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Иван
 */
@Local
public interface CountryLocal {
    void createCountry(Country country);
    void updateCountry(Country country);
    void deleteCountry(int id);

    Country getCountryById(int id);
    Country getCountryByName(String name);
    List<Country> getCountries();
}
