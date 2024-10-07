package com.example.AI_Project.AI_Project.Service;

import com.example.AI_Project.AI_Project.DTO.HospitalDTO;
import com.example.AI_Project.AI_Project.Entity.HospitalEntity;
import com.example.AI_Project.AI_Project.Repository.HospitalRepository;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;
    private final RestTemplate restTemplate;
    private final XmlMapper xmlMapper = new XmlMapper();

    // application.yml 파일에서 API 설정값 주입
    @Value("${spring.api.hospital.endpoint}")
    private String hospitalApiEndpoint;

    @Value("${spring.api.hospital.encoding-key}")
    private String encodingKey;

    // 병원 전체 조회 및 데이터베이스 저장 (최대 10개로 제한)
    @Override
    public List<HospitalDTO> getAllHospitals() {
        List<HospitalEntity> hospitalEntities = new ArrayList<>();
        int pageNo = 1;
        int numOfRows = 100; // 한 번에 조회할 데이터 수 (최대값으로 설정)

        // 필드값 변수 설정
        String serviceKey = encodingKey; // 서비스 키
        String q0 = "대구"; // 시도
        String q1 = "달서"; // 시군구동
        String ord = "NAME"; // 정렬 기준

        while (true) {
            // API 호출 URL 설정
            String url = hospitalApiEndpoint +
                    "?serviceKey=" + serviceKey +
                    "&ORD=" + ord +
                    "&pageNo=" + pageNo +
                    "&numOfRows=" + numOfRows +
                    "&Q0=" + q0 +
                    "&Q1=" + q1;

            // URL 로깅을 위해 작성
            log.info(url);

            // API 호출
            String response = restTemplate.getForObject(url, String.class);
            log.info("API 응답 메세지 : {}", response); // 응답 로깅

            // 응답을 파싱하여 HospitalEntity로 변환
            List<HospitalEntity> entities = parseApiResponse(response);

            // 조회된 병원 정보가 없으면 종료
            if (entities.isEmpty()) {
                log.info("더 이상 데이터를 찾을 수 없습니다. 검색을 종료합니다.");
                break;
            }

            // 데이터베이스에 저장
            for (HospitalEntity entity : entities) {
                hospitalRepository.save(entity); // 각 병원 정보를 데이터베이스에 저장
            }

            // 데이터베이스에 저장한 병원 목록을 hospitalEntities에 추가
            hospitalEntities.addAll(entities);

            // 다음 페이지로 이동
            pageNo++;
        }

        // HospitalEntity를 HospitalDTO로 변환 후 반환
        return hospitalEntities.stream()
                .map(HospitalDTO::entityToDTO)
                .collect(Collectors.toList());
    }

    // XML 응답을 HospitalEntity 리스트로 변환하는 메서드
    private List<HospitalEntity> parseApiResponse(String response) {
        List<HospitalEntity> hospitalEntities = new ArrayList<>();
        try {
            // XML 응답을 파싱하여 병원 정보 목록으로 변환
            XmlResponse xmlResponse = xmlMapper.readValue(response, XmlResponse.class);

            // 병원 정보를 HospitalEntity로 변환
            if (xmlResponse.getBody() != null && xmlResponse.getBody().getItems() != null) {
                for (HospitalItem item : xmlResponse.getBody().getItems()) {
                    HospitalEntity entity = new HospitalEntity();
                    entity.setHpid(item.getHpid());
                    entity.setDutyName(item.getDutyName());
                    entity.setDutyAddr(item.getDutyAddr());
                    entity.setDutyTel1(item.getDutyTel1());
                    entity.setDutyEmcls(item.getDutyEmcls());
                    entity.setWgs84Lon(item.getWgs84Lon());
                    entity.setWgs84Lat(item.getWgs84Lat());
                    entity.setDutyEryn(item.getDutyEryn()); // 진료 가능 여부

                    hospitalEntities.add(entity);
                }
            } else {
                log.warn("응답에서 병원 항목을 찾을 수 없습니다");
            }
        } catch (Exception e) {
            log.error("API 응답 구문 분석 오류", e);
        }
        return hospitalEntities;
    }
}
