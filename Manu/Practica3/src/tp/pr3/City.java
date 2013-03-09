//OK//

//Error en el test !!?

package tp.pr3;

public class City {
	
	private Street[] _cityMap;
	
	public City(){}//Equivalente a cityMap = null 
	
	public City(Street[] cityMap){
		_cityMap = cityMap;
	}
	
	/**
	 * Returns the street that comes out from a given place on a given direction
	 * @param place: place where WALL�E is
	 * @param direction: direction which you want to move
	 * @return if there is a street from the given place on that direction. If there no street returns null
	 */
	public Street lookForStreet(Place currentPlace, Direction currentHeading){
		Street s = null;
		int i = 0;
		boolean encontrada = false;//Ojo, habías puesto true
		
		if(_cityMap != null)//Si no lo chequeas, puedes lanzar una NullPointerException (No sabes si el usuario de la clase ha utilizado el constructor predeterminado, y por tanto el mapa no ha sido creado)
			while(i < _cityMap.length && !encontrada)
				if(_cityMap[i].comeOutFrom(currentPlace, currentHeading)){
					encontrada = true;
					s = _cityMap[i];
				}
				else 
					i++;
		
		return s;
	}

}
