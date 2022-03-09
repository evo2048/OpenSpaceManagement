package com.assist.openspacemanagement.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class OfficeService implements IOfficeService{

    @Autowired
    OfficeRepository officeRepository;

    // create new office in database
    @Override
    public ResponseEntity<String> serviceAddOffice(Office office) {
        try{
            officeRepository.save(office);
        }catch (Exception e){
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
            officeRepository.removeOfficeFromDatabase(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Office was been removed!",HttpStatus.OK);
    }

    // return all office from building with specify id
    @Override
    public List<Office> serviceGetAllOfficeFromBuilding(int buildingId) {
        return officeRepository.getAllOfficeFromBuilding(buildingId);
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
}
