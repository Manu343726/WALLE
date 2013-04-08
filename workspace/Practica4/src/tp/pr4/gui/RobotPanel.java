package tp.pr4.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 * RobotPanel displays information about the robot and its inventory. More specifically,
 * it contains labels to show the robot fuel and the weight of recycled material and a 
 * table that represents the robot inventory. Each row displays information about an
 *  item contained in the inventory.
 * @author Laura
 *
 */
public class RobotPanel extends JPanel{

	private TableModel tableModel;
	private JTable table;
	private JLabel fuelLevel;
	private JLabel recycledMaterial;
}
