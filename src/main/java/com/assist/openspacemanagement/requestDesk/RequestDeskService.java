package com.assist.openspacemanagement.requestDesk;

import com.assist.openspacemanagement.desk.Desk;
import com.assist.openspacemanagement.desk.DeskService;
import com.assist.openspacemanagement.office.Office;
import com.assist.openspacemanagement.office.OfficeService;
import com.assist.openspacemanagement.user.User;
import com.assist.openspacemanagement.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestDeskService implements IRequestDeskService{
    @Autowired
    IRequestDeskRepository requestDeskRepository;

    @Override
    public ResponseEntity<String> serviceAddNewRequestDesk(RequestDesk requestDesk) {
        try{

            RequestDesk oldRequestDesk = requestDeskRepository.findBySenderId(requestDesk.getSenderId());

            if(oldRequestDesk != null && oldRequestDesk.getStatus().equals("pending")){
                return new ResponseEntity<>("You have an request with status pending",HttpStatus.BAD_REQUEST);
            }

            Office office = OfficeService.getOfficeRepository().getById(requestDesk.getOfficeId());
            if (office.getUsableDeskCount() - office.getOccupiedDeskCount() > 0) {
                requestDesk.setStatus("pending");
                requestDeskRepository.save(requestDesk);
            }
            else return new ResponseEntity<>("This office not have free desk",HttpStatus.BAD_REQUEST);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("This request is added with successfully",HttpStatus.CREATED);
    }

    @Override
    public List<RequestDesk> serviceGetAllRequestDesk() {
        return requestDeskRepository.findByStatus();
    }

    @Override
    public ResponseEntity<String> serviceUpdatedRequestDesk(RequestDesk requestDesk) {
        try{
            if (requestDesk.getStatus().equals("accepted")){
                if (OfficeService.updateOfficeOccupationDesk(requestDesk.getOfficeId(),true)) {
                    Desk oldDesk = DeskService.deskRepository.searchForExistingDesk(requestDesk.getSenderId());

                    if(oldDesk != null)
                        DeskService.deskRepository.deleteById(oldDesk.getDeskId());

                    Desk desk = new Desk();
                    desk.setOfficeId(requestDesk.getOfficeId());
                    desk.setUserAssigned(UserService.userRepository.findById(requestDesk.getSenderId()).get());
                    DeskService.deskRepository.save(desk);

                    User user = UserService.userRepository.findById(requestDesk.getSenderId()).get();
                    if(user.getRemoteWorkPercentage() == 100){
                        user.setRemoteWorkPercentage(0);
                        UserService.userRepository.save(user);
                    }
                }else return new ResponseEntity<>("This office no have free desk",HttpStatus.BAD_REQUEST);
            }
            requestDeskRepository.save(requestDesk);
        }catch (Exception e){
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("This request is updated with successfully",HttpStatus.OK);
    }
}
