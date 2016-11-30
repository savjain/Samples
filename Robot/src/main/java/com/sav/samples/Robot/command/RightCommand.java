package com.sav.samples.Robot.command;

import com.sav.samples.Robot.Robot;
import com.sav.samples.Robot.exception.RobotException;

public class RightCommand implements Command {

	public CommandEnum getName() {
		return CommandEnum.RIGHT;
	}

	public void execute(Robot robot) throws RobotException {
		if(robot == null)
			throw new RobotException("Null Robot");
		robot.moveRight();
			
	}

}
