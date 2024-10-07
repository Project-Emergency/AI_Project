package com.example.AI_Project.AI_Project.Service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalItem {
    private String hpid; // 병원 ID
    private String dutyName; // 병원 이름
    private String dutyAddr; // 병원 주소
    private String dutyTel1; // 병원 전화
    private String dutyEmcls; // 응급의료기관코드
    private Double wgs84Lon; // 경도
    private Double wgs84Lat; // 위도
    private int dutyEryn; // 진료 가능 여부
}
