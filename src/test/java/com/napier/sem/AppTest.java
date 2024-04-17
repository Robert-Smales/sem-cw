package com.napier.sem;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

public class AppTest {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
    }

    @Test
    void testPrintCitiesByPopulationAndContinent() {
        // Create a list of CityWithContinent objects to simulate the data
        List<CityWithContinent> cities = new ArrayList<>();
        CityWithContinent city1 = new CityWithContinent("New York", 8537673, "North America");
        CityWithContinent city2 = new CityWithContinent("London", 9304016, "Europe");
        CityWithContinent city3 = new CityWithContinent("Tokyo", 37400068, "Asia");
        cities.add(city1);
        cities.add(city2);
        cities.add(city3);



        // Call the method

        app.printCitiesByPopulationAndContinent(cities);

    }
}

