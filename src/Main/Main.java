//TODO: Correct use of abstract factory?
//TODO: levels
//TODO: power-ups

package Main;

import Factories.*;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File props = new File("res/properties.txt");
        // Create the appropriate factory for the visual implementation
        AbstractFactory f = new J2DFactory();
        // The main game class with main loop, entities, collision detection,...
        Game g = new Game(f, props);
        // Start the game
        g.start();
    }
}
