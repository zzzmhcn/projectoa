package com.zmh.projectoa;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAdminServer
public class ProjectoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectoaApplication.class, args);
	}
}
