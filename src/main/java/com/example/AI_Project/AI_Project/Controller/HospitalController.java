package com.example.AI_Project.AI_Project.Controller;

import com.example.AI_Project.AI_Project.DTO.HospitalDTO;
import com.example.AI_Project.AI_Project.Service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/hospitals")
    public List<HospitalDTO> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    @GetMapping("/hospitals/search")
    public List<HospitalDTO> searchHospitals(@RequestParam String keyword) {
        return hospitalService.searchHospitalsByKeyword(keyword);
    }
}
