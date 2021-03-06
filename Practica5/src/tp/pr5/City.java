

/***CLASS CITY***/
/***
 * This class represents the city where the robot is wandering. 
 * It contains information about the streets and the places in the city
 * 
 * @author Manuel Sánchez Pérez
 */

package tp.pr5;

import tp.pr5.utils.Predicate;
import tp.pr5.utils.Filter;
import java.util.*;

public class City {
	
	private Collection<Street> _cityMap;
	
	/* CONSTRUCTORS */
	
        /**
         * Default ctor
         */
	public City(){}
	
        /**
         * Initialices the city with the specified streets.
         * @param cityMap Array of streets
         */
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
		Street street = null;
                Filter<Street> filter;
		
		if(_cityMap != null && _cityMap.size() > 0){
                    filter = new Filter(_cityMap.iterator(),new Predicate<Street>()
                    {
                        @Override
                        public boolean apply(Street street) 
                        {
                            return street.comeOutFrom(currentPlace, currentHeading);
                        }
                    });
                    
                    if(filter.hasNext())
                        street = filter.next();
                    else
                        street = null;
		}
                
		return street;
	}

}
