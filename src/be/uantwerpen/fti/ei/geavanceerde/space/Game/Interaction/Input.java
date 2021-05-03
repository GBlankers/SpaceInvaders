package be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction;

public abstract class Input {
    public enum Inputs {LEFT, RIGHT, UP, DOWN, SHOOT, STOP}

    public abstract boolean inputAvailable();
    public abstract Inputs getInput();
}
