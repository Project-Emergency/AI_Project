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
    private String hpid;
    private String dutyName;
    private String dutyAddr;
    private String dutyTel1;
    private String wgs84Lat;
    private String wgs84Lon;
}

