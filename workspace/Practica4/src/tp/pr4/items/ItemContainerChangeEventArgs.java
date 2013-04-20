/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr4.items;

/**
 * Contains data of a item container change event
 * @author
 * Manu343726
 */
public class ItemContainerChangeEventArgs {
    private ItemContainerChangeType _type;
    private Item _item;
    
    /***
     * Gets the item that participates in the event.
     * @return A string containing the item id.
     */
    public final Item getItem() { return _item; }
    
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
    public ItemContainerChangeEventArgs(ItemContainerChangeType type , Item item)
    {
        _type = type;
        _item = item;
    }
}
