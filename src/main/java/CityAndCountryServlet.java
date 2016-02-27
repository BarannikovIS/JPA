
import Dao.CityLocal;
import Dao.CityService;
import Dao.CountryLocal;
import Dao.CountryService;
import Entity.City;
import Entity.Country;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Иван
 */
@WebServlet("/cityandcountry")
public class CityAndCountryServlet extends HttpServlet {

    @EJB
    CountryLocal countryService;
    @EJB
    CityLocal cityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("allCountries", countryService.getCountries());
        request.getSession().setAttribute("allCities", cityService.getCities());
        request.getRequestDispatcher("cityandcountry.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("insertCountry") != null) {
            Country country = new Country();
            country.setName(request.getParameter("countryName"));
            countryService.createCountry(country);
        }
        if (request.getParameter("updateCountry") != null) {
            Country country = countryService.getCountryById(Integer.parseInt(request.getParameter("countryId")));
            country.setName(request.getParameter("countryName"));
            countryService.updateCountry(country);
        }
        if (request.getParameter("deleteCountry") != null) {
            countryService.deleteCountry(Integer.parseInt(request.getParameter("deleteCountryId")));
        }

        if (request.getParameter("insertCity") != null) {
            City city = new City();
            city.setName(request.getParameter("cityName"));
            city.setCountry(countryService.getCountryById(Integer.parseInt(request.getParameter("countryIdForCity"))));
            cityService.createCity(city);
        }
        if (request.getParameter("updateCity") != null) {
            City city = cityService.getCityById(Integer.parseInt(request.getParameter("cityId")));
            city.setName(request.getParameter("cityName"));
            city.setCountry(countryService.getCountryById(Integer.parseInt(request.getParameter("countryIdForCity"))));
            cityService.updateCity(city);
        }
        if (request.getParameter("deleteCity") != null) {
            cityService.deleteCity(Integer.parseInt(request.getParameter("deleteCityId")));
        }

        if (request.getParameter("getCityByName") != null) {
            request.setAttribute("city", cityService.getCityByName(request.getParameter("search")));
        }
        if (request.getParameter("getCityById") != null) {
            request.setAttribute("city", cityService.getCityById(Integer.parseInt(request.getParameter("search"))));
        }
        if (request.getParameter("getCountryByName") != null) {
            request.setAttribute("country", countryService.getCountryByName(request.getParameter("search")));
        }
        if (request.getParameter("getCountryById") != null) {
            request.setAttribute("country", countryService.getCountryById(Integer.parseInt(request.getParameter("search"))));
        }
        request.getRequestDispatcher("cityandcountry.jsp").forward(request, response);
    }
}
