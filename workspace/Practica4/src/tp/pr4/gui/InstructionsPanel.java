package tp.pr4.gui;

import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class InstructionsPanel extends JPanel{

	private JButton jButtonMove;
	private JButton jButtonTurn;
	private JButton jButtonPick;
	private JButton jButtonDrop;
	private JButton jButtonQuit;
	private JButton jButtonOperate;
	private JTextField jTextItem;
	private JComboBox jComboRotation;

	
	public InstructionsPanel(){
		initInstructionsPanel();
	}
	
	private void initInstructionsPanel(){
		this.jButtonMove = new JButton();
		this.jButtonTurn = new JButton();
		this.jButtonPick = new JButton();
		this.jButtonDrop = new JButton();
		this.jButtonQuit = new JButton();
		this.jButtonOperate = new JButton();
		this.jTextItem = new JTextField();
		this.jComboRotation = new JComboBox();
		
		this.setBorder(new TitledBorder("Instructions"));
		
		this.jButtonMove.setText("MOVE");
		this.jButtonMove.setName("jButtonMove");
		this.jButtonTurn.setText("TURN");
		this.jButtonTurn.setName("jButtonTurn");
		this.jButtonPick.setText("PICK");
		this.jButtonPick.setName("jButtonPick");
		this.jButtonDrop.setText("DROP");
		this.jButtonDrop.setName("jButtonDrop");
		this.jButtonQuit.setText("QUIT");
		this.jButtonQuit.setName("jButtonQuit");
		this.jButtonOperate.setText("OPERATE");
		this.jButtonOperate.setName("jButtonOperate");
		
		this.jTextItem.setHorizontalAlignment(JTextField.LEFT);
		this.jTextItem.setName("jTextItem");
		
		this.jComboRotation.setName("jComboRotation");
		this.jComboRotation.addItem("LEFT");
		this.jComboRotation.addItem("RIGHT");
		
		this.setLayout(new GridLayout(4,2));
		
		this.add(this.jButtonMove);
		this.add(this.jButtonQuit);
		this.add(this.jButtonTurn);
		this.add(this.jComboRotation);
		this.add(this.jButtonPick);
		this.add(this.jTextItem);
		this.add(this.jButtonDrop);
		this.add(this.jButtonOperate);
	}
	
	
}
