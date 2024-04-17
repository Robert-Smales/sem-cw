package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);

    }
    @Test
    void testGetAllCountriesByPopulation() {
        List<Country> countries = app.getAllCountriesByPopulation();

        // Print the first few countries as per the expected results
        int count = 0;
        for (Country country : countries) {
            System.out.println(country.getName() + " - Population: " + country.getPopulation());
            count++;
            if (count == 3) // Print only the first three countries
                break;
        }
    }
    @Test
    void testgetAllCountriesByPopulationInEurope() {
        List<Country> countries = app.getAllCountriesByPopulationInEurope();

        // Print the first few countries as per the expected results
        int count = 0;
        for (Country country : countries) {
            System.out.println(country.getName() + " - Population: " + country.getPopulation());
            count++;
            if (count == 5) // Print only the first five countries
                break;
        }

    }
    @Test
    void testgetAllCountriesByPopulationInCaribbean() {
        List<Country> countries = app.getAllCountriesByPopulationInCaribbean();

        // Print the first few countries as per the expected results
        int count = 0;
        for (Country country : countries) {
            System.out.println(country.getName() + " - Population: " + country.getPopulation());
            count++;
            if (count == 5) // Print only the first five countries
                break;
        }

    }
    @Test
    void testgetAllCityByPopulation() {
        List<City> cities = app.getAllCityByPopulation();

        // Print the first few countries as per the expected results
        int count = 0;
        for (City city : cities) {
            System.out.println(city.getName() + " - Population: " + city.getPopulation());
            count++;
            if (count == 5) // Print only the first five cities
                break;
        }

    }
    @Test
    public void testGetTopNPopulatedCitiesInContinent() {

        String continent = "Europe"; // Set the continent for testing
        int[] nValues = {5, 10, 20}; // Define different values of N for testing

        for (int n : nValues) {
            List<CityWithContinent> result = app.getTopNPopulatedCitiesInContinent(continent, n);
            System.out.println("Top " + n + " populated cities in " + continent + ":");
            for (CityWithContinent city : result) {
                System.out.println(city.getCityName() + " - Population: " + city.getPopulation() + " - Continent: " + city.getContinent());
            }

        }
    }
}

