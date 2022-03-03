package com.assist.openspacemanagement.repositories;

import com.assist.openspacemanagement.entities.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Integer> {
    public List<BuildingEntity> findAll();
}
