package tp.pr4.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import tp.pr4.NavigationModule;

public class RobotDriver implements ActionListener{

	private NavigationModule navModule;
	
	public RobotDriver(){}
	
	public RobotDriver(NavigationModule navModule){
		this.setModel(navModule);
	}
	
	public void setModel(NavigationModule navModule){
		this.navModule = navModule;
	}
	
	private void changeModel(Component c){
		
	}
	
	private void genericEvent(EventObject event){
		Component c = (Component) event.getSource();
		changeModel(c);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		genericEvent(arg0);
	}
	


}
