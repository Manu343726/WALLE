/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.utils.events;

/**
 * This interface must be implemented by all classes that will have events as members (Classes that act as event senders).
 * @author
 * Manu343726
 * 
 * @remarks This is an empty interface (Really a dummy interface). Its real purpose is to avoid first parameter of an
 *          event handler (The sender parameter) will be Object. 
 *          IMPO, Object means "everything". I think that is not true in the case of event handlers first param. This param only
 *          contains event senders.
 */
public interface EventSender {}
