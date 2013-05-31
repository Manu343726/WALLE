/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5;

import tp.pr5.console.ConsoleLauncher;
import tp.pr5.gui.GUILauncher;

/**
 *
 * @author usuario_local
 */
public abstract class ViewLaunchFactory {
    
    public static UserInterfaceLauncher getLauncher( ApplicationMode mode )
    {
        if(mode == ApplicationMode.GUI || mode == ApplicationMode.BOTH)
            return new GUILauncher();
        else
            return new ConsoleLauncher();
    }
}
