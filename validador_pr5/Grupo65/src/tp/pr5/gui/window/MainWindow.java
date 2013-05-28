package tp.pr5.gui.window;

import tp.pr5.utils.events.WALLE.RobotEngineChangeEventArgs;
import tp.pr5.utils.events.WALLE.RobotEngineChangeType;
import tp.pr5.utils.events.EventSender;
import tp.pr5.utils.events.EventHandler;
import java.awt.Container;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;


import java.util.EventListener;
import java.util.Observable;


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;


import tp.pr5.RobotEngine;
import tp.pr5.gui.InfoLabelUpdater;
import tp.pr5.gui.driver.GUIController;


/**
 * This class represents the main window. The MainWindow contains:
 *   => A menu whit the QUIT action
 *   => An InstructionsPanel with several button to perform MOVE, TURN,
 *      OPERATE, PICK and DROP actions. Additionally it has a combo box
 *      of turn rotations and a text field to write the name id the item
 *      that the robot wants to pick from the current place
 *   => A RobotPanel that displays the robot information(fuel and recycled material)
 *      and the robot inventory. The user can select the items contained in 
 *      the inventory in order to DROP or OPERATE an item
 *   => A NavigationPanel that represents the city.It shows the places that
 *      the robot has visited and an icon that represents the robot heading.
 *      The robot heading is updated when the user performs TURN action.
 *      The visible places are updated when the robot performs a MOVE action.
 *      Once a place is visited, the user can click on in in order to display the 
 *      place description(similar to RADAR command)
 * @author Laura María de Castro Saturio , Manuel Sánchez Pérez
 *
 */

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements InterfaceWindow,
                                                  EventHandler<RobotEngineChangeEventArgs>{
	
	private Container   _mainPanel;
	
	private InstructionsPanel _instructionsPanel;
	private RobotPanel        _robotPanel;
	private NavigationPanel   _navPanel;

	private JMenu     _jMenu;
	private JMenuBar  _jMenuBar;
	private JMenuItem _jMenuItem;
	
	
  
	/**
	 * Initializes the view without driver
	 */
	public MainWindow(RobotPanel robotPanel, NavigationPanel navigationPanel, InstructionsPanel instPanel){
		super("WALL-E The garbage collector");
		initMainWindow(robotPanel, navigationPanel, instPanel);
	}
	
	/**
	 * Initializes the view with driver
	 * @param driver contains the driver in charge of the view.
	 */
	public MainWindow(RobotPanel robotPanel, NavigationPanel navigationPanel, InstructionsPanel instPanel, GUIController driver){
		super("WALL-E The garbage collector");
		initMainWindow(robotPanel, navigationPanel, instPanel);
		setDriver(driver);
	}
	
    /**
     * Initializes all window's components and set them correctly
     */
	public void initMainWindow(RobotPanel robotPanel, NavigationPanel navigationPanel, InstructionsPanel instPanel){
		InfoLabelPanel info_panel = new InfoLabelPanel();
                
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
                _mainPanel.add( info_panel , "South" );
                
                InfoLabelUpdater.getInstance().AddHandler(info_panel);
		
		this.setContentPane(_mainPanel);
		setVisible(true);
	}
	
	@Override
	/**
	 * Public method that is responsible for setting the controller on the elements
	 *  of the view.
	 * @param driver contains the driver in charge of the view.
	 */
	public void setDriver(EventListener driver){
		_instructionsPanel.setDriver(driver);
		//_robotPanel.setDriver(driver); no lo vas a usar
		_navPanel.setDriver(driver);
		_jMenuItem.addActionListener((ActionListener) driver);
	}
	

	@Override
	/**
	 * This method is used to update the view and obtains the info from the models
	 * @param o contains the model
	 * @param arg contains the argument passed from the model
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
        public void update(Observable o, Object arg) {
	}
        
        @Override
        public void update(EventSender sender , RobotEngineChangeEventArgs args)
        {
            if( args.getChangeType() == RobotEngineChangeType.QUIT_REQUESTED )
                if(JOptionPane.showConfirmDialog(null, "¿Close aplication?") == JOptionPane.YES_OPTION)
                            System.exit(0);	
                        else
                            args.abortQuit();
        }
	

}
