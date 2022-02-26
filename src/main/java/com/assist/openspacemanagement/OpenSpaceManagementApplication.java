package com.assist.openspacemanagement;

import com.assist.openspacemanagement.config.AppConfig;
import com.assist.openspacemanagement.entities.UserEntity;
import org.springframework.boot.SpringApplication;

public class OpenSpaceManagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(AppConfig.class, args);

    }

}
