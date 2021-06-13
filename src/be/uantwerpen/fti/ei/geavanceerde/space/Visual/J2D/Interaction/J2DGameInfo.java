package be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Interaction;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction.GameInfo;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.J2DEngine;

import java.awt.*;

/**
 * Class to give the user info about the game
 */
public class J2DGameInfo extends GameInfo {

    /**
     * Update the score in the J2D frame
     * @param score the current score
     */
    @Override
    public void updateScore(int score) {
        Graphics2D g = J2DEngine.getInstance().getG2d();
        String text = "Score: " + score;
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (J2DEngine.getInstance().getSize()*0.4)));
        FontMetrics metrics = g.getFontMetrics();
        g.setColor(new Color(255, 255, 255));
        g.drawString(text, 20, J2DEngine.getInstance().screenHeight - 2*metrics.getHeight());
    }

    /**
     * Show a game over message in the J2D frame (won)
     */
    @Override
    public void showGameOverWin() {
        String text = "You won!";
        Graphics2D g = J2DEngine.getInstance().getG2d();
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (J2DEngine.getInstance().getSize()*0.8)));
        FontMetrics metrics = g.getFontMetrics();
        g.setColor(new Color(0, 255, 0));
        int x = (J2DEngine.getInstance().screenWidth/2) - metrics.stringWidth(text)/2;
        int y = (J2DEngine.getInstance().screenHeight/2);
        g.drawString(text, x, y);
    }

    /**
     * Show a game over message in the J2D frame (lost)
     */
    @Override
    public void showGameOverLose() {
        String text = "You lost!";
        Graphics2D g = J2DEngine.getInstance().getG2d();
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (J2DEngine.getInstance().getSize()*0.8)));
        FontMetrics metrics = g.getFontMetrics();
        g.setColor(new Color(255, 0, 0));
        int x = (J2DEngine.getInstance().screenWidth/2) - metrics.stringWidth(text)/2;
        int y = (J2DEngine.getInstance().screenHeight/2);
        g.drawString(text, x, y);
    }

    /**
     * Show a message the start a new level in the J2D frame
     */
    @Override
    public void showNextLevelMessage() {
        String text = "Press space to continue";
        Graphics2D g = J2DEngine.getInstance().getG2d();
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (J2DEngine.getInstance().getSize()*0.4)));
        FontMetrics metrics = g.getFontMetrics();
        g.setColor(new Color(255, 255, 255));
        int x = (J2DEngine.getInstance().screenWidth/2) - metrics.stringWidth(text)/2;
        int y = (J2DEngine.getInstance().screenHeight/2);
        g.drawString(text, x, y);
    }

    /**
     * Show a start up message in the J2D frame
     * @param isPropertyLoaded is a property file loaded correctly
     */
    @Override
    public void showStartMessage(boolean isPropertyLoaded) {
        String text;
        if(isPropertyLoaded)
            text = "Property file loaded correctly";
        else
            text = "Property file not loaded=>default enemies loaded";
        Graphics2D g = J2DEngine.getInstance().getG2d();
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (J2DEngine.getInstance().getSize()*0.2)));
        FontMetrics metrics = g.getFontMetrics();
        g.setColor(new Color(255, 255, 255));
        int x = (J2DEngine.getInstance().screenWidth/2) - metrics.stringWidth(text)/2;
        int y = (J2DEngine.getInstance().screenHeight/2) - metrics.getHeight();
        g.drawString(text, x, y);
        text = "Use arrow keys to move L/R";
        x = (J2DEngine.getInstance().screenWidth/2) - metrics.stringWidth(text)/2;
        y = y + metrics.getHeight();
        g.drawString(text, x, y);
        text = "Press space to shoot, Q to quit";
        x = (J2DEngine.getInstance().screenWidth/2) - metrics.stringWidth(text)/2;
        y = y + metrics.getHeight();
        g.drawString(text, x, y);
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (J2DEngine.getInstance().getSize()*0.4)));
        metrics = g.getFontMetrics();
        text = "Press a key to start";
        x = (J2DEngine.getInstance().screenWidth/2) - metrics.stringWidth(text)/2;
        g.drawString(text, x, y + metrics.getHeight());
    }
}
