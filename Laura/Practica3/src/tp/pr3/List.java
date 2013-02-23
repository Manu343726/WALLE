package tp.pr3;

public class List<T> extends GenericContainer<T> {

	/**
	 * Initialices the list
	 */
	public List(){}
	
	/**
	 * Initialices a the list with the specified initial allocation size
	 * @param size
	 */
	public List(int size){super(size);}
	
	public void add(T item)
	{
		_insert(_count,item);
	}
	
	/**
	 * Inserts an item at the specific position
	 * @param index Position
	 * @param item item to insert
	 * @throws ArrayIndexOutOfBoundsException (See GenericContainer::_insert())
	 */
	public List<T> add(int index,T item) throws ArrayIndexOutOfBoundsException
	{
		_insert(index,item);
		return this;
	}
	
	/**
	 * Removes the item at the specified index
	 * @param index
	 * @return The removed item
	 * @throws ArrayIndexOutOfBoundsException (See GenericContainer::_insert())
	 */
	public T remove(int index) throws ArrayIndexOutOfBoundsException
	{
		return _remove(index);
	}
	
	/**
	 * Returns the indexth item
	 * @param index
	 * @return the item
	 * @throws ArrayIndexOutOfBoundsException Throws if index is not between 0 and _count - 1
	 */
	public T get(int index) throws ArrayIndexOutOfBoundsException
	{
		if(index >=0 && index < _count)
			return _items[index];
		else
			throw new ArrayIndexOutOfBoundsException("Parameter 'index' must be between 0 and " + (_count-1));
		
	}
	
	/**
	 * Set the value of the indexth item
	 * @param index 
	 * @param item The new value
	 * @throws ArrayIndexOutOfBoundsException Throws if index is not between 0 and _count - 1
	 */
	public void set(int index, T item) throws ArrayIndexOutOfBoundsException
	{
		if(index >=0 && index < _count)
			_items[index] = item;
		else
			throw new ArrayIndexOutOfBoundsException("Parameter 'index' must be between 0 and " + (_count-1));
	}

}
