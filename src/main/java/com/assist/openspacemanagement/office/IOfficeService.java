package com.assist.openspacemanagement.office;

import net.minidev.json.JSONObject;
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
    List<Office> serviceGetAllOffice();

    //get status for all office
    List<JSONObject> serviceGetAllOfficeStatus();

    //get one office status
    ResponseEntity<Object> serviceGetOneOfficeStatus(int id);

    //get one office
    ResponseEntity<Object> serviceGetOneOffice(int id);
}
