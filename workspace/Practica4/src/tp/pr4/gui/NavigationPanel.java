package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.EnumMap;
import java.util.EventListener;
import java.util.Observable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import tp.pr4.Direction;
import tp.pr4.NavigationModule;


/**
 * This class is in charge of the panel that displays the information about the robot 
 * heading and the city that is traversing. It contains the grid that represents the city 
 * in the Swing interface, a text area to show the place descriptions, and a label with an 
 * icon which represents the robot heading
 * 
 * The 11x11 grid contains PlaceCell objects and the first place starts at (5,5). 
 * This panel will update the visited places when the robot moves from one place to another. 
 * Additionally it will show the place description on a text area if the user clicks on a visited place.
 * @author Laura & Manuel
 *
 */
@SuppressWarnings("serial")
public class NavigationPanel extends JPanel implements InterfaceWindow{


	private NavigationModule navModule;
	
	private int row; //Current row
	private int col; //Current column
	
	private PlaceCell[][] cells;   //Grid with the MapCells
	private JTextArea info;        //Text area where the room description is shown
	private JLabel labelRobotHeading; //Label where the WALLE image appears showing the robot heading
	private EnumMap<Direction , ImageIcon> headingIcons; //A map with the icons for each heading direction
	

	public NavigationPanel(NavigationModule navModule){
		initNavigationPanel(navModule);
	}
	
	
	public void initNavigationPanel(NavigationModule navModule){
		this.navModule = navModule;
		row = 4;
		col = 5;
		cells = new PlaceCell[11][11];
		for(int i = 0; i < 11; i++){
			for(int j = 0; j < 11; j++){
				cells[i][j] = new PlaceCell();
			}
		}
		info = new JTextArea();
		labelRobotHeading = new JLabel();
		headingIcons = new EnumMap<Direction, ImageIcon>(Direction.class);
		
		info.setEditable(false);
		
		headingIcons.put(Direction.NORTH, new ImageIcon("src/tp/pr4/gui/images/walleNorth.png"));
		headingIcons.put(Direction.EAST, new ImageIcon("src/tp/pr4/gui/images/walleEast.png"));
		headingIcons.put(Direction.SOUTH, new ImageIcon("src/tp/pr4/gui/images/walleSouth.png"));
		headingIcons.put(Direction.WEST, new ImageIcon("src/tp/pr4/gui/images/walleWest.png"));
		
		labelRobotHeading.setIcon(headingIcons.get(Direction.NORTH));
		
		this.setLayout(new GridLayout(2, 1));
		
		JPanel panelGrid = new JPanel(new GridLayout(11, 11));
		panelGrid.setBorder(new TitledBorder("Navigation Panel"));
		for(int i = 0; i < 11; i++)
			for(int j = 0; j < 11; j++)
				panelGrid.add(cells[i][j]);
		
		JPanel panelAux = new JPanel(new BorderLayout());
		panelAux.add(labelRobotHeading, "West");
		panelAux.add(panelGrid, "Center");
		
		JScrollPane panelAuxText = new JScrollPane(info, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelAuxText.setBorder(new TitledBorder("Log"));
		
		this.add(panelAux);
		this.add(panelAuxText);
	}
	
	public void setDriver(EventListener driver){
		for(int i = 0; i < 11; i++){
			for(int j = 0; j < 11; j++){
				cells[i][j].setDriver(driver);
			}
		}
	}
	
	private void calculateCoords(Direction dir){
		switch(dir){
		case NORTH: row--; break;
		case SOUTH: row++; break;
		case EAST:  col++; break;
		case WEST: col--; break;
		}
	}
	
	
	//Al navPanel le llegan las instrucciones de mov y turn
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg != null){//Creo que esto ya no hace falta, pero no sabre hasta que pruebe 
			if(!(boolean) arg) //Has happened a turn instruction
				labelRobotHeading.setIcon(headingIcons.get(navModule.getCurrentHeading()));
			else{//Has happened a move instruction
			    calculateCoords(((NavigationModule) o).getCurrentHeading());
			    cells[row][col].setCurrentPlace(((NavigationModule) o).getCurrentPlace());
			    for(int i = 0; i < 11; i++){
				    for(int j = 0; j < 11; j++){
					    cells[i][j].update(o, arg);
				    }
			    }
			}
		}
	}

	public void setDescriptionText(String description){
		this.info.setText(description);
	}
	


}
