package Interaction;

public abstract class Input {
    public static enum Inputs {LEFT, RIGHT, UP, DOWN, SPACE, Q};

    public abstract boolean inputAvailable();
    public abstract Inputs getInput();
}
