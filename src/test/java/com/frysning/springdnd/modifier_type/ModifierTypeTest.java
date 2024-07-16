package com.frysning.springdnd.modifier_type;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModifierTypeTest {

	@Test
	public void testValueOrNotSupportedReturnsSTR(){
		ModifierType modifierType = ModifierType.valueOrNotSupported("STR");

		Assertions.assertEquals(ModifierType.STR, modifierType);
	}

	@Test
	public void testValueOrNotSupportedReturnsCHA(){
		ModifierType modifierType = ModifierType.valueOrNotSupported("CHA");

		Assertions.assertEquals(ModifierType.CHA, modifierType);
	}

	@Test
	public void testValueOrNotSupportedReturnsNOT_SUPPORTEDWithNoValid(){
		ModifierType modifierType = ModifierType.valueOrNotSupported("SPELL");

		Assertions.assertEquals(ModifierType.NOT_SUPPORTED, modifierType);
	}

	@Test
	public void testValueOrNotSupportedReturnsNOT_SUPPORTED(){
		ModifierType modifierType = ModifierType.valueOrNotSupported("NOT_SUPPORTED");

		Assertions.assertEquals(ModifierType.NOT_SUPPORTED, modifierType);
	}
}
