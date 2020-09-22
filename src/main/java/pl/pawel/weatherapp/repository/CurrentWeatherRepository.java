package pl.pawel.weatherapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pawel.weatherapp.model.response.CurrentWeatherResponse;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CurrentWeatherRepository extends JpaRepository<CurrentWeatherResponse, Long> {

    Optional<CurrentWeatherResponse> findCurrentWeatherResponseByName(String name);

    @Query(nativeQuery = true, value =
            "select cwr.name as name from CURRENT_WEATHER_RESPONSE cwr")
    Set<String> findAllCities();
}
