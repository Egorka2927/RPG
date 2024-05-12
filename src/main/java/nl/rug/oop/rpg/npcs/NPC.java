package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.components.Player;

import java.io.Serializable;

/**
 * NPC class.
 */

public abstract class NPC implements Serializable {
    private static final long serialVersionUID = 10L;

    /**
     * Used protected, so we can access name in the subclass.
     */
    protected String name;

    /**
     * Used protected, so we can access description in the subclass.
     */
    protected String description;

    /**
     * Used protected, so we can access player in the subclass.
     */
    protected Player player;

    /**
     * NPC constructor.
     * @param player The player object.
     * @param name NPC's name.
     * @param description NPC's description.
     */
    public NPC(Player player, String name, String description) {
        this.player = player;
        this.name = name;
        this.description = description;
    }

}
