
package tp.pr3;

/***ENUM ROTATION***/
/***
 * Represents a robot rotation
 */
public enum Rotation {
	LEFT, RIGHT, UNKNOWN;	
	
	/**
	 * Checks if a given line is a correct Rotation
	 * @param str Given line
	 * @return The correct Rotation
	 */
	public static Rotation parse(String str){
		if(str.equalsIgnoreCase("LEFT"))
			return Rotation.LEFT;
		else if(str.equalsIgnoreCase("RIGHT"))
			return Rotation.RIGHT;
		else
			return Rotation.UNKNOWN;
	}
}
