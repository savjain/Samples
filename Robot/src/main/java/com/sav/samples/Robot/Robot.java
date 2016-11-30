package com.sav.samples.Robot;

import java.awt.Point;

import com.sav.samples.Robot.exception.RobotException;

public class Robot {
	
	
	/**
	 * Possible directions the robot can take
	 */
	private static final Direction[] DIRECTIONS = {	Direction.NORTH, 
										Direction.EAST, 
										Direction.SOUTH, 
										Direction.WEST 
									};
	
	/**
	 * Differential X and Y for each direction w.r.t {@code DIRECTIONS}
	 */
	private static final int[][] MOVE_DX_DY = {
											{0,1},
											{1,0},
											{0,-1},
											{-1,0}
										};

	private int currentDirection = 0;
	
	private Point currentPosition = new Point(0,0);

	private Surface table;

	public Robot(Surface table) {
		this.table = table;
	}

	/**
	 * This method places the Robot at the given position on the table.
	 * 
	 * @param position
	 * @param placementDirection
	 * @throws RobotException
	 */
	public void place(Point position, Direction placementDirection) throws RobotException{
		if(this.table == null)
			throw new RobotException("No table found for the placement");
		if(position == null)
			throw new RobotException("Placement position is null");
		if(placementDirection == null)
			throw new RobotException("Placement direction is null");
		if(!table.validate(position))
			throw new RobotException("Not a valide location for placement");
		
		setRobotPosition(position, placementDirection);
	}
	
	public void moveLeft() {
		if(table == null)
			return;
		if(--currentDirection < 0)
			currentDirection = DIRECTIONS.length - 1;
	}

	public void moveRight() {
		if(table == null)
			return;
		if(++currentDirection >= DIRECTIONS.length)
			currentDirection = 0;
	}
	
	public boolean canMoveForward() {
		if(table == null)
			return false;
		Point newPosition = getNewPosition();
		return table.validate(newPosition);
	}
	
	public void moveForward() {
		if(table == null || !canMoveForward()) 
			return;
		this.currentPosition = getNewPosition();
	}
	
	private Point getNewPosition() {
		Point newPosition = (Point) currentPosition.clone();
		newPosition.translate(MOVE_DX_DY[currentDirection][0], MOVE_DX_DY[currentDirection][1]);
		return newPosition;
	}

	public Direction getCurrentDirection() {
		return DIRECTIONS[currentDirection];
	}

	/**
	 * Sets the Robot's position without any validations
	 * 
	 * @param position
	 * @param placementDirection
	 * @throws RobotException
	 */
	void setRobotPosition(Point position, Direction placementDirection) throws RobotException {
		this.currentPosition = position;
		
		this.currentDirection = getDirectionIndex(placementDirection);		
	}

	private int getDirectionIndex(Direction placementDirection) throws RobotException {
		for (int i = 0; i < DIRECTIONS.length; i++) {
			if(placementDirection == DIRECTIONS[i])
				return i;
		}
		throw new RobotException("Direction not supported by Robot");
	}
	
	public String report() {
		return String.format("%1$d,%2$d,%3$s", currentPosition.x, 
												currentPosition.y, 
												getCurrentDirection());
	}

}
