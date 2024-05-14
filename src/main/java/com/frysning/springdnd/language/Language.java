package com.frysning.springdnd.language;import com.fasterxml.jackson.annotation.JsonProperty;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.GenerationType;import javax.persistence.Id;@Entitypublic class Language {	@JsonProperty(index = 1)	private @Id	@GeneratedValue(strategy = GenerationType.IDENTITY) Long id;	@JsonProperty(index = 2)	private String name;	public Language(Long id, String name) {		this.id = id;		this.name = name;	}	public Language() {	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public Long getId() {		return id;	}	public void setId(Long id) {		this.id = id;	}}