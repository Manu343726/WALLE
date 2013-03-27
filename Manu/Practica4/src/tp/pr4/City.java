

/***CLASS CITY***/
/***
 * This class represents the city where the robot is wandering. 
 * It contains information about the streets and the places in the city
 */

package tp.pr4;

import java.util.*;
import tp.pr4.utils.*;

public class City {
	
	private Collection<Street> _cityMap;
	
	/* CONSTRUCTORS */
	
	public City(){}
	
	public City(Street[] cityMap){
            _cityMap = new ArrayList<>();
            Collections.addAll(_cityMap, cityMap);
	}
	
	
	/* PUBLIC METHODS */
	
	/**
	 * Returns the street that comes out from a given place on a given direction
	 * @param place: place where WALL�E is
	 * @param direction: direction which you want to move
	 * @return if there is a street from the given place on that direction. If there no street returns null
	 */
	public Street lookForStreet(final Place currentPlace,final Direction currentHeading){
		Street s = null;
		int i = 0;
		boolean encontrada = false;
                Filter<Street> filter = null;
		
		if(_cityMap != null && _cityMap.size() > 0){//Como echo de menos las lamdas...
                    filter = new Filter(_cityMap.iterator(),new Predicate<Street>()
                    {
                        @Override
                        public boolean apply(Street street) 
                        {
                            return street.comeOutFrom(currentPlace, currentHeading);
                        }
                    });
                    
                    while(filter.hasNext() && !encontrada)
                    {
                        encontrada = true;
                        s = filter.next();
                    }
		}
                
		return s;
	}

}
