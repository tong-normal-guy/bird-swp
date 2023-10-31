package com.example.birdReproductionManagement;

import com.example.birdReproductionManagement.entity.*;
import com.example.birdReproductionManagement.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;

@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
public class BirdReproductionManagementApplication implements CommandLineRunner {
//public class BirdReproductionManagementApplication{


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


	@Override
	public void run(String... args) throws Exception {

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
//		Cage cage = new Cage();
//		Cage cage1 = new Cage();
//		Cage cage13 = new Cage();
//		Cage cage14 = new Cage();
//		Cage cage15 = new Cage();
//		Cage cage16 = new Cage();
//		Cage cage17 = new Cage();//
//		Cage cage18 = new Cage();//
//		Cage cage19 = new Cage();//
//		Cage cage1a = new Cage();//
//		Cage cage1a1 = new Cage();//
//		Cage cage1a2 = new Cage();//
//
//		Cage cage2 = new Cage();
//		Cage cage22 = new Cage();
//		Cage cage23 = new Cage();
//		Cage cage24 = new Cage();//
//		Cage cage25 = new Cage();//
//
//		Cage cage3 = new Cage();
//		Cage cage31 = new Cage();
//
//		ReproductionProcess process1 = new ReproductionProcess();
//		BirdReproduction fatherRe = new BirdReproduction();
//		BirdReproduction motherRe = new BirdReproduction();
//		BirdReproduction child1Re = new BirdReproduction();
//		BirdReproduction child2Re = new BirdReproduction();
//		BirdReproduction cFailRe = new BirdReproduction();
//
//		ReproductionProcess process12 = new ReproductionProcess();
//		BirdReproduction father2Re = new BirdReproduction();
//		BirdReproduction mother2Re = new BirdReproduction();
//		BirdReproduction child12Re = new BirdReproduction();
//		BirdReproduction child22Re = new BirdReproduction();
//		BirdReproduction cFail2Re = new BirdReproduction();
//		BirdReproduction egg12 = new BirdReproduction();
//
//		ReproductionProcess process13 = new ReproductionProcess();
//		BirdReproduction father3Re = new BirdReproduction();
//		BirdReproduction mother3Re = new BirdReproduction();
//		BirdReproduction egg13Re = new BirdReproduction();
//		BirdReproduction egg23Re = new BirdReproduction();
//		BirdReproduction cFail3Re = new BirdReproduction();
//
//		ReproductionProcess process2 = new ReproductionProcess();
//		BirdReproduction father4Re = new BirdReproduction();
//		BirdReproduction mother4Re = new BirdReproduction();
//		BirdReproduction child14Re = new BirdReproduction();
//		BirdReproduction child24Re = new BirdReproduction();
//		BirdReproduction cFail4Re = new BirdReproduction();
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
//		cage.setQuantity(0);
//		cage.setAvailable(true);
//		cageRepository.save(cage);
//		cage.setLocation("A" + cage.getId());
//		cageRepository.save(cage);
//
//		cage1.setQuantity(0);
//		cage1.setAvailable(true);
//		cageRepository.save(cage1);
//		cage1.setLocation("A"+cage1.getId());
//		cageRepository.save(cage1);
//
//		cage13.setQuantity(0);
//		cage13.setAvailable(true);
//		cageRepository.save(cage13);
//		cage13.setLocation("A"+cage13.getId());
//		cageRepository.save(cage13);
//
//		cage14.setQuantity(0);
//		cage14.setAvailable(true);
//		cageRepository.save(cage14);
//		cage14.setLocation("A"+cage14.getId());
//		cageRepository.save(cage14);
//
//		cage15.setQuantity(0);
//		cage15.setAvailable(true);
//		cageRepository.save(cage15);
//		cage15.setLocation("A"+cage15.getId());
//		cageRepository.save(cage15);
//
//		cage16.setQuantity(0);
//		cage16.setAvailable(true);
//		cageRepository.save(cage16);
//		cage16.setLocation("A"+cage16.getId());
//		cageRepository.save(cage16);
//
//		cage17.setQuantity(0);
//		cage17.setAvailable(true);
//		cageRepository.save(cage17);
//		cage17.setLocation("A"+cage17.getId());
//		cageRepository.save(cage17);
//
//		cage18.setQuantity(0);
//		cage17.setAvailable(true);
//		cageRepository.save(cage18);
//		cage18.setLocation("A"+cage18.getId());
//		cageRepository.save(cage18);
//
//		cage19.setQuantity(0);
//		cage19.setAvailable(true);
//		cageRepository.save(cage19);
//		cage19.setLocation("A"+cage19.getId());
//		cageRepository.save(cage19);
//
//		cage1a.setQuantity(0);
//		cage1a.setAvailable(true);
//		cageRepository.save(cage1a);
//		cage1a.setLocation("A"+cage1a.getId());
//		cageRepository.save(cage1a);
//
//		cage1a1.setQuantity(0);
//		cage1a1.setAvailable(true);
//		cageRepository.save(cage1a1);
//		cage1a1.setLocation("A"+cage1a1.getId());
//		cageRepository.save(cage1a1);
//////CAGE A, CAGE 4 SINGLE, END
//
//////CAGE B, CAGE 4 REPRODUCT, START
//		cage2.setQuantity(0);
//		cage2.setAvailable(true);
//		cage2.setUser(staff1);
//		cageRepository.save(cage2);
//		cage2.setLocation("B" + cage2.getId());
//		cageRepository.save(cage2);
//
//		cage22.setQuantity(0);
//		cage22.setAvailable(true);
//		cage22.setUser(staff2);
//		cageRepository.save(cage22);
//		cage22.setLocation("B" + cage22.getId());
//		cageRepository.save(cage22);
//
//		cage23.setQuantity(0);
//		cage23.setAvailable(true);
//		cage23.setUser(staff2);
//		cageRepository.save(cage23);
//		cage23.setLocation("B" + cage23.getId());
//		cageRepository.save(cage23);
//
//		cage24.setQuantity(0);
//		cage24.setAvailable(true);
//		cage24.setUser(staff3);
//		cageRepository.save(cage24);
//		cage24.setLocation("B" + cage24.getId());
//		cageRepository.save(cage24);
//
//		cage25.setQuantity(0);
//		cage25.setAvailable(true);
//		cage25.setUser(staff3);
//		cageRepository.save(cage25);
//		cage25.setLocation("B" + cage25.getId());
//		cageRepository.save(cage25);
//////CAGE B, CAGE 4 REPRODUCT, END
//
//////CAEG C, CAGE 4 OTHER, START
//		cage3.setQuantity(0);
//		cage3.setAvailable(true);
//		cage3.setUser(staff5);
//		cageRepository.save(cage3);
//		cage3.setLocation("C" + cage3.getId());
//		cageRepository.save(cage3);
//
//		cage31.setQuantity(0);
//		cage31.setAvailable(true);
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
//		cage.setQuantity(cage.getQuantity()+1);
//		cageRepository.save(cage);
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
//		cage1.setQuantity(cage1.getQuantity()+1);
//		cageRepository.save(cage1);
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
//		cage13.setQuantity(cage13.getQuantity()+1);
//		cageRepository.save(cage13);
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
//		cage14.setQuantity(cage14.getQuantity()+1);
//		cageRepository.save(cage14);
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
//		cage15.setQuantity(cage15.getQuantity()+1);
//		cageRepository.save(cage15);
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
//		cage16.setQuantity(cage16.getQuantity()+1);
//		cageRepository.save(cage16);
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
//		cage2.setQuantity(cage2.getQuantity()+1);
//		cageRepository.save(cage2);
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
//		cage22.setQuantity(cage22.getQuantity()+1);
//		cageRepository.save(cage22);
//
//		father3 = new Bird();//
//		father3.setSex(Sex.MALE);
////		father3.setHatchDate(new Date(2023, 1, 1));
//		father3.setAgeRange("Trưởng thành");
//		father3.setMutation("bông");
//		father3.setMutationRate(Float.valueOf(5));
//		father3.setIsAlive(true);
//		father3.setImage("");
//		father3.setFeatherColor("đen trắng");
//		father3.setWeight(Float.valueOf(700));
//		father3.setBirdType(birdType);
//		father3.setCage(cage23);
//		birdRepository.save(father3);
//		cage23.setQuantity(cage23.getQuantity()+1);
//		cageRepository.save(cage23);
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
//		cage17.setQuantity(cage17.getQuantity()+1);
//		cageRepository.save(cage17);
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
//		cage2.setQuantity(cage2.getQuantity()+1);
//		cageRepository.save(cage2);
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
//		cage22.setQuantity(cage22.getQuantity()+1);
//		cageRepository.save(cage22);
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
//		cage23.setQuantity(cage23.getQuantity()+1);
//		cageRepository.save(cage23);
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
//		cage18.setQuantity(cage18.getQuantity()+1);
//		cageRepository.save(cage18);
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
//		cage2.setQuantity(cage2.getQuantity()+1);
//		cageRepository.save(cage2);
//
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
//		cage22.setQuantity(cage22.getQuantity()+1);
//		cageRepository.save(cage22);
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
//		cage19.setQuantity(cage19.getQuantity()+1);
//		cageRepository.save(cage19);
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
//		cage2.setQuantity(cage2.getQuantity()+1);
//		cageRepository.save(cage2);
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
//		cage22.setQuantity(cage22.getQuantity()+1);
//		cageRepository.save(cage22);
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
//		cage1a.setQuantity(cage1a.getQuantity()+1);
//		cageRepository.save(cage1a);
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
//		bird4sale.setFeatherColor("đen trắng");
//		bird4sale.setWeight(Float.valueOf(700));
//		bird4sale.setBirdType(birdType);
//		bird4sale.setCage(cage3);
//		birdRepository.save(bird4sale);
//		cage3.setQuantity(cage3.getQuantity()+3);
//		cageRepository.save(cage3);
//
//////BIRDS 4 SALE, END
//
//////PROCESS, START
//
//		process1.setPairingDate(new Date(2023 - 1900,8 - 1,18));
//		process1.setTotalEgg(0);
//		process1.setFailEgg(0);
//		process1.setStage("Nuôi con");
//		process1.setIsDone(false);
//		process1.setCage(cage2);
//		reproductionProcessRepository.save(process1);
//
//
//		process12.setPairingDate(new Date(2023 - 1900,7 - 1,7));
//		process12.setTotalEgg(0);
//		process12.setFailEgg(0);
//		process12.setStage("Nuôi con");
//		process12.setIsDone(false);
//		process12.setCage(cage22);
//		reproductionProcessRepository.save(process12);
//
//
//		process13.setPairingDate(new Date(2023 - 1900,7 - 1,7));
//		process13.setTotalEgg(0);
//		process13.setFailEgg(0);
//		process13.setStage("Ấp trứng");
//		process13.setIsDone(false);
//		process13.setCage(cage23);
//		reproductionProcessRepository.save(process13);
//
//
//		process2.setPairingDate(new Date(2023 - 1900,7 - 1,7));
//		process2.setTotalEgg(0);
//		process2.setFailEgg(0);
//		process2.setStage("");
//		process2.setIsDone(true);
//		process2.setCage(cage24);
//		reproductionProcessRepository.save(process2);
//////PROCESS, END
//
//////REPRODUCTIONS, START
//		fatherRe.setBird(father);
//		fatherRe.setReproductionProcess(process1);
//		fatherRe.setReproductionRole(ReproductionRole.FATHER);
//		birdReproductionRepository.save(fatherRe);
//
//		father2Re.setBird(father2);
//		father2Re.setReproductionProcess(process12);
//		father2Re.setReproductionRole(ReproductionRole.FATHER);
//		birdReproductionRepository.save(father2Re);
//
//		father3Re.setBird(father3);
//		father3Re.setReproductionProcess(process13);
//		father3Re.setReproductionRole(ReproductionRole.FATHER);
//		birdReproductionRepository.save(father3Re);
//
//		father4Re.setBird(father4);
//		father4Re.setReproductionProcess(process2);
//		father4Re.setReproductionRole(ReproductionRole.FATHER);
//		birdReproductionRepository.save(father4Re);
//
//		motherRe.setBird(mother);
//		motherRe.setReproductionProcess(process1);
//		motherRe.setReproductionRole(ReproductionRole.MOTHER);
//		birdReproductionRepository.save(motherRe);
//
//		mother2Re.setBird(mother2);
//		mother2Re.setReproductionProcess(process12);
//		mother2Re.setReproductionRole(ReproductionRole.MOTHER);
//		birdReproductionRepository.save(mother2Re);
//
//		mother3Re.setBird(mother3);
//		mother3Re.setReproductionProcess(process13);
//		mother3Re.setReproductionRole(ReproductionRole.MOTHER);
//		birdReproductionRepository.save(mother3Re);
//
//		mother4Re.setBird(mother4);
//		mother4Re.setReproductionProcess(process2);
//		mother4Re.setReproductionRole(ReproductionRole.MOTHER);
//		birdReproductionRepository.save(mother4Re);
//
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
//		process1.setTotalEgg(process1.getTotalEgg()+1);
//		reproductionProcessRepository.save(process1);
//
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
//		process12.setTotalEgg(process12.getTotalEgg()+1);
//		reproductionProcessRepository.save(process12);
//
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
//		process2.setTotalEgg(process2.getTotalEgg()+1);
//		reproductionProcessRepository.save(process2);
//
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
//		process1.setTotalEgg(process1.getTotalEgg()+1);
//		reproductionProcessRepository.save(process1);
//
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
//		process12.setTotalEgg(process12.getTotalEgg()+1);
//		reproductionProcessRepository.save(process12);
//
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
//		process2.setTotalEgg(process2.getTotalEgg()+1);
//		reproductionProcessRepository.save(process2);
//	//EGG, START
//		egg12.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 18));
//		egg12.setEggType("fer");
//		egg12.setEggStatus("HATCHED");
//		egg12.setFail(false);
//		egg12.setReproductionRole(ReproductionRole.EGG);
//		egg12.setReproductionProcess(process12);
//		birdReproductionRepository.save(egg12);
//		process12.setTotalEgg(process12.getTotalEgg()+1);
//		reproductionProcessRepository.save(process12);
//
//		egg13Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 18));
//		egg13Re.setEggType("fer");
//		egg13Re.setEggStatus("HATCHED");
//		egg13Re.setFail(false);
//		egg13Re.setReproductionRole(ReproductionRole.EGG);
//		egg13Re.setReproductionProcess(process13);
//		birdReproductionRepository.save(egg13Re);
//		process13.setTotalEgg(process13.getTotalEgg()+1);
//		reproductionProcessRepository.save(process13);
//		process13.setTotalEgg(process13.getTotalEgg()+1);
//		reproductionProcessRepository.save(process13);
//
//		egg23Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 19));
//		egg23Re.setEggType("fer");
//		egg23Re.setEggStatus("HATCHED");
//		egg23Re.setFail(false);
//		egg23Re.setReproductionRole(ReproductionRole.EGG);
//		egg23Re.setReproductionProcess(process13);
//		birdReproductionRepository.save(egg23Re);
//		process13.setTotalEgg(process13.getTotalEgg()+1);
//		reproductionProcessRepository.save(process13);
//		process13.setTotalEgg(process13.getTotalEgg()+1);
//		reproductionProcessRepository.save(process13);
//	//EGG, END
//////REPRODUCTION, END
//
//////FAIL REPRODUCT, START
//		cFailRe.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 19));
//		cFailRe.setEggType("fer");
//		cFailRe.setEggStatus("BROKEN");
//		cFailRe.setFail(true);
//		cFailRe.setFailDate(new Date(2023 - 1900,10 - 1,23));
//		cFailRe.setReproductionRole(ReproductionRole.EGG);
//		cFailRe.setReproductionProcess(process1);
//		birdReproductionRepository.save(cFailRe);
//		process1.setTotalEgg(process1.getTotalEgg()+1);
//		process1.setFailEgg(process1.getFailEgg()+1);
//		reproductionProcessRepository.save(process1);
//
//		cFail2Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 20));
//		cFail2Re.setEggType("fer");
//		cFail2Re.setEggStatus("BROKEN");
//		cFail2Re.setFail(true);
//		cFail2Re.setFailDate(new Date(2023 - 1900,10 - 1,23));
//		cFail2Re.setReproductionRole(ReproductionRole.EGG);
//		cFail2Re.setReproductionProcess(process12);
//		birdReproductionRepository.save(cFail2Re);
//		process12.setTotalEgg(process12.getTotalEgg()+1);
//		process12.setFailEgg(process12.getFailEgg()+1);
//		reproductionProcessRepository.save(process12);
//
//		cFail3Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 18));
//		cFail3Re.setEggType("fer");
//		cFail3Re.setEggStatus("BROKEN");
//		cFail3Re.setFail(true);
//		cFail3Re.setFailDate(new Date(2023 - 1900,10 - 1,19));
//		cFail3Re.setReproductionRole(ReproductionRole.EGG);
//		cFail3Re.setReproductionProcess(process13);
//		birdReproductionRepository.save(cFail3Re);
//		process13.setTotalEgg(process13.getTotalEgg()+1);
//		process13.setFailEgg(process13.getFailEgg()+1);
//		reproductionProcessRepository.save(process13);
//
//		cFail4Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 18));
//		cFail4Re.setEggType("fer");
//		cFail4Re.setEggStatus("BROKEN");
//		cFail4Re.setFail(true);
//		cFail4Re.setFailDate(new Date(2023 - 1900,10 - 1,19));
//		cFail4Re.setReproductionRole(ReproductionRole.EGG);
//		cFail4Re.setReproductionProcess(process2);
//		birdReproductionRepository.save(cFail4Re);
//		process1.setTotalEgg(process1.getTotalEgg()+1);
//		reproductionProcessRepository.save(process1);
//		process2.setTotalEgg(process2.getTotalEgg()+1);
//		process2.setFailEgg(process2.getFailEgg()+1);
//		reproductionProcessRepository.save(process2);
////FAIL REPRODUCT, END
	}
}
