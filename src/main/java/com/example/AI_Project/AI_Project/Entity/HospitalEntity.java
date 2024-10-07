package com.example.AI_Project.AI_Project.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "hospital")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HospitalEntity {

    @Id
    private String hpid; // 병원 ID
    private String dutyName; // 병원 이름
    private String dutyAddr; // 주소
    private String dutyTel1; // 대표 전화
    private String dutyEmcls; // 응급의료기관코드
    private Double wgs84Lon; // 병원 경도
    private Double wgs84Lat; // 병원 위도
    private int dutyEryn; // 진료 가능 여부 (1: 진료 가능, 0: 진료 불가)
}

