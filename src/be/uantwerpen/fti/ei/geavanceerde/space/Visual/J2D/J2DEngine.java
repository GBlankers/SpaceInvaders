package be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D;

import be.uantwerpen.fti.ei.geavanceerde.space.Visual.Engine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Java 2D engine to visualise the game
 */
public class J2DEngine extends Engine {
    /**
     * Singleton pattern to make sure there is only 1 running J2D engine
     */
    private static J2DEngine uniqueEngine;

    // game coordinates
    public static int gameWidth;
    public static int gameHeight;

    // main panel and frame
    private final JFrame mainFrame;
    private final JPanel gamePanel;

    // size of the main frame
    public int screenWidth;
    public int screenHeight;

    // g2d class to draw to
    private Graphics2D g2d;

    // game images
    private BufferedImage g2dImage;
    public BufferedImage imagePlayerShip;
    public BufferedImage enemySprite;
    public BufferedImage playerBullet;
    public BufferedImage backgroundImage;
    public BufferedImage healthImage;
    public BufferedImage enemyBullet;

    public int sizeX;
    public int sizeY;
    private int size;
    private int imageSize;

    /**
     * constructor to initialise the screen width and height through a property file.
     * And initialise the mainPanel and frame.
     * Private constructor for the singleton pattern
     */
    private J2DEngine() {
        super();

        try{
            Scanner propReader = new Scanner(new File("res/J2DProperties.txt"));
            // The property file has to be structured indicated by propertyVisualStructure.txt
            String data = propReader.nextLine();
            List<String> str = Arrays.asList(data.split(","));
            this.screenWidth = Integer.parseInt(str.get(1));
            this.screenHeight = Integer.parseInt(str.get(2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Default values
            this.screenWidth = 1000;
            this.screenHeight = 1000;
        }

        mainFrame = new JFrame();
        gamePanel = new GamePanel();
    }

    /**
     * Singleton pattern
     * @return the only J2DEngine instance
     */
    public static J2DEngine getInstance() {
        if (uniqueEngine == null) {
            uniqueEngine = new J2DEngine();
        }
        return uniqueEngine;
    }

    /**
     * Start the J2D engine. This includes setting up the main frame and game panel
     */
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

    /**
     * Get method for the g2d object
     * @return the g2d object
     */
    public Graphics2D getG2d() {
        return g2d;
    }

    /**
     * Get method for the main frame
     * @return the main frame
     */
    public JFrame getFrame() {
        return mainFrame;
    }

    /**
     * Get method for the size variable. This variable will be used to scale the game coordinates to visual coordinates
     * @return the size variable
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Get method for the size of the images. This is used to crop the enemy sprite image
     * @return the size of the images
     */
    @Override
    public int getImageSize(){ return imageSize; }

    /**
     * Render the game panel
     */
    @Override
    public void render(){
        gamePanel.repaint();
    }

    /**
     * Change the visual dimensions with respect to the game dimensions
     * @param gameWidth the game width
     * @param gameHeight the game height
     */
    @Override
    public void setGameDimensions(int gameWidth, int gameHeight){
        J2DEngine.gameWidth = gameWidth;
        J2DEngine.gameHeight = gameHeight;
        sizeX = screenWidth/gameWidth;
        sizeY = screenHeight/gameHeight;
        this.size = Math.min(sizeX, sizeY);
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

    /**
     * Load all the images for the entities
     */
    private void loadImages() {
        try {
            imagePlayerShip = ImageIO.read(new File("res/playerShip.png"));
            enemySprite = ImageIO.read(new File("res/alienSprite.png"));
            playerBullet = ImageIO.read(new File("res/playerBullet.png"));
            backgroundImage = ImageIO.read(new File("res/spaceBackground.png"));
            healthImage = ImageIO.read(new File("res/heart.png"));
            enemyBullet = ImageIO.read(new File("res/enemyBullet.png"));
        } catch (IOException e){
            System.out.println("Can't load images");
        }
    }

    /**
     * Resize the images to the right dimensions
     * @param original the original image
     * @param targetW the target width
     * @param targetH the target height
     * @return the resized image
     */
    public BufferedImage resizeImage(BufferedImage original, int targetW, int targetH){
        Image result = original.getScaledInstance(targetW, targetH, Image.SCALE_DEFAULT);
        BufferedImage output = new BufferedImage(targetW, targetH, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        output.getGraphics().drawImage(result, 0, 0, null);
        return output;
    }

    /**
     * J2D drawing method
     * @param g Graphics object
     */
    public void doDrawing(Graphics g){
        Graphics2D graphics2D = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();
        graphics2D.drawImage(g2dImage, 0, 0, null);
        graphics2D.dispose();
        if (g2d != null){
            g2d.drawImage(backgroundImage, 0, 0, null);
        }
    }

    /**
     * Class to draw the game object to
     */
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