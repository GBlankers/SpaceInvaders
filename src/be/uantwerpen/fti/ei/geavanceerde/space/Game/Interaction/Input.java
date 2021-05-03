package be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction;

/**
 * Abstract class to define the possible inputs and methods
 */
public abstract class Input {
    /**
     * possible inputs
     */
    public enum Inputs {LEFT, RIGHT, UP, DOWN, SHOOT, STOP}

    /**
     * Check if there are inputs available
     * @return boolean corresponding to inputs available
     */
    public abstract boolean inputAvailable();

    /**
     * return the last input
     * @return the input
     */
    public abstract Inputs getInput();
}
