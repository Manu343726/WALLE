/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.console.driver;

import java.util.Scanner;
import tp.pr5.*;
import tp.pr5.messaging.WallEsMessages;

/**
 * This class is the application MVC controller for the console mode.
 * @author Manuel Sánchez Pérez
 */
public class ConsoleDriver {
    private RobotEngine _engine;
    
    
    /**
     * Creates a new console mode MVC controller with the specified robot engine (model).
     * @param engine A reference to the robot engine used.
     */
    public ConsoleDriver(RobotEngine engine) { _engine = engine; }
    
    /**
     * Starts the game loop
     */
    public void Start()
    {
        Scanner sc = new Scanner(System.in);

        _engine.forceRefresh();

        _engine.printRobotState();

        do{
            System.out.print( WallEsMessages.HEADER );

            _engine.comunicateRobot( Interpreter.generateInstruction( sc.nextLine() ) );
        }while( !_engine.isOver() );

        sc.close();
    }
}
