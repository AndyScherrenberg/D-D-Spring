package com.frysning.springdnd.stats;import org.junit.jupiter.api.Assertions;import org.junit.jupiter.api.Test;public class CalculatedStatTests {    @Test    public void testCalculatedStats() {        CalculatedStat stat_1 = new CalculatedStat(1);        CalculatedStat stat_2 = new CalculatedStat(2);        CalculatedStat stat_3 = new CalculatedStat(3);        CalculatedStat stat_4 = new CalculatedStat(4);        CalculatedStat stat_5 = new CalculatedStat(5);        CalculatedStat stat_6 = new CalculatedStat(6);        CalculatedStat stat_7 = new CalculatedStat(7);        CalculatedStat stat_8 = new CalculatedStat(8);        CalculatedStat stat_9 = new CalculatedStat(9);        CalculatedStat stat_10 = new CalculatedStat(10);        CalculatedStat stat_11 = new CalculatedStat(11);        CalculatedStat stat_12 = new CalculatedStat(12);        CalculatedStat stat_13 = new CalculatedStat(13);        CalculatedStat stat_14 = new CalculatedStat(14);        CalculatedStat stat_15 = new CalculatedStat(15);        CalculatedStat stat_16 = new CalculatedStat(16);        CalculatedStat stat_17 = new CalculatedStat(17);        CalculatedStat stat_18 = new CalculatedStat(18);        CalculatedStat stat_19 = new CalculatedStat(19);        CalculatedStat stat_20 = new CalculatedStat(20);        Assertions.assertEquals(stat_1.getModifier(), -5);        Assertions.assertEquals(stat_2.getModifier(), -4);        Assertions.assertEquals(stat_3.getModifier(), -4);        Assertions.assertEquals(stat_4.getModifier(), -3);        Assertions.assertEquals(stat_5.getModifier(), -3);        Assertions.assertEquals(stat_6.getModifier(), -2);        Assertions.assertEquals(stat_7.getModifier(), -2);        Assertions.assertEquals(stat_8.getModifier(), -1);        Assertions.assertEquals(stat_9.getModifier(), -1);        Assertions.assertEquals(stat_10.getModifier(), 0);        Assertions.assertEquals(stat_11.getModifier(), 0);        Assertions.assertEquals(stat_12.getModifier(), 1);        Assertions.assertEquals(stat_13.getModifier(), 1);        Assertions.assertEquals(stat_14.getModifier(), 2);        Assertions.assertEquals(stat_15.getModifier(), 2);        Assertions.assertEquals(stat_16.getModifier(), 3);        Assertions.assertEquals(stat_17.getModifier(), 3);        Assertions.assertEquals(stat_18.getModifier(), 4);        Assertions.assertEquals(stat_19.getModifier(), 4);        Assertions.assertEquals(stat_20.getModifier(), 5);    }}