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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "postTypes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PostType extends AuditModel{

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

