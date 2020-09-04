package pl.pawel.weatherapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pawel.weatherapp.model.response.CurrentWeatherResponse;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Data
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long weather_id;
    private Long id;
    private String main;
    private String description;
    private String icon;


    public Long getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(Long weather_id) {
        this.weather_id = weather_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
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

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
