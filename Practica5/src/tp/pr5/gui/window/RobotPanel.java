package tp.pr5.gui.window;

import tp.pr5.utils.events.WALLE.RobotEngineChangeEventArgs;
import tp.pr5.utils.events.WALLE.ItemContainerChangeEventArgs;
import tp.pr5.utils.events.WALLE.RobotEngineChangeType;
import tp.pr5.utils.events.WALLE.ItemContainerChangeType;
import tp.pr5.utils.events.EventSender;
import tp.pr5.utils.events.EventHandler;
import tp.pr5.items.ItemContainer;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.EventListener;
import java.util.Observable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import java.util.*;
import javax.swing.JScrollPane;
import tp.pr5.messaging.WallEsMessages;


/**
 * RobotPanel displays information about the robot and its inventory. More specifically,
 * it contains labels to show the robot fuel and the weight of recycled material and a 
 * table that represents the robot inventory. Each row displays information about an
 *  item contained in the inventory.
 * @author Manuel Sánchez Pérez
 *
 */
@SuppressWarnings("serial")
public class RobotPanel extends JPanel implements InterfaceWindow,
                                                  EventHandler<RobotEngineChangeEventArgs>{
	
	/**
	 * This class is used as model of the table that we're going to use as inventory
	 * @author Laura María de Castro & Manuel Sánchez Pérez
	 *
	 */
    class MyTableModel extends AbstractTableModel implements EventHandler<ItemContainerChangeEventArgs>{    
    
        private String[]   _columnNames = {"Id", "Description"};
        
        ItemContainer _container;
        
        public MyTableModel( ItemContainer container )
        {
            _container = container;
            _container.AddHandler(this);
        }

        @Override
        public int getColumnCount(){
                return _columnNames.length;
        }

        @Override
        public int getRowCount(){
                return _container.numberOfItems();
        }

        @Override
        public String getColumnName(int col){
                return _columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col){
            if(col == 0)
                return _container.getItem(row).getId();
            else
                return _container.getItem(row).getDescription();      
        }
        
        @Override
        public void update(EventSender sender , ItemContainerChangeEventArgs args)
        {
            if(args.getChangeType() == ItemContainerChangeType.ITEM_ADDED)
                this.fireTableRowsInserted(args.getItemIndex() , args.getItemIndex());
            else if( args.getChangeType() == ItemContainerChangeType.ITEM_DELETED )
                this.fireTableRowsDeleted(args.getItemIndex() , args.getItemIndex());           
        }
    }

    
    
    private MyTableModel  _tableModel;
    private JTable _table;
    private JLabel _fuelLevelLabel;
    private JLabel _recycledMaterialLabel;
    
    /***
     * Checks if a Item is selected
     * @return True if a item is selected. False if not.
     */
    public boolean isItemSelected() {return _table.getSelectedRow() >= 0; }
    
    /***
     * Gets the id of the selected item
     * @return A string containing the item id.
     */
    public String getSelectedItemId() throws NoSuchElementException
    {
        int index = _table.getSelectedRow();
        
        if(index >= 0)
            return (String)_tableModel.getValueAt(index, 0);
        else
            throw new NoSuchElementException("No item selected");
    }

	/**
	 * Initializes the panel without driver
	 */
    public RobotPanel( ItemContainer items ){
            initRobotPanel( items );
    }

	/**
	 * Initializes the panel with driver
	 * @param driver contains the driver in charge of the panel.
	 */
    public RobotPanel(EventListener driver , ItemContainer items ){
            initRobotPanel( items );
            setDriver(driver);
    }

    /**
     * Initializes all panel's components and set them correctly
     */
    public void initRobotPanel(ItemContainer items ){
        this.setBorder(new TitledBorder("Robot info"));

        _tableModel = new MyTableModel( items );
        _fuelLevelLabel = new JLabel();
        _recycledMaterialLabel = new JLabel();

        _table = new JTable ( _tableModel ); 
        JScrollPane scrollPane = new JScrollPane( _table );
        _table.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        scrollPane.setPreferredSize( new Dimension( 200 , 80)); //Las dimensiones las he puesto a ojo, si no te gusta como queda cambialo

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
        this.add( scrollPane , "South");
    }

    @Override
	/**
	 * Public method that is responsible for setting the controller on the elements
	 *  of the panel.
	 * @param driver contains the driver in charge of the view.
	 */
    public void setDriver(EventListener driver){
            //_table.addMouseListener((MouseListener) driver);//Para que? No lo vas a usar
    }


	/**
	 * This method is used to update the view and obtains the info from the models
	 * @param o contains the model
	 * @param arg contains the argument passed from the model
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
    @Override
    public void update(Observable o, Object arg) {

    }
    
    /**
     * This method handles the robot engine changed event, to update fuel and garbage labels.
     */
    @Override
    public void update(EventSender sender , RobotEngineChangeEventArgs args)
    {
        if( args.getChangeType() == RobotEngineChangeType.FUEL_CHANGE )
            if(args.getValue() > 0)
                _fuelLevelLabel.setText("Fuel: " + args.getValue());
            else
                WallEsMessages.messagesProvider().WriteError( WallEsMessages.NOFUEL , true);
        else if( args.getChangeType() == RobotEngineChangeType.MATERIAL_CHANGE )
            _recycledMaterialLabel.setText("Recycled: " + args.getValue());
    }
}
