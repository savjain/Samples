package com.sav.samples.Robot.command;

import com.sav.samples.Robot.Robot;
import com.sav.samples.Robot.exception.RobotException;

public interface Command {
	
	public CommandEnum getName();
	
	public void execute(Robot robot) throws RobotException;

}
