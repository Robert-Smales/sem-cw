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
}

