package com.assist.openspacemanagement.desk;

import org.springframework.http.ResponseEntity;

public interface IDeskService {
    // add new desk
    ResponseEntity<String> serviceAddDesk(Desk desk);

    // get one desk
    ResponseEntity<Object> serviceGetDesk(int id);

    // delete desk
    ResponseEntity<String> serviceRemoveDesk(int id);
}
