package nl.rug.oop.rpg;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Game class.
 */
public class Game implements Serializable{
    private Input input;
    private Player player;
    private static final long serialVersionUID = 69L;
    private transient Scanner scanner;

    private Room currentRoom;

    /**
     * Game constructor.
     */
    public Game() {
        Sword sword = new Sword("Rusty sword", 1, 10);
        player = new Player("Johnix", 100, 100, sword, 0);
        input = new Input();
        //scanner = new Scanner(System.in);
    }

    /**
     * Method used to read the doors from each file.
     * @param scanner Used to parse through the file.
     * @param room The current room.
     */
    public void readDoors(Scanner scanner, Room room) {
        int count = 0;
        while (count != 1) {
            int numberDoors = scanner.nextInt();
            String data;
            scanner.nextLine();
            for (int i = 0; i < numberDoors; i++) {
                String name, description, destinationRoomFile;
                data = scanner.nextLine();
                data = scanner.nextLine();
                name = data;

                data = scanner.nextLine();
                description = data;

                data = scanner.nextLine();
                destinationRoomFile = data;
                Door door = new Door(this, name, description, destinationRoomFile);
                room.getInteractables().add(door);
            }
            count++;
        }
    }

    /**
     * This method reads the healers from the file.
     * @param scanner scanner.
     * @param room room.
     */
    public void readHealers(Scanner scanner, Room room) {
        int count = 0;
        while (count != 1) {
            int numberHealers = scanner.nextInt();
            String data;
            scanner.nextLine();
            for (int i = 0; i < numberHealers; i++) {
                String name, description;
                int price25, price50, price100;
                data = scanner.nextLine();
                data = scanner.nextLine();
                name = data;

                data = scanner.nextLine();
                description = data;

                price25 = scanner.nextInt();
                scanner.nextLine();
                price50 = scanner.nextInt();
                scanner.nextLine();
                price100 = scanner.nextInt();
                scanner.nextLine();
                Healer healer = new Healer(player, name, description);
                healer.addOption(price25);
                healer.addOption(price50);
                healer.addOption(price100);
                room.getInteractables().add(healer);
            }
            count++;
        }
    }

    /**
     * Method to read weapons from file.
     * @param scanner scanner.
     * @param trader trader.
     */
    public void readWeapons(Scanner scanner, Trader trader) {
        String swordName, spellName, data;
        int swordMinDamage, swordMaxDamage, swordPrice, spellMinDamage, spellMaxDamage, spellPrice;

        data = scanner.nextLine();
        swordName = data;

        swordMinDamage = scanner.nextInt();
        scanner.nextLine();
        swordMaxDamage = scanner.nextInt();
        scanner.nextLine();
        swordPrice = scanner.nextInt();
        scanner.nextLine();

        scanner.nextLine();

        data = scanner.nextLine();
        spellName = data;

        spellMinDamage = scanner.nextInt();
        scanner.nextLine();
        spellMaxDamage = scanner.nextInt();
        scanner.nextLine();
        spellPrice = scanner.nextInt();
        scanner.nextLine();

        Sword sword = new Sword(swordName, swordMinDamage, swordMaxDamage);
        Spell spell = new Spell(spellName, spellMinDamage, spellMaxDamage);

        sword.setPrice(swordPrice);
        spell.setPrice(spellPrice);

        trader.addOption(sword);
        trader.addOption(spell);
    }

    /**
     * Method to read healing doors.
     * @param scanner scanner.
     * @param room current room.
     */
    public void readHealingDoors(Scanner scanner, Room room) {
        int count = 0;
        while (count != 1) {
            int numberDoors = scanner.nextInt();
            String data;
            scanner.nextLine();
            for (int i = 0; i < numberDoors; i++) {
                String name, description, destinationRoomFile;
                data = scanner.nextLine();
                name = scanner.nextLine();

                description = scanner.nextLine();

                destinationRoomFile = scanner.nextLine();
                HealingDoor door = new HealingDoor(this, name, description, destinationRoomFile);
                room.getInteractables().add(door);
            }
            count++;
        }
    }

    /**
     * Method to read trap doors.
     * @param scanner scanner.
     * @param room current room.
     */
    public void readTrapDoors(Scanner scanner, Room room) {
        int count = 0;
        while (count != 1) {
            int numberDoors = scanner.nextInt();
            String data;
            scanner.nextLine();
            for (int i = 0; i < numberDoors; i++) {
                String name, description;
                int doorDamage;
                data = scanner.nextLine();
                name = scanner.nextLine();

                description = scanner.nextLine();

                doorDamage = scanner.nextInt();
                scanner.nextLine();
                TrapDoor door = new TrapDoor(this, name, description, null, doorDamage);
                room.getInteractables().add(door);
            }
            count++;
        }
    }

