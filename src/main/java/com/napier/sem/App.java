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
    /**
     *  method for getting all countries in Europe
     */
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

    //method for getting all countries by population in a reigon
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
    // All the cities in a world organised by largest population to smallest.
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

    // All the cities in a continent organised by largest population to smallest.
    public List<CityWithContinent> getAllCityByPopulationAndContinent() {
        List<CityWithContinent> citiesWithContinent = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            String strSelect =
                    "SELECT c.Name AS CityName, co.Continent,c.Population " +
                            "FROM city c " +
                            "JOIN country co ON c.CountryCode = co.Code " +
                            "ORDER BY co.Continent, c.Population DESC";

            ResultSet rset2 = stmt.executeQuery(strSelect);
            while (rset2.next()) {
                CityWithContinent city = new CityWithContinent(

                        rset2.getString("CityName"),
                        rset2.getInt("Population"),
                        rset2.getString("Continent")

                );
                citiesWithContinent.add(city);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities of continent by population");
        }
        return citiesWithContinent;
    }

    public void printCitiesByPopulationAndContinent(List<CityWithContinent> citiesWithContinent) {


        System.out.println("Cities by Population and Continent:");
        System.out.println("===================================");

        for (CityWithContinent city : citiesWithContinent) {
            System.out.println("City: " + city.getCityName());
            System.out.println("Population: " + city.getPopulation());
            System.out.println("Continent: " + city.getContinent());
            System.out.println("-----------------------------------");
        }
    }




    // all the cities ordered from biggest to smallest by population in a reigon
    public List<City> getAllCityByPopulationInMicronesia() {
        List<City> cities = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            String strSelect =
                    "SELECT * FROM city WHERE District = 'micronesia'" +
                            "ORDER BY Population DESC";
            ResultSet rset3 = stmt.executeQuery(strSelect);
            while (rset3.next()) {
                City city = new City(

                        rset3.getInt("Id"),
                        rset3.getString("Name"),
                        rset3.getString("CountryCode"),
                        rset3.getString("District"),
                        rset3.getInt("Population")

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

        List<CityWithContinent> citiesWithContinent = app.getAllCityByPopulationAndContinent();
        // Call the printCitiesByPopulationAndContinent method
        app.printCitiesByPopulationAndContinent(citiesWithContinent);


        app.disconnect();
    }
}
