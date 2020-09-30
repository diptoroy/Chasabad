package com.atcampus.chasabad.Model.WeatherData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataModel {
//    @SerializedName("coord")
//    public Coord coord;
//    @SerializedName("weather")
//    public List<Weather> weathers = new ArrayList<>();
//    @SerializedName("main")
//    public Main main;
//    @SerializedName("wind")
//    public Wind wind;
//
//
//    public WeatherDataModel(Coord coord, List<Weather> weathers, Main main, Wind wind) {
//        this.coord = coord;
//        this.weathers = weathers;
//        this.main = main;
//        this.wind = wind;
//    }
//
//
//    public Coord getCoord() {
//        return coord;
//    }
//
//    public void setCoord(Coord coord) {
//        this.coord = coord;
//    }
//
//    public List<Weather> getWeathers() {
//        return weathers;
//    }
//
//    public void setWeathers(List<Weather> weathers) {
//        this.weathers = weathers;
//    }
//
//    public Main getMain() {
//        return main;
//    }
//
//    public void setMain(Main main) {
//        this.main = main;
//    }
//
//    public Wind getWind() {
//        return wind;
//    }
//
//    public void setWind(Wind wind) {
//        this.wind = wind;
//    }

    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("visibility")
    @Expose
    private Long visibility;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("dt")
    @Expose
    private Long dt;
    @SerializedName("sys")
    @Expose
    private Long timezone;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private Long cod;

    public WeatherDataModel(Coord coord, List<Weather> weather, String base, Main main, Long visibility, Wind wind, Long dt, Long timezone, Long id, String name, Long cod) {
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.dt = dt;
        this.timezone = timezone;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Long getVisibility() {
        return visibility;
    }

    public void setVisibility(Long visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }


    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }


    public Long getTimezone() {
        return timezone;
    }

    public void setTimezone(Long timezone) {
        this.timezone = timezone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCod() {
        return cod;
    }

    public void setCod(Long cod) {
        this.cod = cod;
    }


}

