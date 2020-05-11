package pl.pawel.weatherapp.controller;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import pl.pawel.weatherapp.config.KeyConfiguration;
import pl.pawel.weatherapp.model.response.CurrentWeatherResponse;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {

    @Autowired
    KeyConfiguration keyConfiguration;

    private Gson gson = new Gson();

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/")
    public String home(Model model){
        CurrentWeatherResponse currentWeatherResponse  = getDataFromApi("Warszawa");
        String date = formatData(currentWeatherResponse);
        model.addAttribute("weather", currentWeatherResponse);
        model.addAttribute("date", date);
        return "home";
    }


    @PostMapping("/")
    public String getOne(String cityName, Model model)
    {
        if (cityName.isEmpty()){
            return "redirect:/";
        }
        CurrentWeatherResponse currentWeatherResponse = getDataFromApi(cityName);
        String date = formatData(currentWeatherResponse);
        model.addAttribute("weather", currentWeatherResponse);
        model.addAttribute("date", date);
        return  "home";
    }


    private CurrentWeatherResponse getDataFromApi(String location)
    {
        String url  = "https://api.openweathermap.org/data/2.5/weather?q="+location+"&appid="+ keyConfiguration.getKeyValue() + "&units=metric&lang=pl";
        String responseWeather = restTemplate.getForObject(url, String.class);
        CurrentWeatherResponse currentWeatherResponse = gson.fromJson(responseWeather, CurrentWeatherResponse.class);
        return currentWeatherResponse;
    }

    private String formatData(CurrentWeatherResponse currentWeatherResponse){
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss");
        String date = Instant.ofEpochSecond(currentWeatherResponse.getDt()).atZone(ZoneId.of("GMT+2")).format(formatter);
        return date;
    }
}
