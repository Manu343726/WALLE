package tp.pr4.gui;

import javax.swing.JMenu;
import javax.swing.JPanel;

import tp.pr4.RobotEngine;

/**
 * This clase represents the main window. The MainWindow contains:
 *   => A menu whit the QUIT action
 *   => An Action panel with several button to perform MOVE, TURN,
 *      OPERATE, PICK and DROP actions. Additionally it has a combo box
 *      of turn rotations and a text field to write the name od the item
 *      that the robot wants to pick from the current place
 *   => A RobotPanel that displays the robot information(fuel and recycled material)
 *      and the robot inventory. The user can select the items contained in 
 *      the inventory in order to DROP or OPERATE an item
 *   => A CityPanel that represents the city.It shows the places that
 *      the robot has visited and an icon that represents the robot heading.
 *      The robot heading is updated when the user performs TURN action.
 *      The visible places are upsdated when the robot performs a MOVE action.
 *      Once a place is visited, the user can click on in in order to display the 
 *      place description(similar to RADAR command)
 * @author Laura
 *
 */

public class MainWindow {

	private RobotEngine robot;
	private JMenu jMenu;
	private JPanel jPanelInstructions;
	private RobotPanel robotPanel;
	private NavigationPanel navPanel;
	
	public MainWindow(RobotEngine robot){
		
	}
	
}
