package com.napier.sem;


import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    private Connection con = null;

    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        boolean shouldWait = false;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                if (shouldWait) {
                    // Wait a bit for db to start
                    Thread.sleep(delay);
                }

                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());

                // Let's wait before attempting to reconnect
                shouldWait = true;
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
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
                    "SELECT * FROM city " +
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

    public List<CityWithContinent> getTopNPopulatedCitiesInContinent(String continent, int n) {
        List<CityWithContinent> topNPopulatedCitiesInContinent = new ArrayList<>();
        try {
            String strSelect =
                    "SELECT c.Name AS CityName, co.Continent, c.Population " +
                            "FROM city c " +
                            "JOIN country co ON c.CountryCode = co.Code " +
                            "WHERE co.Continent = ? " +
                            "ORDER BY c.Population DESC " +
                            "LIMIT ?";

            PreparedStatement pstmt = con.prepareStatement(strSelect);
            pstmt.setString(1, continent);
            pstmt.setInt(2, n);

            ResultSet rset1 = pstmt.executeQuery();

            while (rset1.next()) {
                CityWithContinent city = new CityWithContinent(
                        rset1.getString("CityName"),
                        rset1.getInt("Population"),
                        rset1.getString("Continent")
                );
                topNPopulatedCitiesInContinent.add(city);
            }

            pstmt.close();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
            System.err.println("Failed to get cities of continent by population");
        } catch (NoSuchElementException e) {
            System.err.println("Invalid value for 'n': " + e.getMessage());
            System.err.println("Failed to get cities of continent by population");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return topNPopulatedCitiesInContinent;
    }

    public void printTopNPopulatedCitiesInContinent(List<CityWithContinent> topNPopulatedCitiesInContinent, String continent) {
        System.out.println("Top " + topNPopulatedCitiesInContinent.size() + " populated cities in " + continent + ":");
        System.out.println("===================================");

        for (CityWithContinent city : topNPopulatedCitiesInContinent) {
            System.out.println("City: " + city.getCityName());
            System.out.println("Population: " + city.getPopulation());
            System.out.println("Continent: " + city.getContinent());
            System.out.println("-----------------------------------");
        }
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

        if (args.length < 1) {
            app.connect("localhost:33060", 10000);
        } else {
            app.connect(args[0], Integer.parseInt(args[1]));
        }

        // Initiate list citiesWithContinent, set value equal to result of getAllCityByPopulationAndContinent() method
        List<CityWithContinent> citiesWithContinent = app.getAllCityByPopulationAndContinent();

        // Call the printCitiesByPopulationAndContinent method and display list, citiesWithContinent
        app.printCitiesByPopulationAndContinent(citiesWithContinent);

        Scanner scanner = new Scanner(System.in);
        System.out.println("How many cities would you like to be displayed in Asia? (top N report)");

        int n;
        try {
            n = scanner.nextInt();
        } catch (NoSuchElementException e) {
            System.err.println("Error: Please provide a valid integer input.");
            // Optionally, you can retry or exit the program
            return;
        }

        // Initiate list, citiesWithContinent1 and set value equal to result of getTopNPopulatedCitiesInContinent() method
        List<CityWithContinent> citiesWithContinent1 = app.getTopNPopulatedCitiesInContinent("Asia", n);

        // Call the printTopNCitiesByPopulationAndContinent method and display list, citiesWithContinent1
        app.printTopNPopulatedCitiesInContinent(citiesWithContinent1, "Asia");

        app.disconnect();
    }
}
