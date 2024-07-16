package com.frysning.springdnd.spell;

import com.frysning.springdnd.stats.ReadableStats;
import com.frysning.springdnd.stats.Stat;
import com.frysning.springdnd.trait.ReadableTrait;
import com.frysning.springdnd.trait.Trait;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpellTests {

	@Test
	public void SpellHasCorrectComponentWithDescription() {
		Spell spell = new Spell();
		spell.setComponents(new int[]{1, 2, 3});
		spell.setComponentDescription("leaf 5sp");
		var expected = "V,S,M (leaf 5sp)";
		var result = spell.getComponents();

		Assertions.assertEquals(expected, result);
	}

	@Test
	public void SpellHasCorrectComponentWithNoDuplicates() {
		Spell spell = new Spell();
		spell.setComponents(new int[]{1, 2, 3, 1});
		var expected = "V,S,M";
		var result = spell.getComponents();

		Assertions.assertEquals(expected, result);
	}

	@Test
	public void SpellHasComponentsAreVSM() {
		Spell spell = new Spell();
		spell.setComponents(new int[]{3, 1, 2});
		var expected = "V,S,M";
		var result = spell.getComponents();

		Assertions.assertEquals(expected, result);
	}

	@Test
	public void SpellComponentDoesNotContainNotSupported() {
		Spell spell = new Spell();
		spell.setComponents(new int[]{3, 1, 2, 0, 5, 6, 4});
		var expected = "V,S,M";
		var result = spell.getComponents();

		Assertions.assertEquals(expected, result);
	}

	@Test
	public void SpellComponentsCanBeEmpty() {
		Spell spell = new Spell();
		spell.setComponents(new int[]{});
		var expected = "";
		var result = spell.getComponents();

		Assertions.assertEquals(expected, result);
	}

	@Test
	public void SpellComponentCanBeEmptyWithDescription() {
		Spell spell = new Spell();
		spell.setComponents(new int[]{});
		spell.setComponentDescription("TEST");
		var expected = "(TEST)";
		var result = spell.getComponents();

		Assertions.assertEquals(expected, result);
	}

}
