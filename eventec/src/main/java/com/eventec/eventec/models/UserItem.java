package com.eventec.eventec.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_items")
public class UserItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userid;

    private String userName;

    @Column(unique = true)
    private String email;

    private String password;
    private String zipCode;
    private String state;
    private String city;
    private String neighborhood;
    private String street;

    public enum UserType {
        aluno,
        usuarioComum,
        professor,
        diretor
    }
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(unique = true)
    private Long cpf;

    private String emailInstitucional;

    //Aluno
    private Long ra;
    private String unidade;
    private String semestre;
    private String curso;

    // Relação com EventItem
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<EventItem> events;

    @Override
    public String toString() {
        return String.format("userItem{userId=%d, userName=%s, email=%s, password=%s, cpf=%d, zipCode=%s, state=%s, city=%s, neighborhood=%s, street=%s}",
                userid, userName, email, password, cpf, zipCode, state, city, neighborhood, street);
    }
}
