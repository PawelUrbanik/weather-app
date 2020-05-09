package pl.pawel.weatherapp.controller;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import pl.pawel.weatherapp.config.KeyConfiguration;
import pl.pawel.weatherapp.model.Weather;
import pl.pawel.weatherapp.model.response.CurrentWeatherResponse;

import java.net.URI;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    KeyConfiguration keyConfiguration;

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/")
    public String home(Model model){
        Gson gson = new Gson();
        String url  = "https://api.openweathermap.org/data/2.5/weather?q=Warsaw&appid="+ keyConfiguration.getKeyValue() + "&units=metric";
        String responseWeather = restTemplate.getForObject(url, String.class);
        CurrentWeatherResponse currentWeatherResponse = gson.fromJson(responseWeather, CurrentWeatherResponse.class);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss");
        String date = Instant.ofEpochSecond(currentWeatherResponse.getDt()).atZone(ZoneId.of("GMT+2")).format(formatter);
        model.addAttribute("weather", currentWeatherResponse);
        model.addAttribute("czas", date);
        return "home";
    }
}
