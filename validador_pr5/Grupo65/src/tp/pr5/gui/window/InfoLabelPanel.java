/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.gui.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import tp.pr5.utils.events.EventHandler;
import tp.pr5.utils.events.EventSender;

/**
 * Swing panel for information label.
 * @author
 * Manuel Sánchez
 */
public class InfoLabelPanel extends JPanel implements EventHandler<String> {
    private JLabel _info_label;
    
    /**
     * Initialices a InfoPanelLabel.
     */
    public InfoLabelPanel()
    {
        super( new BorderLayout());
        
        _info_label = new JLabel();
        _info_label.setVerticalAlignment( SwingConstants.CENTER );
        _info_label.setHorizontalAlignment( SwingConstants.CENTER );
        
        this.add( _info_label , "Center" );
        this.setPreferredSize(new Dimension(100,30));
    }
    
    @Override
    public void update(EventSender sender, String args) {
        _info_label.setText(args);
    }   
}
