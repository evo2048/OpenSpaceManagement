package com.assist.openspacemanagement.desk;

import com.assist.openspacemanagement.office.OfficeService;
import com.assist.openspacemanagement.user.User;
import com.assist.openspacemanagement.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeskService implements IDeskService{

    public static DeskRepository deskRepository;

    @Autowired
    public DeskService(DeskRepository deskRepository) {
        this.deskRepository = deskRepository;
    }


    @Override
    public ResponseEntity<String> serviceAddDesk(Desk desk) {
        try{
            int officeID = desk.getOfficeId();

            Desk oldDesk = deskRepository.searchForExistingDesk(desk.getUserAssigned().getUserId());
            if(oldDesk != null){
                OfficeService.updateOfficeOccupationDesk(oldDesk.getOfficeId(),false);
                deskRepository.deleteById(oldDesk.getDeskId());
            }

            User user = UserService.userRepository.getById(desk.getUserAssigned().getUserId());
            if (user.getRemoteWorkPercentage() == 100){
                user.setRemoteWorkPercentage(0);
            }
            UserService.userRepository.save(user);


            if(OfficeService.updateOfficeOccupationDesk(officeID,true))
                deskRepository.save(desk);
            else
                return new ResponseEntity<>("This office is fully!",HttpStatus.BAD_REQUEST);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Desk was been added!",HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> serviceGetDesk(int id) {
        Desk desk;
        try{
            desk = deskRepository.findById(id).get();
        }catch (Exception e){
            return new ResponseEntity<>("An error occur", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(desk,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> serviceRemoveDesk(int id) {
        try{
            if(OfficeService.updateOfficeOccupationDesk(id,false))
                deskRepository.deleteWhereOfficeId(id);
            else
                return new ResponseEntity<>("This office is empty!",HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("An error occur", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Desk was been removed!",HttpStatus.OK);
    }
}
