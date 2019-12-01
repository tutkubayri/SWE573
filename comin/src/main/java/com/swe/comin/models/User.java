package com.swe.comin.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User{

    private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(String name, String surname, String username, String password, Set<Role> roles, String passwordConfirm) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.passwordConfirm = passwordConfirm;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String name;

    @Column(columnDefinition = "text")
    private String surname;

    @Column(columnDefinition = "text")
    private String username;

    @Column(columnDefinition = "text")
    private String password;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Column(columnDefinition = "text")
    private String passwordConfirm;

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
