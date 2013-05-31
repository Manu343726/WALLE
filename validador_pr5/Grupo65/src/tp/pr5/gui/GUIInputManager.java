/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.gui;

import tp.pr5.Direction;

/**
 * This class manages input from the graphical user inteface.
 * @author usuario_local
 */
public class GUIInputManager {
    private static GUIInputManager _instance; //Singleton instance
    
    private String _selected_item_id;
    private String _selected_rotation;
    private String  _input_item_id;
    
    
    /**
     * Returns the last selected item id at the items table.
     * @return A string containing the id.
     */
    public String getSelectedItemId() { return _selected_item_id; }
    
    /**
     * Returns the rotation selected in the rotation combo.
     * @return An instance of Direction enum containing the selected direction.
     */
    public String getSelectedRotation() { return _selected_rotation; }
    
    /**
     * Returns the id that was written in the textbox of the instrutions panel.
     * @return 
     */
    public String getInputItemId() { return _input_item_id; }
    
    /**
     * Updates the selected item.
     * @param data The id of the new selected item.
     */
    public void updateSelectedItemId(String data) { _selected_item_id = data; }
    
    /**
     * Updates the selected rotation
     * @param data The new roation.
     */
    public void updateSelectedRotation( String data ) 
    { 
        _selected_rotation = data; 
    }
    
    /**
     * Updates the id of the input item.
     * @param data The new id.
     */
    public void updateInputItemId(String data) { _input_item_id = data; }
    
    /**
     * Returns the singleton instance.
     * @return A reference to the instance.
     */
    public static GUIInputManager getInstance() 
    {
        if( _instance == null )
            _instance = new GUIInputManager();
        
        return _instance;
    }
    
    private GUIInputManager() {}
}
