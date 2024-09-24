package com.example.AI_Project.AI_Project.Controller;

import com.example.AI_Project.AI_Project.DTO.HospitalDTO;
import com.example.AI_Project.AI_Project.Service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping("/db")
    public List<HospitalDTO> getHospitalsFromDB() {
        return hospitalService.getAllHospitals();
    }

    @GetMapping("/api")
    public List<HospitalDTO> getHospitalsFromAPI() {
        return hospitalService.fetchHospitalsFromAPI();
    }
}
