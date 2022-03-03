package com.assist.openspacemanagement.desk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeskService implements IDeskService{
    @Autowired
    private DeskRepository deskRepository;
}
