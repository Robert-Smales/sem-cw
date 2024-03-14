package com.napier.sem;

import java.util.List;

public class TestCases {
    public static void main(String[] args) {
        App app = new App();
        app.connect();
        // Test case: Retrieving cities by population in Micronesia
        List<City> cities = app.getAllCityByPopulationInMicronesia();
        // Validate the result
        if (cities != null && !cities.isEmpty()) {
            System.out.println("Cities in Micronesia ordered by population:");
            for (City city : cities) {
                System.out.println(city.name + " - Population: " + city.population);
            }
        } else {
            System.out.println("No cities found in Micronesia or an error occurred while fetching data.");
        }
        app.disconnect();
    }
}
