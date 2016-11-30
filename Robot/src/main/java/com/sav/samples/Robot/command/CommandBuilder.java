package com.sav.samples.Robot.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sav.samples.Robot.Direction;
import com.sav.samples.Robot.exception.RobotException;

public class CommandBuilder {
	
	List<String> stringCommands;

	public CommandBuilder(String fileName) throws FileNotFoundException {

		Scanner s = new Scanner(new File(fileName));
		while (s.hasNext()) {
			stringCommands.add(s.next());
		}
		s.close();
	}

	public CommandBuilder(List<String> stringCommands){
		this.stringCommands = stringCommands;
	}
	
	CommandBuilder() {
	}

	public List<Command> buildCommands() throws RobotException {

		if (stringCommands == null || stringCommands.size() == 0)
			throw new RobotException("No Commands recieved");
		ArrayList<Command> commands = new ArrayList<Command>(stringCommands.size());

		for (String command : stringCommands) {
			commands.add(buildCommand(command));
		}

		return commands;
	}

	boolean validateSequence(List<Command> commands) {
		return commands.get(0).getName() == CommandEnum.PLACE;
	}

	Command buildCommand(String command) throws RobotException {
		if (command == null)
			throw new RobotException("Null Command recieved::");
		String[] commandParts = command.split(" ");

		String commandName = commandParts[0].trim();
		CommandEnum cmd = null;
		try {
			cmd = CommandEnum.valueOf(commandName);
		} catch (IllegalArgumentException e) {
			throw new RobotException("Invalid Command Exception::" + commandName);
		}

		switch (cmd) {
		case PLACE:
			if (commandParts.length < 2)
				throw new RobotException("Not enough arguments for Place command::" + command);
			String[] commandParams = commandParts[1].split(",");
			if (commandParams.length < 3)
				throw new RobotException("Not enough arguments for Place command::" + command);
			try {

				int x = Integer.parseInt(commandParams[0]);
				int y = Integer.parseInt(commandParams[1]);
				Direction d = Direction.valueOf(commandParams[2]);
				return new PlaceCommand(x, y, d);
			} catch (NumberFormatException e) {
				throw new RobotException("Unable to parse x and y coordinates for Place command");
			} catch (IllegalArgumentException e) {
				throw new RobotException("Unable to parse direction for Place command");
			}
		case MOVE:
			return new MoveCommand();
		case LEFT:
			return new LeftCommand();
		case RIGHT:
			return new RightCommand();
		case REPORT:
			return new ReportCommand();
		}

		throw new RobotException("This is not reachable code");
	}

}
