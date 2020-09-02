package pl.pawel.weatherapp.service;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import pl.pawel.weatherapp.model.response.CurrentWeatherResponse;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelFileExporter {


    private String[] HEADERS = { "Lokalizacja", "Temperatura"};
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFCellStyle xssfCellStyle;


    public ExcelFileExporter() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("weather");
        xssfCellStyle = workbook.createCellStyle();

    }

    private void writeHeadRow()
    {
        Row row =sheet.createRow(0);

        sheet.setColumnWidth(0, 5000);
        sheet.setColumnWidth(1, 5000);

        xssfCellStyle.setBorderBottom(BorderStyle.MEDIUM);
        xssfCellStyle.setBorderLeft(BorderStyle.MEDIUM);
        xssfCellStyle.setBorderRight(BorderStyle.MEDIUM);
        xssfCellStyle.setBorderTop(BorderStyle.MEDIUM);

        XSSFFont xssfFont = workbook.createFont();
        xssfFont.setBold(true);
        xssfCellStyle.setFont(xssfFont);

        Cell cell = row.createCell(0);
        cell.setCellStyle(xssfCellStyle);
        cell.setCellValue(HEADERS[0]);

        cell = row.createCell(1);
        cell.setCellStyle(xssfCellStyle);
        cell.setCellValue(HEADERS[1]);

        xssfFont.setBold(false);
    }

    private void writeData(List<CurrentWeatherResponse> weatherResponses)
    {
        xssfCellStyle.setBorderBottom(BorderStyle.THIN);
        xssfCellStyle.setBorderLeft(BorderStyle.THIN);
        xssfCellStyle.setBorderRight(BorderStyle.THIN);
        xssfCellStyle.setBorderTop(BorderStyle.THIN);
        int countRow = 1;
         for (CurrentWeatherResponse weatherResponse: weatherResponses)
         {
             Row row = sheet.createRow(countRow);

             Cell cell = row.createCell(0);
             cell.setCellStyle(xssfCellStyle);
             cell.setCellValue(weatherResponse.getName());

             cell = row.createCell(1);
             cell.setCellStyle(xssfCellStyle);
             cell.setCellValue(weatherResponse.getMain().getTemp());

             countRow++;
         }

    }

    public void export(HttpServletResponse response, List<CurrentWeatherResponse> weatherResponses) throws IOException {
        writeHeadRow();
        writeData(weatherResponses);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }


}
