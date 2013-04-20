package tp.pr4.gui;

import java.awt.Color;
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
 * @author Laura María de Castro Saturio , Manuel Sánchez Pérez
 *
 */
@SuppressWarnings("serial")
public class PlaceCell extends JButton implements InterfaceWindow{

	private boolean _active;
	private Place _place;

	
	
	public PlaceCell() {
		// TODO Auto-generated constructor stub
		this.setName("placeCell");
		_active = false;
		_place = new Place();
	}

        @Override
	public void setDriver(EventListener driver){
		this.addActionListener((ActionListener) driver);
	}
	
	
	public boolean isActive(){
		return _active;
	}
        
        public String getPlaceString() //No hagas un getPlace() y a ese le hagas un toString(), le estás dando la instancia de Place a todo quisqui
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
            return _place == place; //Si no se ha hecho nada raro, todo el programa debería hacer referencia a las instancias contenidas por el citymap, de manera que ésto vale.
        }
        
        /**
         * Prints this PlaceCell as walked
         */
        public void setWalked()
        {
            if(_active)
                this.setBackground(Color.GRAY);
        }
	
	public void setCurrentPlace(Place place){
		_place = place;
		_active = true;
		this.setText(place.getName());
		this.setOpaque(true);
                this.setBackground(Color.GREEN);
	}

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
	
