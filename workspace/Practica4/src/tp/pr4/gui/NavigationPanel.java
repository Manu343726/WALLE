package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.EventListener;
import java.util.Observable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import tp.pr4.Direction;
import tp.pr4.NavigationModule;
import tp.pr4.NavigationModuleChangeType;
import tp.pr4.NavigationModuleChangedEventArgs;
import tp.pr4.Place;
import tp.pr4.RobotEngine;
import tp.pr4.WallEsMessages;
import tp.pr4.utils.*;


/**
 * This class is in charge of the panel that displays the information about the robot 
 * heading and the city that is traversing. It contains the grid that represents the city 
 * in the Swing interface, a text area to show the place descriptions, and a label with an 
 * icon which represents the robot heading
 * 
 * The 11x11 grid contains PlaceCell objects and the first place starts at (5,5). 
 * This panel will update the visited places when the robot moves from one place to another. 
 * Additionally it will show the place description on a text area if the user clicks on a visited place.
 * @author Laura & Manuel
 *
 */
@SuppressWarnings("serial")
public class NavigationPanel extends JPanel implements InterfaceWindow{

    private int row; //Current row
    private int col; //Current column

    private PlaceCell[][] cells;   //Grid with the MapCells
    private JTextArea info;        //Text area where the room description is shown
    private JLabel labelRobotHeading; //Label where the WALLE image appears showing the robot heading
    private EnumMap<Direction , ImageIcon> headingIcons; //A map with the icons for each heading direction


    public NavigationPanel(){
            initNavigationPanel();
    }


    public void initNavigationPanel(){
        row = 4;
        col = 5;
        cells = new PlaceCell[11][11];
        for(int i = 0; i < 11; i++){
                for(int j = 0; j < 11; j++){
                        cells[i][j] = new PlaceCell();
                }
        }
        info = new JTextArea();
        labelRobotHeading = new JLabel();
        headingIcons = new EnumMap<Direction, ImageIcon>(Direction.class);

        info.setEditable(false);

        headingIcons.put(Direction.NORTH, new ImageIcon("src/tp/pr4/gui/images/walleNorth.png"));
        headingIcons.put(Direction.EAST, new ImageIcon("src/tp/pr4/gui/images/walleEast.png"));
        headingIcons.put(Direction.SOUTH, new ImageIcon("src/tp/pr4/gui/images/walleSouth.png"));
        headingIcons.put(Direction.WEST, new ImageIcon("src/tp/pr4/gui/images/walleWest.png"));

        labelRobotHeading.setIcon(headingIcons.get(Direction.NORTH));

        this.setLayout(new GridLayout(2, 1));

        JPanel panelGrid = new JPanel(new GridLayout(11, 11));
        panelGrid.setBorder(new TitledBorder("Navigation Panel"));
        for(int i = 0; i < 11; i++)
                for(int j = 0; j < 11; j++)
                        panelGrid.add(cells[i][j]);

        JPanel panelAux = new JPanel(new BorderLayout());
        panelAux.add(labelRobotHeading, "West");
        panelAux.add(panelGrid, "Center");

        JScrollPane panelAuxText = new JScrollPane(info, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelAuxText.setBorder(new TitledBorder("Log"));

        this.add(panelAux);
        this.add(panelAuxText);
    }

    public void setDriver(EventListener driver){
            for(int i = 0; i < 11; i++){
                    for(int j = 0; j < 11; j++){
                            cells[i][j].setDriver(driver);
                    }
            }
    }

    private void calculateCoords(Direction dir){
            switch(dir){
            case NORTH: row--; break;
            case SOUTH: row++; break;
            case EAST:  col++; break;
            case WEST: col--; break;
            }
    }

    private void updatePreviousCell(final Place previousPlace)
    {
        boolean finded = false;
        int i = 0;

        Filter<PlaceCell> filter;

        while(i < cells.length && !finded)
        {
            filter = new Filter(Arrays.asList(cells[i]).iterator() , new Predicate<PlaceCell>()
            {
                @Override
                public boolean apply(PlaceCell cell)
                {
                    return cell.isMyPlace( previousPlace );
                }
            });

            if( filter.hasNext() )
            {
                finded = true;
                filter.next().setWalked();
            }
            else
                ++i;
        }
    }

    //Al navPanel le llegan las instrucciones de mov y turn
    @Override
    public void update(Observable o, Object arg) {
        NavigationModuleChangedEventArgs args = (NavigationModuleChangedEventArgs) arg;

        if( args.getChangeType() == NavigationModuleChangeType.CHANGE_CURRENTDIRECTION ) //Has happened a turn instruction
            labelRobotHeading.setIcon(headingIcons.get( args.getCurrentDirection() ));
        else if( args.getChangeType() == NavigationModuleChangeType.CHANGE_CURRENTPLACE )//Has happened a move instruction
        {    
            calculateCoords( args.getCurrentDirection() );
            updatePreviousCell( args.getPreviousPlace() );
            cells[row][col].setCurrentPlace( args.getCurrentPlace() );
        }
        else //event type == NavigationModuleChangeType::CHANGE_CURRENTPLACE_EXIT
        {
            WallEsMessages.messagesProvider().WriteInfo( WallEsMessages.SHIPFINDED , true );
        }
    }

    public void setDescriptionText(String description){
            this.info.setText(description);
    }
}
