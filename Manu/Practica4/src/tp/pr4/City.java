

/***CLASS CITY***/
/***
 * This class represents the city where the robot is wandering. 
 * It contains information about the streets and the places in the city
 */

package tp.pr4;

public class City {
	
	private Street[] _cityMap;
	
	
	
	
	/* CONSTRUCTORS */
	
	public City(){}
	
	public City(Street[] cityMap){
		_cityMap = cityMap;
	}
	
	
	/* PUBLIC METHODS */
	
	/**
	 * Returns the street that comes out from a given place on a given direction
	 * @param place: place where WALL�E is
	 * @param direction: direction which you want to move
	 * @return if there is a street from the given place on that direction. If there no street returns null
	 */
	public Street lookForStreet(Place currentPlace, Direction currentHeading){
		Street s = null;
		int i = 0;
		boolean encontrada = false;
		
		if(_cityMap != null){
			while(i < _cityMap.length && !encontrada){
				if(_cityMap[i].comeOutFrom(currentPlace, currentHeading)){
					encontrada = true;
					s = _cityMap[i];
				}
				else 
					i++;
			}
		}
		return s;
	}

}
