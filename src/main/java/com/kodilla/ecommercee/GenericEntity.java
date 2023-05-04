package com.kodilla.ecommercee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
public class GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String value;

    public GenericEntity() {
    }

    public String getValue() {
        return value;
    }

    public Long getId() {

        return id;
    }

    public GenericEntity(String value) {

        this.value = value;
    }
}
