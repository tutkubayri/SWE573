package com.swe.comin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.*;

@Entity
@Table(name = "formArea")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FormArea extends AuditModel{

    private static final long serialVersionUID = 1L;

    public FormArea() {
    }

    public FormArea(String label, String dataType, boolean requirement, PostType postType) {
        this.label = label;
        this.dataType = dataType;
        this.requirement = requirement;
        this.postType = postType;
    }

    public boolean getRequirement() {
        return requirement;
    }

    public void setRequirement(boolean requirement) {
        this.requirement = requirement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String label;

    @Column(columnDefinition = "text")
    private String dataType;

    @Column(columnDefinition = "text")
    private boolean requirement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "postType_id", nullable = false)
    @JsonIgnore
    private PostType postType;
}

