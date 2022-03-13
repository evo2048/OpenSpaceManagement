package com.assist.openspacemanagement.office;

import com.assist.openspacemanagement.desk.DeskRepository;
import com.assist.openspacemanagement.desk.DeskService;
import com.assist.openspacemanagement.utils.Diverse;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OfficeService implements IOfficeService{


    static OfficeRepository officeRepository;

    @Autowired
    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    // create new office in database
    @Override
    public ResponseEntity<String> serviceAddOffice(Office office) {
        try{
            officeRepository.save(office);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Office add with successfully!",HttpStatus.CREATED);
    }

    // update an office
    @Override
    public ResponseEntity<String> serviceUpdateOfficeById(Office office) {
        try{
            officeRepository.save(office);
        }catch (Exception e){
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Office update with successfully!",HttpStatus.OK);
    }

    // remove an office if no have users assigned for desk
    @Override
    public ResponseEntity<String> serviceRemoveOffice(int id) {
        try{
            if(officeRepository.getAllUserAssignedOffice(id).get(0) != null){
                return new ResponseEntity<>("This office cannot be removed!",HttpStatus.BAD_REQUEST);
            }
            officeRepository.deleteAllById(Collections.singleton(id));
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Office was been removed!",HttpStatus.OK);
    }

    // return all office from building with specify id
    @Override
    public List<Office> serviceGetAllOffice() {
        return officeRepository.findAll();
    }

    //return all office status
    @Override
    public List<JSONObject> serviceGetAllOfficeStatus() {
        List<JSONObject> lstJSON = new ArrayList<>();
        JSONObject error = new JSONObject();
        error.appendField("error","An error occurred!");
        try{
            officeRepository.findAll().forEach(office -> {lstJSON.add(Diverse.statusToJson(office));});
        }catch (Exception e){
            lstJSON.add(error);
        }
        return lstJSON;
    }

    //return one office status for a specific employee
    @Override
    public ResponseEntity<Object> serviceGetOneOfficeStatus(int id) {
        JSONObject obj;
        try{
            Integer val = officeRepository.findIdOfficeForUser(id);
            if(val != null)
                obj = Diverse.statusToJson(officeRepository.findById(val).get());
            else
                return new ResponseEntity<>("This employee not assigned in any office!",HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    // return a single office
    @Override
    public ResponseEntity<Object> serviceGetOneOffice(int id) {
        Office office;
        try{
            office = officeRepository.findById(id).get();
        }catch (Exception e){
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(office,HttpStatus.OK);
    }

    //if usable is greater than occupied execute update
    //also no execute update
    public static boolean updateOfficeOccupationDesk(int officeId , boolean assigned){
        try{
            Office office = officeRepository.findById(officeId).get();

            int usable = office.getUsableDeskCount();
            int occupied = office.getOccupiedDeskCount();
            if(assigned) {
                if (occupied + 1 > usable)
                    return false;
                office.setOccupiedDeskCount(occupied + 1);
            }else {
                if (occupied - 1 < 0)
                    return false;
                office.setOccupiedDeskCount(occupied - 1);
            }
        }catch (Exception e){
            return false;
        }
        return true ;
    }

    public static OfficeRepository getOfficeRepository() {
        return officeRepository;
    }
}
