package pl.pawel.weatherapp.model;

public class Weather {

    Long id;
    String main;
    String description;
    String icon;

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
