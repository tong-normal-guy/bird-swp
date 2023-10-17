package com.example.birdReproductionManagement;

import com.example.birdReproductionManagement.entity.*;
import com.example.birdReproductionManagement.model.*;
import com.example.birdReproductionManagement.repository.BirdRepository;
import com.example.birdReproductionManagement.repository.BirdTypeRepository;
import com.example.birdReproductionManagement.repository.CageRepository;

import com.example.birdReproductionManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
@RequiredArgsConstructor
public class BirdReproductionManagementApplication implements CommandLineRunner {


	private final UserRepository userRepository;
	private final BirdRepository birdRepository;
	private final BirdTypeRepository birdTypeRepository;
	private final CageRepository cageRepository;
	private String password = "1";
	private String USER = "Admin";

	public static void main(String[] args) {
		SpringApplication.run(BirdReproductionManagementApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {



		Bird bird;
		BirdType birdType;
		Cage cage = new Cage();

		User userAdmin = userRepository.findByUsername("Admin");

		if (userAdmin == null){
			userAdmin = new User();
			userAdmin.setPassword(password);
			userAdmin.setFullName(USER);
			userAdmin.setUsername(USER);
			userAdmin.setEmail(USER + "@gmail.com");
			userAdmin.setCreatedBy("");
			userAdmin.setCreatedDate(new Date(2023, 1, 1));
			userAdmin.setRole(Role.STAFF);
			// save dtb
//			userRepository.save(userAdmin);
		}

		birdType = new BirdType();
		birdType.setId(Long.valueOf(1));
		birdType.setName("than");
		birdType.setIncubate(Long.valueOf(16));
		birdType.setChick(Long.valueOf(16));
		birdType.setSwingBranch(Long.valueOf(7));
//		birdTypeRepository.save(birdType);

		cage.setLocation("A01");
		cage.setQuantity(1);
//		cage.setCageType("S");
//		cageRepository.save(cage);


		bird = new Bird();
		bird.setSex(Sex.MALE);
		bird.setHatchDate(new Date(2023, 1, 1));
		bird.setAgeRange("truong thanh");
		bird.setMutation("bong");
		bird.setMutationRate(Float.valueOf(99));
		bird.setIsAlive(true);
		bird.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
		bird.setFeatherColor("den trang");
		bird.setWeight(Float.valueOf(600));
		bird.setBirdType(birdType);
		bird.setCage(cage);
		birdRepository.save(bird);




	}
}
