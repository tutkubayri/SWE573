package com.swe.comin.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "postTypes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PostType{

    public PostType() {
    }

    public PostType(String name, String usage, Community community, Set<FormArea> formAreas, Set<Post> posts) {
        this.name = name;
        this.usage = usage;
        this.community = community;
        this.formAreas = formAreas;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Set<FormArea> getFormAreas() {
        return formAreas;
    }

    public void setFormAreas(Set<FormArea> formAreas) {
        this.formAreas = formAreas;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String name;

    @Column(columnDefinition = "text")
    private String usage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "community_id", nullable = false)
    @JsonIgnore
    private Community community;

    @OneToMany(mappedBy = "postType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FormArea> formAreas;

    @OneToMany(mappedBy = "postType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Post> posts;
}

