package com.example.AI_Project.AI_Project.Repository;

import com.example.AI_Project.AI_Project.Entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<HospitalEntity, String> {
    List<HospitalEntity> findByDutyNameContaining(String keyword);
}
