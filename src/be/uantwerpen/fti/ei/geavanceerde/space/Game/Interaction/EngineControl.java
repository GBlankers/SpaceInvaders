package be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction;

public abstract class EngineControl {

    /**
     * Initialise the engine
     */
    public abstract void createEngine();

    /**
     * Render all the objects that have been drawn to the current frame
     */
    public abstract void engineRender();

    /**
     * Start the engine
     */
    public abstract void engineStart();

    /**
     * Change the coordinates of the visual engine
     * @param width max game x coordinate
     * @param height max game y coordinate
     */
    public abstract void engineSetGameDimensions(int width, int height);
}
