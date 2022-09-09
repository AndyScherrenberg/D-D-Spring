package com.frysning.springdnd.race;


import com.frysning.springdnd.stats.Stat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "races")
public class Race {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @Column(nullable = false, unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "stat_id", referencedColumnName = "id", nullable = false)
    private Stat stat;

    public Race() {
    }

    public Race(String name, Stat stat) {
        this.name = name;
        this.stat = stat;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat baseStat) {
        this.stat = baseStat;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
