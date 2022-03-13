package com.assist.openspacemanagement.remoteWork;

import com.assist.openspacemanagement.desk.Desk;
import com.assist.openspacemanagement.desk.DeskService;
import com.assist.openspacemanagement.office.OfficeService;
import com.assist.openspacemanagement.user.User;
import com.assist.openspacemanagement.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemoteWorkService implements IRemoteWorkService {
    @Autowired
    private RemoteWorkRepository remoteWorkRepository;

    //created new request for remote work
    @Override
    public ResponseEntity<Object> serviceAddRequestRemoteWork(RemoteWork remoteWork) {
        try {
            User user = UserService.userRepository.findById(remoteWork.getSenderId()).get();

            RemoteWork remoteWorkOld = remoteWorkRepository.findBySenderId(remoteWork.getSenderId());
            if (remoteWorkOld != null) {
                if (!remoteWorkOld.getStatus().equals("pending")) {
                    return new ResponseEntity<>("You have a pending request!", HttpStatus.BAD_REQUEST);
                }
            }
            if (user.getRemoteWorkPercentage() == 0)
                remoteWorkRepository.save(remoteWork);
            else
                return new ResponseEntity<>("Rejected! , You work now remote!", HttpStatus.BAD_REQUEST);

        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(remoteWork, HttpStatus.CREATED);
    }

    //get all request for remote work with status pending
    @Override
    public List<RemoteWork> serviceGetAllRequest() {
        List<RemoteWork> lst;
        try{
            lst = remoteWorkRepository.findByStatus();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return lst;
    }

    //update request
    @Override
    public ResponseEntity<Object> updateRequest(RemoteWork remoteWork) {
        try{
            remoteWorkRepository.save(remoteWork);

            if(remoteWork.getStatus().equals("accepted")){
                if(remoteWork.getPercentage() == 100){
                    Desk desk = DeskService.deskRepository.searchForExistingDesk(remoteWork.getSenderId());
                    DeskService.deskRepository.deleteById(desk.getDeskId());
                    OfficeService.updateOfficeOccupationDesk(desk.getOfficeId(),false);
                }
            }
        }catch(Exception e){
            return new ResponseEntity<>("An error occured!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(remoteWork,HttpStatus.OK);
    }
}
