package be.uantwerpen.fti.ei.geavanceerde.space.Visual;

public abstract class Engine {

    /**
     * Start the engine
     */
    public abstract void start();

    /**
     * Get the grid size
     * @return grid size
     */
    public abstract int getSize();

    /**
     * Get the size of the images
     * @return image size
     */
    public abstract int getImageSize();

    /**
     * Render the current frame
     */
    public abstract void render();

    /**
     * Set the game dimensions
     * @param gameWidth the game width
     * @param gameHeight the game height
     */
    public abstract void setGameDimensions(int gameWidth, int gameHeight);
}
