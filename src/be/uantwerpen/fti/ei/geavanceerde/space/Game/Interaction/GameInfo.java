package be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction;

public abstract class GameInfo {

    /**
     * Show the current score
     * @param score the current score
     */
    public abstract void updateScore(int score);

    /**
     * Show a game over message when the game ended in a win
     */
    public abstract void showGameOverWin();

    /**
     * Show a game over message when the game is lost
     */
    public abstract void showGameOverLose();

    /**
     * Show a message to start a new level
     */
    public abstract void showNextLevelMessage();

    /**
     * Show a start up message
     * @param isPropertyLoaded is a property file loaded correctly
     */
    public abstract void showStartMessage(boolean isPropertyLoaded);
}
