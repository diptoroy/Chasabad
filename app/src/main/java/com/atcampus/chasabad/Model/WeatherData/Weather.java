package com.atcampus.chasabad.Model.WeatherData;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("description")
    public String description;
    @SerializedName("icon")
    public String icon;

    public Weather(String description, String icon) {
        this.description = description;
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
