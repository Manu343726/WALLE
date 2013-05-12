/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.utils.events.WALLE;

import tp.pr5.Direction;
import tp.pr5.Place;

/**
 * Event argumments for NavigationModule change event.
 * @author Laura María de Castro Saturio , Manuel Sánchez Pérez
 */
public class NavigationModuleChangedEventArgs {
    NavigationModuleChangeType _type;
    Place     _previousPlace;
    Direction _previousDirection;
    Place     _currentPlace;
    Direction _currentDirection;
    
    /***
     * Gets the type of the change that raises the event.
     * @return A value of the NavigationModuleChangeType enum.
     */
    public NavigationModuleChangeType getChangeType() { return _type; }
    
    /***
     * Gets WALLE's current place before the change.
     * @return 
     */
    public Place getPreviousPlace() { return _previousPlace; }
    
     /***
     * Gets WALLE's current place before the change.
     * @return 
     */
    public Direction getPreviousDirection() { return _previousDirection; }
    
     /***
     * Gets WALLE's current place after the change.
     * @return 
     */
    public Place getCurrentPlace() { return _currentPlace; }
    
     /***
     * Gets WALLE's current place after the change.
     * @return 
     */
    public Direction getCurrentDirection() { return _currentDirection; }
    
    /**
     * Creates a set of navigation module changed event argumments with the specified data.
     * @param type Change type.
     * @param previousPlace WALLE's current position before the change.
     * @param currentPlace WALLE's current place after the change.
     * @param previousDirection WALLE's current direction before the change.
     * @param currentDirection WALLE's current direction after the change.
     */
    public NavigationModuleChangedEventArgs( NavigationModuleChangeType type , Place previousPlace , Place currentPlace , Direction previousDirection , Direction currentDirection)
    {
        setup( type , previousPlace , currentPlace , previousDirection , currentDirection);
    }
    
    /***
     * Ctor for position changes.
     * @param previousPlace WALLE's current position before the change.
     * @param currentPlace WALLE's current place after the change.
     * @param currentDirection WALLE's current direction.
     */
    public NavigationModuleChangedEventArgs(Place previousPlace , Place currentPlace , Direction currentDirection)
    {
        setup( currentPlace.isSpaceship() ? NavigationModuleChangeType.CHANGE_CURRENTPLACE_EXIT : NavigationModuleChangeType.CHANGE_CURRENTPLACE , previousPlace , currentPlace , currentDirection , currentDirection);
    }
    
     /***
     * Ctor for direction changes.
     * @param previousDirection WALLE's current position before the change.
     * @param currentDirection WALLE's current place after the change.
     * @param currentPlace WALLE's current direction.
     */
    public NavigationModuleChangedEventArgs(Direction previousDirection , Direction currentDirection , Place currentPlace)
    {
        setup( NavigationModuleChangeType.CHANGE_CURRENTDIRECTION , currentPlace , currentPlace , previousDirection , currentDirection );
    }
    
    //Netbeans no me deja usar delegating ctors? raro...
    private void setup( NavigationModuleChangeType type , Place previousPlace , Place currentPlace , Direction previousDirection , Direction currentDirection )
    {
        _type = type;
        _currentPlace = currentPlace;
        _currentDirection = currentDirection;
        _previousPlace = previousPlace;
        _previousDirection = previousDirection;
    }
}
