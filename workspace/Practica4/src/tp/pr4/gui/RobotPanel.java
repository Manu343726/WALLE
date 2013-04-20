package tp.pr4.gui;

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
import tp.pr4.RobotEngine;
import tp.pr4.items.*;

import java.util.*;
import javax.swing.JScrollPane;
import tp.pr4.WallEsMessages;


/**
 * RobotPanel displays information about the robot and its inventory. More specifically,
 * it contains labels to show the robot fuel and the weight of recycled material and a 
 * table that represents the robot inventory. Each row displays information about an
 *  item contained in the inventory.
 * @author Laura María de Castro Saturio , Manuel Sánchez Pérez
 *
 */
@SuppressWarnings("serial")
public class RobotPanel extends JPanel implements InterfaceWindow{

    class MyTableModel extends AbstractTableModel{
        class TableData implements Comparable
        {
            String _id;
            String _description;
            
            public String getId() { return _id; }
            public String getDescription() { return _description; }
            
            public TableData(Item item)
            {
                _id = item.getId();
                _description = item.getDescription();
            }
            
            @Override
            public int compareTo(Object other)
            {
                return _id.compareToIgnoreCase( ((TableData)other).getId());//mmmm ClassCatException si no lo uso bien? Pero compareTo no debería lanzar exceptionces...
            }
        }
        
        private final int COLUMNSCOUNT = 2;
        
        private String[]   _columnNames = {"Id", "Description"};
        
        Collection<TableData> _data;
        
        public MyTableModel()
        {
            _data = new TreeSet<>();
        }

        @Override
        public int getColumnCount(){
                return _columnNames.length;
        }

        @Override
        public int getRowCount(){
                return _data.size();
        }

        @Override
        public String getColumnName(int col){
                return _columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col){
            if(col == 0)
                return ((TableData)_data.toArray()[row]).getId();
            else
                return ((TableData)_data.toArray()[row]).getDescription();      
        }
        
        public void addData(Item item)
        {
            _data.add( new TableData( item ) );
            fireTableDataChanged();//La repinta entera, no es lo mas correcto. Pero como es una tabla muy corta, y ahora mismo lo que me importa es que funcione, me da lo mismo...
        }
        
        public boolean removeData(Item item)
        {
            boolean result = _data.remove( new TableData( item ) );
            
            if( result )
                fireTableDataChanged();//La repinta entera, no es lo mas correcto. Pero como es una tabla muy corta, y ahora mismo lo que me importa es que funcione, me da lo mismo...
            
            return result;
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

    public RobotPanel(){
            initRobotPanel();
    }

    public RobotPanel(EventListener driver){
            initRobotPanel();
            setDriver(driver);
    }

    public void initRobotPanel(){
        this.setBorder(new TitledBorder("Robot info"));

        _tableModel = new MyTableModel();
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
    public void setDriver(EventListener driver){
            //_table.addMouseListener((MouseListener) driver);//Para que? No lo vas a usar
    }

    //Update captura dos eventos: robotEngine::changed() (Para actualizar los labels de fuel y material) e ItemContainer::changed() (Para actualizar la lista de items). 
    //Los diferencio mirando si puedo hacer el casting del argumento del evento a ItemContainerChangedEventArgs
    @Override
    public void update(Observable o, Object arg) {
        if( arg instanceof ItemContainerChangeEventArgs)
        {//Es un evento de cambio del item container (Se ha ejecutado la instrucción pick o drop)
            ItemContainerChangeEventArgs changeData = (ItemContainerChangeEventArgs)arg;
            
            if(changeData.getChangeType() == ItemContainerChangeType.ITEM_ADDED)
            {//Pick
                _tableModel.addData( changeData.getItem() );//Ya se encarga el tableModel de poner todo bonito...
            }
            else
            {//Drop
                _tableModel.removeData(  changeData.getItem() );
            }
        }
        else
        {//Es un evento de actualización del engine (Cualquier otra cosa: Actualizamos los labels).
            if(!(boolean)arg)
            {
                _fuelLevelLabel.setText("Fuel: " + ((RobotEngine) o).getFuel());
                _recycledMaterialLabel.setText("Recycled: " + ((RobotEngine) o).getRecycledMaterial());
            }
            else
                WallEsMessages.messagesProvider().WriteError( WallEsMessages.NOFUEL , true);
        }
    }
}
