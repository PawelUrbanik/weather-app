package pl.pawel.weatherapp.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyConfiguration {

    @Value("${weather-app.key}")
    private String keyValue;

    public  String getKeyValue(){
        return keyValue;
    }
}
