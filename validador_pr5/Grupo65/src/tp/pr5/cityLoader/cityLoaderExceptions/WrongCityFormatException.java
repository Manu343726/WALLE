package tp.pr5.cityLoader.cityLoaderExceptions;

@SuppressWarnings("serial")
/**
 * This class represents a citymap file parsing error
 * @author Laura María de Castro Saturio , Manuel Sánchez Pérez
 *
 */
public class WrongCityFormatException extends Exception {
	public WrongCityFormatException(){}
	public WrongCityFormatException(String message){super(message);}
}
