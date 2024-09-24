package com.example.AI_Project.AI_Project.Service;

import com.example.AI_Project.AI_Project.DTO.HospitalDTO;
import com.example.AI_Project.AI_Project.Entity.HospitalEntity;
import com.example.AI_Project.AI_Project.Repository.HospitalRepository;
import com.example.AI_Project.AI_Project.Service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${spring.api.hospital.endpoint}")
    private String hospitalApiEndpoint;

    @Value("${spring.api.hospital.encoding-key}")
    private String encodingKey;

    @Override
    public List<HospitalDTO> getAllHospitals() {
        List<HospitalEntity> hospitals = hospitalRepository.findAll();
        return hospitals.stream()
                .map(HospitalDTO::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HospitalDTO> fetchHospitalsFromAPI() {
        String url = hospitalApiEndpoint + "?serviceKey=" + encodingKey;

        // RestTemplate를 사용하여 API 호출
        HospitalDTO[] response = restTemplate.getForObject(url, HospitalDTO[].class);

        if (response != null) {
            return List.of(response);
        } else {
            return List.of();  // 데이터가 없으면 빈 리스트 반환
        }
    }
}
