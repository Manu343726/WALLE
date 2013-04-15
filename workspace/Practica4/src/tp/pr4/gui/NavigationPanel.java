package tp.pr4.gui;

import java.util.EnumMap;
import java.util.EventListener;

import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import tp.pr4.Direction;
import tp.pr4.Place;


/**
 * This class is in charge of the panel that displays the information about the robot 
 * heading and the city that is traversing. It contains the grid that represents the city 
 * in the Swing interface, a text area to show the place descriptions, and a label with an 
 * icon which represents the robot heading
 * 
 * The 11x11 grid contains PlaceCell objects and the first place starts at (5,5). 
 * This panel will update the visited places when the robot moves from one place to another. 
 * Additionally it will show the place description on a text area if the user clicks on a visited place.
 * @author Laura
 *
 */
@SuppressWarnings("serial")
public class NavigationPanel extends JPanel{

	private int row; //Current row
	private int col; //Current column
	private PlaceCell[][] cells;   //Grid with the MapCells
	private JTextArea info;        //Text area where the room description is shown
	private JLabel labelRobotHeading; //Label where the WALLE image appears showing the robot heading
	private EnumMap<Direction , ImageIcon> headingIcons; //A map with the icons for each heading direction
	
	public NavigationPanel(){
		initNavigationPanel();
	}
	
	public NavigationPanel(EventListener driver){
		initNavigationPanel();
		setDriver(driver);
	}
	
	public void initNavigationPanel(){
		
	}
	
	public void setDriver(EventListener driver){
		
	}
	
	public void update(){
		
	}



}
