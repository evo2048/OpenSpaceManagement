package com.assist.openspacemanagement.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingService implements IBuildingService{
    @Autowired
    private BuildingRepository buildingRepository;
}
