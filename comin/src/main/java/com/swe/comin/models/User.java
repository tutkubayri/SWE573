package com.swe.comin.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User{

    public User(){}

    public User(@NotBlank @Size(min = 3, max = 50) String name, @NotBlank String surname, @NotBlank @Size(max = 15) String username, @NotBlank @Size(max = 8) String password, @NotBlank @Size(max = 40) @Email String email) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Column(columnDefinition = "text")
    private String surname;

    @NaturalId
    @NotBlank
    @Size(max = 15)
    @Column(unique = true)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 8)
    @Column(unique = true)
    private String password;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    @Column(unique = true)
    private String email;
}