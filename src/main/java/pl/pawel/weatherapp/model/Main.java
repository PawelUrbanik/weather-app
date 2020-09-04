package pl.pawel.weatherapp.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
public class Main {

    @Id
    @GeneratedValue()
    private Long id;
    private Double temp;
    private Double feels_like;
    private Double temp_min;
    private Double temp_max;
    private int pressure;
    private int humidity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public void setFeels_like(Double feels_like) {
        this.feels_like = feels_like;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Double getTemp() {
        return temp;
    }

    public Double getFeels_like() {
        return feels_like;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public Double getTemp_max() {
        return temp_max;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    @Override
    public String toString() {
        return "Main{" +
                "temp=" + temp +
                ", feels_like=" + feels_like +
                ", temp_min=" + temp_min +
                ", temp_max=" + temp_max +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }
}
