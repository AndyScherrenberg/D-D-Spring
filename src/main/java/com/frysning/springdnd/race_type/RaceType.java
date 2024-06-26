package com.frysning.springdnd.race_type;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "race_type")
public class RaceType {

	@JsonProperty(index = 1)
	private @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

	@Column(nullable = false, unique = true)
	@JsonProperty(index = 2)
	private String name;

	public RaceType(String name) {
		this.name = name;
	}

	public RaceType() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
