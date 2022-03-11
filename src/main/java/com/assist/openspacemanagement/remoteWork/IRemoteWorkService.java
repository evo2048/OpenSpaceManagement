package com.assist.openspacemanagement.remoteWork;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRemoteWorkService {
    //add request remote work
    ResponseEntity<Object> serviceAddRequestRemoteWork(RemoteWork remoteWork);

    // get all request remote work
    List<RemoteWork> serviceGetAllRequest();

    //update request
    ResponseEntity<Object> updateRequest(RemoteWork remoteWork);
}
