package com.example.AI_Project.AI_Project.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private boolean emergencyRoomAvailable;
}
