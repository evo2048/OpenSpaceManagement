package com.assist.openspacemanagement.remoteWork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management")
public class RemoteWorkController {
    @Autowired
    private IRemoteWorkService remoteWorkService;

    @PostMapping("/remote")
    public ResponseEntity<Object> addRequestRemoteWork(@RequestBody RemoteWork remoteWork){
        return remoteWorkService.serviceAddRequestRemoteWork(remoteWork);
    }

}
