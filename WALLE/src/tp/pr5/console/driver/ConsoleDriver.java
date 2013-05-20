/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.console.driver;

import java.util.Scanner;
import tp.pr5.*;
import tp.pr5.messaging.WallEsMessages;

/**
 *
 * @author usuario_local
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

        System.out.println(_navigation.getCurrentPlace().toString());

        _engine.printRobotState();

        do{
            System.out.print(WallEsMessages.HEADER);

            _engine.comunicateRobot(Interpreter.generateInstruction(sc.nextLine()));
        }while( _engine.isOver() );

        if(_quit)
            WallEsMessages.messagesProvider().WriteInfo( WallEsMessages.ENDAPPLICATION );
        else if(_fuelAmount == 0)
            WallEsMessages.messagesProvider().WriteInfo( WallEsMessages.NOFUEL );
        else
            WallEsMessages.messagesProvider().WriteInfo( WallEsMessages.SHIPFINDED );

        sc.close();
    }
}
