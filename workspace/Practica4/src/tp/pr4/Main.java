
package tp.pr4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import tp.pr4.cityLoader.CityLoaderFromTxtFile;
import tp.pr4.cityLoader.cityLoaderExceptions.WrongCityFormatException;

import tp.pr4.utils.*;


public class Main {
    private static boolean HACIENDO_EL_FRIKI = true;
    
    private static void testPredicates()
    {
        Collection<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
        
        Predicate<Integer> primo = new Predicate<Integer>()
        {
            @Override
            public boolean apply(Integer data)
            {
                for(int i = 2 ; i < data ; ++i)
                    if(data % i == 0)
                        return false;

                return true;
            }
        };
            
        Predicate<Integer> impar = new Predicate<Integer>()
        {
            @Override
            public boolean apply(Integer data)
            {
                return data % 2 != 0;
            }
        };
            
        Filter<Integer> filter = new Filter<>(numbers.iterator() , primo.or(impar));
        
        while(filter.hasNext())
            System.out.println(filter.next());
    }
    
    /**
     * Creates the city, the engine and finally
     * starts the simulation
     * @param args
     */
    public static void main(String[] args) {
        if(!HACIENDO_EL_FRIKI)
        {
            CityLoaderFromTxtFile loader = new CityLoaderFromTxtFile();
            City city = null;

            if(args.length > 0 && args[0].length() > 0){
                    try{ 
                            city = loader.loadCity(new FileInputStream(args[0])); 
                    }
                    catch(FileNotFoundException ex1){

                            System.err.println("Error reading the map file: " + args[0] + " (No existe el fichero o el directorio)");

                            //System.out.println("ERROR TYPE 2: NO FILE");

                            System.exit(2);
                    }
                    catch(WrongCityFormatException ex2){

                            System.err.println("Error reading the map file: " + args[0] + "(Formato incorrecto)");

                            //System.out.println("ERROR TYPE 2: BAD FORMAT");

                            System.exit(2);
                    }

                    // create the engine of the game
                    RobotEngine engine = new RobotEngine(city, loader.getInitialPlace(),Direction.NORTH);
                    // plays
                    engine.startEngine();
            }
            else{

                    System.err.println("Bad params.");
                    System.err.println("Usage: java tp.pr3.Main <mapfile>");
                    System.err.println();
                    System.err.println("<mapfile> : file with the description of the city.");

                    //System.out.println("ERROR TYPE 1: NO PARAMS");

                    System.exit(1);
            }
        }
        else
            testPredicates();
    }
}
