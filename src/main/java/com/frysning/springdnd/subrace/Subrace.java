package com.frysning.springdnd.subrace;

import javax.persistence.*;

@Entity
@Table(name = "sub_race")
public class Subrace {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @Column(nullable = false, unique = true)
    private String name;

}
