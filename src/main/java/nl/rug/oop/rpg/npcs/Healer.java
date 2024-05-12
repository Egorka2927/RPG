package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.utils.Input;
import nl.rug.oop.rpg.components.Player;
import nl.rug.oop.rpg.interfaces.Inspectable;
import nl.rug.oop.rpg.interfaces.Interactable;

/**
 * Healer class.
 */
public class Healer extends NPC implements Interactable, Inspectable {

    private Input input;

    private int[] healingOptions;

    private int index;

    /**
     * Healer constructor.
     * @param player player.
     * @param name name.
     * @param description description.
     */
    public Healer(Player player, String name, String description) {
        super(player, name, description);
        healingOptions = new int[3];
        index = 0;
    }

    /**
     * This method prints the healing options.
     */
    public void printOptions() {
        for (int i = 0; i < healingOptions.length; i++) {
            if (i == 0) {
                System.out.println("(" + i + ") 25 hp for " + healingOptions[i] + " coins");
            } else if (i == 1) {
                System.out.println("(" + i + ") 50 hp for " + healingOptions[i] + " coins");
            } else {
                System.out.println("(" + i + ") 100 hp for " + healingOptions[i] + " coins");
            }
        }
    }

    /**
     * This method lets the user choose the healing option.
     */
    public void chooseOption() {
        int answer = input.userInput(3);
        if (answer == 0) {
            if (player.getBitCoins() >= healingOptions[0]) {
                if (player.getHealthPoints() + 25 > 100) {
                    player.setHealthPoints(100);
                    player.setBitCoins(player.getBitCoins() - healingOptions[0]);
                } else {
                    player.setHealthPoints(player.getHealthPoints() + 25);
                    player.setBitCoins(player.getBitCoins() - healingOptions[0]);
                }
            } else {
                System.out.println("You do not have enough coins, you brokie.");
                System.out.print("\n");
            }
        } else if (answer == 1) {
            if (player.getBitCoins() >= healingOptions[1]) {
                if (player.getHealthPoints() + 50 > 100) {
                    player.setHealthPoints(100);
                    player.setBitCoins(player.getBitCoins() - healingOptions[1]);
                } else {
                    player.setHealthPoints(player.getHealthPoints() + 50);
                    player.setBitCoins(player.getBitCoins() - healingOptions[1]);
                }
            } else {
                System.out.println("You do not have enough coins, you brokie.");
                System.out.print("\n");
            }
        } else {
            if (player.getBitCoins() >= healingOptions[2]) {
                player.setHealthPoints(100);
                player.setBitCoins(player.getBitCoins() - healingOptions[2]);
            } else {
                System.out.println("You do not have enough coins, you brokie.");
                System.out.print("\n");
            }
        }
    }

    @Override
    public void interact() {
        inspect();
        input = new Input();
        System.out.println("You have " + player.getBitCoins() + " coins");
        System.out.println("You have " + player.getHealthPoints() + " hp");
        System.out.println("Do you want to heal?");
        System.out.println("(0) Yes");
        System.out.println("(1) No");
        int answer = input.userInput(2);
        if (answer == 0) {
            System.out.println("Choose an option:");
            printOptions();
            chooseOption();
            System.out.println("You have " + player.getHealthPoints() + " hp");
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    /**
     * This method adds an option to the array of options.
     * @param price price.
     */
    public void addOption(int price) {
        healingOptions[index] = price;
        index++;
    }
}
