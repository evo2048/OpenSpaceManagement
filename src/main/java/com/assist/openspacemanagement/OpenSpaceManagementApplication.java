package com.assist.openspacemanagement;

import com.assist.openspacemanagement.security.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpenSpaceManagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(AppConfig.class, args);

    }

}
