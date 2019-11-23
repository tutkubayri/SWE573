package com.swe.comin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "postTypes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PostType extends AuditModel{

    private static final long serialVersionUID = 1L;

    public PostType(){}

    public PostType(String name, String usage) {
        this.name = name;
        this.usage = usage;
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

