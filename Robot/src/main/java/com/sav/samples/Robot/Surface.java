package com.sav.samples.Robot;

import java.awt.Point;

public class Surface {
	
	private int rows;
	
	private int columns;
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public Surface(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
	}
	
	public boolean validate(Point location) {
		return (location.x >=0 && location.x < this.columns) && (location.y >=0 && location.y < this.rows) ;
	}

}
