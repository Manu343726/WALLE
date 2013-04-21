
package tp.pr4;

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
            Rotation result;
            
            if(str.equalsIgnoreCase("LEFT"))
                result = Rotation.LEFT;
            else if(str.equalsIgnoreCase("RIGHT"))
                result = Rotation.RIGHT;
            else
                result = Rotation.UNKNOWN;

            return result;
	}
}
