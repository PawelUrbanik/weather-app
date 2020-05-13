package pl.pawel.weatherapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pawel.weatherapp.model.response.CurrentWeatherResponse;

@Repository
public interface CurrentWeatherRepository extends JpaRepository<CurrentWeatherResponse, Long> {
}
