package com.assist.openspacemanagement.office;

import com.assist.openspacemanagement.building.Building;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("management")
public class OfficeController {
    @Autowired
    private IOfficeService officeService;

    //get office by id
    @GetMapping("/office/{id}")
    public ResponseEntity<Object> getOneOffice(@PathVariable("id") int id){
        return officeService.serviceGetOneOffice(id);
    }

    //get one office status by id
    @GetMapping("/office/status")
    public ResponseEntity<Object> getOneStatusOffice(@RequestParam("id") int id){
        return officeService.serviceGetOneOfficeStatus(id);
    }

    //get all offices
    @GetMapping("/office/all")
    public List<Office> getAllOffices(){
        return officeService.serviceGetAllOffice();
    }

    //get all offices status
    @GetMapping("office/status/all")
    public List<JSONObject> getStatusForAllOffice(){
        return officeService.serviceGetAllOfficeStatus();
    }

    // add office
    @PostMapping("/office")
    public ResponseEntity<String> addOffice(@RequestBody Office office){
        return officeService.serviceAddOffice(office);
    }

    // update one office
    @PutMapping("/office")
    public ResponseEntity<String> updateOffice(@RequestBody Office office){
        return officeService.serviceUpdateOfficeById(office);
    }

    //delete office
    @DeleteMapping("/office")
    public ResponseEntity<String> deleteOffice(@RequestParam("id") int id){
        return officeService.serviceRemoveOffice(id);
    }

}
