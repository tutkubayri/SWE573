package com.swe.comin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "formAreas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FormArea extends AuditModel{

    private static final long serialVersionUID = 1L;

    public FormArea() {
        super();
    }

    public FormArea(String label, String dataType, boolean isRequired, PostType postType) {
        super();
        this.label = label;
        this.dataType = dataType;
        this.isRequired = isRequired;
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
    private boolean isRequired;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "postType_id", nullable = false)
    @JsonIgnore
    private PostType postType;
}

