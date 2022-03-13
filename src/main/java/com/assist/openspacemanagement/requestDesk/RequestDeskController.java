package com.assist.openspacemanagement.requestDesk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("management")
public class RequestDeskController {
    @Autowired
    IRequestDeskService requestDeskService;

    //get all request with status pending
    @GetMapping("/requestdesk/all")
    public List<RequestDesk> getAllRequestDesk(){
        return requestDeskService.serviceGetAllRequestDesk();
    }

    //add new request desk
    @PostMapping("/requestdesk")
    public ResponseEntity<String> addNewRequestDesk(@RequestBody RequestDesk requestDesk){
        return requestDeskService.serviceAddNewRequestDesk(requestDesk);
    }

    //updated an request desk
    @PutMapping("/requestdesk")
    public ResponseEntity<String> updatedRequestDesk(@RequestBody RequestDesk requestDesk){
        return requestDeskService.serviceUpdatedRequestDesk(requestDesk);
    }
}
