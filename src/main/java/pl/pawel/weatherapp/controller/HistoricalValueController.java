package pl.pawel.weatherapp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pawel.weatherapp.model.response.CurrentWeatherResponse;
import pl.pawel.weatherapp.repository.CurrentWeatherRepository;
import pl.pawel.weatherapp.service.CurrentWeatherResponseService;
import pl.pawel.weatherapp.service.ExcelFileExporter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

@Controller
@RequestMapping("/historic")
public class HistoricalValueController {

    CurrentWeatherRepository currentWeatherRepository;
    CurrentWeatherResponseService currentWeatherResponseService;
    ExcelFileExporter excelFileExporter;

    public HistoricalValueController(CurrentWeatherRepository currentWeatherRepository, CurrentWeatherResponseService currentWeatherResponseService){
        this.currentWeatherRepository = currentWeatherRepository;
        this.currentWeatherResponseService = currentWeatherResponseService;

    }

    @PostMapping("/save")
    public void exportToExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<String> names = Arrays.asList(request.getParameterValues("cities"));
        final String filename = "weather_new.xlsx";
        List<CurrentWeatherResponse> currentWeatherResponses = currentWeatherResponseService.findAllByCities(names);

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
