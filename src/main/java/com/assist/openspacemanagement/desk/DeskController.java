package com.assist.openspacemanagement.desk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeskController {
    @Autowired
    private IDeskService deskService;
}
