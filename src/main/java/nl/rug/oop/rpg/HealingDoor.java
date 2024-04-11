package nl.rug.oop.rpg;

/**
 * Healing door class.
 */
public class HealingDoor extends Door {

    private Input input;

    /**
     * Door constructor.
     *
     * @param game                    Our game instance.
     * @param doorName                Name of the door.
     * @param description             Description of the door.
     * @param destinationRoomFileName The room file name which the door  to.
     */
    public HealingDoor(Game game, String doorName, String description, String destinationRoomFileName) {
        super(game, doorName, description, destinationRoomFileName);
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
                System.out.println("You feel much better after entering this door");
                game.getPlayer().setHealthPoints(100);
                System.out.println("You have " + game.getPlayer().getHealthPoints() + " hp now");
                // loading the next room
                destinationRoom = game.readFileRoom(destinationRoomFileName, destinationRoom);
                game.setCurrentRoom(destinationRoom);
            }
        }
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }
}
