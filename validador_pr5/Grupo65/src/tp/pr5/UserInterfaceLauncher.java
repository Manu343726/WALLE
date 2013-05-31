/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5;

/**
 * An interface for user interface launchers.
 * @author Manuel Sánchez Pérez
 */
public interface UserInterfaceLauncher {
    /**
     * Launches the application user interface with the speficied engine as model.
     * @param engine Game model.
     */
    public void launch( RobotEngine engine );
    
    /**
     * Launches the application user interface with the speficied engine as model.
     * @param engine Game model.
     * @param count Number of interfaces to launch.
     */
    public void launch( RobotEngine engine , int count );
}
