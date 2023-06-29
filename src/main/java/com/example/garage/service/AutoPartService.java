package com.example.garage.service;

import com.example.garage.config.EncoderConfig;
import com.example.garage.model.AutoPart;
import com.example.garage.model.User;
import com.example.garage.repository.dao.AutoPartRepository;
import com.example.garage.repository.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoPartService {

    private final AutoPartRepository autoPartRepository;

    @Autowired
    public AutoPartService(AutoPartRepository autoPartRepository) {
        this.autoPartRepository = autoPartRepository;
    }

    public List<AutoPart> getAllAutoParts() {
        return autoPartRepository.findAll();
    }

    public AutoPart getAutoPartByName(String autoPartName) {
        return autoPartRepository.findAutoPartByAutoPartName(autoPartName);
    }

    public AutoPart createAutoPart(AutoPart autoPart) {
        return autoPartRepository.save(autoPart);
    }

    public void deleteAutoPartByName(String autoPartName) {
        autoPartRepository.deleteAutoPartByAutoPartName(autoPartName);
    }

}
