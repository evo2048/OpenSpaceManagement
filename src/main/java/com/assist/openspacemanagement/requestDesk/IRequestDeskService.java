package com.assist.openspacemanagement.requestDesk;


import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IRequestDeskService {
    //add new request desk
    ResponseEntity<String> serviceAddNewRequestDesk(RequestDesk requestDesk);
    //get all request desk
    List<RequestDesk> serviceGetAllRequestDesk();
    //updated an request desk
    ResponseEntity<String> serviceUpdatedRequestDesk(RequestDesk requestDesk);
}
