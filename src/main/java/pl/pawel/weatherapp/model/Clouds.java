package pl.pawel.weatherapp.model;

import lombok.NoArgsConstructor;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
public class Clouds {
    @Id
    Long id;
    @Column(name = "all_clouds")
    int all;

    @Override
    public String toString() {
        return "Clouds{" +
                "all=" + all +
                '}';
    }
}
