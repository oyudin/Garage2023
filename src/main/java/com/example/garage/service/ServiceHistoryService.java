package com.example.garage.service;

import com.example.garage.model.Car;
import com.example.garage.model.ServiceHistory;
import com.example.garage.repository.CarRepository;
import com.example.garage.repository.ServiceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceHistoryService {

    private final ServiceHistoryRepository serviceHistoryRepository;
    private final CarRepository carRepository;

    @Autowired
    public ServiceHistoryService(ServiceHistoryRepository serviceHistoryRepository, CarRepository carRepository) {
        this.serviceHistoryRepository = serviceHistoryRepository;
        this.carRepository = carRepository;
    }

    public List<ServiceHistory> getServiceHistoryByCar(Long carId) {
        return serviceHistoryRepository.findByCarId(carId);
    }

    public Optional<ServiceHistory> getServiceHistoryById(Long serviceHistoryId) {
        return serviceHistoryRepository.findServiceHistoryById(serviceHistoryId);
    }

    public ServiceHistory saveServiceHistoryForCar(long carId, ServiceHistory serviceHistory) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found with id " + carId));
        serviceHistory.setCar(car);
        return serviceHistoryRepository.save(serviceHistory);
    }

    public Optional<ServiceHistory> updateServiceHistory(Long serviceHistoryId, ServiceHistory serviceHistory) {
        Optional<ServiceHistory> updatedServiceHistory = getServiceHistoryById(serviceHistoryId);

        if (updatedServiceHistory.isPresent()) {
            ServiceHistory existingServiceHistory = updatedServiceHistory.get();
            existingServiceHistory.setService_date(serviceHistory.getService_date());
            existingServiceHistory.setNext_service_date(serviceHistory.getNext_service_date());
            existingServiceHistory.setMileage(serviceHistory.getMileage());
            existingServiceHistory.setPrice(serviceHistory.getPrice());
            existingServiceHistory.setDescription(serviceHistory.getDescription());

//            serviceHistoryRepository.save(existingServiceHistory);

            return Optional.of(existingServiceHistory);
        } else {
            return Optional.empty();
        }
    }


    public void deleteServiceHistory(long id) {
        ServiceHistory serviceHistory = serviceHistoryRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Service History not found with id " + id));
        serviceHistoryRepository.delete(serviceHistory);
    }

    public String generateServiceHistoryTable(ServiceHistory serviceHistory) {
        StringBuilder table = new StringBuilder();

        table.append("<style>")
                .append("table { width: 100%; border-collapse: collapse; font-family: Arial, sans-serif; }")
                .append("th, td { padding: 10px; text-align: left; border: 1px solid #ddd; }")
                .append("th { background-color: #f4f4f4; font-weight: bold; }")
                .append("tr:nth-child(even) { background-color: #f9f9f9; }")
                .append("tr:hover { background-color: #f1f1f1; }")
                .append("</style>");

        table.append("<table>");
        table.append("<thead><tr>")
//                .append("<th>ID</th>")
                .append("<th>Car ID</th>")
                .append("<th>Номер</th>")
                .append("<th>Дата Обслуговування</th>")
                .append("<th>Пробіг</th>")
                .append("<th>Опис</th>")
                .append("<th>Ціна</th>")
                .append("<th>Наступна дата обслуговування</th>")
                .append("</tr></thead>");

        table.append("<tbody>");

        //                .append("<td>").append(serviceHistory.getId()).append("</td>")
        table.append("<tr>")
                .append("<td>").append(serviceHistory.getCar().getId()).append("</td>")
                .append("<td>").append(serviceHistory.getCar().getNumber()).append("</td>")
//                .append("<td>").append(serviceHistory.getId()).append("</td>")
                .append("<td>").append(serviceHistory.getService_date()).append("</td>")
                .append("<td>").append(serviceHistory.getMileage()).append("</td>")
                .append("<td>").append(serviceHistory.getDescription() == null ? "N/A" : serviceHistory.getDescription()).append("</td>")
                .append("<td>").append(serviceHistory.getPrice()).append(" грн").append("</td>")
                .append("<td>").append(serviceHistory.getNext_service_date() == null ? "N/A" : serviceHistory.getNext_service_date()).append("</td>")
                .append("</tr>");

        table.append("</tbody>");
        table.append("</table>");

        return table.toString();
    }

    public String generateFullServiceHistoryTable(List<ServiceHistory> serviceHistories) {
        StringBuilder table = new StringBuilder();

        table.append("<style>")
                .append("table { width: 100%; border-collapse: collapse; font-family: Arial, sans-serif; }")
                .append("th, td { padding: 10px; text-align: left; border: 1px solid #ddd; }")
                .append("th { background-color: #f4f4f4; font-weight: bold; }")
                .append("tr:nth-child(even) { background-color: #f9f9f9; }")
                .append("tr:hover { background-color: #f1f1f1; }")
                .append("</style>");

        table.append("<table>");
        table.append("<thead><tr>")
//                .append("<th>ID</th>")
                .append("<th>Car ID</th>")
                .append("<th>Номер</th>")
                .append("<th>Дата Обслуговування</th>")
                .append("<th>Пробіг</th>")
                .append("<th>Опис</th>")
                .append("<th>Ціна</th>")
                .append("<th>Наступна дата обслуговування</th>")
                .append("</tr></thead>");

        table.append("<tbody>");
        for (ServiceHistory serviceHistory : serviceHistories) {
            //                .append("<td>").append(serviceHistory.getId()).append("</td>")
            table.append("<tr>")
                    .append("<td>").append(serviceHistory.getCar().getId()).append("</td>")
                    .append("<td>").append(serviceHistory.getCar().getNumber()).append("</td>")
//                .append("<td>").append(serviceHistory.getId()).append("</td>")
                    .append("<td>").append(serviceHistory.getService_date()).append("</td>")
                    .append("<td>").append(serviceHistory.getMileage()).append("</td>")
                    .append("<td>").append(serviceHistory.getDescription() == null ? "N/A" : serviceHistory.getDescription()).append("</td>")
                    .append("<td>").append(serviceHistory.getPrice()).append(" грн").append("</td>")
                    .append("<td>").append(serviceHistory.getNext_service_date() == null ? "N/A" : serviceHistory.getNext_service_date()).append("</td>")
                    .append("</tr>");
        }

        table.append("</tbody>");
        table.append("</table>");

        return table.toString();
    }

}
