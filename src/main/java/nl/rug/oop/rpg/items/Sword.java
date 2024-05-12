package nl.rug.oop.rpg.items;

/**
 * Sword class.
 */
public class Sword extends Weapon {
    private static final long serialVersionUID = 896L;
    private int price;

    /**
     * Sword constructor.
     * @param name Name of the sword.
     * @param minDamage Minimum amount of damage the sword can do.
     * @param maxDamage Maximum amount of damage the sword can do.
     */
    public Sword(String name, int minDamage, int maxDamage) {
        super(name, minDamage, maxDamage);
    }
}
