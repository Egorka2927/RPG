package nl.rug.oop.rpg.doors;

import nl.rug.oop.rpg.components.Game;
import nl.rug.oop.rpg.utils.Input;
import nl.rug.oop.rpg.interfaces.Inspectable;
import nl.rug.oop.rpg.interfaces.Interactable;

/**
 * Trap door class.
 */
public class TrapDoor extends Door implements Interactable, Inspectable {
    private static final long serialVersionUID = 55L;
    private Input input;
    private int trapDoorDamage;

    /**
     * Door constructor.
     *
     * @param game                    Our game instance.
     * @param doorName                Name of the door.
     * @param description             Description of the door.
     * @param destinationRoomFileName The room file name which the door  to.
     * @param trapDoorDamage trap door damage.
     */
    public TrapDoor(Game game, String doorName, String description,
                    String destinationRoomFileName, int trapDoorDamage) {
        super(game, doorName, description, destinationRoomFileName);
        this.trapDoorDamage = trapDoorDamage;
    }

    @Override
    public void interact() {
        inspect();
        input = new Input();
        System.out.println("Do you want to enter?");
        System.out.println("(0) Yes");
        System.out.println("(1) No");

        int answer = input.userInput(2);
        if (answer == 0) {
            if (checkEnemies()) {
                System.out.println("You have not defeated all the enemies");
            } else {
                System.out.println("It's a trap!\nYou have received " + trapDoorDamage + " damage");
                game.getPlayer().setHealthPoints(game.getPlayer().getHealthPoints() - trapDoorDamage);
            }
        }
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }
}
