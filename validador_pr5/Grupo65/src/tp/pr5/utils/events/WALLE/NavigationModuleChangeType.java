/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.utils.events.WALLE;

/**
 * The set of changes that could be performed at the navigation module
 * @author
 * Laura María de Castro Saturio , Manuel Sánchez Pérez
 */
public enum NavigationModuleChangeType {
    CHANGE_CURRENTPLACE,     ///< Current place changed (Move instruction).
    CHANGE_CURRENTDIRECTION, ///< Current direction changed (Turn instruction).
    CHANGE_CURRENTPLACE_EXIT ///< Current place changed, new place has spaceship (Game end).
}
