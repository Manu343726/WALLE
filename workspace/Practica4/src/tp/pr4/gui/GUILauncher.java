/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr4.gui;

import tp.pr4.RobotEngine;

/**
 * Launches the application GUI
 * @author
 * Laura María de Castro Saturio , Manuel Sánchez Pérez
 */
public abstract class GUILauncher {
    /***
     * Launches the application GUI
     * @param engine Pointer to the game model.
     */
    public static void launch(RobotEngine engine)
    {
        InstructionsPanel instructionsPanel = new InstructionsPanel();
        RobotPanel robotPanel = new RobotPanel();
        NavigationPanel navigationPanel = new NavigationPanel();
        RobotDriver driver = new RobotDriver( engine , engine.getNavigationModule() , navigationPanel , instructionsPanel , robotPanel);
        MainWindow window = new MainWindow( engine , robotPanel , navigationPanel , instructionsPanel , driver);

        engine.addObserver(window );
        engine.addItemsObserver( robotPanel );
        engine.addNavigationObserver( navigationPanel );

        engine.forceRefresh();
        
        window.setVisible( true );
    }
}
