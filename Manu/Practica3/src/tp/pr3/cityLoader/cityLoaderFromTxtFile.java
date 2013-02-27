package tp.pr3.cityLoader;

import java.io.InputStream;
import java.util.Scanner;

import tp.pr3.City;
import tp.pr3.List;
import tp.pr3.Place;
import tp.pr3.Street;
import tp.pr3.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr3.items.Item;

public class cityLoaderFromTxtFile {
	Place _initialPlace;
	
	public Place getInitialPlace() {return _initialPlace;}
	
	public cityLoaderFromTxtFile(){}
	
	public City loadCity(InputStream file) throws WrongCityFormatException
	{
		List<Street> streets = new List<Street>();
		List<Place> places = new List<Place>();
		List<Item> items = new List<Item>();
		
	
		return null;
	}
	
	public void loadPlaces(List<Place> places, Scanner reader) throws WrongCityFormatException
	{
		int placeIndex = 0;
		int newPlaceIndex;
		String placeName;
		String placeDescription;
		boolean isSpaceship;
		boolean end = false;
		
		while(!end && reader.hasNext())
		{
			end = reader.next().equals("EndPlaces"); //Place
			
			if(!end)
			{
				newPlaceIndex = reader.nextInt(); //indice del place
				
				if((placeIndex==0 && newPlaceIndex==0) || (placeIndex>0 && newPlaceIndex == placeIndex + 1))
				{
					placeName = reader.next();
					placeDescription = reader.next().replace("_", " ");
					
					switch(reader.next())
					{
					case "noSpaceShip":
						isSpaceship = false;
						break;
					case "spaceShip":
						isSpaceship = true;
						break;
					default:
						throw new WrongCityFormatException();
					}
				}	
				else
					throw new WrongCityFormatException(); //Place inicial no tiene indice cero o los indices no son consecutivos
			
				places.add(new Place(placeName,isSpaceship,placeDescription));
			}
		}
		
		if(!end) throw new WrongCityFormatException();
	}
	
	void loadStreets(List<Street> streets,int placesCount, Scanner reader)throws WrongCityFormatException
	{
		int streetIndex = 0;
		int newStreetIndex;
		String streetName;
		String streetDescription;
		int beginIndex=0,endIndex=1;
		boolean end = false;
		
		while(!end && reader.hasNext())
		{
			end = reader.next().equals("EndStreets"); //Street
			
			if(!end)
			{
				newStreetIndex = reader.nextInt();
				
				if((streetIndex == 0 && newStreetIndex == 0) || (streetIndex > 0 && newStreetIndex == (streetIndex + 1)))
				{
					reader.next();//Place (Begin)
					beginIndex = reader.nextInt();
					
					if(beginIndex < placesCount)
					{
						
					}
					else
						throw new WrongCityFormatException();
				}
				else
					throw new WrongCityFormatException();//Street inicial no tiene indice cero o los indices no son consecutivos
					
			}
		}
		
		if(!end) throw new WrongCityFormatException();
	}
	
}
