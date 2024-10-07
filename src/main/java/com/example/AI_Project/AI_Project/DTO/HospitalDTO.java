package com.example.AI_Project.AI_Project.DTO;

import com.example.AI_Project.AI_Project.Entity.HospitalEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HospitalDTO {
    private String hpid; // 병원 ID
    private String dutyName; // 병원 이름
    private String dutyAddr; // 주소
    private String dutyTel1; // 대표 전화
    private String dutyEmcls; // 응급의료기관코드
    private Double wgs84Lon; // 병원 경도
    private Double wgs84Lat; // 병원 위도

    public static HospitalDTO entityToDTO(HospitalEntity hospitalEntity) {
        return new HospitalDTO(
                hospitalEntity.getHpid(),
                hospitalEntity.getDutyName(),
                hospitalEntity.getDutyAddr(),
                hospitalEntity.getDutyTel1(),
                hospitalEntity.getDutyEmcls(),
                hospitalEntity.getWgs84Lon(),
                hospitalEntity.getWgs84Lat()
        );
    }

    public HospitalEntity dtoToEntity() {
        return new HospitalEntity(hpid, dutyName, dutyAddr, dutyTel1, dutyEmcls, wgs84Lon, wgs84Lat);
    }
}
