package tp.pr4.gui;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.event.MouseListener;

import java.util.EventListener;
import java.util.Observable;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.*;

import tp.pr4.RobotEngine;
import tp.pr4.items.ItemContainer;


/**
 * RobotPanel displays information about the robot and its inventory. More specifically,
 * it contains labels to show the robot fuel and the weight of recycled material and a 
 * table that represents the robot inventory. Each row displays information about an
 *  item contained in the inventory.
 * @author Laura
 *
 */
@SuppressWarnings("serial")
public class RobotPanel extends JPanel implements InterfaceWindow{

	
	class MyTableModel extends AbstractTableModel{
		private String[] columnNames = {"Id", "Description"};
		private Object[][] data = {{"5", "5"}};
		
		public int getColumnCount(){
			return columnNames.length;
		}
		
		public int getRowCount(){
			return data.length;
		}
		
		public String getColumnName(int col){
			return columnNames[col];
		}
		
		public Object getValueAt(int row, int col){
			return data[row][col];
		}
			
	}
	
	
	private ItemContainer robotContainer;
	private MyTableModel tableModel;
    private JTable table;
	private JLabel fuelLevelLabel;
	private JLabel recycledMaterialLabel;
	
	public RobotPanel(ItemContainer robotContainer){
		initRobotPanel(robotContainer);
	}
	
	public RobotPanel(ItemContainer robotContainer, EventListener driver){
		initRobotPanel(robotContainer);
		setDriver(driver);
	}
	

	
	public void initRobotPanel(ItemContainer robotContainer){
		this.robotContainer = robotContainer;
		
		this.setBorder(new TitledBorder("Robot info"));
		
		this.tableModel = new MyTableModel();
		this.table = new JTable(tableModel);
		this.fuelLevelLabel = new JLabel();
		this.recycledMaterialLabel = new JLabel();

	    //table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	
		
		
		
		this.fuelLevelLabel.setText("Fuel: ");
		this.fuelLevelLabel.setName("fuelLevelLabel");
		fuelLevelLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		this.recycledMaterialLabel.setText("Recycled: ");
		this.recycledMaterialLabel.setName("recycledMaterialLable");
		recycledMaterialLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		
		this.setLayout(new BorderLayout());
		
		JPanel auxPanel = new JPanel(new FlowLayout());
		auxPanel.add(fuelLevelLabel);
		auxPanel.add(recycledMaterialLabel);
		
        this.add(auxPanel, "North");
		this.add(table, "South");
		
	}
	
	public void setDriver(EventListener driver){
		table.addMouseListener((MouseListener) driver);
	}
	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.fuelLevelLabel.setText("Fuel: " + ((RobotEngine) o).getFuel());
		this.recycledMaterialLabel.setText("Recycled: " + ((RobotEngine) o).getRecycledMaterial());
	}

}
