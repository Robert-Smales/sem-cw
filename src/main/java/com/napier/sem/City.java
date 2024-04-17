package com.napier.sem;


public class City {

        public int id;
        public String name;
        public String countrycode;
        public String district;

        public int population;
        // Add more fields as needed

        // Constructor
        public City(int id, String name, String countrycode, String district, int population) {

            this.id = id;
            this.name = name;
            this.countrycode = countrycode;
            this.district = district;
            this.population = population;

        }
    // Getter method for name
    public String getName() {
        return name;
    }

    // Getter method for population
    public int getPopulation() {
        return population;
    }


}

