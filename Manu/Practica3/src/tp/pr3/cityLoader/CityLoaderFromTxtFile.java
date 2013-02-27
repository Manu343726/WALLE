package tp.pr3.cityLoader;

import java.io.InputStream;
import java.util.Scanner;

import tp.pr3.City;
import tp.pr3.Direction;
import tp.pr3.List;
import tp.pr3.Place;
import tp.pr3.Street;
import tp.pr3.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr3.items.*;

public class CityLoaderFromTxtFile {
	Place _initialPlace;
	
	public Place getInitialPlace() {return _initialPlace;}
	
	public CityLoaderFromTxtFile(){}
	
	public City loadCity(InputStream file) throws WrongCityFormatException
	{
		List<Street> streets = new List<Street>();
		List<Place> places = new List<Place>();
		
		Scanner reader = new Scanner(file);
		
		if(reader.next().equalsIgnoreCase("BeginCity"))
		{
			loadPlaces(places,reader);
			loadStreets(streets,places,reader);
			loadItems(places,reader);
			
			_initialPlace = places.get(0);
		}
		else
		{
			reader.close();
			throw new WrongCityFormatException();
		}
		
		reader.close();
		return new City(streets.toArray());
	}
	
	private void loadPlaces(List<Place> places, Scanner reader) throws WrongCityFormatException
	{
		int placeIndex = 0;
		int newPlaceIndex;
		String placeName;
		String placeDescription;
		boolean isSpaceship;
		boolean end = false;
		
		if(reader.next().equalsIgnoreCase("BeginPlaces"))
		{
			while(!end && reader.hasNext())
			{
				end = reader.next().equalsIgnoreCase("EndPlaces"); //Place
				
				if(!end)
				{
					newPlaceIndex = reader.nextInt();
					
					if((placeIndex==0 && newPlaceIndex==0) || (placeIndex>0 && newPlaceIndex == placeIndex + 1))
					{
						placeName = reader.next();
						placeDescription = reader.next().replace("_", " ");
						
						switch(reader.next())
						{
						case "noSpaceShip" : isSpaceship = false; break;
						case "spaceShip"   : isSpaceship = true;  break;
						default            : throw new WrongCityFormatException(); 
						}
					}	
					else
						throw new WrongCityFormatException(); //Place inicial no tiene indice cero o los indices no son consecutivos
				
					places.add(new Place(placeName,isSpaceship,placeDescription));
				}
			}
			
			if(!end) throw new WrongCityFormatException();
		}
		else
			throw new WrongCityFormatException();
	}
	
	private void loadStreets(List<Street> streets,List<Place> places, Scanner reader)throws WrongCityFormatException
	{
		int streetIndex = 0;
		int newStreetIndex;
		Direction streetDirection;
		boolean streetOpen;
		String streetCode;
		int beginIndex=0,endIndex=1;
		boolean end = false;
		
		if(reader.next().equalsIgnoreCase("BeginStreets"))
		{
			while(!end && reader.hasNext())
			{
				end = reader.next().equalsIgnoreCase("EndStreets"); //Street
				
				if(!end)
				{
					newStreetIndex = reader.nextInt();
					
					if((streetIndex == 0 && newStreetIndex == 0) || (streetIndex > 0 && newStreetIndex == (streetIndex + 1)))
					{
						reader.next();//Place (Begin)
						beginIndex = reader.nextInt();
						
						if(beginIndex < places.size())
						{
							streetDirection = Direction.parse(reader.next());
							
							if(streetDirection != Direction.UNKNOWN)
							{
								reader.next();//Place (end)
								endIndex = reader.nextInt();
								
								if(endIndex < places.size())
								{
									switch(reader.next().toLowerCase())
									{
									case "open"   : streetOpen = true;  break;
									case "closed" : streetOpen = false; break;
									default       : throw new WrongCityFormatException();
									}
									
									if(!streetOpen)
										streetCode = reader.next();
									else
										streetCode = "????";
								}
								else
									throw new WrongCityFormatException();
							}
							else
								throw new WrongCityFormatException();
						}
						else
							throw new WrongCityFormatException();
					}
					else
						throw new WrongCityFormatException();//Street inicial no tiene indice cero o los indices no son consecutivos
						
					
					streets.add(new Street(places.get(beginIndex),streetDirection,places.get(endIndex),streetOpen,streetCode));
				}
			}
			
			if(!end) throw new WrongCityFormatException();
		}
	}
	
	private void loadItems(List<Place> places,Scanner reader) throws WrongCityFormatException
	{
		int itemIndex = 0;
		int newItemIndex;
		String itemType;
		String itemId;
		String itemDescription;
		int itemPower=0;
		int itemTimes=0;
		String cardCode="";
		int placeIndex;
		
		boolean end = false;
		
		if(reader.next().equalsIgnoreCase("BeginItems"))
		{
			while(!end && reader.hasNext())
			{
				itemType = reader.next().toLowerCase();
				end = itemType.equalsIgnoreCase("EndItems");
				
				if(!end)
				{
					newItemIndex = reader.nextInt();
					
					if((itemIndex == 0 && newItemIndex == 0) || (itemIndex > 0 && newItemIndex == itemIndex + 1))
					{
						itemId = reader.next();
						itemDescription = reader.next();
						
						switch(itemType)
						{
						case "fuel": 
							itemPower = reader.nextInt();
							itemTimes = reader.nextInt();
							break;
						case "garbage":
							itemPower = reader.nextInt();
							break;
						case "codecard":
							cardCode = reader.next().replace("_", " ");
							break;
						default:
							throw new WrongCityFormatException();
						}
						
						reader.next();//Place
						placeIndex = reader.nextInt();
						
						if(placeIndex < places.size())
						{
							switch(itemType)
							{
							case "fuel"     : places.get(placeIndex).addItem(new Fuel(itemId,itemDescription,itemPower,itemTimes)); break;
							case "garbage"  : places.get(placeIndex).addItem(new Garbage(itemId,itemDescription,itemPower));        break;
							case "codecard" : places.get(placeIndex).addItem(new CodeCard(itemId,itemDescription,cardCode));        break;
							}
						}
						else
							throw new WrongCityFormatException();
					}
					else
						throw new WrongCityFormatException();
				}
			}
			
			if(!end) throw new WrongCityFormatException();
		}
		else
			throw new WrongCityFormatException();
	}
}
