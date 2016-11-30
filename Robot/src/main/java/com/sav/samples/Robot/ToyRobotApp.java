package com.sav.samples.Robot;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.sav.samples.Robot.command.Command;
import com.sav.samples.Robot.command.CommandBuilder;

/**
 * Control Application for the Toy Robot
 *
 */
public class ToyRobotApp {
    public static void main( String[] args ) throws Exception {
    	
    	if(args.length == 0) {
    		printUsage();
    		System.exit(1);
    	}
    	
    	
		CommandBuilder builder = getCommandBuilder(args);

		Surface table = new Surface(5,5);
		Robot robot = new Robot(table);
		
		List<Command> commands = builder.buildCommands();
		for (Command command : commands) {
			command.execute(robot);
		}
    	
    }

	private static void printUsage() {
		System.out.println("\n\nUsage:");
		System.out.println("\t1. ToyRobotApp -f <FileName>");
		System.out.println("\t2. ToyRobotApp -c <List of commands seperated by ';'>\n\n");
	}

	private static CommandBuilder getCommandBuilder(String[] args) throws FileNotFoundException {
		if(args[0].equals("-f") && args.length > 1) {
			// Create Command builder from file containing commands
			return new CommandBuilder(args[1]);
		} else if(args[0].equals("-c") && args.length > 1) {
			String[] strCommands = args[1].split(";");
			ArrayList<String> commandsList = new ArrayList<String>();
			
			for (int i = 0; i < strCommands.length; i++) {
				commandsList.add(strCommands[i]);
			}
			return new CommandBuilder(commandsList);
		} else {
			printUsage();
			System.exit(1);
		}
		return null;
	}
}
