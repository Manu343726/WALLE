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

/**
 * 
 * @author Laura & Manuel
 *
 */
public class RobotDriver implements ActionListener, //para los JButton
									MouseListener{

	private RobotEngine _engine;
	@SuppressWarnings("unused")
	private NavigationModule _nav;
	private NavigationPanel _navPanel;
	private InstructionsPanel _instPanel;
	
	public RobotDriver(){
		this(null, null, null, null);
	}
	
	public RobotDriver(RobotEngine eng, NavigationModule nav, NavigationPanel navPanel, InstructionsPanel instructions){
		this.setModel(eng, nav, navPanel, instructions);
	}
	
	public void setModel(RobotEngine eng, NavigationModule nav, NavigationPanel navPanel, InstructionsPanel instructions){
		_engine = eng;
		_nav = nav;
		_navPanel = navPanel;
		_instPanel = instructions;
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
			
		}
		else if(c.getName().equals("jButtonOperate")){
			
		}
		else if(c.getName().equals("placeCell")){
			_navPanel.setDescriptionText(((PlaceCell) c).getPlace().toString());
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
