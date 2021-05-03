package be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.EnemyBullet;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.EnemyShip;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.PlayerBullet;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.PlayerShip;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.AbstractFactory.AbstractFactory;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction.Input;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Input.KeyboardInput;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.Engine;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Entities.J2DEnemyBullet;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Entities.J2DEnemyShip;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Entities.J2DPlayerBullet;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Entities.J2DPlayerShip;

import java.awt.*;


public class J2DFactory extends AbstractFactory {

    @Override
    public PlayerShip createPlayerShip(double x, double y){
        return new J2DPlayerShip(x, y);
    }

    @Override
    public EnemyShip createEnemyShip(double x, double y) {
        return new J2DEnemyShip(x, y);
    }

    @Override
    public PlayerBullet createPlayerBullet(double x, double y) {
        return new J2DPlayerBullet(x, y);
    }

    @Override
    public EnemyBullet createEnemyBullet(double x, double y) {
        return new J2DEnemyBullet(x, y);
    }

    @Override
    public Engine createEngine() {
        return J2DEngine.getInstance();
    }

    @Override
    public void engineRender() {
        J2DEngine.getInstance().render();
    }

    @Override
    public void engineStart() {
        J2DEngine.getInstance().start();
    }

    @Override
    public void engineSetGameDimensions(int width, int height) {
        J2DEngine.getInstance().setGameDimensions(width, height);
    }

    @Override
    public Input createInput() {
        return new KeyboardInput(J2DEngine.getInstance());
    }

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
    public void gameOverWin() {
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
    public void gameOverLose() {
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
    public void nextLevel() {
        String text = "Press space to continue";
        Graphics2D g = J2DEngine.getInstance().getG2d();
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (J2DEngine.getInstance().getSize()*0.4)));
        FontMetrics metrics = g.getFontMetrics();
        g.setColor(new Color(255, 255, 255));
        int x = (J2DEngine.getInstance().screenWidth/2) - metrics.stringWidth(text)/2;
        int y = (J2DEngine.getInstance().screenHeight/2);
        g.drawString(text, x, y);
    }

    @Override
    public double getEntitySize(){
        return J2DEngine.getInstance().getEntitySize();
    }
}
