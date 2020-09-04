package pl.pawel.weatherapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
@Data
public class Coordinate {

    @Id
    @GeneratedValue()
    private Long id;

    private Double lon;
    private Double lat;


    @Override
    public String toString() {
        return "Coordinate{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
