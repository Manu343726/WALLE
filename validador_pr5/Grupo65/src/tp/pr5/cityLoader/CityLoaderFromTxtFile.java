package tp.pr5.cityLoader;

import tp.pr5.items.Fuel;
import tp.pr5.items.Garbage;
import tp.pr5.items.CodeCard;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import tp.pr5.City;
import tp.pr5.Direction;
import tp.pr5.Place;
import tp.pr5.Street;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;

public class CityLoaderFromTxtFile {
	Place _initialPlace;
	
	/**
	 * Returns the loaded city initial place
	 * @return
	 */
	public Place getInitialPlace() {return _initialPlace;}
	
	/**
	 * Initializes the loader
	 */
	public CityLoaderFromTxtFile(){}
	
	/**
	 * Loads a city from the specified input stream.
	 * @param file
	 * @return The loaded city
	 * @throws WrongCityFormatException Only if the stream format is not correct.
	 */
	public City loadCity(InputStream file) throws WrongCityFormatException
	{
		ArrayList<Street> streets = new ArrayList<>();
		ArrayList<Place> places = new ArrayList<>();
		Street[] map;
		
		Scanner reader = new Scanner(file);
		
		if(LoaderParser.parseMark(reader, "BeginCity"))
		{
			loadPlaces(places,reader);
			loadStreets(streets,places,reader);
			loadItems(places,reader);
			
			_initialPlace = places.get(0);
		}
		else
			LoaderParser.closeAndThrow(reader);
		
		reader.close();
		
		map=new Street[streets.size()];
		streets.toArray(map);
		return new City(map);
	}
	
	private void loadPlaces(ArrayList<Place> places, Scanner reader) throws WrongCityFormatException
	{
		int placeIndex = 0;
		int newPlaceIndex = 0;
		String placeName="";
		String placeDescription="";
		boolean isSpaceship=false;
		boolean end = false;
                
                boolean error = false;
                
                String isSpaceShip = "";
		
		if(LoaderParser.parseMark(reader, "BeginPlaces"))
		{
			while(!end && reader.hasNext() && !error)
			{
				end = LoaderParser.parseMark(reader, "EndPlaces"); //Place
				
				if(!end)
				{
					newPlaceIndex = LoaderParser.parseInt(reader);
					
					if((placeIndex==0 && newPlaceIndex==0) || newPlaceIndex == (placeIndex + 1))
					{
						placeName = LoaderParser.parseString(reader);
						placeDescription = LoaderParser.parseString(reader).replace("_", " ");
						
						switch( LoaderParser.parseString(reader) )
						{
						case "noSpaceShip" : isSpaceship = false; break;
						case "spaceShip"   : isSpaceship = true;  break;
						default            : LoaderParser.closeAndThrow(reader);
						}
					}	
					else
						error = true; //Place inicial no tiene indice cero o los indices no son consecutivos
				
					places.add(new Place(placeName,isSpaceship,placeDescription));
				}
				
				placeIndex = newPlaceIndex;
			}
			
			error = !end;
		}
		else
			error = true;
                
                if( error )
                    LoaderParser.closeAndThrow(reader);
	}
	
	private void loadStreets(ArrayList<Street> streets,ArrayList<Place> places, Scanner reader)throws WrongCityFormatException
	{
		int streetIndex = 0;
		int newStreetIndex = 0;
		Direction streetDirection=Direction.UNKNOWN;
		boolean streetOpen=false;
		String streetCode="";
		int beginIndex=0,endIndex=1;
		boolean end = false;
                
                boolean error = false;
		
		if(LoaderParser.parseMark(reader, "BeginStreets"))
		{
			while(!end && reader.hasNext() && !error)
			{
				end = LoaderParser.parseMark(reader, "EndStreets"); //Street
				
				if(!end)
				{
					newStreetIndex = LoaderParser.parseInt(reader);
					
					if((streetIndex == 0 && newStreetIndex == 0) || newStreetIndex == (streetIndex + 1))
					{
						LoaderParser.parseString(reader);//Place (Begin)
						beginIndex = LoaderParser.parseInt(reader);
						
						if(beginIndex < places.size())
						{
							streetDirection = Direction.parse(LoaderParser.parseString(reader));
							
							if(streetDirection != Direction.UNKNOWN)
							{
								LoaderParser.parseString(reader);//Place (end)
								endIndex = LoaderParser.parseInt(reader);
								
								if(endIndex < places.size())
								{
									switch(LoaderParser.parseString(reader).toLowerCase())
									{
									case "open"   : streetOpen = true;  break;
									case "closed" : streetOpen = false; break;
									default       : LoaderParser.closeAndThrow(reader);
									}
									
									if(!streetOpen)
										streetCode = LoaderParser.parseString(reader);
									else
										streetCode = "????";
								}
								else
									error = true;
							}
							else
								error = true;
						}
						else
							error = true;
					}
					else
						error = true;//Street inicial no tiene indice cero o los indices no son consecutivos
						
					if(!error)
                                            streets.add(new Street(places.get(beginIndex),streetDirection,places.get(endIndex),streetOpen,streetCode));
				}
				
				streetIndex = newStreetIndex;
			}
			
			error = !end;
		}
		else
			error = true;
		
                if( error )
                    LoaderParser.closeAndThrow( reader );
	}
	
	private void loadItems(ArrayList<Place> places,Scanner reader) throws WrongCityFormatException
	{
		int itemIndex = 0;
		int newItemIndex = 0;
		String itemType;
		String itemId;
		String itemDescription;
		int itemPower=0;
		int itemTimes=0;
		String cardCode="";
		int placeIndex;
		
		boolean end = false;
		
		if(LoaderParser.parseMark(reader,"BeginItems"))
		{
			while(!end && reader.hasNext())
			{
				itemType = LoaderParser.parseString(reader).toLowerCase();
				end = itemType.equalsIgnoreCase("EndItems");
				
				if(!end)
				{
					newItemIndex = LoaderParser.parseInt(reader);
					
					if((itemIndex == 0 && newItemIndex == 0) || newItemIndex == (itemIndex + 1))
					{
						itemId = LoaderParser.parseString(reader);
						itemDescription = LoaderParser.parseString(reader).replace('_', ' ');//Pues resulta que no lo había hecho...                                                   
                                                
						switch(itemType)
						{
						case "fuel": 
							itemPower = LoaderParser.parseInt(reader);
							itemTimes = LoaderParser.parseInt(reader);
							break;
						case "garbage":
							itemPower = LoaderParser.parseInt(reader);
							break;
						case "codecard":
							cardCode = LoaderParser.parseString(reader).replace("_", " ");
							break;
						default:
							LoaderParser.closeAndThrow(reader);
						}
						
						LoaderParser.parseString(reader);//Place
						placeIndex = LoaderParser.parseInt(reader);
						
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
							LoaderParser.closeAndThrow(reader);
					}
					else
						LoaderParser.closeAndThrow(reader);
				}
				
				itemIndex = newItemIndex;
			}
			
			if(!end) LoaderParser.closeAndThrow(reader);
		}
		else
			LoaderParser.closeAndThrow(reader);
	}
}
