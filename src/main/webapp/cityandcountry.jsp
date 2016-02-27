<%-- 
    Document   : cityandcountry
    Created on : 27.02.2016, 23:56:46
    Author     : Иван
--%>

<%@page import="java.util.List"%>
<%@page import="Entity.Country"%>
<%@page import="Entity.City"%>
<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Cityandcountry</title>
    </head>
    <body>
        <form action="/JPACountryAndCity/cityandcountry" method="post">
            
            All countries:
            <table border="1px">
                <tr>
                    <td>id country</td>
                    <td>name country</td>
                </tr>
                <%
                    List<Country> allCountries = (List<Country>) request.getSession().getAttribute("allCountries");
                    for (int i = 0; i < allCountries.size(); i++) {
                %>
                <tr>
                    <td>
                        <%=allCountries.get(i).getId()%>
                    </td>
                    <td>
                        <%=allCountries.get(i).getName()%>
                    </td>
                </tr>
                <%}%>
            </table>
            All cities:
            <table border="1px">
                <tr>
                    <td>id city</td>
                    <td>name city</td>
                    <td>id country</td>
                </tr>
                <%
                    List<City> allCities = (List<City>) request.getSession().getAttribute("allCities");
                    for (int i = 0; i < allCities.size(); i++) {
                %>
                <tr>
                    <td>
                        <%=allCities.get(i).getId()%>
                    </td>
                    <td>
                        <%=allCities.get(i).getName()%>
                    </td>
                    <td>
                        <%=allCities.get(i).getCountry().getId()%>
                    </td>
                </tr>
                <%}%>
            </table>
            
            <br> 
            <br>

            country ID (for update):<input type="text" name="countryId" id="countryId"><br>
            country Name(for insert and update:<input type="text" name="countryName" id="countryName"><br>
            <input type="submit" name="insertCountry" value="insert">
            <input type="submit" name="updateCountry" value="update">
            <br> 
            <br>
            
            city ID (for update):<input type="text" name="cityId" id="cityId"><br>
            city Name (for insert and update:<input type="text" name="cityName" id="cityName"><br>
            country id (for insert and update):<input type="text" name="countryIdForCity" id="countryIdForCity"><br>
            <input type="submit" name="insertCity" value="insert">
            <input type="submit" name="updateCity" value="update">
            <br>
            <br>

            Delete COUNTRY ID:<input type="text" name="deleteCountryId" id="deleteCountryId"><br>
            <input type="submit" name="deleteCountry" value="delete">
            <br>
            <br>

            Delete CITY ID:<input type="text" name="deleteCityId" id="deleteCityId"><br>
            <input type="submit" name="deleteCity" value="delete"><br>
            <br>
            <br>

            <input type="text" name="search" id="search" placeholder="search option"><br>
            <input type="submit" name="getCityByName" value="City By Name">
            <input type="submit" name="getCityById" value="City By Id">
            <input type="submit" name="getCountryByName" value="Country By Name">
            <input type="submit" name="getCountryById" value="Country By Id">

            <%
                City city = (City) request.getAttribute("city");
                Country country = (Country) request.getAttribute("country");
                if (city != null) {%>
            City:
            <%=city.getId()%>
            <%=city.getName()%>
            <%=city.getCountry().getId()%>
            <%}%>
            <% if (country != null) {%>
            Country:
            <%= country.getId()%>
            <%= country.getName()%>

            <%}%>

        </form>
    </body>
</html>
