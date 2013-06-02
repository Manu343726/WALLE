package tp.pr5;

import tp.pr5.utils.events.Event;
import tp.pr5.messaging.WallEsMessages;
import tp.pr5.utils.events.WALLE.NavigationModuleChangedEventArgs;
import tp.pr5.gui.window.NavigationPanel;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.items.Item;

/***CLASS NAVIGATION MODULE***/
/**
 * This class is in charge of the robot navigation features.
 *  It contains the city where the robot looks for garbage, the current place where the robot is, 
 *  and the current direction of the robot. It contains methods to handle the different robot movements 
 *  and to pick and drop items at the current place.
 *  
 *  @author Laura María de Castro & Manuel Sánchez Pérez
 */

public class NavigationModule extends Event<NavigationModuleChangedEventArgs>{	
    private City      _city;
    private Place     _currentPlace;
    private Direction _currentDirection;

    private NavigationPanel _navPanel;

    /* CONSTRUCTORS */

    public NavigationModule(City city, Place place){
        _city = city;
        _currentPlace = place;
        _currentDirection = Direction.NORTH;
    }

    /* PUBLIC METHODS */

    /**
     * Checks if the robot has arrived at a spaceship
     * @return true if an only if the current place is the spaceship
     */
    public boolean atSpaceship(){
        return _currentPlace.isSpaceship();
    }

    /**
     * Returns the robot heading
     * @return The direction where the robot is facing to.
     */
    public Direction getCurrentHeading(){
        return _currentDirection;
    }

    /**
     * Returns the current place where the robot is
     * @return The current place
     */
    public Place getCurrentPlace(){	
        return _currentPlace;
    }

    /**
     * Updates the current direction of the robot according to the rotation
     * @param rotation  left or right
     */
    public void rotate(Rotation rotation){
        initHeading( _currentDirection.rotate( rotation ) ); //Menos código duplicado. No se como no me había dado cuenta antes.
    }

    /**
     * Tries to pick an item characterized by a given identifier from the current place. 
     * If the action was completed the item is removed from the current place.
     * @param id The identifier of the item
     * @return The item of identifier id if it exists in the place. Otherwise the method returns null
     */
    public Item pickItemFromCurrentPlace(String id){
        return _currentPlace.pickItem(id);//No notifico: Si se hace pick en navigation module, se hará una inserción en robotEngine::itemContainer y viceversa. Esos cambios están controlados por el evento changed del item container del engine.
    }

    /**
     * Checks if there is an item with a given id in this place
     * @param id Identifier of the item we are looking for
     * @return true if and only if an item with this id is in the current place
     */
    public boolean findItemInCurrentPlace(String id){
        return _currentPlace.existItem(id);
    }

    /**
     * Drop an item in the current place. It assumes that there is no other item with the same name/id there. 
     * Otherwise, the behaviour is undefined.
     * @param it The name of the item to be dropped.
     * @return true if the action has been completed correctly
     */
    public boolean dropItemAtCurrentPlace(Item it){
        return _currentPlace.dropItem(it); //No notifico: igual que en pick.
    }

    /**
     * Initializes the current heading according to the parameter
     * @param direction New direction for the robot
     */
    public void initHeading(Direction direction){
        Direction previous = _currentDirection;

        _currentDirection = direction;

        reportObservers( new NavigationModuleChangedEventArgs( previous , _currentDirection , _currentPlace) );
    }

    /**
     * Returns the street opposite the robot
     * @return The street which the robot is facing, or null, if there is not any street in this direction
     */
    public Street getHeadingStreet(){
        return _city.lookForStreet(_currentPlace, _currentDirection);
    }

    /**
     * Raises the Observable.update() event
     * @param move The event arg that specifies if the change of the model is a move instruction ( See NavigationPanle::update() )
     */
    public void reportObservers(NavigationModuleChangedEventArgs args)
    {
        this.RaiseEvent(args);
    }
    
     /***
     * Forces change event.
     */
    public void forceRefresh()
    {
        reportObservers( new NavigationModuleChangedEventArgs( _currentPlace , _currentPlace , _currentDirection ) );
    }

    /**
     * The method tries to move the robot following the current direction. 
     * If the movement is not possible because there is no street, or there is a street which is closed, then it throws an exception.
     * Otherwise the current place is updated according to the movement
     * @throws InstructionExecutionException An exception with a message about the encountered problem
     */
    public void move() throws InstructionExecutionException{
        Street currentStreet = getHeadingStreet();
        Place previous = _currentPlace;

        if(currentStreet != null){
                if(currentStreet.isOpen()){
                    _currentPlace = currentStreet.nextPlace(_currentPlace);
                    reportObservers( new NavigationModuleChangedEventArgs(previous , _currentPlace , _currentDirection ) );
                }
                else
                    throw new InstructionExecutionException(WallEsMessages.STREETCLOSED);
        }
        else
            throw new InstructionExecutionException(WallEsMessages.NOSTREET);
    }

    /**
     * Prints the information (description + inventory) of the current place
     */
    public void scanCurrentPlace(){
        WallEsMessages.messagesProvider().WriteInfo(_currentPlace.toString());
    }
	
	
}
