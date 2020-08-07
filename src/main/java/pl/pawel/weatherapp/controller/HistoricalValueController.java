package pl.pawel.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pawel.weatherapp.model.response.CurrentWeatherResponse;
import pl.pawel.weatherapp.repository.CurrentWeatherRepository;
import pl.pawel.weatherapp.service.CSVFileService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/historic")
public class HistoricalValueController {

    @Autowired
    CurrentWeatherRepository currentWeatherRepository;

    @Autowired
    CSVFileService csvFileService;

    @PostMapping("/save")
    public String saveFile(){
        List<CurrentWeatherResponse> currentWeatherResponses = currentWeatherRepository.findAll();
        currentWeatherResponses.forEach(System.out::println);
        try {

            csvFileService.createCSVFile(currentWeatherResponses);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "save";
    }

    @GetMapping()
    public String historicalValue(){


        return "save";
    }
}
