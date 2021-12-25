package com.gestmaint.api;

import com.gestmaint.api.entities.RoleEntity;
import com.gestmaint.api.repositories.RoleRepository;
import com.gestmaint.api.utils.ERole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class ApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(RoleRepository roleRepository) {
		return args -> {
			if(roleRepository.findAll().size() == 0){
				Arrays.stream(ERole.values())
						.forEach(role -> roleRepository.save(new RoleEntity(null, role)));
			}
		};
	}

}
