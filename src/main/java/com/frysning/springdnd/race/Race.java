package com.frysning.springdnd.race;

import com.frysning.springdnd.combiner.BaseObject;
import com.frysning.springdnd.stats.Stat;

import javax.persistence.*;

@Entity
@Table(name = "race")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Race extends BaseObject {

	public Race() {
	}

	public Race(String name, Stat stat) {
		this.name = name;
		this.stat = stat;
	}

}
