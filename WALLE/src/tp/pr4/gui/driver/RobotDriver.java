package tp.pr4.gui.driver;

import java.awt.Component;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;


import tp.pr4.Interpreter;
import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.messaging.WallEsMessages;
import tp.pr4.gui.window.InstructionsPanel;
import tp.pr4.gui.window.NavigationPanel;
import tp.pr4.gui.window.PlaceCell;
import tp.pr4.gui.window.RobotPanel;

/**
 * 
 * @author Laura María de Castro Saturio , Manuel Sánchez Pérez
 *
 */
public class RobotDriver implements ActionListener{

	private RobotEngine _engine;
	private NavigationModule _nav;
	private NavigationPanel _navPanel;
	private InstructionsPanel _instPanel;
        private RobotPanel _robotPanel;
	
        /**
         * Default ctor.
         */
	public RobotDriver(){
		this(null, null, null, null,null);
	}
	
        /**
         * Creates a driver with the specified data.
         * @param eng Application model (Robot engine)
         * @param nav Application model (Navigation module)
         * @param navPanel GUI navigation panel.
         * @param instructions GUI instructions panel.
         * @param robotPanel  GUI robot panel.
         */
	public RobotDriver(RobotEngine eng, NavigationModule nav, NavigationPanel navPanel, InstructionsPanel instructions, RobotPanel robotPanel){
		this.setModel(eng, nav, navPanel, instructions, robotPanel);
	}
	
         /**
         * Setups the application events driver.
         * @param eng Application model (Robot engine)
         * @param nav Application model (Navigation module)
         * @param navPanel GUI navigation panel.
         * @param instructions GUI instructions panel.
         * @param robotPanel  GUI robot panel.
         */
	public void setModel(RobotEngine eng, NavigationModule nav, NavigationPanel navPanel, InstructionsPanel instructions, RobotPanel robotPanel){
		_engine = eng;
		_nav = nav;
		_navPanel = navPanel;
		_instPanel = instructions;
                _robotPanel = robotPanel;
	}
	
        /**
         * Performs application GUI events handling.
         * @param c GUI component that raised the event.
         */
	private void changeModel(Component c){
		if(c.getName().equals("jButtonMove")){
			_engine.comunicateRobot(Interpreter.generateInstruction("move"));
		}
		else if(c.getName().equals("jButtonQuit")){
			_engine.requestQuit();
		}
		else if(c.getName().equalsIgnoreCase("jButtonTurn")){
			_engine.comunicateRobot(Interpreter.generateInstruction("turn " + _instPanel.getSelectedDirection()));
		}
		else if(c.getName().equals("jButtonPick")){
			_engine.comunicateRobot(Interpreter.generateInstruction("pick " + _instPanel.getIdWritten()));
		}
		else if(c.getName().equals("jButtonDrop")){
                    if( _robotPanel.isItemSelected() )
                        _engine.comunicateRobot( Interpreter.generateInstruction( "drop " + _robotPanel.getSelectedItemId() ) );
                    else
                        WallEsMessages.messagesProvider().WriteError("No item selected");
		}
		else if(c.getName().equals("jButtonOperate")){
                    if( _robotPanel.isItemSelected() )
                        _engine.comunicateRobot( Interpreter.generateInstruction( "operate " + _robotPanel.getSelectedItemId() ) );
                    else
                        WallEsMessages.messagesProvider().WriteError("No item selected");
		}
		else if(c.getName().equals("placeCell")){
                    PlaceCell cell = (PlaceCell) c;
                    
                    if( cell.isActive() )
			_navPanel.setDescriptionText( cell.getPlaceString() );
		}
		else if(c.getName().equals("jMenuQuit")){
			_engine.requestQuit();
		}
	}
	
	private void genericEvent(EventObject event){
		Component c = (Component) event.getSource();
		System.err.println(c.getName());
		changeModel(c);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.err.print("actionperformed: ");
		genericEvent(arg0);
	}


	


}
