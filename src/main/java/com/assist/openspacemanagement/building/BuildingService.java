package com.assist.openspacemanagement.building;

import com.assist.openspacemanagement.desk.Desk;
import com.assist.openspacemanagement.office.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingService implements IBuildingService{
    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public ResponseEntity<String> serviceAddBuilding(Building building) {
        try{
            buildingRepository.save(building);
        }catch (Exception e){
            return new ResponseEntity<>("An error occurred", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Building added!",HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> serviceUpdateBuilding(Building building) {
        try{
            buildingRepository.save(building);
        }catch (Exception e){
            return new ResponseEntity<>("An error occurred", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Building updated!",HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> serviceGetBuilding(int id) {
        Building building;
        try{
            building = buildingRepository.findById(id).get();
            if (building  == null){
                return new ResponseEntity<>("This building not exist!", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("An error occurred", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(building,HttpStatus.CREATED);
    }

    @Override
    public List<Building> serviceGetAllBuildings() {
        return buildingRepository.getAllBuildingFromDatabase();
    }

    // delete building only no have office
    @Override
    public ResponseEntity<String> serviceRemoveBuilding(int id){
        try{
            List<Integer> listUserAssigned = buildingRepository.getUserAssigned(id);
            if(!listUserAssigned.isEmpty())
                return new ResponseEntity<>("This building cannot be delete!", HttpStatus.BAD_REQUEST);
            buildingRepository.deleteBuildingById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Building was removed! ",HttpStatus.OK);
    }
}
