package be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Interaction;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction.EngineControl;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.J2DEngine;

/**
 * Class to control the J2D engine (initialising, start, set dimensions and render)
 */
public class J2DEngineControl extends EngineControl {

    /**
     * Initialise the J2D engine
     */
    @Override
    public void createEngine() {
        J2DEngine.getInstance();
    }

    /**
     * Render all the objects that have been drawn to the current J2D frame
     */
    @Override
    public void engineRender() {
        J2DEngine.getInstance().render();
    }

    /**
     * Start the J2D engine
     */
    @Override
    public void engineStart() {
        J2DEngine.getInstance().start();
    }

    /**
     * Change the coordinates of the J2D engine
     * @param width  max game x coordinate
     * @param height max game y coordinate
     */
    @Override
    public void engineSetGameDimensions(int width, int height) {
        J2DEngine.getInstance().setGameDimensions(width, height);
    }
}
