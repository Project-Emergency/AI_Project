package com.example.AI_Project.AI_Project.Service;

import com.example.AI_Project.AI_Project.DTO.HospitalDTO;
import com.example.AI_Project.AI_Project.Entity.HospitalEntity;
import com.example.AI_Project.AI_Project.Repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    // 병원 전체 조회 (최대 10개로 제한)
    @Override
    public List<HospitalDTO> getAllHospitals() {
        List<HospitalEntity> entities = hospitalRepository.findAll();
        return entities.stream()
                .map(HospitalDTO::entityToDTO)  // DTO 변환
                .limit(10)  // 트래픽에 따라서 최대 10개로 제한
                .collect(Collectors.toList());
    }

    // 키워드로 병원 정보 조회
    @Override
    public List<HospitalDTO> searchHospitalsByKeyword(String keyword) {
        List<HospitalEntity> entities = hospitalRepository.findByDutyNameContaining(keyword);
        return entities.stream()
                .map(HospitalDTO::entityToDTO)  // DTO 변환
                .collect(Collectors.toList());
    }
}
