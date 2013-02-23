package tp.pr2;

public class CodeCard extends Item {
	
    private String code;
	
	public CodeCard(String id, String description, String code){
		super(id, description);
		this.code = code;
	}
	
	public boolean canBeUsed(){
		return true; //Cards always can be used
	}
	
	public String getCode(){
		return this.code;
	}
	
	public boolean use(RobotEngine r, Place p){
		if(this.canBeUsed()){
		    Street street = r.getHeadingStreet();
		    if(street == null)
			    return false;
		    else{
		        if(street.isOpen())
			        return street.close(this); //No puedes devolver siempre true, puede ser que la tarjeta no valga para esa calle (El id no coincide, mira la implementación de street.cose() y street.open())
		        else
			        return street.open(this); //Igual que la anterior
		    }
		}
		else
			return false;
	}
}
