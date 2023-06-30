package com.example.garage.controller;

import com.example.garage.model.AutoPart;
import com.example.garage.service.AutoPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
public class AutoPartController {

    private final AutoPartService autoPartService;

    @Autowired
    public AutoPartController(AutoPartService autoPartService) {
        this.autoPartService = autoPartService;
    }

    @GetMapping("/autopart")
    //@Cacheable(value = "users", key = "#username")
    public String getAllAutoParts(Model model) {
        model.addAttribute("autoPart", autoPartService.getAllAutoParts());
        return "AutoParts";
    }

    @GetMapping("/autopart/{autoPartName}")
    @ResponseBody
    public AutoPart getAutoPartByName(@PathVariable String autoPartName) {
        return autoPartService.getAutoPartByName(autoPartName);
    }

    @GetMapping("/autopart/create")
    public String registration() {
        return "CreateAutoPart";
    }

    @PostMapping("/garage/person/car/{carId}/autopart/create")
    @ResponseBody
    public String createAutoPart(@RequestBody AutoPart autoPart, @PathVariable int carId) {
        autoPart.setAutoPartCarId(carId);
        AutoPart createdAutoPart = autoPartService.createAutoPart(autoPart);
        if (createdAutoPart != null) {
            return "success";
        } else {
            return "error";
        }
    }

    @DeleteMapping("/autopart/{autoPartName}")
    @ResponseBody
    public void deleteAutoPartByName(@PathVariable String autoPartName) {
        autoPartService.deleteAutoPartByName(autoPartName);
    }

    /*-------------Auto Parts By Car------------*/

//    @GetMapping("autopart/car/{carId}")
//    @ResponseBody
//    public List<AutoPart> getListAutoPartsByCarId(@PathVariable int carId) {
//        return autoPartService.getListOfAutoPartsByCar(carId);
//    }

    @GetMapping("/garage/autopart/car/{carId}")
    public String getListAutoPartsByCarId(Model model, @PathVariable int carId) {
        List<AutoPart> autoPartListByCar = autoPartService.getListOfAutoPartsByCar(carId);
        model.addAttribute("autoPartListByCar", autoPartListByCar);
        return "AutoParts";
    }

}