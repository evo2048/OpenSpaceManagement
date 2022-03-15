package com.assist.openspacemanagement.desk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("management")
public class DeskController {
    @Autowired
    private IDeskService deskService;

    //get desk
    @GetMapping("/desk/{id}")
    public ResponseEntity<Object> getDesk(@PathVariable("id") int id){
        return deskService.serviceGetDesk(id);
    }
    // add new desk
    @PostMapping("/desk")
    public ResponseEntity<String> addDesk(@RequestBody Desk desk){
        return deskService.serviceAddDesk(desk);
    }

    //delete desk
    @DeleteMapping("/desk")
    public ResponseEntity<String> addDesk(@RequestParam("id") int officeID) {
        return deskService.serviceRemoveDesk(officeID);
    }

}
