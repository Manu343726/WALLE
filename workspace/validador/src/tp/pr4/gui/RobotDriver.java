package tp.pr4.gui;

import java.awt.Component;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;


import tp.pr4.Interpreter;
import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.WallEsMessages;

/**
 * 
 * @author Laura María de Castro Saturio , Manuel Sánchez Pérez
 *
 */
public class RobotDriver implements ActionListener, //para los JButton
				    MouseListener{

	private RobotEngine _engine;
	private NavigationModule _nav;
	private NavigationPanel _navPanel;
	private InstructionsPanel _instPanel;
        private RobotPanel _robotPanel;
	
	public RobotDriver(){
		this(null, null, null, null,null);
	}
	
	public RobotDriver(RobotEngine eng, NavigationModule nav, NavigationPanel navPanel, InstructionsPanel instructions, RobotPanel robotPanel){
		this.setModel(eng, nav, navPanel, instructions, robotPanel);
	}
	
	public void setModel(RobotEngine eng, NavigationModule nav, NavigationPanel navPanel, InstructionsPanel instructions, RobotPanel robotPanel){
		_engine = eng;
		_nav = nav;
		_navPanel = navPanel;
		_instPanel = instructions;
                _robotPanel = robotPanel;
	}
	
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	


}
