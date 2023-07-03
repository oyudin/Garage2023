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

    // Working version

//    @GetMapping("/garage/autopart/car/{carId}")
//    public String getListAutoPartsByCarId(Model model, @PathVariable int carId) {
//        List<AutoPart> autoPartListByCar = autoPartService.getListOfAutoPartsByCar(carId);
//        model.addAttribute("autoPartListByCar", autoPartListByCar);
//        return "AutoParts";
//    }

    //    @GetMapping("/garage/persons/{personId}/cars/{carId}")
////    @ResponseBody
//    public String getListAutoPartsByCarId(Model model, @PathVariable int personId, @PathVariable int carId) {
//        List<AutoPart> autoPartListByCar = autoPartService.getListOfAutoPartsByCar(carId);
//        model.addAttribute("autoPartListByCar", autoPartListByCar);
//        return "AutoPartsByCar";
//    }
    @GetMapping("/garage/persons/{personId}/cars/{carId}")
//    @ResponseBody
    public String getListAutoPartsByCarId(Model model, @PathVariable int personId, @PathVariable int carId) {
        model.addAttribute("autoPartListByCar", autoPartService.getListOfAutoPartsByCar(carId));
        return "AutoPartsByCar";
    }


    @GetMapping("garage/persons/{personId}/cars/{carId}/create")
    public String showAutoPartCreatingPage(@PathVariable int personId, @PathVariable int carId) {
        return "CreateAutoPart";
    }


    @PostMapping("garage/persons/{personId}/cars/{carId}/createAutoPart")
    public String createAutoPart(@RequestBody AutoPart autoPart, @PathVariable int personId, @PathVariable int carId) {
        autoPartService.save(autoPart, carId);
        return "redirect:/garage/persons/" + personId + "/cars";
    }

    @DeleteMapping("/autopart/{autoPartName}")
    @ResponseBody
    public void deleteAutoPartByName(@PathVariable String autoPartName) {
        autoPartService.deleteAutoPartByName(autoPartName);
    }

    /*-------------Auto Parts By Car------------*/

//    @GetMapping("garage/persons/{personId}/cars/{carId}")
//    @ResponseBody
//    public List<AutoPart> getListAutoPartsByCarId(@PathVariable int carId, @PathVariable int personId) {
//        return autoPartService.getListOfAutoPartsByCar(carId);
//    }


}