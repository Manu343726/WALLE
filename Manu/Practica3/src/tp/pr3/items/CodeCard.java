package tp.pr3.items;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.Street;

/***CLASS CODECARD***/
/*
 * A CodeCard can open or close the door placed in the streets. The card contains a code that must
 *  match the street code in order to perform the action.
 */

public class CodeCard extends Item {
	

    private String _code;
	
   /**
    * Code Card constructor
    * @param id Code card name
    * @param description Code card description
    * @param code Secret code stored in the code card
    */
    public CodeCard(String id, String description, String code){
            super(id, description);
            _code = code;
    }

    @Override
    /**
     * A code card always can be used. Since the robot has the code card it never loses it
     */
    public boolean canBeUsed(){
            return true; 
    }

    /**
     * Gets the code stored in the code card
     * @return A String that represents the code
     */
    public String getCode(){
            return _code;
    }

    @Override
    /**
     * The method to use a code card. If the robot is in a place which contains a street in the direction he is
     * looking at, then the street is opened or closed if the street code and the card code match.
     */
    public boolean use(RobotEngine r, NavigationModule navigation){
        boolean result;
        Street street = navigation.getHeadingStreet();

        if(this.canBeUsed()){
            street = navigation.getHeadingStreet();
            
            if(street == null)
                    result = false;
            else{
                if(street.isOpen())
                        result = street.close(this); 
                else
                        result = street.open(this); 
            }
        }
        else
                result = false;
        
        return result;
    }
}
