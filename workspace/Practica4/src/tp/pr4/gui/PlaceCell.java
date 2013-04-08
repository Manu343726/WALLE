package tp.pr4.gui;

import java.io.Serializable;

import javax.swing.JButton;

import tp.pr4.Place;

/**
 * This class represents a Place in the city on the Swing
 * interface. It is a button, which name is the place name.
 * 
 * A PlaceCell needs to store a reference to the place that 
 * it represents. However, this place should not be modified 
 * by the PlaceCell
 * 
 * When the user clicks on a PlaceCell the CityPanel will show 
 * the place description if the Place was also peviously visited
 * @author Laura
 *
 */
public class PlaceCell extends JButton implements Serializable{

	private NavigationPanel owner;
	boolean active;
	Place thePlace;
	
}
