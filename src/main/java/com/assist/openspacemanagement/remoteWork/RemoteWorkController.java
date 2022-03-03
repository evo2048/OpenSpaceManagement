package com.assist.openspacemanagement.remoteWork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoteWorkController {
    @Autowired
    private IRemoteWorkService remoteWorkService;
}
