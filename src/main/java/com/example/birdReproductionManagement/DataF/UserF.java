package com.example.birdReproductionManagement.DataF;

import com.example.birdReproductionManagement.model.Bird;
import com.example.birdReproductionManagement.model.Sex;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;
@Builder
public class UserF {
    Long id;
    Sex sex = Sex.valueOf("MALE");
    Date hatchDate = new Date(1/1/2023);
    String ageRange = "truong thanh";
    String mutation = "bong";
    float mutationRate = 99;
    Boolean isAlive = true;
    String img = "";
    String featherColor = "den trang";
    float weight = 600;

    Bird bird = new Bird();

//    bird.setId(1);
//    bird.Sex.setSex("MALE");
//    bird.setAgeRange("truong thanh");
//    bird.mutation("bong");
//    bird.setIsAlive(true);
//    bird.setImg("");
//    bird.FeatherColor("den trang");
//    bird.setWeight(600);
}
