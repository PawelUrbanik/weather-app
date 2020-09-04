package pl.pawel.weatherapp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pawel.weatherapp.model.response.CurrentWeatherResponse;
import pl.pawel.weatherapp.repository.CurrentWeatherRepository;
import pl.pawel.weatherapp.service.ExcelFileExporter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/historic")
public class HistoricalValueController {

    CurrentWeatherRepository currentWeatherRepository;
    ExcelFileExporter excelFileExporter;

    public HistoricalValueController(CurrentWeatherRepository currentWeatherRepository){
        this.currentWeatherRepository = currentWeatherRepository;

    }

    @GetMapping("/save")
    public void exportToExcel(HttpServletResponse response) throws IOException {

        final String filename = "weather_new.xlsx";
        List<CurrentWeatherResponse> currentWeatherResponses = currentWeatherRepository.findAll();
        response.setContentType("application/octet-stream");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        ExcelFileExporter excelFileExporter = new ExcelFileExporter();

        excelFileExporter.export(response, currentWeatherResponses);

    }




    @GetMapping()
    public String historicalValue(){
        return "save";
    }
}
