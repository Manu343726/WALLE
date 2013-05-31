/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.console;

import tp.pr5.RobotEngine;
import tp.pr5.UserInterfaceLauncher;
import tp.pr5.console.driver.ConsoleDriver;

/**
 * User interface launcher for the console-only application mode.
 * @author usuario_local
 */
public class ConsoleLauncher implements UserInterfaceLauncher {

    /**
     * Launches the application user interface with the speficied engine as model.
     * @param engine Game model.
     * @param count Number of interfaces to launch.
     */
    @Override
    public void launch(RobotEngine engine , int count)
    {
        launch( engine );
    }
    
    /**
     * Launches the application user interface with the speficied engine as model.
     * @param engine Game model.
     */
    @Override
    public void launch(RobotEngine engine) {
        ConsoleDriver controller = new ConsoleDriver( engine );
        ConsoleView view = new ConsoleView( engine );
        controller.Start();
    }
    
}
