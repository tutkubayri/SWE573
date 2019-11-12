package com.swe.comin.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "community")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Community extends AuditModel {

    private static final long serialVersionUID = 1L;

    public Community(){}

    public Community(@NotBlank @Size(min = 3, max = 50) String name, String description, String semanticTag) {
        this.name = name;
        this.description = description;
        this.semanticTag = semanticTag;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSemanticTag() {
        return semanticTag;
    }

    public void setSemanticTag(String semanticTag) {
        this.semanticTag = semanticTag;
    }

    public Set<PostType> getPostTypes() {
        return postTypes;
    }

    public void setPostTypes(Set<PostType> postTypes) {
        this.postTypes = postTypes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String semanticTag;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PostType> postTypes;
}