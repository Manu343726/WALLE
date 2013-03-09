package tp.pr3.cityLoader.cityLoaderExceptions;

@SuppressWarnings("serial")
public class WrongCityFormatException extends Exception {
	public WrongCityFormatException(){}
	public WrongCityFormatException(String message){super(message);}
}
