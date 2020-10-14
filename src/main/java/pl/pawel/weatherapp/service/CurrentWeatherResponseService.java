package pl.pawel.weatherapp.service;

import org.springframework.stereotype.Service;
import pl.pawel.weatherapp.model.response.CurrentWeatherResponse;
import pl.pawel.weatherapp.repository.CurrentWeatherRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class CurrentWeatherResponseService {

    private CurrentWeatherRepository currentWeatherRepository;

    public CurrentWeatherResponseService(CurrentWeatherRepository currentWeatherRepository) {
        this.currentWeatherRepository = currentWeatherRepository;
    }


    public void addCurrentWeatherResponse(CurrentWeatherResponse weatherResponse) {
        Optional<CurrentWeatherResponse> lastValue = currentWeatherRepository.findFirstByNameOrderByConvertedDateDesc(weatherResponse.getName());
        if (lastValue.isPresent() && weatherResponse.getDt().equals(lastValue.get().getDt())) {
//            System.out.println("Not saved");
        }else {
            currentWeatherRepository.save(weatherResponse);
//            System.out.println("Saved");
        }
    }

    public void formatDate(CurrentWeatherResponse currentWeatherResponse) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss");
        String date = Instant.ofEpochSecond(currentWeatherResponse.getDt()).atZone(ZoneId.of("GMT+2")).format(formatter);
        currentWeatherResponse.setConvertedDate(date);
    }

    public List<CurrentWeatherResponse> findAllByCities(List<String> names) {
        List<CurrentWeatherResponse> resultSet = new ArrayList<>();
        names.forEach(name -> {
            Optional<List<CurrentWeatherResponse>> response = currentWeatherRepository.findAllCurrentWeatherResponseByName(name);
            response.ifPresent(e -> resultSet.addAll(response.get()));
        });

        return resultSet;
    }

    public Set<String> findAllCities() {
        return currentWeatherRepository.findAllCities();
    }

    public List<CurrentWeatherResponse> findAllByCitiesBetwenStartDateAndEndDate(List<String> names, String startDatetime, String endDatetime) {
        LocalDateTime localStartDateTime = LocalDateTime.parse(startDatetime);
        Timestamp startTimestamp = Timestamp.valueOf(localStartDateTime);
        LocalDateTime localEndDateTime = LocalDateTime.parse(endDatetime);
        Timestamp endTimestamp = Timestamp.valueOf(localEndDateTime);
        List<CurrentWeatherResponse> resultSet = new ArrayList<>();
        names.forEach(name -> {
            Optional<List<CurrentWeatherResponse>> responseFromDB = currentWeatherRepository.findAllByNameAndDtBetween(name, startTimestamp.getTime()/1000L, endTimestamp.getTime()/1000L);
            responseFromDB.ifPresent(e -> resultSet.addAll(responseFromDB.get()));
        });
        resultSet.forEach(System.out::println);
        return resultSet;
    }
}
