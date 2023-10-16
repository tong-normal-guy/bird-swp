package com.example.birdReproductionManagement;

import com.example.birdReproductionManagement.model.Role;
import com.example.birdReproductionManagement.model.UserEntity;
import com.example.birdReproductionManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class BirdReproductionManagementApplication implements CommandLineRunner {


	private final UserRepository userRepository;
	private String password = "1";
	private String ADMIN = "Admin";

	public static void main(String[] args) {
		SpringApplication.run(BirdReproductionManagementApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		UserEntity userAdmin = userRepository.findByUsername(ADMIN);
		if (userAdmin == null){
			userAdmin = new UserEntity();
			userAdmin.setPassword(password);
			userAdmin.setFullName(ADMIN);
			userAdmin.setUsername(ADMIN);
			userAdmin.setEmail(ADMIN + "@gmail.com");
			userAdmin.setRole(Role.ADMIN);
			// save dtb
			userRepository.save(userAdmin);
		}
	}
}
