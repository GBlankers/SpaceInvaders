//TODO: Correct use of abstract factory?
//TODO: enemy shooting
//TODO: Player lives
//TODO: levels
//TODO: power-ups

package Main;

import Factories.*;

public class Main {

    public static void main(String[] args) {
        // Create the appropriate factory for the visual implementation
        AbstractFactory f = new J2DFactory();
        // The main game class with main loop, entities, collision detection,...
        Game g = new Game(f);
        // Start the game
        g.start();
    }
}
