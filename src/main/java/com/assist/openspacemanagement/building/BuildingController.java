package com.assist.openspacemanagement.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildingController {
    @Autowired
    private IBuildingService buildingService;
}
