package com.example.AI_Project.AI_Project.Repository;

import com.example.AI_Project.AI_Project.Entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {
    List<HospitalEntity> findByEmergencyRoomAvailableTrue();
}