package com.assist.openspacemanagement.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.assist.openspacemanagement.entities")
@EnableJpaRepositories("com.assist.openspacemanagement.repositories")
@ComponentScan("com.assist.openspacemanagement")
@Import({WebSecurityConfig.class})
public class AppConfig {  }
