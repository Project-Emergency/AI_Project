package com.example.AI_Project.AI_Project.Service;

import com.example.AI_Project.AI_Project.DTO.HospitalDTO;
import com.example.AI_Project.AI_Project.Entity.HospitalEntity;
import com.example.AI_Project.AI_Project.Repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Value("${spring.api.hospital.endpoint}")
    private String apiUrl;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public String getMedicalData() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiUrl, String.class);

        // 응답 데이터를 파싱하여 HospitalDTO 리스트로 변환
        List<HospitalDTO> hospitalDTOs = parseResponse(response);

        // DTO를 Entity로 변환하여 DB에 저장
        saveHospitals(hospitalDTOs);

        return response; // 필요에 따라 다른 데이터를 반환할 수 있습니다.
    }

    private List<HospitalDTO> parseResponse(String response) {
        // 응답 데이터 파싱 로직을 여기에 구현 (JSON 파싱 등)
        return Arrays.asList(); // 여기에 실제 DTO 리스트로 변환한 결과를 반환
    }

    private void saveHospitals(List<HospitalDTO> hospitalDTOs) {
        for (HospitalDTO dto : hospitalDTOs) {
            HospitalEntity entity = dto.dtoToEntity();
            hospitalRepository.save(entity);
        }
    }

    @Override
    public List<HospitalDTO> getAllHospitals() {
        List<HospitalEntity> entities = hospitalRepository.findAll();
        return entities.stream()
                .map(HospitalDTO::entityToDTO)
                .toList();
    }
}
