package com.assist.openspacemanagement.building;

import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBuildingService {
    // add building to database
    ResponseEntity<String> serviceAddBuilding(Building building);
    //update building in database
    ResponseEntity<String> serviceUpdateBuilding(Building building);
    // get one building from database
    ResponseEntity<Object> serviceGetBuilding(int id);
    // get all building from database
    List<Building> serviceGetAllBuildings();
    //remove a building by id
    ResponseEntity<String> serviceRemoveBuilding(int id);
    //get building metrics
    JSONObject serviceGetBuildingsMetrics(int id);
}
