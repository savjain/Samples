package com.sav.samples.Robot;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import com.sav.samples.Robot.exception.RobotException;

public class RobotTest {

	@Test
	public void testReport() throws RobotException {
		Robot robot = new Robot(null);
		robot.setRobotPosition(new Point(7,9), Direction.SOUTH);
		
		assertEquals("7,9,SOUTH", robot.report());
	}

	@Test
	public void testChangeDirections() throws RobotException {
		Robot robot = new Robot(null);
		
		robot.setRobotPosition(new Point(7,9), Direction.SOUTH);
		
		robot.moveLeft();		
		assertEquals(Direction.EAST, robot.getCurrentDirection());
		robot.moveLeft();		
		assertEquals(Direction.NORTH, robot.getCurrentDirection());
		robot.moveLeft();		
		assertEquals(Direction.WEST, robot.getCurrentDirection());
		robot.moveLeft();		
		assertEquals(Direction.SOUTH, robot.getCurrentDirection());

		robot.moveRight();
		assertEquals(Direction.WEST, robot.getCurrentDirection());
		robot.moveRight();
		assertEquals(Direction.NORTH, robot.getCurrentDirection());
		robot.moveRight();
		assertEquals(Direction.EAST, robot.getCurrentDirection());
		robot.moveRight();
		assertEquals(Direction.SOUTH, robot.getCurrentDirection());

		robot.moveRight();
		assertEquals(Direction.WEST, robot.getCurrentDirection());
	}
	
	@Test
	public void testCanMove() throws RobotException {
		Surface table = new Surface(3,3);
		
		Robot robot = new Robot(table);
		
		robot.setRobotPosition(new Point(0,1), Direction.SOUTH);
		assertTrue(robot.canMoveForward());

		robot.setRobotPosition(new Point(0,2), Direction.NORTH);
		assertFalse(robot.canMoveForward());

		robot.setRobotPosition(new Point(2,0), Direction.WEST);
		assertTrue(robot.canMoveForward());
	}
	

	@Test
	public void testMoveForward() throws RobotException {
		Surface table = new Surface(3,3);
		
		Robot robot = new Robot(table);
		
		robot.setRobotPosition(new Point(0,1), Direction.SOUTH);
		robot.moveForward();
		assertEquals("0,0,SOUTH", robot.report());

		robot.moveLeft();
		robot.moveForward();
		assertEquals("1,0,EAST", robot.report());

		robot.moveLeft();
		robot.moveForward();
		assertEquals("1,1,NORTH", robot.report());

		robot.moveForward();
		assertEquals("1,2,NORTH", robot.report());
	
		// cannot move any further
		robot.moveForward();
		assertEquals("1,2,NORTH", robot.report());
	}

}
