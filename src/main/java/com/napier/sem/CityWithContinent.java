package com.napier.sem;

public class CityWithContinent {
    private String cityName;
    private int population;
    private String continent;

    public CityWithContinent(String cityName, int population, String continent){

        this.cityName = cityName;
        this.population = population;
        this.continent = continent;
    }

    public String getCityName(){
        return cityName;
    }
    public int getPopulation(){
        return population;
    }

    public String getContinent(){
        return continent;
    }
}
