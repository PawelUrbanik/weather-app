package pl.pawel.weatherapp.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.pawel.weatherapp.config.KeyConfiguration;
import pl.pawel.weatherapp.model.response.CurrentWeatherResponse;

@Service
public class DataFromOWMService {

    KeyConfiguration keyConfiguration;
    private Gson gson;
    RestTemplate restTemplate;

    public DataFromOWMService(KeyConfiguration keyConfiguration) {
        this.keyConfiguration = keyConfiguration;
        this.gson = new Gson();
        this.restTemplate = new RestTemplate();
    }

    public CurrentWeatherResponse getDataFromApi(String location)
    {
        String url  = "https://api.openweathermap.org/data/2.5/weather?q="+location+"&appid="+ keyConfiguration.getKeyValue() + "&units=metric&lang=pl";
        String responseWeather = null;
        try {
            responseWeather = restTemplate.getForObject(url, String.class);
        }catch (HttpClientErrorException e)
        {
            System.err.println(e.getMessage());
        }
        CurrentWeatherResponse currentWeatherResponse = gson.fromJson(responseWeather, CurrentWeatherResponse.class);
        return currentWeatherResponse;
    }
}
