package tp.pr4.gui;

import java.awt.Color;

import tp.pr4.Direction;
import tp.pr4.NavigationModule;

import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Observable;



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
public class PlaceCell extends JButton implements InterfaceWindow{

	private boolean active;
	private Place place;

	
	
	public PlaceCell() {
		// TODO Auto-generated constructor stub
		this.setName("placeCell");
		active = false;
		place = new Place();
	}

	public void setDriver(EventListener driver){
		this.addActionListener((ActionListener) driver);
	}
	
	
	public boolean isActive(){
		return this.active;
	}

	public Place getPlace(){
		return place;
	}
	
	public void setCurrentPlace(Place place){
		this.place = place;
		this.active = true;
		this.setText(place.getName());
		this.setOpaque(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(((NavigationModule) arg0).getCurrentPlace() == place){
			this.setBackground(Color.GREEN);
		}
		else if(active)
			this.setBackground(Color.GRAY);
	}
			
	
}
	
