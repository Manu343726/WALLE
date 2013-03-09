//OK//

package tp.pr3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import tp.pr3.cityLoader.CityLoaderFromTxtFile;
import tp.pr3.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr3.items.CodeCard;
import tp.pr3.items.Fuel;
import tp.pr3.items.Garbage;


public class Main {
	/**
	 * Creates the city, the engine and finally
	 * starts the simulation
	 * @param args
	 */
	public static void main(String[] args) {
		CityLoaderFromTxtFile loader = new CityLoaderFromTxtFile();
		City city = null;
		
		if(args.length > 0 && args[0].length() > 0)
		{
			try{ city = loader.loadCity(new FileInputStream(args[0])); }
			catch(FileNotFoundException ex1)
			{
				System.err.println("Error reading the map file: " + args[0] + " (No existe el fichero o el directorio)");
				
				//System.out.println("ERROR TYPE 2: NO FILE");
				
				System.exit(2);
			}
			catch(WrongCityFormatException ex2)
			{
				System.err.println("Error reading the map file: " + args[0] + "(Formato incorrecto)");
				
				//System.out.println("ERROR TYPE 2: BAD FORMAT");
				
				System.exit(2);
			}
			
			// crate the engine of the game
			RobotEngine engine = new RobotEngine(city, loader.getInitialPlace(),Direction.NORTH);
			// plays
			engine.startEngine();
		}
		else
		{
			System.err.println("Bad params.");
			System.err.println("Usage: java tp.pr3.Main <mapfile>");
			System.err.println();
			System.err.println("<mapfile> : file with the description of the city.");
			
			//System.out.println("ERROR TYPE 1: NO PARAMS");
			
			System.exit(1);
		}
	}

}
