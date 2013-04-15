package tp.pr4.gui;

import java.awt.BorderLayout;
import java.util.EventListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import tp.pr4.RobotEngine;

/**
 * RobotPanel displays information about the robot and its inventory. More specifically,
 * it contains labels to show the robot fuel and the weight of recycled material and a 
 * table that represents the robot inventory. Each row displays information about an
 *  item contained in the inventory.
 * @author Laura
 *
 */
@SuppressWarnings("serial")
public class RobotPanel extends JPanel{

	private RobotEngine robot;
	private DefaultTableModel tableModel;
	private JTable table;
	private JLabel fuelLevelLabel;
	private JLabel recycledMaterialLabel;
	
	public RobotPanel(RobotEngine robot){
		initRobotPanel(robot);
	}
	
	public RobotPanel(RobotEngine robot, EventListener driver){
		initRobotPanel(robot);
		setDriver(driver);
	}
	
	public void initRobotPanel(RobotEngine robot){
		this.robot = robot;;
		
		this.setBorder(new TitledBorder("Robot info"));
		
		this.tableModel = new DefaultTableModel();
		this.table = new JTable(tableModel);
		this.fuelLevelLabel = new JLabel();
		this.recycledMaterialLabel = new JLabel();
		
		tableModel.addColumn("Id");
		tableModel.addColumn("Description");
		
		this.fuelLevelLabel.setText("Fuel: " + robot.getFuel());
		this.fuelLevelLabel.setName("fuelLevelLabel");
		
		this.recycledMaterialLabel.setText("Recycled: " + robot.getFuel());
		this.recycledMaterialLabel.setName("recycledMaterialLable");
		
		this.setLayout(new BorderLayout());
		
		
		
	}
	
	public void setDriver(EventListener driver){
		
	}
	
	public void update(RobotEngine robot){
		
	}
}
