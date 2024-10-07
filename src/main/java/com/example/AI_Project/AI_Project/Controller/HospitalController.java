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

    // 병원 전체 조회
    @GetMapping("/hospitals")
    public List<HospitalDTO> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }
}
