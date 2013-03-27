package tp.pr3.cityLoader;

import tp.pr3.cityLoader.cityLoaderExceptions.*;

import java.util.Scanner;
import java.util.regex.*;

/**
 * This class provides methods to safe-parsing input streams.
 * @author Manu343726
 *
 */
public abstract class LoaderParser {
	/**
	 * This metod closes the specified scanner and throws a WrongCityFormatException
	 * @param reader
	 * @throws WrongCityFormatException
	 */
	public static void closeAndThrow(Scanner reader) throws WrongCityFormatException
	{
		reader.close();
		throw new WrongCityFormatException();
	}
	
	/**
	 * Tries to parse a header mark, and checks if its equals to the specified mark
	 * @param reader Input stream
	 * @param mark Mark to be compared
	 * @return True only if the specified mark equals to the parsed mark
	 * @throws WrongCityFormatException If the mark can't be parsed.
	 */
	public static boolean parseMark(Scanner reader, String mark) throws WrongCityFormatException
	{
		if(!reader.hasNext())
		{
			closeAndThrow(reader);
			return false; //Inalcanzable, en fin...
		}
		else
			return reader.next().equalsIgnoreCase(mark);
	}
	
	/**
	 * Tries to parse an int.
	 * @param reader Input stream
	 * @return The parsed int
	 * @throws WrongCityFormatException If the input can't be parsed or the input its not a number.
	 */
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
	
	/**
	 * Tries to parse a string
	 * @param reader Input stream
	 * @return The parsed string
	 * @throws WrongCityFormatException If the input can't be parsed.
	 */
	public static String parseString(Scanner reader) throws WrongCityFormatException
	{
		if(reader.hasNext())
			return reader.next();
		else
			closeAndThrow(reader);
		
		return "";//Inalcanzable...
	}
	
	
}