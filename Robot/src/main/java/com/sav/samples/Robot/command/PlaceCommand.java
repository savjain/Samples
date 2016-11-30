package com.sav.samples.Robot.command;

import java.awt.Point;

import com.sav.samples.Robot.Direction;
import com.sav.samples.Robot.Robot;
import com.sav.samples.Robot.exception.RobotException;

public class PlaceCommand implements Command {
	
	
	private Point position;
	private Direction direction;

	public PlaceCommand(int x, int y, Direction direction) {
		this.position = new Point(x,y);
		this.direction = direction;
	}

	public CommandEnum getName() {
		return CommandEnum.PLACE;
	}
	
	public void execute(Robot robot) throws RobotException {
		if(robot == null)
			throw new RobotException("Null Robot");
		robot.place(position, direction);
	}


}
