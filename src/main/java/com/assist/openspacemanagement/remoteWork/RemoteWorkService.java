package com.assist.openspacemanagement.remoteWork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoteWorkService implements IRemoteWorkService {
    @Autowired
    private RemoteWorkRepository remoteWorkRepository;
}
