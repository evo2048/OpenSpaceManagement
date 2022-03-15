package com.assist.openspacemanagement.building;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("management")
public class BuildingController {
    @Autowired
    private IBuildingService buildingService;

    //get building by id
    @GetMapping("/building/{id}")
    public ResponseEntity<Object> getOneBuilding(@PathVariable("id") int id){
        return buildingService.serviceGetBuilding(id);
    }
    //get all building
    @GetMapping("/building/all")
    public List<Building> getAllBuildings(){
        return buildingService.serviceGetAllBuildings();
    }

    //get building metrics
    @GetMapping("/building/metrics")
    public JSONObject getBuildingMetrics(@RequestParam("id") int buildingId){
        return buildingService.serviceGetBuildingsMetrics(buildingId);
    }

    // add a building
    @PostMapping("/building")
    public ResponseEntity<String> addBuilding(@RequestBody Building building){
        return buildingService.serviceAddBuilding(building);
    }
    // update one building
    @PutMapping("/building")
    public ResponseEntity<String> updateBuilding(@RequestBody Building building){
        return buildingService.serviceUpdateBuilding(building);
    }
    //delete building
    @DeleteMapping("/building")
    public ResponseEntity<String> deleteBuilding(@RequestParam("id") int id){
        return buildingService.serviceRemoveBuilding(id);
    }

}
