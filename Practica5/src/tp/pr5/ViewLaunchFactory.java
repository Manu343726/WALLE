/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5;

import tp.pr5.console.ConsoleLauncher;
import tp.pr5.gui.GUILauncher;

/**
 * Abstract factory for the application interface launcher.
 * @author Manuel Sánchez Pérez
 */
public abstract class ViewLaunchFactory {
    
    /**
     * Provides the specific view launcher of a given application mode.
     * @param mode Application view mode.
     * @return A reference to the view launcher.
     */
    public static UserInterfaceLauncher getLauncher( ApplicationMode mode )
    {
        if(mode == ApplicationMode.GUI || mode == ApplicationMode.BOTH)
            return new GUILauncher();
        else
            return new ConsoleLauncher();
    }
}
