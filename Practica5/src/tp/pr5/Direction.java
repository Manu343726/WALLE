

package tp.pr5;

/***ENUM DIRECTION***/
/***
 * Represents the set of directions that the robot can follow
 * 
 * @author Manuel Sánchez Pérez
 */
public enum Direction {
	EAST, NORTH, SOUTH, UNKNOWN, WEST;
	
	/* PUBLIC METHODS */
	
	/**
	 * Returns the opposite direction from a given direction
	 * @return the opposite direction
	 */
	public Direction opposite(){
            Direction result;
            
            switch(this){
            case EAST:  result = Direction.WEST;break;
            case NORTH: result = Direction.SOUTH;break;
            case SOUTH: result = Direction.NORTH;break;
            case WEST:  result = Direction.EAST;break;
            default:    result = Direction.UNKNOWN;break;
            }
                
            return result;
	}
	
	/**
	 * Calculate a direction from this direction with a given rotation
	 * @param rotation: the given rotation
	 * @return the new direction
	 */
	public Direction rotate(Rotation rotation){
            Direction result;
            
            switch(this){
            case NORTH: 
                    if(rotation == Rotation.RIGHT)
                            result = Direction.EAST;
                    else
                            result = Direction.WEST; 
                
                break;
            case SOUTH:
                    if(rotation == Rotation.RIGHT)
                            result = Direction.WEST;
                    else
                            result = Direction.EAST;
                
                break;
            case EAST:
                    if(rotation == Rotation.RIGHT)
                            result = Direction.SOUTH;
                    else
                            result = Direction.NORTH;
                
                break;
            case WEST:
                    if(rotation == Rotation.RIGHT)
                            result = Direction.NORTH;
                    else
                            result = Direction.SOUTH;
                
                break;
            default:
                    result = Direction.UNKNOWN;
            }
            
            return result;
	}
	
	
	/**
	 * Checks a given line and transform it into a Direction
	 * @param str Given line
	 * @return A correct direction
	 */
	public static Direction parse(String str){
		Direction result;
                
		switch(str.toUpperCase()){
		    case "NORTH" : result = Direction.NORTH;break;
		    case "SOUTH" : result = Direction.SOUTH;break;
		    case "EAST"  : result = Direction.EAST;break;
		    case "WEST"  : result = Direction.WEST;break;
		    default      : result = Direction.UNKNOWN;break;
		}
                
                return result;
	}
}
