package nl.rug.oop.rpg.items;

import java.io.Serializable;

/**
 * Weapon class.
 */
public abstract class Weapon implements Serializable {
    private static final long serialVersionUID = 33L;
    private String weaponName;
    private int weaponMinDamage;
    private int weaponMaxDamage;
    private int weaponDamage;
    private int price;

    /**
     * Weapon constructor.
     * @param weaponName Name of the weapon.
     * @param minDamage Minimum amount of damage the weapon can do.
     * @param maxDamage Maximum amount of damage the weapon can do.
     */
    public Weapon(String weaponName, int minDamage, int maxDamage) {
        this.weaponName = weaponName;
        this.weaponMinDamage = minDamage;
        this.weaponMaxDamage = maxDamage;
        this.weaponDamage = (int) (Math.random() * (maxDamage - minDamage + 1) + minDamage);
    }

    public String getWeaponName() {
        return weaponName;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public void resetWeaponDamage() {
        this.weaponDamage = (int)(Math.random()*(this.weaponMaxDamage-this.weaponMinDamage + 1)+this.weaponMinDamage);
    }

    public int getWeaponMinDamage() {
        return this.weaponMinDamage;
    }

    public int getWeaponMaxDamage() {
        return this.weaponMaxDamage;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
