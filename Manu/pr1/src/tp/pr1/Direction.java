package tp.pr1;

public enum Direction{
	NORTH,
	SOUTH,
	EAST,
	WEST,
	UNKNOWN;
	
	public Direction opposite(){		
		switch(this)
		{
		case NORTH:
			return Direction.SOUTH;
		case SOUTH:
			return Direction.NORTH;
		case EAST:
			return Direction.WEST;
		case WEST:
			return Direction.EAST;
		default:
			return UNKNOWN;
		}
	}
}
