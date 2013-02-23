package tp.pr1.testprofesor;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class) 

@Suite.SuiteClasses( { 
						PlaceTest.class, 
						StreetTest.class,
						InstructionTest.class, 
						InterpreterTest.class, 
					} ) 


public class AllTests {
    // Add new classes to the SuiteClasses array
}
