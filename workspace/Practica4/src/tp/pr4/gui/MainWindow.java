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
 *   => An InstructionsPanel with several button to perform MOVE, TURN,
 *      OPERATE, PICK and DROP actions. Additionally it has a combo box
 *      of turn rotations and a text field to write the name od the item
 *      that the robot wants to pick from the current place
 *   => A RobotPanel that displays the robot information(fuel and recycled material)
 *      and the robot inventory. The user can select the items contained in 
 *      the inventory in order to DROP or OPERATE an item
 *   => A NavigationPanel that represents the city.It shows the places that
 *      the robot has visited and an icon that represents the robot heading.
 *      The robot heading is updated when the user performs TURN action.
 *      The visible places are upsdated when the robot performs a MOVE action.
 *      Once a place is visited, the user can click on in in order to display the 
 *      place description(similar to RADAR command)
 * @author Laura María de Castro Saturio , Manuel Sánchez Pérez
 *
 */

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements InterfaceWindow{
	
	private RobotEngine _robot;
	
	private Container   _mainPanel;
	
	private InstructionsPanel _instructionsPanel;
	private RobotPanel        _robotPanel;
	private NavigationPanel   _navPanel;

	private JMenu     _jMenu;
	private JMenuBar  _jMenuBar;
	private JMenuItem _jMenuItem;
	
	public MainWindow(RobotEngine robotEngine, RobotPanel robotPanel, NavigationPanel navigationPanel, InstructionsPanel instPanel){
		super("WALL-E The garbage collector");
		initMainWindow(robotEngine, robotPanel, navigationPanel, instPanel);
	}
	
	public MainWindow(RobotEngine robot, RobotPanel robotPanel, NavigationPanel navigationPanel, InstructionsPanel instPanel, RobotDriver driver){
		super("WALL-E The garbage collector");
		initMainWindow(robot, robotPanel, navigationPanel, instPanel);
		setDriver(driver);
	}
	
	public void initMainWindow(RobotEngine robot, RobotPanel robotPanel, NavigationPanel navigationPanel, InstructionsPanel instPanel){
		_robot = robot;
		
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_mainPanel = this.getContentPane();

		_instructionsPanel = instPanel;
		_robotPanel = robotPanel;
		_navPanel = navigationPanel;
		
		_jMenuBar = new JMenuBar();
		setJMenuBar(_jMenuBar);
		_jMenu = new JMenu("File");
		_jMenuBar.add(_jMenu);
		_jMenuItem = new JMenuItem("Quit");
		_jMenu.add(_jMenuItem);
		_jMenuItem.setName("jMenuQuit");
		
		JSplitPane panelAux = new JSplitPane(SwingConstants.VERTICAL, _instructionsPanel, _robotPanel);
		
		_mainPanel = new JPanel(new BorderLayout());
		_mainPanel.add(panelAux, "North");
		_mainPanel.add(_navPanel, "Center");

		
		this.setContentPane(_mainPanel);
		setVisible(true);
	}
	
	@Override
	public void setDriver(EventListener driver){
		_instructionsPanel.setDriver(driver);
		//_robotPanel.setDriver(driver); no lo vas a usar
		_navPanel.setDriver(driver);
		_jMenuItem.addActionListener((ActionListener) driver);
	}
	

	//La ventana principal es la observadora del RobotEngine
	//Por tanto solo trata la instruccion QUIT
	//Por otro lado llama a robotPanel para que actualize fuel y material reciclado
	@Override
	public void update(Observable o, Object arg) {
		if(_robot.quit()){
			System.exit(0); //Esto de cargarnos la aplicación a lo bestia desde aquí no me acaba de convencer...
		}
		else{
			_robotPanel.update(o, arg);
		}
	}
	

}
