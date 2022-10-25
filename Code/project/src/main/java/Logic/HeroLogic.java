package Logic;

import Entities.Hero;
import Entities.Position;

public class HeroLogic {
    private Hero hero;

    private void processPlayerMovement(Position pos){
         /* TODO
            access board datatype to check state of x,y tile
            if movement allowed then return true else false
            process any score changes.
         */
        boolean validMovement = true; //temp


        if (validMovement){
            hero.setPosition(pos);
        }


    }
}
