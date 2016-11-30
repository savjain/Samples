package com.sav.samples.Robot.command;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.sav.samples.Robot.exception.RobotException;

public class CommandBuilderTest {
	
	CommandBuilder builder = new CommandBuilder();

	@Rule
    public org.junit.rules.ExpectedException thrown = ExpectedException.none();

	@Test
	public void testBuildPlaceCommand() throws RobotException {
		Command c = builder.buildCommand("PLACE 1,2,NORTH");
		assertNotNull(c);
		assertEquals(CommandEnum.PLACE,c.getName());
	}
	
	@Test(expected=RobotException.class)
	public void testBuildPlaceCommandNegative1() throws RobotException {
		builder.buildCommand("PLACE 1,2");
	}
	
	@Test(expected=RobotException.class)
	public void testBuildPlaceCommandNegative2() throws RobotException {
		builder.buildCommand("PLACE NORTH,1,2");
	}
	
	@Test(expected=RobotException.class)
	public void testBuildPlaceCommandNegative3() throws RobotException {
		builder.buildCommand("PLACE,1,2,NORTH");
	}

	@Test(expected=RobotException.class)
	public void testBuildPlaceCommandNegative4() throws RobotException {
		builder.buildCommand("PLACE 1,2,NORTHEAST");
	}

	@Test
	public void testBuildMoveCommand() throws RobotException {
		Command c = builder.buildCommand("MOVE");
		assertNotNull(c);
		assertEquals(CommandEnum.MOVE,c.getName());
	}
	
	@Test
	public void testBuildLeftCommand() throws RobotException {
		Command c = builder.buildCommand("LEFT");
		assertNotNull(c);
		assertEquals(CommandEnum.LEFT,c.getName());
	}
	
	@Test
	public void testBuildRightCommand() throws RobotException {
		Command c = builder.buildCommand("RIGHT");
		assertNotNull(c);
		assertEquals(CommandEnum.RIGHT,c.getName());
	}

	@Test
	public void testBuildReportCommand() throws RobotException {
		Command c = builder.buildCommand("REPORT");
		assertNotNull(c);
		assertEquals(CommandEnum.REPORT,c.getName());
	}
	
	@Test
	public void testCommandListBuilder() throws RobotException {
		List<String> strCommands = new ArrayList<String>(5);
		strCommands.add("PLACE 1,3,SOUTH");
		strCommands.add("MOVE");
		strCommands.add("LEFT");
		strCommands.add("MOVE");
		strCommands.add("MOVE");
		strCommands.add("RIGHT");
		strCommands.add("MOVE");
		strCommands.add("REPORT");
		
		builder = new CommandBuilder(strCommands);
		List<Command> commands = builder.buildCommands();
		assertEquals(8, commands.size());
		
		assertTrue(builder.validateSequence(commands));

	}
	

}
