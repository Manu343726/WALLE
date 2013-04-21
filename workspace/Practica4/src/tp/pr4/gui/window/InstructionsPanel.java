package tp.pr4.gui.window;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * This class represent a panel with several buttons to perform MOVE, TURN,
 * OPERATE, PICK and DROP actions. Additionally it has a combo box
 * of turn rotations and a text field to write the name id the item
 * that the robot wants to pick from the current place
 * @author Laura María de Castro Saturio , Manuel Sánchez Pérez
 *
 */
@SuppressWarnings("serial")
public class InstructionsPanel extends JPanel{

	private JButton _jButtonMove;
	private JButton _jButtonTurn;
	private JButton _jButtonPick;
	private JButton _jButtonDrop;
	private JButton _jButtonQuit;
	private JButton _jButtonOperate;
	private JTextField _jTextItem;
	@SuppressWarnings("rawtypes")
	private JComboBox _jComboRotation;

	/**
	 * This method is used to get the direction selected on the panel's combo box
	 * @return the direction selected
	 */
	public String getSelectedDirection(){
		return (String)_jComboRotation.getSelectedItem();
	}
	
	/**
	 * This method is used to get the id written on the text field
	 * @return the text written 
	 */
	public String getIdWritten(){
		return _jTextItem.getText();
	}
	
	
	/**
	 * Initializes the panel without driver
	 */
	public InstructionsPanel(){
		initInstructionsPanel();
	}
	
	/**
	 * Initializes the panel with driver
	 * @param driver contains the driver in charge of the view.
	 */
	public InstructionsPanel(EventListener driver){
		initInstructionsPanel();
		setDriver(driver);
	}
	
	
    /**
     * Initializes all panel's components and set them correctly
     */
	private void initInstructionsPanel(){
		_jButtonMove = new JButton();
		_jButtonTurn = new JButton();
		_jButtonPick = new JButton();
		_jButtonDrop = new JButton();
		_jButtonQuit = new JButton();
		_jButtonOperate = new JButton();
		_jTextItem = new JTextField();
		_jComboRotation = new JComboBox();
		
		this.setBorder(new TitledBorder("Instructions"));
		
		_jButtonMove.setText("MOVE");
		_jButtonMove.setName("jButtonMove");
		_jButtonTurn.setText("TURN");
		_jButtonTurn.setName("jButtonTurn");
		_jButtonPick.setText("PICK");
		_jButtonPick.setName("jButtonPick");
		_jButtonDrop.setText("DROP");
		_jButtonDrop.setName("jButtonDrop");
		_jButtonQuit.setText("QUIT");
		_jButtonQuit.setName("jButtonQuit");
		_jButtonOperate.setText("OPERATE");
		_jButtonOperate.setName("jButtonOperate");
		
		_jTextItem.setHorizontalAlignment(JTextField.LEFT);
		_jTextItem.setName("jTextItem");
		
		_jComboRotation.setName("jComboRotation");
		_jComboRotation.addItem("LEFT");
		_jComboRotation.addItem("RIGHT");
		_jComboRotation.setEditable(false);
		
		this.setLayout(new GridLayout(4,2));
		
		this.add(_jButtonMove);
		this.add(_jButtonQuit);
		this.add(_jButtonTurn);
		this.add(_jComboRotation);
		this.add(_jButtonPick);
		this.add(_jTextItem);
		this.add(_jButtonDrop);
		this.add(_jButtonOperate);
	}
	
	
	/**
	 * Public method that is responsible for setting the controller on the elements
	 *  of the view.
	 * @param driver contains the driver in charge of the view.
	 */
	public void setDriver(EventListener driver){
		_jButtonMove.addActionListener((ActionListener) driver);
		_jButtonTurn.addActionListener((ActionListener) driver);
		_jButtonPick.addActionListener((ActionListener) driver);
		_jButtonDrop.addActionListener((ActionListener) driver);
		_jButtonQuit.addActionListener((ActionListener) driver);
		_jButtonOperate.addActionListener((ActionListener) driver);
		_jTextItem.addActionListener((ActionListener) driver);
		_jComboRotation.addActionListener((ActionListener) driver);
	}
}
