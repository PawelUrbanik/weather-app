package pl.pawel.weatherapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
public class Clouds {
    @Id
    @GeneratedValue()
    private Long id;
    @Column(name = "all_clouds")
    private int all;

    @Override
    public String toString() {
        return "Clouds{" +
                "all=" + all +
                '}';
    }
}
