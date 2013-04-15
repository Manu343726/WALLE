package tp.pr4.gui;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.EventListener;

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
@SuppressWarnings("serial")
public class PlaceCell extends JButton{

	private NavigationPanel navPanel;
	private boolean active;
	private Place place;
	
	public PlaceCell(NavigationPanel navPanel, Place place){
		initPlaceCell(navPanel, place);
	}
	
	public PlaceCell(NavigationPanel navPanel, Place place, EventListener driver){
		initPlaceCell(navPanel, place);
		setDriver(driver);
	}
	
	public void initPlaceCell(NavigationPanel navPanel, Place place){
		this.navPanel = navPanel;
		this.place = place;
		this.active = true;
		this.setName(place.getName());//He hecho un get para el nombre del lugar, creo que es lo mas comodo
		
	}
	
	public void setDriver(EventListener driver){
		this.addActionListener((ActionListener) driver);
	}
	
	public void update(){
		//if(/*lugar donde estoy*/ == place){
		    this.setBackground(Color.GREEN);
		    this.setText(place.getName());
		//}
		//else if(active)
			this.setBackground(Color.GRAY);
			
	}
}
