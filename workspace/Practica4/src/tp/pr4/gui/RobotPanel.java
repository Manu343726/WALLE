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
import tp.pr4.RobotEngine;
import tp.pr4.items.ItemContainer;


/**
 * RobotPanel displays information about the robot and its inventory. More specifically,
 * it contains labels to show the robot fuel and the weight of recycled material and a 
 * table that represents the robot inventory. Each row displays information about an
 *  item contained in the inventory.
 * @author Laura & Manuel
 *
 */
@SuppressWarnings("serial")
public class RobotPanel extends JPanel implements InterfaceWindow{

	
	class MyTableModel extends AbstractTableModel{
		private String[] columnNames = {"Id", "Description"};
		private Object[][] data = {{"5", "5"}};
		
                @Override
		public int getColumnCount(){
			return columnNames.length;
		}
		
                @Override
		public int getRowCount(){
			return data.length;
		}
		
                @Override
		public String getColumnName(int col){
			return columnNames[col];
		}
		
                @Override
		public Object getValueAt(int row, int col){
			return data[row][col];
		}
			
	}
	
	@SuppressWarnings("unused")
	private ItemContainer _robotContainer;
	private MyTableModel _tableModel;
    private JTable _table;
	private JLabel _fuelLevelLabel;
	private JLabel _recycledMaterialLabel;
	
	private int _rowIndex = 0;
	
	public RobotPanel(ItemContainer robotContainer){
		initRobotPanel(robotContainer);
	}
	
	public RobotPanel(ItemContainer robotContainer, EventListener driver){
		initRobotPanel(robotContainer);
		setDriver(driver);
	}
	

	
	public void initRobotPanel(ItemContainer robotContainer){
		_robotContainer = robotContainer;
		
		this.setBorder(new TitledBorder("Robot info"));
		
		_tableModel = new MyTableModel();
		_table = new JTable(_tableModel);
		_fuelLevelLabel = new JLabel();
		_recycledMaterialLabel = new JLabel();

	    //table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		_fuelLevelLabel.setText("Fuel: ");
		_fuelLevelLabel.setName("fuelLevelLabel");
		_fuelLevelLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		_recycledMaterialLabel.setText("Recycled: ");
		_recycledMaterialLabel.setName("recycledMaterialLable");
		_recycledMaterialLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		
		this.setLayout(new BorderLayout());
		
		JPanel auxPanel = new JPanel(new FlowLayout());
		auxPanel.add(_fuelLevelLabel);
		auxPanel.add(_recycledMaterialLabel);
		
        this.add(auxPanel, "North");
		this.add(_table, "South");
		
	}
	
	public void setDriver(EventListener driver){
		_table.addMouseListener((MouseListener) driver);
	}
	


	
	//Cuando hagamos pick, operate o drop vamos a pasar por arg el objeto en cuestion
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		_fuelLevelLabel.setText("Fuel: " + ((RobotEngine) o).getFuel());
		_recycledMaterialLabel.setText("Recycled: " + ((RobotEngine) o).getRecycledMaterial());
		//Vamos a mirar si el objeto que nos pasan esta en el container del robot
		//Si esta hemos hecho un PICK
		if(_robotContainer.containsItem((String) arg)){
			//ponemos el objeto en la tabla
			_tableModel.setValueAt(_robotContainer.getItem((String) arg).getId(), _rowIndex, 0);
			_tableModel.setValueAt(_robotContainer.getItem((String) arg).getDescription(), _rowIndex, 0);
			_rowIndex++;
		}
		//Si no hemos hecho un DROP
		else
                {}	//lo quitamos de la tabla
		
	}

}
