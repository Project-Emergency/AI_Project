package com.example.AI_Project.AI_Project.DTO;

import com.example.AI_Project.AI_Project.Entity.HospitalEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HospitalDTO {
    private String hpid;
    private String dutyName;
    private String dutyAddr;
    private String dutyTel1;
    private String wgs84Lat;
    private String wgs84Lon;
}
