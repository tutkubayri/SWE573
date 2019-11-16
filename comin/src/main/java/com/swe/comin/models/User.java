package com.swe.comin.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User{

    private static final long serialVersionUID = 1L;

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

}
