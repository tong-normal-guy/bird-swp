package com.example.birdReproductionManagement;

import com.example.birdReproductionManagement.entity.*;
import com.example.birdReproductionManagement.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
@RequiredArgsConstructor
//public class BirdReproductionManagementApplication implements CommandLineRunner {
public class BirdReproductionManagementApplication{


	private final UserRepository userRepository;
	private final BirdRepository birdRepository;
	private final BirdTypeRepository birdTypeRepository;
	private final CageRepository cageRepository;
	private final ReproductionProcessRepository reproductionProcessRepository;
	private final BirdReproductionRepository birdReproductionRepository;
	private String password = "1";
	private String ADMIN = "admin";
	private  String STAFF = "staff";
	private  String STAFF2 = "staff2";
	private  String STAFF3 = "staff3";
	private  String STAFF4 = "staff4";
	private  String STAFF5 = "staff5";
	public static void main(String[] args) {
		SpringApplication.run(BirdReproductionManagementApplication.class, args);
	}


//	@Override
//	public void run(String... args) throws Exception {
//
//		User userAdmin = userRepository.findByUsername(ADMIN);
//		User staff1 = userRepository.findByUsername(STAFF);
//		User staff2 = userRepository.findByUsername(STAFF2);
//		User staff3 = userRepository.findByUsername(STAFF3);
//		User staff4 = userRepository.findByUsername(STAFF4);
//		User staff5 = userRepository.findByUsername(STAFF5);
//
//		Bird bird;
//		Bird bird2;
//		Bird bird3;
//		Bird bird4;
//		Bird bird5;
//		Bird bird6;
//
//		Bird father;
//		Bird mother;
//		Bird child1;
//		Bird child2;
//
//		Bird father2;
//		Bird mother2;
//		Bird child12;
//		Bird child22;
//
//		Bird father3;
//		Bird mother3;
//		Bird child13;//
//		Bird child23;//
//
//		Bird father4;
//		Bird mother4;
//		Bird child14;//
//		Bird child24;//
//
//		Bird bird4sale;
//
//		BirdType birdType;
//		BirdType birdType2;
//
//		Cage cage;
//		Cage cage1;
//		Cage cage13;
//		Cage cage14;
//		Cage cage15;
//		Cage cage16;
//		Cage cage17;//
//		Cage cage18;//
//		Cage cage19;//
//		Cage cage1a;//
//		Cage cage1a1;//
//		Cage cage1a2;//
//
//		Cage cage2;
//		Cage cage22;
//		Cage cage23;
//		Cage cage24;//
//		Cage cage25;//
//
//		Cage cage3;
//		Cage cage31;
//
//		ReproductionProcess process1;
//		BirdReproduction fatherRe;
//		BirdReproduction motherRe;
//		BirdReproduction child1Re;
//		BirdReproduction child2Re;
//		BirdReproduction cFailRe;
//
//		ReproductionProcess process12;
//		BirdReproduction father2Re;
//		BirdReproduction mother2Re;
//		BirdReproduction child12Re;
//		BirdReproduction child22Re;
//		BirdReproduction cFail2Re;
//		BirdReproduction egg12;
//
//		ReproductionProcess process13;
//		BirdReproduction father3Re;
//		BirdReproduction mother3Re;
//		BirdReproduction egg13Re;
//		BirdReproduction egg23Re;
//		BirdReproduction cFail3Re;
//
//		ReproductionProcess process2;
//		BirdReproduction father4Re;
//		BirdReproduction mother4Re;
//		BirdReproduction child14Re;
//		BirdReproduction child24Re;
//		BirdReproduction cFail4Re;
///////////////////////////////////////////////////////////////////////////////////////////
//////USER, START
//		if (userAdmin == null){
//			userAdmin = new User();
//			userAdmin.setPassword(password);
//			userAdmin.setFullName(ADMIN);
//			userAdmin.setUsername(ADMIN);
//			userAdmin.setEmail(ADMIN + "@gmail.com");
//			userAdmin.setRole(Role.ADMIN);
//////			 save dtb
//			userRepository.save(userAdmin);
//		}
//		if (staff1 == null){
//			staff1 = new User();
//			staff1.setPassword(password);
//			staff1.setFullName(STAFF);
//			staff1.setUsername(STAFF);
//			staff1.setCreatedBy("admin");
//			staff1.setCreatedDate(new Date(2022 - 1900, 1 - 1, 1));
//			staff1.setEmail(STAFF + "@gmail.com");
//			staff1.setRole(Role.STAFF);
//////			 save dtb
//			userRepository.save(staff1);
//		}
//
//		if (staff2 == null){
//			staff2 = new User();
//			staff2.setPassword(password);
//			staff2.setUsername(STAFF2);
//			staff2.setFullName(STAFF2);
//			staff2.setCreatedBy("admin");
//			staff2.setCreatedDate(new Date(2022 - 1900, 1 - 1, 1));
//			staff2.setEmail(STAFF2 + "@gmail.com");
//			staff2.setRole(Role.STAFF);
//////			 save dtb
//			userRepository.save(staff2);
//		}
//
//		if (staff3 == null){
//			staff3 = new User();
//			staff3.setPassword(password);
//			staff3.setUsername(STAFF3);
//			staff3.setFullName(STAFF3);
//			staff3.setCreatedBy("admin");
//			staff3.setCreatedDate(new Date(2022 - 1900, 1 - 1, 1));
//			staff3.setEmail(STAFF3 + "@gmail.com");
//			staff3.setRole(Role.STAFF);
//////			 save dtb
//			userRepository.save(staff3);
//		}
//
//		if (staff4 == null){
//			staff4 = new User();
//			staff4.setPassword(password);
//			staff4.setUsername(STAFF4);
//			staff4.setFullName(STAFF4);
//			staff4.setCreatedBy("admin");
//			staff4.setCreatedDate(new Date(2022 - 1900, 1 - 1, 1));
//			staff4.setEmail(STAFF4 + "@gmail.com");
//			staff4.setRole(Role.STAFF);
//////			 save dtb
//			userRepository.save(staff4);
//		}
//		if (staff5 == null){
//			staff5 = new User();
//			staff5.setPassword(password);
//			staff5.setUsername(STAFF5);
//			staff5.setFullName(STAFF5);
//			staff5.setCreatedBy("admin");
//			staff5.setCreatedDate(new Date(2022 - 1900, 1 - 1, 1));
//			staff5.setEmail(STAFF5 + "@gmail.com");
//			staff5.setRole(Role.STAFF);
//////			 save dtb
//			userRepository.save(staff5);
//		}
//////USER END
//
//////BIRD TYPE, START
//		birdType = new BirdType();
//		birdType.setName("Chích chòe than");
//		birdType.setIncubate(Long.valueOf(16));
//		birdType.setChick(Long.valueOf(16));
//		birdType.setSwingBranch(Long.valueOf(7));
//		birdTypeRepository.save(birdType);
//
//		birdType2 = new BirdType();
//		birdType2.setName("Chích chòe lửa");
//		birdType2.setIncubate(Long.valueOf(16));
//		birdType2.setChick(Long.valueOf(16));
//		birdType2.setSwingBranch(Long.valueOf(7));
//		birdTypeRepository.save(birdType2);
//////BIRD TYPE, END
//
//////CAGE A, CAGE 4 SINGLE, START
//		cage = new Cage();
//		cage.setQuantity(1);
//		cageRepository.save(cage);
//		cage.setLocation("A" + cage.getId());
//		cageRepository.save(cage);
//
//		cage1 = new Cage();
//		cage1.setQuantity(1);
//		cageRepository.save(cage1);
//		cage1.setLocation("A"+cage1.getId());
//		cageRepository.save(cage1);
//
//		cage13 = new Cage();
//		cage13.setQuantity(1);
//		cageRepository.save(cage13);
//		cage13.setLocation("A"+cage13.getId());
//		cageRepository.save(cage13);
//
//		cage14 = new Cage();
//		cage14.setQuantity(1);
//		cageRepository.save(cage14);
//		cage14.setLocation("A"+cage14.getId());
//		cageRepository.save(cage14);
//
//		cage15 = new Cage();
//		cage15.setQuantity(1);
//		cageRepository.save(cage15);
//		cage15.setLocation("A"+cage15.getId());
//		cageRepository.save(cage15);
//
//		cage16 = new Cage();
//		cage16.setQuantity(1);
//		cageRepository.save(cage16);
//		cage16.setLocation("A"+cage16.getId());
//		cageRepository.save(cage16);
//
//		cage17 = new Cage();
//		cage17.setQuantity(1);
//		cageRepository.save(cage17);
//		cage17.setLocation("A"+cage17.getId());
//		cageRepository.save(cage17);
//
//		cage18 = new Cage();
//		cage18.setQuantity(1);
//		cageRepository.save(cage18);
//		cage18.setLocation("A"+cage18.getId());
//		cageRepository.save(cage18);
//
//		cage19 = new Cage();
//		cage19.setQuantity(1);
//		cageRepository.save(cage19);
//		cage19.setLocation("A"+cage19.getId());
//		cageRepository.save(cage19);
//
//		cage1a = new Cage();
//		cage1a.setQuantity(1);
//		cageRepository.save(cage1a);
//		cage1a.setLocation("A"+cage1a.getId());
//		cageRepository.save(cage1a);
//
//		cage1a1 = new Cage();
//		cage1a1.setQuantity(0);
//		cageRepository.save(cage1a1);
//		cage1a1.setLocation("A"+cage1a1.getId());
//		cageRepository.save(cage1a1);
//////CAGE A, CAGE 4 SINGLE, END
//
//////CAGE B, CAGE 4 REPRODUCT, START
//		cage2 = new Cage();
//		cage2.setQuantity(4);
//		cage2.setUser(staff1);
//		cageRepository.save(cage2);
//		cage2.setLocation("B" + cage2.getId());
//		cageRepository.save(cage2);
//
//		cage22 = new Cage();
//		cage22.setQuantity(4);
//		cage22.setUser(staff2);
//		cageRepository.save(cage22);
//		cage22.setLocation("B" + cage22.getId());
//		cageRepository.save(cage22);
//
//		cage23 = new Cage();
//		cage23.setQuantity(4);
//		cage23.setUser(staff2);
//		cageRepository.save(cage23);
//		cage23.setLocation("B" + cage23.getId());
//		cageRepository.save(cage23);
//
//		cage24 =new Cage();
//		cage24.setQuantity(0);
//		cage24.setUser(staff3);
//		cageRepository.save(cage24);
//		cage24.setLocation("B" + cage24.getId());
//		cageRepository.save(cage24);
//
//		cage25 =new Cage();
//		cage25.setQuantity(0);
//		cage25.setUser(staff3);
//		cageRepository.save(cage25);
//		cage25.setLocation("B" + cage25.getId());
//		cageRepository.save(cage25);
//////CAGE B, CAGE 4 REPRODUCT, END
//
//////CAEG C, CAGE 4 OTHER, START
//		cage3 =new Cage();
//		cage3.setQuantity(1);
//		cage3.setUser(staff5);
//		cageRepository.save(cage3);
//		cage3.setLocation("C" + cage3.getId());
//		cageRepository.save(cage3);
//
//		cage31 =new Cage();
//		cage31.setQuantity(0);
//		cage31.setUser(staff5);
//		cageRepository.save(cage31);
//		cage31.setLocation("C" + cage31.getId());
//		cageRepository.save(cage31);
//////CAGE C, CAGE 4 OTHER, END
//
//////SINGLE BIRD, START
//		bird = new Bird();
//		bird.setSex(Sex.MALE);
////		bird.setHatchDate(new Date(2023, 1, 1));
//		bird.setAgeRange("Trưởng thành");
//		bird.setMutation("bông");
//		bird.setMutationRate(Float.valueOf(5));
//		bird.setIsAlive(true);
//		bird.setImage("");
////		bird.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		bird.setFeatherColor("đen trắng");
//		bird.setWeight(Float.valueOf(700));
//		bird.setBirdType(birdType);
//		bird.setCage(cage);
//		birdRepository.save(bird);
//
//		bird2 = new Bird();
//		bird2.setSex(Sex.FEMALE);
////		bird2.setHatchDate(new Date(2023, 1, 1));
//		bird2.setAgeRange("Trưởng thành");
//		bird2.setMutation("");
//		bird2.setMutationRate(Float.valueOf(0));
//		bird2.setIsAlive(true);
//		bird2.setImage("");
////		bird.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		bird2.setFeatherColor("đen trắng");
//		bird2.setWeight(Float.valueOf(600));
//		bird2.setBirdType(birdType);
//		bird2.setCage(cage1);
//		birdRepository.save(bird2);
//
//		bird3 = new Bird();
//		bird3.setSex(Sex.MALE);
////		bird3.setHatchDate(new Date(2023, 1, 1));
//		bird3.setAgeRange("Trưởng thành");
//		bird3.setMutation("bông");
//		bird3.setMutationRate(Float.valueOf(5));
//		bird3.setIsAlive(true);
//		bird3.setImage("");
////		bird3.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		bird3.setFeatherColor("đen trắng");
//		bird3.setWeight(Float.valueOf(700));
//		bird3.setBirdType(birdType);
//		bird3.setCage(cage13);
//		birdRepository.save(bird3);
//
//		bird4 = new Bird();
//		bird4.setSex(Sex.FEMALE);
////		bird4.setHatchDate(new Date(2023, 1, 1));
//		bird4.setAgeRange("Trưởng thành");
//		bird4.setMutation("bông");
//		bird4.setMutationRate(Float.valueOf(1));
//		bird4.setIsAlive(true);
//		bird4.setImage("");
////		bird4.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		bird4.setFeatherColor("đen trắng");
//		bird4.setWeight(Float.valueOf(700));
//		bird4.setBirdType(birdType);
//		bird4.setCage(cage14);
//		birdRepository.save(bird4);
//
//		bird5 = new Bird();
//		bird5.setSex(Sex.MALE);
////		bird5.setHatchDate(new Date(2023, 1, 1));
//		bird5.setAgeRange("Trưởng thành");
//		bird5.setMutation("");
//		bird5.setMutationRate(Float.valueOf(0));
//		bird5.setIsAlive(true);
//		bird5.setImage("");
////		bird5.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		bird5.setFeatherColor("đen trắng");
//		bird5.setWeight(Float.valueOf(700));
//		bird5.setBirdType(birdType);
//		bird5.setCage(cage15);
//		birdRepository.save(bird5);
//
//		bird6 = new Bird();
//		bird6.setSex(Sex.FEMALE);
////		bird6.setHatchDate(new Date(2023, 1, 1));
//		bird6.setAgeRange("Trưởng thành");
//		bird6.setMutation("bông");
//		bird6.setMutationRate(Float.valueOf(1));
//		bird6.setIsAlive(true);
//		bird6.setImage("");
////		bird6.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		bird6.setFeatherColor("đen trắng");
//		bird6.setWeight(Float.valueOf(700));
//		bird6.setBirdType(birdType);
//		bird6.setCage(cage16);
//		birdRepository.save(bird6);
//////SINGLE BIRD, END
//
//////BIRDS WITH PROCESS, START
//		father = new Bird();
//		father.setSex(Sex.MALE);
////		father.setHatchDate(new Date(2023, 1, 1));
//		father.setAgeRange("Trưởng thành");
//		father.setMutation("bông");
//		father.setMutationRate(Float.valueOf(5));
//		father.setIsAlive(true);
//		father.setImage("");
////		bird.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		father.setFeatherColor("đen trắng");
//		father.setWeight(Float.valueOf(700));
//		father.setBirdType(birdType);
//		father.setCage(cage2);
//		birdRepository.save(father);
//
//		father2 = new Bird();//
//		father2.setSex(Sex.MALE);
////		father2.setHatchDate(new Date(2023, 1, 1));
//		father2.setAgeRange("Trưởng thành");
//		father2.setMutation("bông");
//		father2.setMutationRate(Float.valueOf(5));
//		father2.setIsAlive(true);
//		father2.setImage("");
////		bird.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		father2.setFeatherColor("đen trắng");
//		father2.setWeight(Float.valueOf(700));
//		father2.setBirdType(birdType);
//		father2.setCage(cage22);
//		birdRepository.save(father2);
//
//		father3 = new Bird();//
//		father3.setSex(Sex.MALE);
////		father3.setHatchDate(new Date(2023, 1, 1));
//		father3.setAgeRange("Trưởng thành");
//		father3.setMutation("bông");
//		father3.setMutationRate(Float.valueOf(5));
//		father3.setIsAlive(true);
//		father3.setImage("");
////		father3.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		father3.setFeatherColor("đen trắng");
//		father3.setWeight(Float.valueOf(700));
//		father3.setBirdType(birdType);
//		father3.setCage(cage23);
//		birdRepository.save(father3);
//
//		father4 = new Bird();//
//		father4.setSex(Sex.MALE);
////		father4.setHatchDate(new Date(2023, 1, 1));
//		father4.setAgeRange("Trưởng thành");
//		father4.setMutation("bông");
//		father4.setMutationRate(Float.valueOf(5));
//		father4.setIsAlive(true);
//		father4.setImage("");
////		father4.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		father4.setFeatherColor("đen trắng");
//		father4.setWeight(Float.valueOf(700));
//		father4.setBirdType(birdType);
//		father4.setCage(cage17);
//		birdRepository.save(father4);
//
//		mother = new Bird();
//		mother.setSex(Sex.FEMALE);
////		mother.setHatchDate(new Date(2023 - 1900, 1 - 1, 1));
//		mother.setAgeRange("Trưởng thành");
//		mother.setMutation("");
//		mother.setMutationRate(Float.valueOf(0));
//		mother.setIsAlive(true);
//		mother.setImage("");
////		mother.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		mother.setFeatherColor("đen trắng");
//		mother.setWeight(Float.valueOf(600));
//		mother.setBirdType(birdType);
//		mother.setCage(cage2);
//		birdRepository.save(mother);
//
//		mother2 = new Bird();//
//		mother2.setSex(Sex.FEMALE);
////		mother2.setHatchDate(new Date(2023 - 1900, 1 - 1, 1));
//		mother2.setAgeRange("Trưởng thành");
//		mother2.setMutation("");
//		mother2.setMutationRate(Float.valueOf(0));
//		mother2.setIsAlive(true);
//		mother2.setImage("");
////		mother2.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		mother2.setFeatherColor("đen trắng");
//		mother2.setWeight(Float.valueOf(600));
//		mother2.setBirdType(birdType);
//		mother2.setCage(cage22);
//		birdRepository.save(mother2);
//
//		mother3 = new Bird();//
//		mother3.setSex(Sex.FEMALE);
////		mother3.setHatchDate(new Date(2023 - 1900, 1 - 1, 1));
//		mother3.setAgeRange("Trưởng thành");
//		mother3.setMutation("");
//		mother3.setMutationRate(Float.valueOf(0));
//		mother3.setIsAlive(true);
//		mother3.setImage("");
////		mother3.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		mother3.setFeatherColor("đen trắng");
//		mother3.setWeight(Float.valueOf(600));
//		mother3.setBirdType(birdType);
//		mother3.setCage(cage23);
//		birdRepository.save(mother3);
//
//		mother4 = new Bird();
//		mother4.setSex(Sex.FEMALE);
////		mother4.setHatchDate(new Date(2023 - 1900, 1 - 1, 1));
//		mother4.setAgeRange("Trưởng thành");
//		mother4.setMutation("");
//		mother4.setMutationRate(Float.valueOf(0));
//		mother4.setIsAlive(true);
//		mother4.setImage("");
////		mother4.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		mother4.setFeatherColor("đen trắng");
//		mother4.setWeight(Float.valueOf(600));
//		mother4.setBirdType(birdType);
//		mother4.setCage(cage18);
//		birdRepository.save(mother4);
//	//CHILD, START
//		child1 = new Bird();
//		child1.setSex(Sex.MALE);
//		child1.setHatchDate(new Date(2023 - 1900, 10 - 1, 17));
//		child1.setAgeRange("Non");
//		child1.setMutation("bông");
//		child1.setMutationRate(Float.valueOf(5));
//		child1.setIsAlive(true);
//		child1.setImage("");
////		child1.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		child1.setFeatherColor("đen trắng");
//		child1.setWeight(Float.valueOf(100));
//		child1.setBirdType(birdType);
//		child1.setCage(cage2);
//		child1.setFatherId(father.getId());
//		child1.setMotherId(mother.getId());
//		birdRepository.save(child1);
//
//		child12 = new Bird();//
//		child12.setSex(Sex.MALE);
//		child12.setHatchDate(new Date(2023 - 1900, 10 - 1,1));
//		child12.setAgeRange("Chuyền cành");
//		child12.setMutation("bông");
//		child12.setMutationRate(Float.valueOf(5));
//		child12.setIsAlive(true);
//		child12.setImage("");
////		child12.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		child12.setFeatherColor("đen trắng");
//		child12.setWeight(Float.valueOf(100));
//		child12.setBirdType(birdType);
//		child12.setCage(cage22);
//		child12.setFatherId(father2.getId());
//		child12.setMotherId(mother2.getId());
//		birdRepository.save(child12);
//
////		child13 = new Bird();//
////		child13.setSex(Sex.MALE);
////		child13.setHatchDate(new Date(2023 - 1900, 10 - 1,1));
////		child13.setAgeRange("Non");
////		child13.setMutation("bông");
////		child13.setMutationRate(Float.valueOf(99));
////		child13.setIsAlive(true);
////		child13.setImage("");
//////		child13.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
////		child13.setFeatherColor("đen trắng");
////		child13.setWeight(Float.valueOf(100));
////		child13.setBirdType(birdType);
////		child13.setCage(cage23);
////		child13.setFatherId(father3.getId());
////		child13.setMotherId(mother3.getId());
////		birdRepository.save(child13);
//
//		child14 = new Bird();//
//		child14.setSex(Sex.MALE);
//		child14.setHatchDate(new Date(2023 - 1900, 10 - 1,1));
//		child14.setAgeRange("Trưởng thành");
//		child14.setMutation("bông");
//		child14.setMutationRate(Float.valueOf(1));
//		child14.setIsAlive(true);
//		child14.setImage("");
////		child14.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		child14.setFeatherColor("đen trắng");
//		child14.setWeight(Float.valueOf(400));
//		child14.setBirdType(birdType);
//		child14.setCage(cage19);
//		child14.setFatherId(father4.getId());
//		child14.setMotherId(mother4.getId());
//		birdRepository.save(child14);
//
//		child2 = new Bird();
//		child2.setSex(Sex.FEMALE);
//		child2.setHatchDate(new Date(2023 - 1900, 10 - 1, 18));
//		child2.setAgeRange("Non");
//		child2.setMutation("");
//		child2.setMutationRate(Float.valueOf(0));
//		child2.setIsAlive(true);
//		child2.setImage("");
////		child2.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		child2.setFeatherColor("đen trắng");
//		child2.setWeight(Float.valueOf(100));
//		child2.setBirdType(birdType);
//		child2.setCage(cage2);
//		child2.setFatherId(father.getId());
//		child2.setMotherId(mother.getId());
//		birdRepository.save(child2);
//
//		child22 = new Bird();//
//		child22.setSex(Sex.FEMALE);
//		child22.setHatchDate(new Date(2023 - 1900, 10, 2));
//		child22.setAgeRange("Chuyền cành");
//		child22.setMutation("");
//		child22.setMutationRate(Float.valueOf(0));
//		child22.setIsAlive(true);
//		child22.setImage("");
////		child22.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		child22.setFeatherColor("đen trắng");
//		child22.setWeight(Float.valueOf(100));
//		child22.setBirdType(birdType);
//		child22.setCage(cage22);
//		child22.setFatherId(father2.getId());
//		child22.setMotherId(mother2.getId());
//		birdRepository.save(child22);
//
//		child24 = new Bird();
//		child24.setSex(Sex.FEMALE);
//		child24.setHatchDate(new Date(2023 - 1900, 10 - 1, 18));
//		child24.setAgeRange("Trưởng thành");
//		child24.setMutation("");
//		child24.setMutationRate(Float.valueOf(0));
//		child24.setIsAlive(true);
//		child24.setImage("");
////		child24.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		child24.setFeatherColor("đen trắng");
//		child24.setWeight(Float.valueOf(400));
//		child24.setBirdType(birdType);
//		child24.setCage(cage1a);
//		child24.setFatherId(father4.getId());
//		child24.setMotherId(mother4.getId());
//		birdRepository.save(child24);
//	//CHILD, END
//////BIRDS WITH PROCESS, END
//
//////BIRDS 4 SALE, START
//		bird4sale = new Bird();
//		bird4sale.setSex(Sex.MALE);
////		bird4sale.setHatchDate(new Date(2023, 1, 1));
//		bird4sale.setAgeRange("Trưởng thành");
//		bird4sale.setMutation("bông");
//		bird4sale.setMutationRate(Float.valueOf(5));
//		bird4sale.setIsAlive(true);
//		bird4sale.setImage("");
////		bird4sale.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
//		bird4sale.setFeatherColor("đen trắng");
//		bird4sale.setWeight(Float.valueOf(700));
//		bird4sale.setBirdType(birdType);
//		bird4sale.setCage(cage3);
//		birdRepository.save(bird4sale);
//
//////BIRDS 4 SALE, END
//
//////PROCESS, START
//		process1 = new ReproductionProcess();
//		process1.setPairingDate(new Date(2023 - 1900,8 - 1,18));
//		process1.setExpEggHatchDate(new Date(2023 - 1900, 10 - 1,17));
//		process1.setExpSwingBranch(new Date(2023 - 1900,11 - 1, 2));
//		process1.setExpAdultBirdDate(new Date(2023 - 1900,11 - 1,9));
//		process1.setTotalEgg(3);
//		process1.setStage("Nuôi con");
//		process1.setFailEgg(1);
//		process1.setIsDone(false);
//		process1.setCage(cage2);
//		reproductionProcessRepository.save(process1);
//
//		process12 = new ReproductionProcess();//
//		process12.setPairingDate(new Date(2023 - 1900,7 - 1,7));
//		process12.setExpEggHatchDate(new Date(2023 - 1900, 10 - 1,1));
//		process12.setExpSwingBranch(new Date(2023 - 1900,10 - 1, 17));
//		process12.setExpAdultBirdDate(new Date(2023 - 1900,10 - 1,24));
//		process12.setTotalEgg(3);
//		process12.setStage("Nuôi con");
//		process12.setFailEgg(1);
//		process12.setIsDone(false);
//		process12.setCage(cage22);
//		reproductionProcessRepository.save(process12);
//
//		process13 = new ReproductionProcess();//
//		process13.setPairingDate(new Date(2023 - 1900,7 - 1,7));
////		process13.setExpEggHatchDate(new Date(2023 - 1900, 10 - 1,1));
////		process13.setExpSwingBranch(new Date(2023 - 1900,10 - 1, 17));
////		process13.setExpAdultBirdDate(new Date(2023 - 1900,10 - 1,24));
//		process13.setTotalEgg(3);
//		process13.setStage("Ấp trứng");
//		process13.setFailEgg(1);
//		process13.setIsDone(false);
//		process13.setCage(cage23);
//		reproductionProcessRepository.save(process13);
//
//		process2 = new ReproductionProcess();//
//		process2.setPairingDate(new Date(2023 - 1900,7 - 1,7));
////		process2.setExpEggHatchDate(new Date(2023 - 1900, 10 - 1,1));
////		process2.setExpSwingBranch(new Date(2023 - 1900,10 - 1, 17));
////		process2.setExpAdultBirdDate(new Date(2023 - 1900,10 - 1,24));
//		process2.setTotalEgg(3);
//		process2.setStage("");
//		process2.setFailEgg(1);
//		process2.setIsDone(true);
//		process2.setCage(cage24);
//		reproductionProcessRepository.save(process2);
//////PROCESS, END
//
//////REPRODUCTIONS, START
//		fatherRe = new BirdReproduction();
//		fatherRe.setBird(father);
//		fatherRe.setReproductionProcess(process1);
//		fatherRe.setReproductionRole(ReproductionRole.FATHER);
//		birdReproductionRepository.save(fatherRe);
//
//		father2Re = new BirdReproduction();//
//		father2Re.setBird(father2);
//		father2Re.setReproductionProcess(process12);
//		father2Re.setReproductionRole(ReproductionRole.FATHER);
//		birdReproductionRepository.save(father2Re);
//
//		father3Re = new BirdReproduction();
//		father3Re.setBird(father3);
//		father3Re.setReproductionProcess(process13);
//		father3Re.setReproductionRole(ReproductionRole.FATHER);
//		birdReproductionRepository.save(father3Re);
//
//		father4Re = new BirdReproduction();
//		father4Re.setBird(father4);
//		father4Re.setReproductionProcess(process2);
//		father4Re.setReproductionRole(ReproductionRole.FATHER);
//		birdReproductionRepository.save(father4Re);
//
//		motherRe = new BirdReproduction();
//		motherRe.setBird(mother);
//		motherRe.setReproductionProcess(process1);
//		motherRe.setReproductionRole(ReproductionRole.MOTHER);
//		birdReproductionRepository.save(motherRe);
//
//		mother2Re = new BirdReproduction();//
//		mother2Re.setBird(mother2);
//		mother2Re.setReproductionProcess(process12);
//		mother2Re.setReproductionRole(ReproductionRole.MOTHER);
//		birdReproductionRepository.save(mother2Re);
//
//		mother3Re = new BirdReproduction();//
//		mother3Re.setBird(mother3);
//		mother3Re.setReproductionProcess(process13);
//		mother3Re.setReproductionRole(ReproductionRole.MOTHER);
//		birdReproductionRepository.save(mother3Re);
//
//		mother4Re = new BirdReproduction();//
//		mother4Re.setBird(mother4);
//		mother4Re.setReproductionProcess(process2);
//		mother4Re.setReproductionRole(ReproductionRole.MOTHER);
//		birdReproductionRepository.save(mother4Re);
//
//		child1Re = new BirdReproduction();
//		child1Re.setBird(child1);
//		child1Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 1));
//		child1Re.setActEggHatchDate(new Date(2023 - 1900, 10 - 1, 17));
////		child1Re.setActSwingBranch(new Date(2023 - 1900, 10 - 1, 24));
// 		child1Re.setEggType("fer");
//		child1Re.setEggStatus("HATCHED");
//		child1Re.setFail(false);
//		child1Re.setReproductionRole(ReproductionRole.CHILD);
//		child1Re.setReproductionProcess(process1);
//		birdReproductionRepository.save(child1Re);
//
//		child12Re = new BirdReproduction();//
//		child12Re.setBird(child2);
//		child12Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 17));
//		child12Re.setActEggHatchDate(new Date(2023 - 1900, 10 - 1, 1));
//		child12Re.setActSwingBranch(new Date(2023 - 1900, 10 - 1, 17));
//		child12Re.setEggType("fer");
//		child12Re.setEggStatus("HATCHED");
//		child12Re.setFail(false);
//		child12Re.setReproductionRole(ReproductionRole.CHILD);
//		child12Re.setReproductionProcess(process12);
//		birdReproductionRepository.save(child12Re);
//
//		child14Re = new BirdReproduction();//
//		child14Re.setBird(child2);
//		child14Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 17));
//		child14Re.setActEggHatchDate(new Date(2023 - 1900, 10 - 1, 1));
//		child14Re.setActSwingBranch(new Date(2023 - 1900, 10 - 1, 17));
//		child14Re.setEggType("fer");
//		child14Re.setEggStatus("HATCHED");
//		child14Re.setFail(false);
//		child14Re.setReproductionRole(ReproductionRole.CHILD);
//		child14Re.setReproductionProcess(process2);
//		birdReproductionRepository.save(child14Re);
//
//		child2Re = new BirdReproduction();
//		child2Re.setBird(child2);
//		child2Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 2));
//		child2Re.setActEggHatchDate(new Date(2023 - 1900, 10 - 1, 18));
////		child2Re.setActSwingBranch(new Date(2023 - 1900, 10 - 1, 25));
//		child2Re.setEggType("fer");
//		child2Re.setEggStatus("HATCHED");
//		child2Re.setFail(false);
//		child2Re.setReproductionRole(ReproductionRole.CHILD);
//		child2Re.setReproductionProcess(process1);
//		birdReproductionRepository.save(child2Re);
//
//		child22Re = new BirdReproduction();//
//		child22Re.setBird(child22);
//		child22Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 18));
//		child22Re.setActEggHatchDate(new Date(2023 - 1900, 10 - 1, 2));
//		child22Re.setActSwingBranch(new Date(2023 - 1900, 10 - 1, 18));
//		child22Re.setEggType("fer");
//		child22Re.setEggStatus("HATCHED");
//		child22Re.setFail(false);
//		child22Re.setReproductionRole(ReproductionRole.CHILD);
//		child22Re.setReproductionProcess(process12);
//		birdReproductionRepository.save(child22Re);
//
//		child24Re = new BirdReproduction();//
//		child24Re.setBird(child24);
//		child24Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 18));
//		child24Re.setActEggHatchDate(new Date(2023 - 1900, 10 - 1, 2));
//		child24Re.setActSwingBranch(new Date(2023 - 1900, 10 - 1, 18));
//		child24Re.setEggType("fer");
//		child24Re.setEggStatus("HATCHED");
//		child24Re.setFail(false);
//		child24Re.setReproductionRole(ReproductionRole.CHILD);
//		child24Re.setReproductionProcess(process2);
//		birdReproductionRepository.save(child24Re);
//	//EGG, START
//		egg12 = new BirdReproduction();
//		egg12.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 18));
//		egg12.setEggType("fer");
//		egg12.setEggStatus("HATCHED");
//		egg12.setFail(false);
//		egg12.setReproductionRole(ReproductionRole.EGG);
//		egg12.setReproductionProcess(process12);
//		birdReproductionRepository.save(egg12);
//
//		egg13Re = new BirdReproduction();
//		egg13Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 18));
//		egg13Re.setEggType("fer");
//		egg13Re.setEggStatus("HATCHED");
//		egg13Re.setFail(false);
//		egg13Re.setReproductionRole(ReproductionRole.EGG);
//		egg13Re.setReproductionProcess(process13);
//		birdReproductionRepository.save(egg13Re);
//
//		egg23Re = new BirdReproduction();
//		egg23Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 19));
//		egg23Re.setEggType("fer");
//		egg23Re.setEggStatus("HATCHED");
//		egg23Re.setFail(false);
//		egg23Re.setReproductionRole(ReproductionRole.EGG);
//		egg23Re.setReproductionProcess(process13);
//		birdReproductionRepository.save(egg23Re);
//	//EGG, END
//////REPRODUCTION, END
//
//////FAIL REPRODUCT, START
//		cFailRe = new BirdReproduction();
//		cFailRe.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 19));
//		cFailRe.setEggType("fer");
//		cFailRe.setEggStatus("BROKEN");
//		cFailRe.setFail(true);
//		cFailRe.setFailDate(new Date(2023 - 1900,10 - 1,23));
//		cFailRe.setReproductionRole(ReproductionRole.EGG);
//		cFailRe.setReproductionProcess(process1);
//		birdReproductionRepository.save(cFailRe);
//
//		cFail2Re = new BirdReproduction();//
//		cFail2Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 20));
//		cFail2Re.setEggType("fer");
//		cFail2Re.setEggStatus("BROKEN");
//		cFail2Re.setFail(true);
//		cFail2Re.setFailDate(new Date(2023 - 1900,10 - 1,23));
//		cFail2Re.setReproductionRole(ReproductionRole.EGG);
//		cFail2Re.setReproductionProcess(process12);
//		birdReproductionRepository.save(cFail2Re);
//
//		cFail3Re = new BirdReproduction();//
//		cFail3Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 18));
//		cFail3Re.setEggType("fer");
//		cFail3Re.setEggStatus("BROKEN");
//		cFail3Re.setFail(true);
//		cFail3Re.setFailDate(new Date(2023 - 1900,10 - 1,19));
//		cFail3Re.setReproductionRole(ReproductionRole.EGG);
//		cFail3Re.setReproductionProcess(process13);
//		birdReproductionRepository.save(cFail3Re);
//
//		cFail4Re = new BirdReproduction();//
//		cFail4Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 18));
//		cFail4Re.setEggType("fer");
//		cFail4Re.setEggStatus("BROKEN");
//		cFail4Re.setFail(true);
//		cFail4Re.setFailDate(new Date(2023 - 1900,10 - 1,19));
//		cFail4Re.setReproductionRole(ReproductionRole.EGG);
//		cFail4Re.setReproductionProcess(process2);
//		birdReproductionRepository.save(cFail4Re);
//////FAIL REPRODUCT, END
//	}
}
