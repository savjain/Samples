package com.sav.samples.Robot.command;

import com.sav.samples.Robot.Robot;
import com.sav.samples.Robot.exception.RobotException;

public class ReportCommand implements Command {

	public CommandEnum getName() {
		return CommandEnum.REPORT;
	}

	public void execute(Robot robot) throws RobotException {
		if(robot == null)
			throw new RobotException("Null Robot");
		System.out.println("Current Location is => " + robot.report());
			
	}
}
