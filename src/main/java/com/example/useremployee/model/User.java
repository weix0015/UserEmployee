package com.example.useremployee.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(unique = true)
    private String email;
    //@JsonIgnore - skal klares med DTO
    private String password;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonBackReference //do not include in json response
    private Employee employee;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
