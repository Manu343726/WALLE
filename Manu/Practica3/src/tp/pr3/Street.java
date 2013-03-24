
package tp.pr3;

import tp.pr3.items.CodeCard;

/***CLASS STREET***/
/***
 * Represents a street as its starting place, its final place, and its direction.
 */
public class Street {
	
	private Place     _source;
	private Place     _target;
	private Direction _direction;
	private boolean   _isOpen;
	private String    _code;
	
	/* CONSTRUCTORS */
	
	/**
	 * Default constructor
	 */
	public Street(){}
	
	/**
	 * Parameterized constructor
	 * @param source The starting place (See class description)
	 * @param direction The street direction (See class description)
	 * @param target The final place (See class description)
	 */	
	public Street(Place source, Direction direction, Place target){
		_source = source;
		_target = target;
		_direction = direction;
		_isOpen = true;
	}
	
	public Street(Place source, Direction direction, Place target, boolean isOpen, String code){
		_source = source;
		_target = target;
		_direction = direction;
		_isOpen = isOpen;
		_code = code;
	}
	
	/* PUBLIC METHODS */
	
	/**
	 * Checks if this street matches to a certain place and direction
	 * @param place: The starting or final place of the street
	 * @param whichDirection: The street direction
	 * @return True if the values match with this street. False in other case.
	 */
	public boolean comeOutFrom(Place place, Direction whichDirection){
		return (place == _source && whichDirection == _direction) || (place == _target && whichDirection == _direction.opposite());
	}
	
	/**
	 * Returns the place on the other side of the street
	 * @param whereAmI: The starting or final place of the street
	 * @return the place on the other side of the street from the specified place
	 */
	public Place nextPlace(Place whereAmI){
            Place result;
            
            if (whereAmI == _source)
                    result = _target;
            else if (whereAmI == _target)
                    result = _source;
            else
                    result = null;
            
            return result;
	}
	
	public boolean isOpen(){
		return _isOpen;
	}
	
	public boolean open(CodeCard card){
            /***************************************
             * boolean open() table:               *
             * ----------------------------------- *
             * isOpen | GoodCode # isOpen | return *
             * -------|----------#--------|------- *
             *  false |   false  #  false | false  *
             *  false |   true   #  true  | true   *
             *   true |   false  #  true  | false  *
             *   true |   true   #  true  | true   *
             **************************************/
            
            boolean goodCode = _code.equalsIgnoreCase(card.getCode());
            
            _isOpen = _isOpen || goodCode;
            
            return goodCode;
	}
	
	public boolean close(CodeCard card){
             /**************************************
             * boolean close() table:              *
             * ----------------------------------- *
             * isOpen | GoodCode # isOpen | return *
             * -------|----------#--------|------- *
             *  false |   false  #  false | false  *
             *  false |   true   #  false | true   *
             *   true |   false  #  true  | false  *
             *   true |   true   #  false | true   *
             **************************************/
            
            boolean goodCode = _code.equalsIgnoreCase(card.getCode());
            
            _isOpen = _isOpen && !goodCode;
            
            return goodCode;
	}
}
