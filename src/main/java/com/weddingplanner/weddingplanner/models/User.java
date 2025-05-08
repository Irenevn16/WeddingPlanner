package com.weddingplanner.weddingplanner.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;

//@MappedSuperclass
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "This field cannot be empty")
    private String name;

    @Min(value = 0, message = "Age must be a positive number")
    private int age;

    @NotEmpty(message = "This field cannot be empty")
    private String username;

    @NotEmpty(message = "This field cannot be empty")
    private String password;

    @NotNull(message = "This field cannot be empty")
    @Enumerated(EnumType.STRING)
    private Role role;
}
