package pl.pawel.weatherapp.model.response;

import lombok.Getter;
import org.hibernate.annotations.ManyToAny;
import pl.pawel.weatherapp.model.*;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.Arrays;

@Entity
public class CurrentWeatherResponse {

    public CurrentWeatherResponse() {
    }
    @Id
    Long id;
    @Resource(name = "coordinate")
    @OneToOne
    private Coordinate coord;
    @ManyToOne
    Weather[] weather;
    String base;
    @OneToOne
    Main main;
    Long visibility;
    @OneToOne
    Wind wind;
    @OneToOne
    Clouds clouds;
    Long dt;
    @OneToOne
    Sys sys;
    Long timezone;

    String name;
    int cod;
    String convertedDate;

    public Coordinate getCoord() {
        return coord;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public Long getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Long getDt() {
        return dt;
    }

    public Sys getSys() {
        return sys;
    }

    public Long getTimezone() {
        return timezone;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }

    public String getConvertedDate() {
        return convertedDate;
    }

    public void setConvertedDate(String convertedDate) {
        this.convertedDate = convertedDate;
    }

    @Override
    public String toString() {
        return "CurrentWeatherResponse{" +
                "coord=" + coord +
                ", weather=" + Arrays.toString(weather) +
                ", base='" + base + '\'' +
                ", main=" + main +
                ", visibility=" + visibility +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", dt=" + dt +
                ", sys=" + sys +
                ", timezone=" + timezone +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                '}';
    }
}
