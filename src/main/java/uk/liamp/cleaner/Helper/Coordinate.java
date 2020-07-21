package uk.liamp.cleaner.Helper;

/**
 * A helper class used to store coordinates, and provide functions for use in traversing
 * the grid and checking if a coordinate is within a boundary.
 * @author liampuk
 */
public class Coordinate {
	
	private int x;
	private int y;
	
	public Coordinate(int[] xy) {
		this.x = xy[0];
		this.y = xy[1];
	}
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Navigation functions to move around the coordinate grid.
	 */
	public void goNorth() {
		y++;
	}
	
	public void goSouth() {
		y--;
	}
	
	public void goEast() {
		x++;
	}
	
	public void goWest() {
		x--;
	}
	
	/**
	 * Returns the coordinates as a 2d array for building into the CleanerResponseDTO.
	 */
	public int[] getXYArray() {
		return new int[] {x,y};
	}
	
	/**
	 * Method used to check if a coordinate is inside a boundary, defined by a parameter
	 * coordinate denoting the top right corner of a rectangle.
	 */
	public boolean isInbound(Coordinate bound) {
		return x >=0 && y >= 0 && x <= bound.getX() && y <= bound.getY();
	}
	
	/**
	 * Used to check if a List contains a coordinate.
	 */
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		if(!(o instanceof Coordinate)) {
			return false;
		}
		Coordinate c = (Coordinate) o;
		return c.getX() == x && c.getY() == y;
	}

}
