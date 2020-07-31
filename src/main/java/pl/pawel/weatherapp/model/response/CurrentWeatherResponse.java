package pl.pawel.weatherapp.model.response;

import org.hibernate.annotations.Cascade;
import org.springframework.context.annotation.Scope;
import pl.pawel.weatherapp.model.*;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Scope("request")
public class CurrentWeatherResponse {

    public CurrentWeatherResponse() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Resource(name = "coordinate")
    @OneToOne(cascade = CascadeType.ALL)
    private Coordinate coord;
    @OneToMany(cascade = CascadeType.ALL)
    List<Weather> weather;
    String base;
    @OneToOne(cascade = CascadeType.ALL)
    Main main;
    Long visibility;
    @OneToOne(cascade = CascadeType.ALL)
    Wind wind;
    @OneToOne(cascade = CascadeType.ALL)
    Clouds clouds;
    Long dt;
    @OneToOne(cascade = CascadeType.ALL)
    Sys sys;
    Long timezone;

    String name;
    int cod;
    String convertedDate;

    public CurrentWeatherResponse(CurrentWeatherResponse weatherResponse) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coordinate getCoord() {
        return coord;
    }

    public void setCoord(Coordinate coord) {
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

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Long getTimezone() {
        return timezone;
    }

    public void setTimezone(Long timezone) {
        this.timezone = timezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
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
                "id=" + id +
                ", coord=" + coord +
                ", weather=" + weather +
                ", base='" + base + '\'' +
                ", main=" + main +
                ", visibility=" + visibility +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", dt=" + dt +
                ", sys=" + sys +
                ", timezone=" + timezone +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                ", convertedDate='" + convertedDate + '\'' +
                '}';
    }
}
