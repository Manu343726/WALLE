package tp.pr3;

import tp.pr3.instructions.exceptions.*;

public class NavigationModule {
	private City _city;
	private Place _currentPlace;
	private Direction _currentDirection;
	
	public boolean   atSpaceShip()       {return _currentPlace.isSpaceship();}
	public Direction getCurrentHeading() {return _currentDirection;}
	public Place     getCurrentPlace()   {return _currentPlace;}
 	
	
	public NavigationModule(City city, Place place)
	{
		_city = city;
		_currentPlace = place;
		_currentDirection = Direction.NORTH;
	}
	
	public void rotate(Rotation rotation)
	{
		_currentDirection.rotate(rotation);
	}
	
	public Item pickItemFromCurrentPlace(String id)
	{
		return _currentPlace.pickItem(id);
	}
	
	public boolean findItemInCurrentPlace(String id)
	{
		return _currentPlace.existItem(id);
	}
	
	public void dropItemAtCurrentPlace(Item it)
	{
		_currentPlace.dropItem(it);
	}
	
	public void initHeading(Direction direction)
	{
		_currentDirection = direction;
	}
	
	public Street getHeadingStreet()
	{
		return _city.lookForStreet(_currentPlace, _currentDirection);
	}
	
	public void move() throws InstructionExecutionException
	{
		Street currentStreet = getHeadingStreet();
		
		if(currentStreet != null && currentStreet.isOpen())
			_currentPlace = currentStreet.nextPlace(_currentPlace);
		else
			throw new InstructionExecutionException();
	}
}
