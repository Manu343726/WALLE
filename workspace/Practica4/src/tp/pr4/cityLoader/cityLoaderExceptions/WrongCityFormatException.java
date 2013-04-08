package tp.pr4.cityLoader.cityLoaderExceptions;

@SuppressWarnings("serial")
/**
 * This class represents a citymap file parsing error
 * @author Manu343726
 *
 */
public class WrongCityFormatException extends Exception {
	public WrongCityFormatException(){}
	public WrongCityFormatException(String message){super(message);}
}