package com.frysning.springdnd.subrace;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "sub_race")
public class Subrace {

	@JsonProperty(index = 1)
	private @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

	@Column(nullable = false, unique = true)
	@JsonProperty(index = 2)
	private String name;

}
