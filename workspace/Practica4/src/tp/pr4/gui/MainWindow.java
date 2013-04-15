package tp.pr4.gui;

import java.awt.Container;
import java.awt.EventQueue;
import java.util.EventListener;

import javax.swing.JFrame;
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

@SuppressWarnings("serial")
public class MainWindow extends JFrame{

	private RobotEngine robot;
	private Container mainPanel;
	private JMenu jMenu;
	private InstructionsPanel instructionsPanel;
	private RobotPanel robotPanel;
	private NavigationPanel navPanel;
	
	public MainWindow(){
		super("WALL-E The garbage collector");
		initMainWindow();
	}
	
	public MainWindow(RobotDriver driver){
		super("WALL-E The garbage collector");
		initMainWindow();
		setDriver(driver);
	}
	
	public void initMainWindow(){
		this.setSize(3*320, 340); //No se realmente que tamaño ponerle
		this.mainPanel = this.getContentPane();
		
		this.jMenu = new JMenu();
		this.instructionsPanel = new InstructionsPanel();
		this.robotPanel = new RobotPanel(robot);
		this.navPanel = new NavigationPanel();
		
	}
	
	public void setDriver(EventListener driver){
		this.instructionsPanel.setDriver(driver);
		this.robotPanel.setDriver(driver);
		this.navPanel.setDriver(driver);
	}
	
	public void update(){
		
	}
	
	//Para pruebas
	public void arranca(){
		EventQueue.invokeLater(new Runnable(){
		public void run(){
			setVisible(true);
		}
		});
	}
	
	public static void main(String[] args){
		final MainWindow m = new MainWindow();
		m.arranca();
	}
}
