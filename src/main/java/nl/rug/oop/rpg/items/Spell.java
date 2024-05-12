package nl.rug.oop.rpg.items;

/**
 * Spell class.
 */
public class Spell extends Weapon {
    private static final long serialVersionUID = 547L;

    private int price;

    /**
     * Spell constructor.
     * @param name spell name
     * @param minDamage spell min damage.
     * @param maxDamage spell max damage.
     */
    public Spell(String name, int minDamage, int maxDamage) {
        super(name, minDamage, maxDamage);
    }

}
