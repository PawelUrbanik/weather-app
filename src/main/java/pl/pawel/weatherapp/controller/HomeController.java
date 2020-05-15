package pl.pawel.weatherapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.pawel.weatherapp.model.response.CurrentWeatherResponse;
import pl.pawel.weatherapp.service.CurrentWeatherResponseService;
import pl.pawel.weatherapp.service.DataFromOWMService;


@Controller
public class HomeController {

    DataFromOWMService dataFromOWMService;
    CurrentWeatherResponseService currentWeatherResponseService;

    public HomeController(DataFromOWMService dataFromOWMService, CurrentWeatherResponseService currentWeatherResponseService) {
        this.dataFromOWMService = dataFromOWMService;
        this.currentWeatherResponseService = currentWeatherResponseService;
    }

    @GetMapping("/")
    public String home(Model model){
        CurrentWeatherResponse currentWeatherResponse  = dataFromOWMService.getDataFromApi("Warszawa");
        currentWeatherResponseService.formatDate(currentWeatherResponse);
        currentWeatherResponseService.addCurrentWeatherResponse(currentWeatherResponse);
        model.addAttribute("weather", currentWeatherResponse);
        return "home";
    }


    @PostMapping("/")
    public String getOneLocation(String cityName, Model model)
    {
        CurrentWeatherResponse currentWeatherResponse = dataFromOWMService.getDataFromApi(cityName);
        if (currentWeatherResponse==null) return "redirect:/";
        currentWeatherResponseService.formatDate(currentWeatherResponse);
        currentWeatherResponseService.addCurrentWeatherResponse(currentWeatherResponse);
        model.addAttribute("weather", currentWeatherResponse);
        return  "home";
    }

}
