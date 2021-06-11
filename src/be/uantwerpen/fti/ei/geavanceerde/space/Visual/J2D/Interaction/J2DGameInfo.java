package be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Interaction;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction.GameInfo;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.J2DEngine;

import java.awt.*;

public class J2DGameInfo extends GameInfo {

    @Override
    public void updateScore(int score) {
        Graphics2D g = J2DEngine.getInstance().getG2d();
        String text = "Score: " + score;
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (J2DEngine.getInstance().getSize()*0.4)));
        FontMetrics metrics = g.getFontMetrics();
        g.setColor(new Color(255, 255, 255));
        g.drawString(text, 20, J2DEngine.getInstance().screenHeight - 2*metrics.getHeight());
    }

    @Override
    public void showGameOverWin() {
        String text = "You Win!";
        Graphics2D g = J2DEngine.getInstance().getG2d();
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (J2DEngine.getInstance().getSize()*0.8)));
        FontMetrics metrics = g.getFontMetrics();
        g.setColor(new Color(0, 255, 0));
        int x = (J2DEngine.getInstance().screenWidth/2) - metrics.stringWidth(text)/2;
        int y = (J2DEngine.getInstance().screenHeight/2);
        g.drawString("You win!", x, y);
    }

    @Override
    public void showGameOverLose() {
        String text = "You Lose!";
        Graphics2D g = J2DEngine.getInstance().getG2d();
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (J2DEngine.getInstance().getSize()*0.8)));
        FontMetrics metrics = g.getFontMetrics();
        g.setColor(new Color(255, 0, 0));
        int x = (J2DEngine.getInstance().screenWidth/2) - metrics.stringWidth(text)/2;
        int y = (J2DEngine.getInstance().screenHeight/2);
        g.drawString(text, x, y);
    }

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
}
