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

    @GetMapping("/apis.data.go.kr/B552657/HsptlAsembySearchService")
    public List<HospitalDTO> getAllHospitals(@RequestParam String Q0, @RequestParam String Q1) {
        return hospitalService.getAllHospitals(Q0, Q1);
    }
}
