package tp.pr4.gui.window;

import java.awt.Color;
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
 * @author Laura María de Castro Saturio , Manuel Sánchez Pérez
 *
 */
@SuppressWarnings("serial")
public class PlaceCell extends JButton implements InterfaceWindow{

	private boolean _active;
	private Place _place;

	
    /**
     * Initializes the button
     */
	public PlaceCell() {
		// TODO Auto-generated constructor stub
		this.setName("placeCell");
		_active = false;
		_place = new Place();
	}

    @Override
	/**
	 * Public method that is responsible for setting the controller on the button.
	 * @param driver contains the driver in charge of the button.
	 */
	public void setDriver(EventListener driver){
		this.addActionListener((ActionListener) driver);
	}
	
	/**
	 * This method is used to know if the cell is active
	 * @return true if the cell is active
	 */
	public boolean isActive(){
		return _active;
	}
        
	/**
	 * This method is used to know the name and description of the place referenced in the cell
	 * @return the place's description
	 */
    public String getPlaceString() 
    {
        return _place.toString();
    }
    
    /***
     * Checks if the speficied place is the place of this PlaceCell.
     * @param place The specified place.
     * @return 
     */
    public boolean isMyPlace(Place place)
    {
        return _place == place; 
    }
    
    /**
     * Prints this PlaceCell as walked
     */
    public void setWalked()
    {
        if(_active)
            this.setBackground(Color.GRAY);
    }
	
    /**
     * This method is used to set a place on the cell
     * @param place contains the place that the cell will represent
     */
	public void setCurrentPlace(Place place){
		_place = place;
		_active = true;
		this.setText(place.getName());
		this.setOpaque(true);
                this.setBackground(Color.GREEN);
	}

	
	/**
	 * This method is used to update the view and obtains the info from the models
	 * @param arg0 contains the model
	 * @param arg1 contains the argument passed from the model
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
            /* Ya no hace falta, pero si quito update() creo que refunfuña
		if(((NavigationModule) arg0).getCurrentPlace() == _place){
			this.setBackground(Color.GREEN);
		}
		else if(_active)
			this.setBackground(Color.GRAY);
            */
	}
			
	
}
	
