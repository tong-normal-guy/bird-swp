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
public class BirdReproductionManagementApplication implements CommandLineRunner {


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
	public static void main(String[] args) {
		SpringApplication.run(BirdReproductionManagementApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		User userAdmin = userRepository.findByUsername(ADMIN);
		User staff1 = userRepository.findByUsername(STAFF);
		User staff2 = userRepository.findByUsername(STAFF2);
		User staff3 = userRepository.findByUsername(STAFF3);
		User staff4 = userRepository.findByUsername(STAFF4);

		Bird bird;
		Bird bird2;

		Bird father;
		Bird mother;
		Bird child1;
		Bird child2;

		Bird father2;
		Bird mother2;
		Bird child12;
		Bird child22;

		BirdType birdType;
		BirdType birdType2;

		Cage cage;
		Cage cage1;

		Cage cage2;
		Cage cage22;

		ReproductionProcess process1;
		BirdReproduction fatherRe;
		BirdReproduction motherRe;
		BirdReproduction child1Re;
		BirdReproduction child2Re;
		BirdReproduction cFailRe;

		ReproductionProcess process12;
		BirdReproduction father2Re;
		BirdReproduction mother2Re;
		BirdReproduction child12Re;
		BirdReproduction child22Re;
		BirdReproduction cFail2Re;
///////////////////////////////////////////////////////////////////////////
		if (userAdmin == null){
			userAdmin = new User();
			userAdmin.setPassword(password);
			userAdmin.setFullName(ADMIN);
			userAdmin.setUsername(ADMIN);
			userAdmin.setEmail(ADMIN + "@gmail.com");
			userAdmin.setRole(Role.ADMIN);
////			 save dtb
//			userRepository.save(userAdmin);
		}
		if (staff1 == null){
			staff1 = new User();
			staff1.setPassword(password);
			staff1.setFullName(STAFF);
			staff1.setUsername(STAFF);
			staff1.setCreatedBy("admin");
			staff1.setCreatedDate(new Date(2023 - 1900, 1 - 1, 1));
			staff1.setEmail(STAFF + "@gmail.com");
			staff1.setRole(Role.STAFF);
////			 save dtb
//			userRepository.save(staff1);
		}

		if (staff2 == null){
			staff2 = new User();
			staff2.setPassword(password);
			staff2.setUsername(STAFF2);
			staff2.setFullName(STAFF2);
			staff2.setCreatedBy("admin");
			staff1.setCreatedDate(new Date(2023 - 1900, 1 - 1, 1));
			staff2.setEmail(STAFF2 + "@gmail.com");
			staff2.setRole(Role.STAFF);
////			 save dtb
//			userRepository.save(staff2);
		}

		if (staff3 == null){
			staff3 = new User();
			staff3.setPassword(password);
			staff3.setUsername(STAFF3);
			staff3.setFullName(STAFF3);
			staff3.setCreatedBy("admin");
			staff1.setCreatedDate(new Date(2023 - 1900, 1 - 1, 1));
			staff3.setEmail(STAFF3 + "@gmail.com");
			staff3.setRole(Role.STAFF);
////			 save dtb
//			userRepository.save(staff3);
		}

		if (staff4 == null){
			staff4 = new User();
			staff4.setPassword(password);
			staff4.setUsername(STAFF4);
			staff4.setFullName(STAFF4);
			staff4.setCreatedBy("admin");
			staff1.setCreatedDate(new Date(2023 - 1900, 1 - 1, 1));
			staff4.setEmail(STAFF4 + "@gmail.com");
			staff4.setRole(Role.STAFF);
////			 save dtb
//			userRepository.save(staff4);
		}



		birdType = new BirdType();
		birdType.setName("than");
		birdType.setIncubate(Long.valueOf(16));
		birdType.setChick(Long.valueOf(16));
		birdType.setSwingBranch(Long.valueOf(7));
//		birdTypeRepository.save(birdType);

		birdType2 = new BirdType();
		birdType2.setName("lua");
		birdType2.setIncubate(Long.valueOf(16));
		birdType2.setChick(Long.valueOf(16));
		birdType2.setSwingBranch(Long.valueOf(7));
//		birdTypeRepository.save(birdType2);

		cage = new Cage();
		cage.setLocation("A01");
		cage.setQuantity(1);
//		cageRepository.save(cage);

		cage1 = new Cage();
		cage1.setLocation("A02");
		cage1.setQuantity(1);
//		cageRepository.save(cage1);

		cage2 = new Cage();
		cage2.setLocation("B01");
		cage2.setQuantity(4);
		cage2.setUser(staff1);
//		cageRepository.save(cage2);

		cage22 = new Cage();
		cage22.setLocation("B02");
		cage22.setQuantity(4);
		cage22.setUser(staff2);
//		cageRepository.save(cage22);

		bird = new Bird();
		bird.setSex(Sex.MALE);
//		bird.setHatchDate(new Date(2023, 1, 1));
		bird.setAgeRange("truong thanh");
		bird.setMutation("bong");
		bird.setMutationRate(Float.valueOf(99));
		bird.setIsAlive(true);

		bird.setImage("");
//		bird.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
		bird.setFeatherColor("den trang");
		bird.setWeight(Float.valueOf(700));
		bird.setBirdType(birdType);
		bird.setCage(cage);

//		birdRepository.save(bird);

		bird2 = new Bird();
		bird2.setSex(Sex.FEMALE);
//		bird2.setHatchDate(new Date(2023, 1, 1));
		bird2.setAgeRange("truong thanh");
		bird2.setMutation("");
		bird2.setMutationRate(Float.valueOf(0));
		bird2.setIsAlive(true);
		bird2.setImage("");
//		bird.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
		bird2.setFeatherColor("den trang");
		bird2.setWeight(Float.valueOf(600));
		bird2.setBirdType(birdType);
		bird2.setCage(cage2);
//		birdRepository.save(bird2);

		father = new Bird();
		father.setSex(Sex.MALE);
//		father.setHatchDate(new Date(2023, 1, 1));
		father.setAgeRange("truong thanh");
		father.setMutation("bong");
		father.setMutationRate(Float.valueOf(99));
		father.setIsAlive(true);
		father.setImage("");
//		bird.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
		father.setFeatherColor("den trang");
		father.setWeight(Float.valueOf(700));
		father.setBirdType(birdType);
		father.setCage(cage2);
//		birdRepository.save(father);

		father2 = new Bird();//
		father2.setSex(Sex.MALE);
//		father2.setHatchDate(new Date(2023, 1, 1));
		father2.setAgeRange("truong thanh");
		father2.setMutation("bong");
		father2.setMutationRate(Float.valueOf(99));
		father2.setIsAlive(true);
		father2.setImage("");
//		bird.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
		father2.setFeatherColor("den trang");
		father2.setWeight(Float.valueOf(700));
		father2.setBirdType(birdType);
		father2.setCage(cage22);
//		birdRepository.save(father2);

		mother = new Bird();
		mother.setSex(Sex.FEMALE);
		mother.setHatchDate(new Date(2023 - 1900, 1 - 1, 1));
		mother.setAgeRange("truong thanh");
		mother.setMutation("");
		mother.setMutationRate(Float.valueOf(0));
		mother.setIsAlive(true);
		mother.setImage("");
//		mother.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
		mother.setFeatherColor("den trang");
		mother.setWeight(Float.valueOf(600));
		mother.setBirdType(birdType);
		mother.setCage(cage2);
//		birdRepository.save(mother);

		mother2 = new Bird();//
		mother2.setSex(Sex.FEMALE);
		mother2.setHatchDate(new Date(2023 - 1900, 1 - 1, 1));
		mother2.setAgeRange("truong thanh");
		mother2.setMutation("");
		mother2.setMutationRate(Float.valueOf(0));
		mother2.setIsAlive(true);
		mother2.setImage("");
//		mother2.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
		mother2.setFeatherColor("den trang");
		mother2.setWeight(Float.valueOf(600));
		mother2.setBirdType(birdType);
		mother2.setCage(cage22);
//		birdRepository.save(mother2);

		child1 = new Bird();
		child1.setSex(Sex.MALE);
		child1.setHatchDate(new Date(2023 - 1900, 10 - 1, 17));
		child1.setAgeRange("non");
		child1.setMutation("bong");
		child1.setMutationRate(Float.valueOf(99));
		child1.setIsAlive(true);
		child1.setImage("");
//		child1.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
		child1.setFeatherColor("den trang");
		child1.setWeight(Float.valueOf(100));
		child1.setBirdType(birdType);
		child1.setCage(cage2);
//		birdRepository.save(child1);

		child12 = new Bird();//
		child12.setSex(Sex.MALE);
		child12.setHatchDate(new Date(2023 - 1900, 10 - 1,1));
		child12.setAgeRange("chuyen");
		child12.setMutation("bong");
		child12.setMutationRate(Float.valueOf(99));
		child12.setIsAlive(true);
		child12.setImage("");
//		child12.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
		child12.setFeatherColor("den trang");
		child12.setWeight(Float.valueOf(100));
		child12.setBirdType(birdType);
		child12.setCage(cage22);
//		birdRepository.save(child12);

		child2 = new Bird();
		child2.setSex(Sex.FEMALE);
		child2.setHatchDate(new Date(2023 - 1900, 10 - 1, 18));
		child2.setAgeRange("non");
		child2.setMutation("");
		child2.setMutationRate(Float.valueOf(0));
		child2.setIsAlive(true);
		child2.setImage("");
//		child2.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
		child2.setFeatherColor("den trang");
		child2.setWeight(Float.valueOf(100));
		child2.setBirdType(birdType);
		child2.setCage(cage2);
//		birdRepository.save(child2);

		child22 = new Bird();//
		child22.setSex(Sex.FEMALE);
		child22.setHatchDate(new Date(2023 - 1900, 10, 2));
		child22.setAgeRange("chuyen");
		child22.setMutation("");
		child22.setMutationRate(Float.valueOf(0));
		child22.setIsAlive(true);
		child22.setImage("");
//		child22.setImage("https://firebasestorage.googleapis.com/v0/b/birdreproductionswp.appspot.com/o/imgsrc%2FthanBong.png?alt=media&token=945eaa26-1640-4385-a52d-8fbb183504d4&_gl=1*czf4q3*_ga*NTA4MjM3NDgwLjE2OTQ5MzYyNjU.*_ga_CW55HF8NVT*MTY5NzQ3MzUwMC43LjEuMTY5NzQ3MzUxNS40NS4wLjA.");
		child22.setFeatherColor("den trang");
		child22.setWeight(Float.valueOf(100));
		child22.setBirdType(birdType);
		child22.setCage(cage22);
//		birdRepository.save(child22);

		process1 = new ReproductionProcess();
		process1.setPairingDate(new Date(2023 - 1900,8 - 1,18));
		process1.setExpEggHatchDate(new Date(2023 - 1900, 10 - 1,17));
		process1.setExpSwingBranch(new Date(2023 - 1900,11 - 1, 2));
		process1.setExpAdultBirdDate(new Date(2023 - 1900,11 - 1,9));
		process1.setTotalEgg(3);
		process1.setStage("chim non");
		process1.setFailEgg(1);
		process1.setIsDone(false);
		process1.setCage(cage2);
//		reproductionProcessRepository.save(process1);

		process12 = new ReproductionProcess();//
		process12.setPairingDate(new Date(2023 - 1900,7 - 1,7));
		process12.setExpEggHatchDate(new Date(2023 - 1900, 10 - 1,1));
		process12.setExpSwingBranch(new Date(2023 - 1900,10 - 1, 17));
		process12.setExpAdultBirdDate(new Date(2023 - 1900,10 - 1,24));
		process12.setTotalEgg(3);
		process12.setStage("chim chuyen");
		process12.setFailEgg(1);
		process12.setIsDone(false);
		process12.setCage(cage22);
//		reproductionProcessRepository.save(process12);

		fatherRe = new BirdReproduction();
		fatherRe.setBird(father);
		fatherRe.setReproductionProcess(process1);
		fatherRe.setReproductionRole(ReproductionRole.FATHER);
//		birdReproductionRepository.save(fatherRe);

		father2Re = new BirdReproduction();//
		father2Re.setBird(father2);
		father2Re.setReproductionProcess(process12);
		father2Re.setReproductionRole(ReproductionRole.FATHER);
//		birdReproductionRepository.save(father2Re);

		motherRe = new BirdReproduction();
		motherRe.setBird(mother);
		motherRe.setReproductionProcess(process1);
		motherRe.setReproductionRole(ReproductionRole.MOTHER);
//		birdReproductionRepository.save(motherRe);

		mother2Re = new BirdReproduction();//
		mother2Re.setBird(mother2);
		mother2Re.setReproductionProcess(process12);
		mother2Re.setReproductionRole(ReproductionRole.MOTHER);
//		birdReproductionRepository.save(mother2Re);

		child1Re = new BirdReproduction();
		child1Re.setBird(child1);
		child1Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 1));
		child1Re.setActEggHatchDate(new Date(2023 - 1900, 10 - 1, 17));
//		child1Re.setActSwingBranch(new Date(2023 - 1900, 10 - 1, 24));
 		child1Re.setEggType("fer");
		child1Re.setEggStatus("hatched");
		child1Re.setFail(false);
		child1Re.setReproductionRole(ReproductionRole.CHILD);
		child1Re.setReproductionProcess(process1);
//		birdReproductionRepository.save(child1Re);

