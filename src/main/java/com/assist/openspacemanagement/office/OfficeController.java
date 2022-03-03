package com.assist.openspacemanagement.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfficeController {
    @Autowired
    private IOfficeService officeService;
}
