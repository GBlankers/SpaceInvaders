package Visual.J2D;

import Visual.Engine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class J2DEngine extends Engine {
    private static J2DEngine uniqueEngine;
    public static int gameWidth;
    public static int gameHeight;

    private final JFrame mainFrame;
    private final JPanel gamePanel;

    private final int screenWidth;
    private final int screenHeight;

    private Graphics2D g2d;
    private BufferedImage g2dImage;
    public BufferedImage imagePlayerShip;
    public BufferedImage enemySprite;
    public BufferedImage playerBullet;
    public BufferedImage backgroundImage;
    public BufferedImage healthImage;
    public BufferedImage enemyBullet;

    private int size;
    private int imageSize;

    private J2DEngine() {
        super();
        this.screenWidth = 1200;
        this.screenHeight = 1000;
        mainFrame = new JFrame();
        gamePanel = new GamePanel();
    }

    public static J2DEngine getInstance() {
        if (uniqueEngine == null) {
            uniqueEngine = new J2DEngine();
        }
        return uniqueEngine;
    }

    @Override
    public void start() {
        mainFrame.setTitle("Space invaders");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(this.screenWidth, this.screenHeight);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setFocusable(true);
        gamePanel.setLayout(null);
        mainFrame.add(gamePanel);
        mainFrame.setVisible(true);
    }

    @Override
    public Graphics2D getG2d() {
        return g2d;
    }

    @Override
    public JFrame getFrame() {
        return mainFrame;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getImageSize(){ return imageSize; }

    @Override
    public double getEntitySize(){
        if (screenHeight<screenWidth){
            if (gameWidth < gameHeight)
                return (screenHeight*1.0/imageSize)/gameHeight;
            return (screenHeight*1.0/imageSize)/gameWidth;
        }

        if (gameWidth < gameHeight)
            return (screenWidth*1.0/imageSize)/gameHeight;
        return (screenWidth*1.0/imageSize)/gameWidth;
    }

    @Override
    public void render(){
        gamePanel.repaint();
    }

    @Override
    public void setGameDimensions(int gameWidth, int gameHeight){
        J2DEngine.gameWidth = gameWidth;
        J2DEngine.gameHeight = gameHeight;
        this.size = Math.min(screenWidth/gameWidth, screenHeight/gameHeight);
        this.imageSize = (int) (size*0.8);
        loadImages();
        try {
            backgroundImage = resizeImage(backgroundImage, mainFrame.getWidth(), mainFrame.getHeight());
            imagePlayerShip = resizeImage(imagePlayerShip, imageSize, imageSize);
            enemySprite = resizeImage(enemySprite, imageSize*7, imageSize);
            playerBullet = resizeImage(playerBullet, (int) (imageSize*0.4), (int) (imageSize*0.8));
            healthImage = resizeImage(healthImage, (int) (imageSize*0.4), (int) (imageSize*0.4));
            enemyBullet = resizeImage(enemyBullet, (int) (imageSize*0.6), (int) (imageSize*0.6));
        } catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        g2dImage = new BufferedImage(mainFrame.getWidth(), mainFrame.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        g2d = g2dImage.createGraphics();
        g2d.drawImage(backgroundImage, 0, 0, null);
    }

    private void loadImages() {
        try {
            imagePlayerShip = ImageIO.read(new File("res/playerShip2.png"));
            enemySprite = ImageIO.read(new File("res/alienSprite.png"));
            playerBullet = ImageIO.read(new File("res/playerBullet.png"));
            backgroundImage = ImageIO.read(new File("res/spaceBackground.png"));
            healthImage = ImageIO.read(new File("res/heart.png"));
            enemyBullet = ImageIO.read(new File("res/enemyBullet.png"));
        } catch (IOException e){
            System.out.println("Can't load images");
        }
    }

    public BufferedImage resizeImage(BufferedImage original, int targetW, int targetH){
        Image result = original.getScaledInstance(targetW, targetH, Image.SCALE_DEFAULT);
        BufferedImage output = new BufferedImage(targetW, targetH, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        output.getGraphics().drawImage(result, 0, 0, null);
        return output;
    }

    public void doDrawing(Graphics g){
        Graphics2D graphics2D = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();
        graphics2D.drawImage(g2dImage, 0, 0, null);
        graphics2D.dispose();
        if (g2d != null){
            g2d.drawImage(backgroundImage, 0, 0, null);
        }
    }

    class GamePanel extends JPanel {

        public GamePanel(){
            super(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            doDrawing(g);
        }
    }

}