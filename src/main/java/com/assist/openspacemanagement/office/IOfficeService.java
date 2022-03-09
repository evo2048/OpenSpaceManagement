package com.assist.openspacemanagement.office;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOfficeService {
    //add office
    ResponseEntity<String> serviceAddOffice(Office office);

    //update an office by id
    ResponseEntity<String> serviceUpdateOfficeById(Office office);

    //remove office from database
    ResponseEntity<String> serviceRemoveOffice(int id);

    //get all offices from a building by building_id
    List<Office> serviceGetAllOfficeFromBuilding(int buildingId);

    //get one office
    ResponseEntity<Object> serviceGetOneOffice(int id);
}
