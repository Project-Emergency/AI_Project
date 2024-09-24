package com.example.AI_Project.AI_Project.DTO;

import com.example.AI_Project.AI_Project.Entity.HospitalEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HospitalDTO {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private boolean emergencyRoomAvailable;

    public static HospitalDTO entityToDTO(HospitalEntity hospitalEntity) {
        return new HospitalDTO(
                hospitalEntity.getId(),
                hospitalEntity.getName(),
                hospitalEntity.getAddress(),
                hospitalEntity.getPhoneNumber(),
                hospitalEntity.isEmergencyRoomAvailable()
        );
    }

    public HospitalEntity dtoToEntity(){
        return new HospitalEntity(id, name, address, phoneNumber, emergencyRoomAvailable);
    }
}
