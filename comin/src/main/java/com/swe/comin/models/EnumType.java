package com.swe.comin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.text.Normalizer;
import java.util.Set;

@Entity
@Table(name = "enum")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EnumType {

    public EnumType() {
    }

    public EnumType(FormArea formArea, String value){
        this.value = value;
        this.formArea = formArea;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FormArea getFormArea() {
        return formArea;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setFormArea(FormArea formArea) {
        this.formArea = formArea;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "formArea_id", nullable = false)
    @JsonIgnore
    private FormArea formArea;

    @Column(columnDefinition = "text")
    private String value;
}