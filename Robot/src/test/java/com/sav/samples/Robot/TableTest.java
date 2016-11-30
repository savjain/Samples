package com.sav.samples.Robot;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

public class TableTest {

	@Test
	public void testValidate() {
		Surface t = new Surface(5, 5);
		assertTrue(t.validate(new Point(3, 3)));

		assertTrue(t.validate(new Point(0, 0)));
		
		assertFalse(t.validate(new Point(-1, 3)));
		
		assertFalse(t.validate(new Point(2, -1)));
		
		assertFalse(t.validate(new Point(2, 6)));
		
		assertFalse(t.validate(new Point(6, 2)));
	}

}
