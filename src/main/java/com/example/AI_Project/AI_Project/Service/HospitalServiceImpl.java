package com.example.AI_Project.AI_Project.Service;

import com.example.AI_Project.AI_Project.DTO.HospitalDTO;
import com.example.AI_Project.AI_Project.Entity.HospitalEntity;
import com.example.AI_Project.AI_Project.Repository.HospitalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.net.URI;
import java.util.stream.Collectors;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    private static final Logger log = LoggerFactory.getLogger(HospitalServiceImpl.class);

    private final RestTemplate restTemplate;

    @Autowired
    public HospitalServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${spring.api.hospital.endpoint}")
    private String endpoint;

    @Value("${spring.api.hospital.encoding-key}")
    private String encodingKey;

    @Override
    @Transactional
    public List<HospitalDTO> getAllHospitals(String Q0, String Q1) {
        try {
            // URL 생성 (인코딩 키를 직접 쿼리 문자열로 추가)
            String url = UriComponentsBuilder.fromHttpUrl(endpoint)
                    .queryParam("serviceKey", encodingKey)
                    .queryParam("ORD", "NAME")
                    .queryParam("pageNo", "1")
                    .queryParam("numOfRows", "100")
                    .queryParam("Q0", Q0)
                    .queryParam("Q1", Q1)
                    .build()
                    .toUriString();

            // URL 확인을 위한 로그 출력
            log.info("Request URL: {}", url);

            // API 호출
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            // 응답을 수동으로 디코딩
            byte[] bytes = response.getBody().getBytes(StandardCharsets.ISO_8859_1);
            String decodedResponse = new String(bytes, StandardCharsets.UTF_8);
            log.info("Decoded response: {}", decodedResponse);

            // XML 파싱
            var doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(decodedResponse)));

            var xpath = XPathFactory.newInstance().newXPath();
            var nodeList = (NodeList) xpath.evaluate("//item", doc, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                var node = nodeList.item(i);
                HospitalEntity entity = new HospitalEntity();
                entity.setHpid(xpath.evaluate("hpid", node));
                entity.setDutyName(xpath.evaluate("dutyName", node));
                entity.setDutyAddr(xpath.evaluate("dutyAddr", node));
                entity.setDutyTel1(xpath.evaluate("dutyTel1", node));
                entity.setWgs84Lat(xpath.evaluate("wgs84Lat", node));
                entity.setWgs84Lon(xpath.evaluate("wgs84Lon", node));

                hospitalRepository.save(entity);
                log.info("Saved hospital data : {}", entity);
            }

        } catch (Exception e) {
            log.error("Error parsing XML response", e);
        }

        // 저장된 데이터 반환
        return hospitalRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private HospitalDTO toDTO(HospitalEntity entity) {
        HospitalDTO dto = new HospitalDTO();
        dto.setHpid(entity.getHpid());
        dto.setDutyName(entity.getDutyName());
        dto.setDutyAddr(entity.getDutyAddr());
        dto.setDutyTel1(entity.getDutyTel1());
        dto.setWgs84Lat(entity.getWgs84Lat());
        dto.setWgs84Lon(entity.getWgs84Lon());
        return dto;
    }
}
