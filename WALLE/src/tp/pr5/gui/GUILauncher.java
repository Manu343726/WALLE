/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.gui;

import tp.pr5.RobotEngine;
import tp.pr5.gui.driver.RobotDriver;
import tp.pr5.gui.window.InstructionsPanel;
import tp.pr5.gui.window.MainWindow;
import tp.pr5.gui.window.NavigationPanel;
import tp.pr5.gui.window.RobotPanel;

/**
 * Launches the application GUI
 * @author
 * Laura María de Castro Saturio , Manuel Sánchez Pérez
 */
public abstract class GUILauncher {
    /**
     * Launches the application GUI
     * @param engine Pointer to the game model.
     */
    public static void launch(RobotEngine engine)
    {
        InstructionsPanel instructionsPanel = new InstructionsPanel();
        RobotPanel robotPanel = new RobotPanel( engine.getItemContainer() );
        NavigationPanel navigationPanel = new NavigationPanel();
        RobotDriver driver = new RobotDriver( engine , engine.getNavigationModule() /* dolor */ , navigationPanel , instructionsPanel , robotPanel);
        MainWindow window = new MainWindow( engine , robotPanel , navigationPanel , instructionsPanel , driver);

        engine.AddHandler( window );
        engine.addNavigationObserver( navigationPanel );

        engine.forceRefresh();
        
        window.setVisible( true );
    }
}
