package com.frysning.springdnd.spell;

import com.frysning.springdnd.modifier_type.ModifierType;
import com.frysning.springdnd.stats.ReadableStats;
import com.frysning.springdnd.stats.Stat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReadableSpellTest {

	@Test
	public void testSpellWithFormats() {
		Spell spell = new Spell();
		spell.setDescription("I AM A {0}");
		ReadableStats readableStats = new ReadableStats(createStat());
		ReadableSpell readableSpell = new ReadableSpell(spell, readableStats, 0, "CASTER", ModifierType.CHA);
		String result = "I AM A CASTER";
		Assertions.assertEquals(result, readableSpell.getDescription());
	}

	@Test
	public void testSpellSaveDC() {
		Spell spell = new Spell();
		spell.setDescription("I AM A {0}");
		ReadableStats readableStats = new ReadableStats(createStat());
		ReadableSpell readableSpell = new ReadableSpell(spell, readableStats, 2, "CASTER", ModifierType.CHA);
		Assertions.assertEquals(14, readableSpell.getSpellSave() );
	}

	@Test
	public void testSpellDescriptionWithCastingModifier(){
		var description = "On a hit, the target takes force damage equal to 1d8 + [SPELL].";
		Spell spell = new Spell();
		spell.setDescription(description);
		ReadableStats readableStats = new ReadableStats(createStat());
		ReadableSpell readableSpell = new ReadableSpell(spell, readableStats, 2, "CASTER", ModifierType.CHA);
		Assertions.assertEquals("On a hit, the target takes force damage equal to 1d8 + 4(CHA).", readableSpell.getDescription());
	}

	@Test
	public void testSpellDescriptionWithCastingModifierAndName(){
		var description = "{0}, 1d8 + [SPELL]. and [STR]";
		Spell spell = new Spell();
		spell.setDescription(description);
		ReadableStats readableStats = new ReadableStats(createStat());
		ReadableSpell readableSpell = new ReadableSpell(spell, readableStats, 2, "CASTER", ModifierType.CHA);
		Assertions.assertEquals("CASTER, 1d8 + 4(CHA). and DC 10 STR", readableSpell.getDescription());
	}

	private Stat createStat() {
		Stat stat = new Stat();
		stat.setCharisma(18);
		stat.setStrength(10);
		stat.setWisdom(10);
		stat.setDexterity(10);
		stat.setConstitution(10);
		stat.setIntelligence(10);
		return stat;
	}

}
