package com.frysning.springdnd.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class StatRepositoryImpl implements StatCustomRepository {

    @Autowired
    @Lazy
    StatRepository statRepository;

    @Override
    public Stat getStat(Stat stat) {
        Stat newStat = null;
        if (stat.getId() != null && stat.getId() != 0) {
            newStat = statRepository.getReferenceById(stat.getId());
        }

        if (newStat == null) {
            newStat = statRepository.findEntity(stat);
        }

        if (newStat == null) {
            newStat = statRepository.save(stat);
        }
        return newStat;
    }

    public Stat findEntity(Stat stat) {
      return statRepository.findEntity(stat);
    }
}