//OK//

package tp.pr3;


/***
 * Represents a robot rotation
 */
public enum Rotation {
	LEFT, RIGHT, UNKNOWN;	
	
	public static Rotation parse(String str)
	{
		if(str.equalsIgnoreCase("LEFT"))
			return Rotation.LEFT;
		else if(str.equalsIgnoreCase("RIGHT"))
			return Rotation.RIGHT;
		else
			return Rotation.UNKNOWN;
	}
}
