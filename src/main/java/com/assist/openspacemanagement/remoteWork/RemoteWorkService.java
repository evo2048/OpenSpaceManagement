package com.assist.openspacemanagement.remoteWork;

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

    @Override
    public ResponseEntity<Object> serviceAddRequestRemoteWork(RemoteWork remoteWork) {
        try {
            User user = UserService.userRepository.findById(remoteWork.getSenderId()).get();

            RemoteWork remoteWorkOld = remoteWorkRepository.findBySenderId(remoteWork.getSenderId());
            if (remoteWorkOld != null) {
                if (!remoteWorkOld.status.equals("pending")) {
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

    @Override
    public List<RemoteWork> serviceGetAllRequest() {
        return null;
    }
}
