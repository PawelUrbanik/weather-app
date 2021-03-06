package pl.pawel.weatherapp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pawel.weatherapp.model.response.CurrentWeatherResponse;
import pl.pawel.weatherapp.service.CurrentWeatherResponseService;
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

    final String FILENAME = "weather_new.xlsx";

    CurrentWeatherResponseService currentWeatherResponseService;

    public HistoricalValueController(CurrentWeatherResponseService currentWeatherResponseService) {
        this.currentWeatherResponseService = currentWeatherResponseService;
    }

    @PostMapping("/save")
    public void exportToExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<String> names = Arrays.asList(request.getParameterValues("cities"));
        String startDatetime = request.getParameter("startTime");
        String endDatetime = request.getParameter("endTime");
        List<CurrentWeatherResponse> currentWeatherResponses = null;


        if (!startDatetime.isEmpty() && !endDatetime.isEmpty()) {
            currentWeatherResponses = currentWeatherResponseService.findAllByCitiesBetwenStartDateAndEndDate(names, startDatetime, endDatetime);
        } else {
            currentWeatherResponses = currentWeatherResponseService.findAllByCities(names);
        }


        response.setContentType("application/octet-stream");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + FILENAME + "\"");
        ExcelFileExporter excelFileExporter = new ExcelFileExporter();
        excelFileExporter.export(response, currentWeatherResponses);
    }


    @GetMapping()
    public String historicalValue(Model model) {
        Set<String> cities = currentWeatherResponseService.findAllCities();
        model.addAttribute("cities", cities);
        return "save";
    }
}
