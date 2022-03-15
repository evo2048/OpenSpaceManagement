package com.assist.openspacemanagement.remoteWork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("management")
public class RemoteWorkController {
    @Autowired
    private IRemoteWorkService remoteWorkService;

    //add request for remote work
    @PostMapping("/remote")
    public ResponseEntity<Object> addRequestRemoteWork(@RequestBody RemoteWork remoteWork){
        return remoteWorkService.serviceAddRequestRemoteWork(remoteWork);
    }

    //get all request for remote work
    @GetMapping("/remote")
    public List<RemoteWork> getAllRequestRemoteWork(){
        return remoteWorkService.serviceGetAllRequest();
    }

    //update request
    @PutMapping("/remote")
    public  ResponseEntity<Object> updateRequest(@RequestBody RemoteWork remoteWork){
        return remoteWorkService.updateRequest(remoteWork);
    }

}
