package com.napier.sem;


import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;

public class App {
    private Connection con = null;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                Thread.sleep(30000);
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException | InterruptedException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            }
        }
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public List<Country> getAllCountriesByPopulation() {
        List<Country> countries = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            String strSelect =
                    "SELECT * FROM country " +
                            "ORDER BY Population DESC";
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next()) {
                Country country = new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        rset.getDouble("SurfaceArea"),
                        rset.getInt("IndepYear"),
                        rset.getInt("Population"),
                        rset.getDouble("LifeExpectancy"),
                        rset.getDouble("GNP"),
                        rset.getDouble("GNPOld"),
                        rset.getString("LocalName"),
                        rset.getString("GovernmentForm"),
                        rset.getString("HeadOfState"),
                        rset.getInt("Capital"),
                        rset.getString("Code2")
                );
                countries.add(country);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by population");
        }
        return countries;
    }
    //method for getting all countries in Europe
    public List<Country> getAllCountriesByPopulationInEurope() {
        List<Country> countries = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            String strSelect =
                    "SELECT * FROM country WHERE Continent = 'Europe' " +
                            "ORDER BY Population DESC";
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next()) {
                Country country = new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        rset.getDouble("SurfaceArea"),
                        rset.getInt("IndepYear"),
                        rset.getInt("Population"),
                        rset.getDouble("LifeExpectancy"),
                        rset.getDouble("GNP"),
                        rset.getDouble("GNPOld"),
                        rset.getString("LocalName"),
                        rset.getString("GovernmentForm"),
                        rset.getString("HeadOfState"),
                        rset.getInt("Capital"),
                        rset.getString("Code2")
                );
                countries.add(country);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by population");
        }
        return countries;
    }

    //method for getting all countries in Europe
    public List<Country> getAllCountriesByPopulationInCaribbean() {
        List<Country> countries = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            String strSelect =
                    "SELECT * FROM country WHERE Region = 'caribbean' " +
                            "ORDER BY Population DESC";
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next()) {
                Country country = new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        rset.getDouble("SurfaceArea"),
                        rset.getInt("IndepYear"),
                        rset.getInt("Population"),
                        rset.getDouble("LifeExpectancy"),
                        rset.getDouble("GNP"),
                        rset.getDouble("GNPOld"),
                        rset.getString("LocalName"),
                        rset.getString("GovernmentForm"),
                        rset.getString("HeadOfState"),
                        rset.getInt("Capital"),
                        rset.getString("Code2")
                );
                countries.add(country);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by population");
        }
        return countries;
    }
    public List<City> getAllCityByPopulation() {
        List<City> cities = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            String strSelect =
                    "SELECT * FROM city" +
                            "ORDER BY Population DESC";
            ResultSet rset1 = stmt.executeQuery(strSelect);
            while (rset1.next()) {
                City city = new City(

                        rset1.getInt("Id"),
                        rset1.getString("Name"),
                        rset1.getString("CountryCode"),
                        rset1.getString("District"),
                        rset1.getInt("Population")

                );
                cities.add(city);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities by population");
        }
        return cities;
    }

    public List<City> getAllCityByPopulationInMicronesia() {
        List<City> cities = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            String strSelect =
                    "SELECT * FROM city WHERE Reigon = 'Micronesia'" +
                            "ORDER BY Population DESC";
            ResultSet rset1 = stmt.executeQuery(strSelect);
            while (rset1.next()) {
                City city = new City(

                        rset1.getInt("Id"),
                        rset1.getString("Name"),
                        rset1.getString("CountryCode"),
                        rset1.getString("District"),
                        rset1.getInt("Population")

                );
                cities.add(city);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities by population");
        }
        return cities;
    }

    public static void main(String[] args) {
        App app = new App();
        app.connect();
      //List<Country> countries = app.getAllCountriesByPopulationInCaribbean();
      //System.out.println("Population of Country organised largest to smallest in Caribbean");
      // for (Country country : countries) {
      //         System.out.println(country.name + " - Population: " + country.population);
      // }
        List<City> cities = app.getAllCityByPopulationInMicronesia();
        System.out.println("Population of City organised largest to smallest in Micronesia");
        for (City city : cities) {


            System.out.println(city.name + " - Population: " + city.population);
        }

        app.disconnect();
    }
}
