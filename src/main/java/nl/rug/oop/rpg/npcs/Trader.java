package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.utils.Input;
import nl.rug.oop.rpg.components.Player;
import nl.rug.oop.rpg.items.Weapon;
import nl.rug.oop.rpg.interfaces.Inspectable;
import nl.rug.oop.rpg.interfaces.Interactable;

import java.util.ArrayList;
import java.util.List;

/**
 * Trader class.
 */
public class Trader extends NPC implements Interactable, Inspectable {
    private static final long serialVersionUID = 234L;
    private Input input;

    private List<Weapon> tradingOptions;

    private int index;

    /**
     * Trader constructor.
     * @param player player.
     * @param name trader name.
     * @param description trader description.
     */
    public Trader(Player player, String name, String description) {
        super(player, name, description);
        tradingOptions = new ArrayList<>();
        index = 0;
    }

    /**
     * Print options method.
     */
    public void printOptions() {
        for (int i = 0; i < tradingOptions.size(); i++) {
            if (i == 0) {
                System.out.println("(" + i + ") " + tradingOptions.get(i).getWeaponName() +
                        " for " + tradingOptions.get(i).getPrice() + " coins");
            } else {
                System.out.println("(" + i + ") " + tradingOptions.get(i).getWeaponName() +
                        " for " + tradingOptions.get(i).getPrice() + " coins");
            }
        }
    }

    /**
     * Choose options method.
     */
    public void chooseOptions() {
        int answer = input.userInput(2);

        if (player.getBitCoins() >= tradingOptions.get(answer).getPrice()) {
            player.addWeapon(tradingOptions.get(answer));
            System.out.println("You have bought the " + tradingOptions.get(answer).getWeaponName() +
                    " weapon for " + tradingOptions.get(answer).getPrice() + " coins.");
            player.setBitCoins(player.getBitCoins() - tradingOptions.get(answer).getPrice());
            tradingOptions.remove(answer);
            System.out.print("\n");
        } else {
            System.out.println("You do not have enough coins, you brokie.");
            System.out.print("\n");
        }
    }

    @Override
    public void interact() {
        inspect();
        input = new Input();
        System.out.println("You have " + player.getBitCoins() + " coins.");
        System.out.println("Do you want to trade?");
        System.out.println("(0) Yes");
        System.out.println("(1) No");
        int answer = input.userInput(2);
        if (answer == 0) {
            System.out.println("Choose an option:");
            if (!tradingOptions.isEmpty()) {
                printOptions();
                chooseOptions();
            } else {
                System.out.println("No items left");
            }
        }
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    @Override
    public String getName() {
        return name;
    }

    public void addOption(Weapon weapon) {
        tradingOptions.add(index, weapon);
        index++;
    }
}
