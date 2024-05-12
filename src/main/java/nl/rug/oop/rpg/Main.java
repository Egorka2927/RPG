package nl.rug.oop.rpg;

import nl.rug.oop.rpg.components.Game;

/**
 * Main class.
 *
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.gameLoop();
    }
}

