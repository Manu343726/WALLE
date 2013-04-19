package tp.pr4.gui;

import java.awt.Container;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;


import java.util.EventListener;
import java.util.Observable;


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;


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
public class MainWindow extends JFrame implements InterfaceWindow{
	
	private RobotEngine robot;
	
	private Container mainPanel;
	
	private InstructionsPanel instructionsPanel;
	private RobotPanel robotPanel;
	private NavigationPanel navPanel;

	private JMenu jMenu;
	private JMenuBar jMenuBar;
	private JMenuItem jMenuItem;
	
	public MainWindow(RobotEngine robotEngine, RobotPanel robotPanel, NavigationPanel navigationPanel, InstructionsPanel instPanel){
		super("WALL-E The garbage collector");
		initMainWindow(robot, robotPanel, navigationPanel, instPanel);
	}
	
	public MainWindow(RobotEngine robot, RobotPanel robotPanel, NavigationPanel navigationPanel, InstructionsPanel instPanel, RobotDriver driver){
		super("WALL-E The garbage collector");
		initMainWindow(robot, robotPanel, navigationPanel, instPanel);
		setDriver(driver);
	}
	
	public void initMainWindow(RobotEngine robot, RobotPanel robotPanel, NavigationPanel navigationPanel, InstructionsPanel instPanel){
		this.robot = robot;
		
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainPanel = this.getContentPane();

		this.instructionsPanel = instPanel;
		this.robotPanel = robotPanel;
		this.navPanel = navigationPanel;
		
		jMenuBar = new JMenuBar();
		setJMenuBar(jMenuBar);
		jMenu = new JMenu("File");
		jMenuBar.add(jMenu);
		jMenuItem = new JMenuItem("Quit");
		jMenu.add(jMenuItem);
		jMenuItem.setName("jMenuQuit");
		
		JSplitPane panelAux = new JSplitPane(SwingConstants.VERTICAL, instructionsPanel, robotPanel);
		
		this.mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(panelAux, "North");
		mainPanel.add(navPanel, "Center");

		
		this.setContentPane(mainPanel);
		setVisible(true);
	}
	
	
	public void setDriver(EventListener driver){
		this.instructionsPanel.setDriver(driver);
		this.robotPanel.setDriver(driver);
		this.navPanel.setDriver(driver);
		this.jMenuItem.addActionListener((ActionListener) driver);
	}
	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(robot.quit()){
			System.exit(0);
		}
		navPanel.update(o, arg);
		robotPanel.update(o, arg);
	}
	

}
