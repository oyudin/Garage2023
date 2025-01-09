package com.example.garage.service;

import com.example.garage.model.ServiceHistory;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class DocumentService {

    public byte[] generateServiceReport(String title, String instructions, String[][] data) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
             PdfDocument pdfDocument = new PdfDocument(pdfWriter);
             Document document = new Document(pdfDocument)) {

            // Подключаем стандартный шрифт Times-Roman, поддерживающий кириллицу
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

            // Добавляем заголовок
            document.add(new Paragraph(title).setFont(font).setFontSize(14).setBold());

            // Добавляем инструкции
            document.add(new Paragraph(instructions).setFont(font).setFontSize(12));

            // Добавляем таблицу
            drawTable(document, font, data);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toByteArray();
    }

    private void drawTable(Document document, PdfFont font, String[][] data) {
        // Создаем таблицу с 2 колонками
        Table table = new Table(2);
        table.setWidth(UnitValue.createPercentValue(100)); // Таблица занимает всю ширину страницы

        // Заполняем таблицу данными
        for (String[] row : data) {
            table.addCell(new Cell().add(new Paragraph(row[0]).setFont(font).setFontSize(12)).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph(row[1]).setFont(font).setFontSize(12)).setTextAlignment(TextAlignment.CENTER));
        }

        document.add(table);
    }

    public ReportData prepareReportData(ServiceHistory serviceHistory) {
        String title = "Отчет об обслуживании автомобиля";
        String instructions = "Детали обслуживания:";
        String[][] data = {
                {"Дата обслуживания", serviceHistory.getService_date() != null ? serviceHistory.getService_date() : "N/A"},
                {"Описание", serviceHistory.getDescription() != null ? serviceHistory.getDescription() : "N/A"},
                {"Пробег (км)", serviceHistory.getMileage() != null ? serviceHistory.getMileage().toString() : "N/A"},
                {"Следующее обслуживание", serviceHistory.getNext_service_date() != null ? serviceHistory.getNext_service_date() : "N/A"}
        };

        return new ReportData(title, instructions, data);
    }

    public static class ReportData {
        private final String title;
        private final String instructions;
        private final String[][] data;

        public ReportData(String title, String instructions, String[][] data) {
            this.title = title;
            this.instructions = instructions;
            this.data = data;
        }

        public String getTitle() {
            return title;
        }

        public String getInstructions() {
            return instructions;
        }

        public String[][] getData() {
            return data;
        }
    }
}
