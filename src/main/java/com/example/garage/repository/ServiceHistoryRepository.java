package com.example.garage.repository;

import com.example.garage.model.ServiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceHistoryRepository extends JpaRepository<ServiceHistory, Long> {
    List<ServiceHistory> findByCarId(Long clientId);
    Optional<ServiceHistory> findServiceHistoryById(Long serviceHistoryId);
}
