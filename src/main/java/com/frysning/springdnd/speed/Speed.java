package com.frysning.springdnd.speed;import com.fasterxml.jackson.annotation.JsonIgnore;import com.frysning.springdnd.speed_type.SpeedType;import javax.persistence.*;@Entitypublic class Speed {	private @Id	@GeneratedValue(strategy = GenerationType.IDENTITY) Long id;	@ManyToOne	@JoinColumn(name = "speedtype_id", referencedColumnName = "id", nullable = false)	private SpeedType speedType;	private int range;	@Transient	private Long speedTypeId = -1L;	@Transient	private String speedTypeName = "";	public Speed() {	}	public Speed(Long id, SpeedType speedType, int range) {		this.id = id;		this.speedType = speedType;		this.range = range;	}	public Long getId() {		return id;	}	public void setId(Long id) {		this.id = id;	}	public void setSpeedTypeId(Long id) {		speedTypeId = id;		createSpeedTypeForSearching();	}	public void setSpeedTypeName(String name) {		speedTypeName = name;		createSpeedTypeForSearching();	}	private void createSpeedTypeForSearching() {		speedType = new SpeedType(speedTypeId, speedTypeName);	}	@JsonIgnore	public String getSpeedName() {		if (speedType != null) {			return speedType.getName();		}		return "";	}	@JsonIgnore	public Long getSpeedTypeId() {		if (speedType != null) {			return speedType.getId();		}		return -1L;	}	public SpeedType getSpeedType() {		return this.speedType;	}	public void setSpeedType(SpeedType speedType) {		this.speedType = speedType;	}	public int getRange() {		return range;	}	public void setRange(int range) {		this.range = range;	}}