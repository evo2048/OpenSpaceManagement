package com.assist.openspacemanagement.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan("com.assist.openspacemanagement")
@Import({WebSecurityConfig.class})
public class AppConfig {  }
