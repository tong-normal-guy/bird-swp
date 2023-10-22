package com.example.birdReproductionManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "full_name", columnDefinition = "nvarchar(255)")
    private String fullName;
    @Column(name = "image", columnDefinition = "varchar(1000)")
    private String image;
    @Column(name = "created_by", columnDefinition = "nvarchar(255)")
    private String createdBy;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "image", columnDefinition = "nvarchar(500)")
    private String userImage;


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Cage> cages = new ArrayList<>();
}
