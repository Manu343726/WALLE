package tp.pr1;

public class Street {
	private Place source;
	private Place target;
	private Direction direction;
	
	public Street(){source=new Place();target=new Place();direction=Direction.UNKNOWN;}
	public Street(Place source, Direction direction, Place target){
		this.source=source;
		this.target=target;
		this.direction=direction;
	}
	
	public boolean comeOutFrom(Place place, Direction whichDirection)	{	
		if(place==source)
			return direction.equals(whichDirection);
		else if(place==target)
			return direction.equals(whichDirection.opposite());
		else
			return false;
	}
	
	public Place nextPlace(Place whereAmI){
			Place returnValue=null;
			
			if(whereAmI==source)
				returnValue=this.target;
			else if(whereAmI==target)
				returnValue=this.source;
			
			return returnValue;
	}
}
