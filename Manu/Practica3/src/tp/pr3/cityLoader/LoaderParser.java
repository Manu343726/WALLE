package tp.pr3.cityLoader;

import tp.pr3.cityLoader.cityLoaderExceptions.*;

import java.util.Scanner;
import java.util.regex.*;

public abstract class LoaderParser {
	public static void closeAndThrow(Scanner reader) throws WrongCityFormatException
	{
		reader.close();
		throw new WrongCityFormatException();
	}
	
	public static boolean parseMark(Scanner reader, String mark) throws WrongCityFormatException
	{
		String next;
		
		if(!reader.hasNext())
		{
			closeAndThrow(reader);
			return false; //Inalcanzable, en fin...
		}
		else
			return reader.next().equalsIgnoreCase(mark);
	}
	
	public static int parseInt(Scanner reader) throws WrongCityFormatException
	{
		Pattern regex = Pattern.compile("-?[0-9]+");
		String next;
		Matcher matcher;
		
		if(reader.hasNext())
		{
			next = reader.next();
			matcher = regex.matcher(next);
			
			if(matcher.find() && !matcher.find())
				return Integer.parseInt(next);
			else
				closeAndThrow(reader);
		}
		else
			closeAndThrow(reader);
		
		return -1;//Inalcanzable, pero para que el compilador se quede tranquilo...Si tubiera una macro no tendría que hacer éstas cosas
	}
	
	public static String parseString(Scanner reader) throws WrongCityFormatException
	{
		if(reader.hasNext())
			return reader.next();
		else
			closeAndThrow(reader);
		
		return "";//Inalcanzable...
	}
	
	
}
