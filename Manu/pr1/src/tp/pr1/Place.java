package tp.pr1;

public class Place {
	private String name;
	private String description;
	private boolean isship;
	
	public Place(){name="";description="";isship=false;}//mmmmmm....
	public Place(String name, boolean isShip, String description){
		this.name=name;
		this.isship=isShip;
		this.description=description;
	}
	
	public boolean isSpaceship(){return isship;};
	public String toString(){return "'" + name + "':\n" + description + "\n(IsSpaceShip: " + isship + ")";}
	/*Muestra:
	 * 'Name':
	 * Description
	 * (IsSpaceShip: true/false)
	 */
	
}
