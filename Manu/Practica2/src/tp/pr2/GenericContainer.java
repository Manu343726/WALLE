package tp.pr2;

public abstract class GenericContainer<T>
{
	/*********************************************************
	 * The purpose of this class is to implement the base    *
	 * for common secuential data containers: lists,queues,  *
	 * stacks,etc.										     *
	 * 														 *
	 * This class provides protected methods for insert	and  *
	 * remove items, wich it will be used by the subclasses. *
	 * 													     *
	 * For example, a stack can only insert elements at      *
	 * the end (push), and erase elements at the end (pop).  *
	 * This would be implemented by calls to protected		 *
	 * functions GenericContainer::_insert(item,_count) and  *
	 * GenericContainer::_remove(_count-1) 					 *	
	 ********************************************************/
	
	protected T[] _items;
	private int _size;//Allocated size
	protected int _count;//Allocated memory in use (Number of items stored in the container)
	
	public static final int INITIALALLOCSIZE = 10;
	
	//Getters:
	/**
	 * Returns the number of items stored in the container
	 * @return
	 */
	public int size(){return _count;}
	
	//Constructors:
	
	public GenericContainer()
	{
		_init(INITIALALLOCSIZE);
	}
	
	public GenericContainer(int size)
	{
		_init(size);
	}
	
	@SuppressWarnings("unchecked")
	private void _init(int size)
	{
		if(size > 0)
		{
			_items = (T[])new Object[size];
			_size = size;
			_count = 0;
		}
		else
			_init(INITIALALLOCSIZE);
	}
	
	//Memory allocation:
	
	@SuppressWarnings("unchecked")
	private void _realloc(int newSize)
	{
		T[] newArray = (T[])new Object[newSize];
		int minSize = Math.min(_size, newSize);
		
		for(int i=0;i<minSize;++i)
			newArray[i] = _items[i];
		
		_items = newArray;
		_size = newSize;
	}
	
	private GenericContainerMemoryState _checkState()
	{
		if(_count == _size)
			return GenericContainerMemoryState.NEEDSEXPAND;
		else if(_count < (_size*2/3))
			return GenericContainerMemoryState.NEEDSCONTRACT;
		else
			return GenericContainerMemoryState.OK;
	}
	
	
	/**
	 * Moves all elements from the indexth one position to the right. The indexth item is doubled.
	 * @param index
	 */
	private void _rightShift(int index)
	{
		for(int i = _count-1;i>=index;--i)
			_items[i+1] = _items[i];
	}
	
	/**
	 * Moves all elements from the indexth one position to the left. The indexth item is erased.
	 * @param index
	 */
	private void _leftShift(int index)
	{
		for(int i = index;i<_count-1;++i)
			_items[i] = _items[i+1];
	}
	
	//Inserting and erasing methods for subclasses:
	
	protected void _insert(int index,T item) throws ArrayIndexOutOfBoundsException
	{
		if(index>=0 && index <= _count)//NOTA: Puedes insertar tanto en una posición ya existente en el contenedor (index >=0 && index < _count), como al final de éste (index == _count)
		{
			if(_checkState() == GenericContainerMemoryState.NEEDSEXPAND)
				_realloc(_size*3/2);
			
			_rightShift(index);
			_items[index] = item;
			_count++;
		}
		else
			throw new ArrayIndexOutOfBoundsException("Parameter 'index' must be between 0 and " + _count);
	}
	
	protected T _remove(int index) throws ArrayIndexOutOfBoundsException 
	{	
		T removedItem;
		
		if(index >=0 && index < _count)
		{
			removedItem = _items[index];
			_leftShift(index);
			_count--;
			
			if(_checkState() == GenericContainerMemoryState.NEEDSCONTRACT)
				_realloc(_size*2/3);
			
			return removedItem;
		}
		else
			throw new ArrayIndexOutOfBoundsException("Parameter 'index' must be between 0 and " + (_count-1));
	}
	
	//toString():
	
	public String toString()
	{
		String str = "{";
		
		for(int i = 0;i < _count;++i)
			if(i < _count - 1)
				str += _items[i].toString() + ",";
			else
				str += _items[i].toString();
		
		return str + "}";
	}
}