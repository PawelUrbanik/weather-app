package pl.pawel.weatherapp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pawel.weatherapp.model.response.CurrentWeatherResponse;
import pl.pawel.weatherapp.repository.CurrentWeatherRepository;
import pl.pawel.weatherapp.service.ExcelFileExporter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/historic")
public class HistoricalValueController {

    CurrentWeatherRepository currentWeatherRepository;
    ExcelFileExporter excelFileExporter;

    public HistoricalValueController(CurrentWeatherRepository currentWeatherRepository){
        this.currentWeatherRepository = currentWeatherRepository;

    }

    @PostMapping("/save")
    public void exportToExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {


        System.out.println(Arrays.toString(request.getParameterValues("cities")));

        final String filename = "weather_new.xlsx";
        //TODO zmiana z findAll na find by name
        List<CurrentWeatherResponse> currentWeatherResponses = currentWeatherRepository.findAll();
        response.setContentType("application/octet-stream");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        ExcelFileExporter excelFileExporter = new ExcelFileExporter();

        excelFileExporter.export(response, currentWeatherResponses);

    }




    @GetMapping()
    public String historicalValue(Model model){
        Set<String> cities = currentWeatherRepository.findAllCities();
        model.addAttribute("cities", cities);
        return "save";
    }
}