		child12Re = new BirdReproduction();//
		child12Re.setBird(child2);
		child12Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 17));
		child12Re.setActEggHatchDate(new Date(2023 - 1900, 10 - 1, 1));
		child12Re.setActSwingBranch(new Date(2023 - 1900, 10 - 1, 17));
		child12Re.setEggType("fer");
		child12Re.setEggStatus("hatched");
		child12Re.setFail(false);
		child12Re.setReproductionRole(ReproductionRole.CHILD);
		child12Re.setReproductionProcess(process12);
//		birdReproductionRepository.save(child12Re);

		child2Re = new BirdReproduction();
		child2Re.setBird(child2);
		child2Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 2));
		child2Re.setActEggHatchDate(new Date(2023 - 1900, 10 - 1, 18));
//		child2Re.setActSwingBranch(new Date(2023 - 1900, 10 - 1, 25));
		child2Re.setEggType("fer");
		child2Re.setEggStatus("hatched");
		child2Re.setFail(false);
		child2Re.setReproductionRole(ReproductionRole.CHILD);
		child2Re.setReproductionProcess(process1);
//		birdReproductionRepository.save(child2Re);

		child22Re = new BirdReproduction();//
		child22Re.setBird(child22);
		child22Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 18));
		child22Re.setActEggHatchDate(new Date(2023 - 1900, 10 - 1, 2));
		child22Re.setActSwingBranch(new Date(2023 - 1900, 10 - 1, 18));
		child22Re.setEggType("fer");
		child22Re.setEggStatus("hatched");
		child22Re.setFail(false);
		child22Re.setReproductionRole(ReproductionRole.CHILD);
		child22Re.setReproductionProcess(process12);
//		birdReproductionRepository.save(child22Re);

		cFailRe = new BirdReproduction();
		cFailRe.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 19));
		cFailRe.setEggType("fer");
		cFailRe.setEggStatus("broken");
		cFailRe.setFail(true);
		cFailRe.setFailDate(new Date(2023 - 1900,10 - 1,23));
		cFailRe.setReproductionRole(ReproductionRole.EGG);
		cFailRe.setReproductionProcess(process1);
//		birdReproductionRepository.save(cFailRe);

		cFail2Re = new BirdReproduction();//
		cFail2Re.setEggLaidDate(new Date(2023 - 1900, 10 - 1, 20));
		cFail2Re.setEggType("fer");
		cFail2Re.setEggStatus("broken");
		cFail2Re.setFail(true);
		cFail2Re.setFailDate(new Date(2023 - 1900,10 - 1,23));
		cFail2Re.setReproductionRole(ReproductionRole.EGG);
		cFail2Re.setReproductionProcess(process12);
//		birdReproductionRepository.save(cFail2Re);

	}
}
