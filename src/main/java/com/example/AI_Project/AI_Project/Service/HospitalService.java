package com.example.AI_Project.AI_Project.Service;

import com.example.AI_Project.AI_Project.DTO.HospitalDTO;

import java.util.List;

public interface HospitalService {
    String getMedicalData(); // API에서 데이터 가져오기
    List<HospitalDTO> getAllHospitals(); // DB에서 모든 병원 조회하기
}
