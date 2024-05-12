package nl.rug.oop.rpg.components;

import nl.rug.oop.rpg.interfaces.Interactable;

import java.io.Serializable;
import java.util. *;

/**
 * Room class.
 */
public class Room implements Serializable {
    private static final long serialVersionUID = 2L;
    private String description;

    private List<Interactable> interactables;

    public Room(String description) {
        interactables = new ArrayList<>();
        this.description = description;
    }

    /**
     * Method to show room description and  every interactable in the room.
     */
    public void showInteractables() {
        System.out.println(description);
        if (!interactables.isEmpty()) {
            for (int i = 0; i < interactables.size(); i++) {
                System.out.println("(" + i + ") " + interactables.get(i).getName());
            }
            System.out.println("(" + interactables.size() + ")" + " Saves");
            System.out.println("What do you want to do?");
        }
    }

    public List<Interactable> getInteractables() {
        return interactables;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
