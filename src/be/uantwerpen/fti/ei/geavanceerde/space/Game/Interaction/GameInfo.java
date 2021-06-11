package be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction;

public abstract class GameInfo {

    public abstract void updateScore(int score);
    public abstract void showGameOverWin();
    public abstract void showGameOverLose();
    public abstract void showNextLevelMessage();
}
