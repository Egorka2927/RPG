package nl.rug.oop.rpg.doors;

import nl.rug.oop.rpg.components.Game;
import nl.rug.oop.rpg.utils.Input;
import nl.rug.oop.rpg.components.Room;
import nl.rug.oop.rpg.interfaces.Inspectable;
import nl.rug.oop.rpg.interfaces.Interactable;
import nl.rug.oop.rpg.npcs.Enemy;

import java.io.Serializable;

/**
 * Door class.
 */

public class Door implements Interactable, Inspectable, Serializable {
    private static final long serialVersionUID = 60L;

    private Input input;

    /**
     * Door name.
     */
    protected String doorName;

    /**
     * Door description.
     */
    protected String description;

    /**
     * Door destination.
     */
    protected String destinationRoomFileName;

    /**
     * Room which door leads to.
     */
    protected Room destinationRoom;

    /**
     * Game.
     */

    protected Game game;

    /**
     * Door constructor.
     * @param game Our game instance.
     * @param doorName Name of the door.
     * @param description Description of the door.
     * @param destinationRoomFileName The room file name which the door  to.
     */
    public Door(Game game, String doorName, String description, String destinationRoomFileName) {
        this.doorName = doorName;
        this.description = description;
        this.destinationRoomFileName = destinationRoomFileName;
        this.game = game;
    }

    /**
     * This method checks whether there are undefeated enemies in the room.
     * @return boolean.
     */
    public boolean checkEnemies() {
        for (Interactable i : game.getCurrentRoom().getInteractables()) {
            if (i instanceof Enemy) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void interact() {
        input = new Input();
        inspect();
        System.out.println("Do you want to enter?");
        System.out.println("(0) Yes");
        System.out.println("(1) No");

        int answer = input.userInput(2);
        if (answer == 0) {
            if (checkEnemies()) {
                System.out.println("You have not defeated all the enemies");
            } else {
                // loading the next room
                destinationRoom = game.readFileRoom(destinationRoomFileName, destinationRoom);
                game.setCurrentRoom(destinationRoom);
            }
        }
    }

    @Override
    public String getName() {
        return doorName;
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }
}
