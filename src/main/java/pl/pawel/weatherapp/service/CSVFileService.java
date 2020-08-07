package pl.pawel.weatherapp.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import pl.pawel.weatherapp.model.response.CurrentWeatherResponse;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class CSVFileService {

    String[] HEADERS = { "location", "temp"};

    public FileWriter createCSVFile(List<CurrentWeatherResponse> weatherResponses) throws IOException {
        //TODO Prawidłowe kodowanie
        FileWriter out = new FileWriter("weather_new.csv", StandardCharsets.UTF_8);
        System.out.println(out.getEncoding());
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.EXCEL
                .withHeader(HEADERS))) {
            weatherResponses.forEach(currentWeatherResponse -> {
                try {
                    System.out.println(currentWeatherResponse.getName() +" "+  currentWeatherResponse.getMain().getTemp());
                    printer.printRecord(currentWeatherResponse.getName(), currentWeatherResponse.getMain().getTemp());
                } catch (IOException e) {
                    e.printStackTrace();

                }
            });

            out.close();
            return out;
        }

    }
}
