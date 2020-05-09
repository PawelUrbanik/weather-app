package pl.pawel.weatherapp.model;

public class Sys {
    int type;
    Long id;
    Double message;
    String country;
    Long sunrise;
    Long sunset;

    @Override
    public String toString() {
        return "Sys{" +
                "type=" + type +
                ", id=" + id +
                ", message=" + message +
                ", country='" + country + '\'' +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                '}';
    }
}
