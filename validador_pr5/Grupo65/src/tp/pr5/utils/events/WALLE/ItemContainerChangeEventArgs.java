/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.utils.events.WALLE;

import tp.pr5.items.Item;

/**
 * Contains data of a item container change event
 * @author
 * Laura María de Castro Saturio , Manuel Sánchez Pérez
 */
public class ItemContainerChangeEventArgs {
    private ItemContainerChangeType _type;
    private Item _item;
    private int _item_index;
    
    /***
     * Gets the item that participates in the event.
     * @return A string containing the item id.
     */
    public final Item getItem() { return _item; }
    
    /**
     * Gets the index of the item that participates in the event.
     * @return An integer containing the index.
     */
    public int getItemIndex() { return _item_index; }
    
    /***
     * Gets the type of the change in the ItemContainer.
     * @return ItemContainerChangeType::ITEM_ADDED if a addition was performed. ItemContainerChangeType::ITEM_DELETED if the action was a erasure.
     */
    public final ItemContainerChangeType getChangeType() { return _type; }
    
    /***
     * Creates a ItemContainerChangeEventArgs with the specified data.
     * @param type The type of the change.
     * @param itemId The item that participates in the change.
     */
    public ItemContainerChangeEventArgs(ItemContainerChangeType type , Item item , int index)
    {
        _type = type;
        _item = item;
        _item_index = index;
    }
}
