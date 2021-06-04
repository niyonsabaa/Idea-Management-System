package com.flyhub.ideamanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//(exclude = {DataSourceAutoConfiguration.class })
@EnableJpaAuditing
//@MapperScan(value = "com.iamapsycho.dao")
public class IdeamanagementsystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(IdeamanagementsystemApplication.class, args);		
	}
}