    /**
     * Method to read traders.
     * @param scanner scanner.
     * @param room current room.
     */
    public void readTraders(Scanner scanner, Room room) {
        int count = 0;
        while (count != 1) {
            int numberTraders = scanner.nextInt();
            String data;
            scanner.nextLine();
            for (int i = 0; i < numberTraders; i++) {
                String name, description;
                data = scanner.nextLine();
                name = scanner.nextLine();

                data = scanner.nextLine();
                description = data;

                Trader trader = new Trader(player, name, description);

                readWeapons(scanner, trader);

                room.getInteractables().add(trader);
            }
            count++;
        }
    }

    /**
     * Method used to read enemies from each file.
     * @param scanner Used to parse through the file.
     * @param room The current room.
     */
    public void readEnemies(Scanner scanner, Room room) {
        int count = 0;
        while (count != 1) {
            String data;
            int numberEnemies = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < numberEnemies; i++) {
                String name, description;
                int healthPoints, minDamage, maxDamage, coins;
                data = scanner.nextLine();
                name = scanner.nextLine();

                data = scanner.nextLine();
                description = data;

                healthPoints = scanner.nextInt();
                scanner.nextLine();
                minDamage = scanner.nextInt();
                scanner.nextLine();
                maxDamage = scanner.nextInt();
                scanner.nextLine();
                coins = scanner.nextInt();
                scanner.nextLine();

                Enemy enemy = new Enemy(player, name, description, healthPoints,
                        minDamage, maxDamage, coins);
                room.getInteractables().add(enemy);
            }
            count++;
        }
    }

    /**
     * Method used to read everything that is in a room.
     * @param fileName We read from files, so we give a file name.
     * @param room Current room.
     * @return We return the current room with everything read.
     */
    public Room readFileRoom(String fileName, Room room) {
        try {
            File myObj = new File("rooms" + File.separator + fileName);
            scanner = new Scanner(myObj);
            String data, roomDescription;

            roomDescription = scanner.nextLine();
            data = scanner.nextLine();
            room = new Room(roomDescription);

            readDoors(scanner, room);
            readTrapDoors(scanner, room);
            readHealingDoors(scanner, room);
            readEnemies(scanner, room);
            readHealers(scanner, room);
            readTraders(scanner, room);

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return room;
    }

    /**
     * Print saving options method.
     */
    public void printSavingOptions() {
        System.out.println("(0) QuickSave");
        System.out.println("(1) QuickLoad");
        System.out.println("(2) Save");
        System.out.println("(3) Load");
        System.out.println("(4) Go back");
    }

    /**
     * Choose saving options method.
     */
    public void chooseSavingOption() {
        //input = new Input();
        int answer = input.userInput(5);
        if (answer == 0) {
            Serializer.quickSave(currentRoom, "quicksave.ser");
            //input.getInput().close();
        } else if (answer == 1) {
            try {
                currentRoom = Serializer.quickLoad("savedgames" + File.separator + "quicksave.ser");
                //input.getInput().close();
            } catch (IOException ioException) {
                System.err.println("IO exception");
            } catch (ClassNotFoundException classNotFoundException) {
                System.err.println("Class is not found");
            }
        } else if (answer == 2) {
            System.out.println("This option does not work");
        } else if (answer == 3) {
            System.out.println("This option does not work");
        } else {
            System.out.println("This option does not work");
        }
    }

    /**
     * Check if the player died.
     * @param interactable Interactables list.
     * @param answer input answer.
     * @return return 0 if player died, 1 otherwise.
     */
    public int checkDeath(Interactable interactable, int answer) {
        if (interactable instanceof Enemy) {
            if (player.getHealthPoints() > 0 && ((Enemy) interactable).getEnemyHealthPoints() <= 0) {
                System.out.println("You have collected " + ((Enemy) interactable).getCoins() + " coins");
                player.setBitCoins(player.getBitCoins() + ((Enemy) interactable).getCoins());
                currentRoom.getInteractables().remove(answer);
                return 1;
            } else if (player.getHealthPoints() <= 0) {
                System.out.println("You have lost");
                return 0;
            }
        } else if (interactable instanceof TrapDoor) {
            if (player.getHealthPoints() <= 0) {
                System.out.println("You have lost");
                return 0;
            }
        }
        return 1;
    }

    /**
     * Method for the game loop. Runs until the end of the game.
     */
    public void gameLoop() {
        //input = new Input();
        currentRoom = readFileRoom("room1.txt", currentRoom);
        while (true) {
            input = new Input();
            currentRoom.showInteractables();
            if (currentRoom.getInteractables().isEmpty()) {
                //input.getInput().close();
                break;
            }
            int answer = input.userInput(currentRoom.getInteractables().size() + 1);
            if (answer == currentRoom.getInteractables().size()) {
                printSavingOptions();
                chooseSavingOption();
                //input.getInput().close();
                continue;
            }
            Interactable interactable = currentRoom.getInteractables().get(answer);
            interactable.interact();

            if (checkDeath(interactable, answer) == 0) {
                //input.getInput().close();
                break;
            }
            //input.getInput().close();
        }
    }

    public void setCurrentRoom(Room destinationRoom) {
        currentRoom = destinationRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Player getPlayer() {
        return player;
    }
}
