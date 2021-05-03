package be.uantwerpen.fti.ei.geavanceerde.space.Game.Main;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.AbstractFactory.*;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.J2DFactory;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        // Create the appropriate factory for the visual implementation
        AbstractFactory f = new J2DFactory();
        // The main game class with main loop, entities, collision detection,...
        Game g = new Game(f, new File("res/properties.txt"));
        // Start the game
        g.start();
    }
}
