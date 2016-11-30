package com.sav.samples.Robot.command;

import com.sav.samples.Robot.Robot;
import com.sav.samples.Robot.exception.RobotException;

public class MoveCommand implements Command{

	public CommandEnum getName() {
		return CommandEnum.MOVE;
	}
	
	public void execute(Robot robot) throws RobotException {
		if(robot == null)
			throw new RobotException("Null Robot");
		
		if(robot.canMoveForward())
			robot.moveForward();
	}

}
