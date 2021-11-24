package ru.sllite.springtodo.model;

import ru.sllite.springtodo.enums.AuthorityType;

import javax.persistence.*;

@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private AuthorityType name;

    public Authority() {}

    public Authority(AuthorityType name) {
        this.name = name;
    }

    public AuthorityType getName() {
        return name;
    }
}
