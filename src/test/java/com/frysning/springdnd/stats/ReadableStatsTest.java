package com.frysning.springdnd.stats;

import com.frysning.springdnd.modifier_type.ModifierType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReadableStatsTest {

	@Test
	public void testReadableStatGetSTR() {
		ReadableStats readableStats = new ReadableStats(createStat());
		Assertions.assertEquals(3, readableStats.getStrength().getModifier());
	}

	@Test
	public void testReadableStatGetModifierBasedOnSTRModifier() {
		ReadableStats readableStats = new ReadableStats(createStat());
		Assertions.assertEquals(3, readableStats.getStatModifier(ModifierType.STR));
	}

	@Test
	public void testReadableStatGetModifierBasedOnINTModifier() {
		ReadableStats readableStats = new ReadableStats(createStat());
		Assertions.assertEquals(-2, readableStats.getStatModifier(ModifierType.INT));
	}

	private Stat createStat() {
		Stat stat = new Stat();
		stat.setCharisma(18);
		stat.setStrength(16);
		stat.setWisdom(14);
		stat.setDexterity(12);
		stat.setConstitution(8);
		stat.setIntelligence(6);
		return stat;
	}
}
