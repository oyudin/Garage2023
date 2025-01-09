package com.example.garage.controller;

import com.example.garage.model.Car;
import com.example.garage.model.ServiceHistory;
import com.example.garage.service.CarService;
import com.example.garage.service.ClientService;
import com.example.garage.service.DocumentService;
import com.example.garage.service.ServiceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("garage/clients/{ignoredClientId}/cars/{ignoredCarId}/service-history/{serviceHistoryId}/print")
public class PrintServiceHistoryController {

    private final ServiceHistoryService serviceHistoryService;
    private final CarService carService;
    private final ClientService clientService;
    private final DocumentService documentService;

    @Autowired
    public PrintServiceHistoryController(ServiceHistoryService serviceHistoryService, ClientService clientService,
                                         CarService carService, DocumentService documentService) {

        this.serviceHistoryService = serviceHistoryService;
        this.clientService = clientService;
        this.carService = carService;
        this.documentService = documentService;
    }

    @GetMapping
    public ResponseEntity<byte[]> printServiceHistory(@PathVariable int ignoredClientId,
                                                      @PathVariable Long ignoredCarId,
                                                      @PathVariable Long serviceHistoryId) {

        // Получаем ServiceHistory из базы данных
        Optional<ServiceHistory> serviceHistoryOptional = serviceHistoryService.getServiceHistoryById(serviceHistoryId);

        if (serviceHistoryOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ServiceHistory serviceHistory = serviceHistoryOptional.get();

        // Формируем данные для отчета с помощью сервиса
        DocumentService.ReportData reportData = documentService.prepareReportData(serviceHistory);

        // Генерируем PDF-отчет
        byte[] pdfContents = documentService.generateServiceReport(
                reportData.getTitle(),
                reportData.getInstructions(),
                reportData.getData()
        );

        // Устанавливаем заголовки для ответа
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=service_report_" + serviceHistoryId + ".pdf");
        headers.add("Content-Type", "application/pdf");

        // Возвращаем PDF в ответе
        return new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);
    }

}
