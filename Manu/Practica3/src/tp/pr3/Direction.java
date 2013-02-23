//OK//

package tp.pr3;


/***
 * Represents the set of directions that the robot can follow
 */
public enum Direction {
	EAST, NORTH, SOUTH, UNKNOWN, WEST;
	
	/* PUBLIC METHODS */
	/**
	 * Returns the opposite direction from a given direction
	 * @return the opposite direction
	 */
	public Direction opposite(){
		switch(this){
		case EAST: return Direction.WEST;
		case NORTH: return Direction.SOUTH;
		case SOUTH: return Direction.NORTH;
		case WEST: return Direction.EAST;
		default: return Direction.UNKNOWN;
		}
	}
	
	/**
	 * Calculate a direction from this direction with a given rotation
	 * @param rotation: the given rotation
	 * @return the new direction
	 */
	public Direction rotate(Rotation rotation){
		switch(this){
		case NORTH: 
			if(rotation == Rotation.RIGHT)
				return Direction.EAST;
			else
				return Direction.WEST; 
		case SOUTH:
			if(rotation == Rotation.RIGHT)
				return Direction.WEST;
			else
				return Direction.EAST;
		case EAST:
			if(rotation == Rotation.RIGHT)
				return Direction.SOUTH;
			else
				return Direction.NORTH;
		case WEST:
			if(rotation == Rotation.RIGHT)
				return Direction.NORTH;
			else
				return Direction.SOUTH;
		default:
			return Direction.UNKNOWN;
		}
	}
}
