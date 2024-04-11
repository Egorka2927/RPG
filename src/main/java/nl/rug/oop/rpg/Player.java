package nl.rug.oop.rpg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Player class.
 */
public class Player implements Serializable {
    private static final long serialVersionUID = 30L;
    private String name;

    private int bitCoins;

    private int healthPoints;
    private int manaPoints;
    private List<Weapon> weapons;

    /**
     * Player constructor.
     * @param name Player's name.
     * @param healthPoints Player's health points.
     * @param manaPoints Player's mana points.
     * @param weapon Player's weapon.
     * @param coins Player's coins.
     */
    public Player(String name, int healthPoints, int manaPoints, Weapon weapon, int coins) {

        this.name = name;
        this.healthPoints = healthPoints;
        this.manaPoints = manaPoints;
        weapons = new ArrayList<>();
        this.addWeapon(weapon);
        this.bitCoins = coins;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setBitCoins(int bitCoins) {
        this.bitCoins = bitCoins;
    }

    public int getBitCoins() {
        return bitCoins;
    }
}
