/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.gui;

import tp.pr5.RobotEngine;
import tp.pr5.UserInterfaceLauncher;
import tp.pr5.gui.driver.GUIController;
import tp.pr5.gui.window.InstructionsPanel;
import tp.pr5.gui.window.MainWindow;
import tp.pr5.gui.window.NavigationPanel;
import tp.pr5.gui.window.RobotPanel;

/**
 * Launches the application GUI
 * @author
 * Laura María de Castro Saturio , Manuel Sánchez Pérez
 */
public class GUILauncher implements UserInterfaceLauncher {
    /**
     * Launches the application GUI
     * @param engine Pointer to the game model.
     */
    @Override
    public void launch(RobotEngine engine)
    {
        InstructionsPanel instructionsPanel = new InstructionsPanel();
        RobotPanel robotPanel = new RobotPanel( engine.getItemContainer() );
        NavigationPanel navigationPanel = new NavigationPanel();
        GUIController driver = new GUIController( engine , engine.getNavigationModule() /* dolor */ , navigationPanel , instructionsPanel , robotPanel);
        MainWindow window = new MainWindow( engine , robotPanel , navigationPanel , instructionsPanel , driver);

        engine.AddHandler( window );
        engine.addNavigationObserver( navigationPanel );

        engine.forceRefresh();
        
        window.setVisible( true );
    }
}
