package be.uantwerpen.fti.ei.geavanceerde.space.Game.Main;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.Components.MovementComponent;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.AbstractFactory.AbstractFactory;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction.EngineControl;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction.GameInfo;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction.Input;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.System.MovementUpdater;
import be.uantwerpen.fti.ei.geavanceerde.space.Game.AstractEntities.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/**
 * The main game class containing al the game logic, game loop, collision detection, ...
 */
public class Game {
    // Abstract factory to create entities, control the visual settings, ...
    private final AbstractFactory abs;
    // Game timer to control the time each frame takes
    private final GameTimer gameTimer;
    // Input to control the player ship
    private final Input input;
    // Game info control
    private final GameInfo gameInfo;
    // Engine control
    private final EngineControl engineControl;

    // Default
    // GameWidth => how many entities can be next to each other
    private int gameWidth = 9;
    // GameHeight => how many entities can be above each other
    private int gameHeight = 7;
    // frames per second
    private int fps = 16;

    // Main loop control
    private boolean isRunning = false;

    // Player score
    private int score = 0;

    // Are there levels
    private boolean lvls = false;
    // Which level is running
    private int lvlNr = 1;

    // instance variable to have control over the speed of the entities
    // This way the speed can be altered if the fps of game size is changed
    private double speedPlayer;
    private double speedEnemyX;
    private double speedEnemyY;

    // Containers to keep track of all the entities on screen
    private final ArrayList<EnemyShip> enemies = new ArrayList<>();
    private final ArrayList<PlayerBullet> playerBullets = new ArrayList<>();
    private final ArrayList<EnemyBullet> enemyBullets = new ArrayList<>();
    private final ArrayList<PositiveBonus> positiveBonuses = new ArrayList<>();
    private PlayerShip playerShip;

    // Property file
    private final File properties;

    /**
     * Constructor to initialize the game
     * @param abs abstract factory used to make entities, control the visual settings, ...
     * @param properties property file to control the settings
     */
    public Game(AbstractFactory abs, File properties){
        this.abs = abs;
        // initialise the engine
        this.engineControl = abs.createEngineControl();
        this.engineControl.createEngine();
        // create input
        this.input = abs.createInput();
        // Create game info
        this.gameInfo = abs.createGameInfo();
        // Game timer to control the frame time
        this.gameTimer = new GameTimer(fps);
        // property file for the settings
        this.properties = properties;
    }

    /**
     * Initialise the game through the property file, create entities, render the screen and start the main loop
     */
    public void start(){
        boolean propertyFileLoaded = false;
        // the engine will start and create a frame and panel
        engineControl.engineStart();

        try {
            // read the property file
            Scanner propReader = new Scanner(properties);
            // The property file has to be structured indicated by propertyStructure.txt
            String data = propReader.nextLine();
            List<String> str = Arrays.asList(data.split(","));

            gameWidth = Integer.parseInt(str.get(1));
            gameHeight = Integer.parseInt(str.get(2));

            data = propReader.nextLine();
            str = Arrays.asList(data.split(","));
            fps = Integer.parseInt(str.get(1));

            gameTimer.changeFps(fps);

            engineControl.engineSetGameDimensions(gameWidth, gameHeight);

            // Are there levels given in the property file?
            if(propReader.nextLine().equals("lvl1")){
                lvls = true;
                // read the level and create all the enemies
                while(propReader.hasNext()){
                    data = propReader.nextLine();
                    // stop condition
                    if(data.equals("Q")){
                        propertyFileLoaded = true;
                        break;
                    }
                    str = Arrays.asList(data.split(","));
                    enemies.add(abs.createEnemyShip(Integer.parseInt(str.get(0)), Integer.parseInt(str.get(1))));
                    if(Integer.parseInt(str.get(0))>gameWidth || Integer.parseInt(str.get(1))>gameHeight ||
                            Integer.parseInt(str.get(0))<0 || Integer.parseInt(str.get(1))<0){
                        enemies.clear();
                        defaultEnemyCreation();
                        //System.out.println("Tried to create an enemy outside the game => default enemies are created");
                        break;
                    }
                }
            } else {
                // if there are no levels the default enemy structure is used
                defaultEnemyCreation();
            }
        // no file found => default settings
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());

            // x:[0;9] y:[0;7]
            engineControl.engineSetGameDimensions(gameWidth, gameHeight);

            // Default values if the property file cannot be found
            defaultEnemyCreation();
        }

        // entities are spaced 1 unit from each other in game coordinates
        // Entities will take up 0.7 space => needed for collision detection
        EnemyShip.gameWidth = 0.7;
        EnemyShip.gameHeight = 0.7;
        PlayerShip.gameWidth = 0.7;
        PlayerShip.gameHeight = 0.7;

        // Speed of the entities dependent on the height of the screen and the fps
        EnemyBullet.speed = gameHeight/(fps*3.0);
        PlayerBullet.speed = -gameHeight/(fps*2.0);
        PositiveBonus.speed = gameHeight/(fps*3.0);
        speedPlayer = gameWidth/(fps*1.8);
        speedEnemyX = gameWidth/(fps*6.0);
        speedEnemyY = gameHeight/(fps*3.0);

        // create a playerShip at the bottom of the screen in the middle
        playerShip = abs.createPlayerShip(gameWidth/2.0, gameHeight-1);

        // Make the entities visible
        engineControl.engineRender();
        // Wait for input before starting the game
        while (!input.inputAvailable()){
            gameInfo.showStartMessage(propertyFileLoaded);
            engineControl.engineRender();
        }


        // Execute the main loop
        run();
    }

    /**
     * If there is no property file or this file is badly structured, make
     * a default array of enemies
     */
    private void defaultEnemyCreation() {
        enemies.add(abs.createEnemyShip(1, 0));
        enemies.add(abs.createEnemyShip(2, 0));
        enemies.add(abs.createEnemyShip(3, 0));
        enemies.add(abs.createEnemyShip(4, 0));
        enemies.add(abs.createEnemyShip(5, 0));
        enemies.add(abs.createEnemyShip(6, 0));
        enemies.add(abs.createEnemyShip(7, 0));
        enemies.add(abs.createEnemyShip(2, 1));
        enemies.add(abs.createEnemyShip(3, 1));
        enemies.add(abs.createEnemyShip(4, 1));
        enemies.add(abs.createEnemyShip(5, 1));
        enemies.add(abs.createEnemyShip(6, 1));
    }

    /**
     * Main Game loop
     */
    public void run(){
        boolean loadLevel = false;
        isRunning = true;
        Input.Inputs direction;

        while (isRunning){
            // Start timer
            gameTimer.tick();

            // Stop the game or load a new level
            if(enemies.isEmpty()){
                // Delete all the entities from screen
                enemyBullets.clear();
                playerBullets.clear();
                positiveBonuses.clear();
                if(!loadLevel){
                    // There are no levels => no enemies = game over
                    if(!lvls){
                        gameInfo.showGameOverWin();
                        isRunning = false;
                    } else { // load the new level
                        lvlNr ++;
                        // Check if there is another level in the property file
                        if(nextLevelAvailable()){
                            // speed up the enemies
                            speedEnemyY = speedEnemyY*2;
                            // show the next level text
                            gameInfo.showNextLevelMessage();
                            // new level can be loaded
                            loadLevel = true;
                        } else {
                            gameInfo.showGameOverWin();
                            isRunning = false;
                        }
                    }
                } else {
                    gameInfo.showNextLevelMessage();
                    // Wait for user input to load the level
                    if(input.inputAvailable()){
                        if(input.getInput()== Input.Inputs.SHOOT){
                            loadNewLevel();
                            loadLevel = false;
                        }
                    }
                }
            }

            // Check if the player is dead
            if (playerShip.getHealth() == 0){
                gameInfo.showGameOverLose();
                isRunning = false;
            }

            // default movement = stay still
            playerShip.setDirection(0, 0);

            // check if there is user input
            if(input.inputAvailable()){
                // Get the available input
                direction = input.getInput();

                // Change the player ship movement depending on the input or shoot or stop the game
                if(direction == Input.Inputs.LEFT){
                    if(playerShip.getMovementComponent().getX() > 0) {
                        playerShip.setDirection(-speedPlayer, 0);
                    }
                } else if(direction == Input.Inputs.RIGHT){
                    if(playerShip.getMovementComponent().getX() < gameWidth-1){
                        playerShip.setDirection(speedPlayer, 0);
                    }
                } else if(direction == Input.Inputs.SHOOT){
                    if (playerBullets.size() < 5) {
                        playerBullets.add(abs.createPlayerBullet(playerShip.getMovementComponent().getX(), playerShip.getMovementComponent().getY()));
                    }
                } else if(direction == Input.Inputs.STOP){
                    isRunning = false;
                    System.exit(0);
                }
            }

            // Set the direction of the enemies
            enemyMovement();
            // Visualise the entities
            visualiseEntities();
            // Move the entities
            executeMovement();
            // Make some enemies shoot
            enemyShoot();
            // Check if the enemies are hit by a player bullet
            checkEnemyHit();
            // Check if the player is hit by an enemy bullet
            checkPlayerHit();
            // Delete off screen bullets
            bulletCleanUp();
            // Spawn bonuses
            spawnBonus();
            // Positive bonus collision detection
            positiveBonusHit();
            // Delete off screen bonuses
            clearBonus();

            // Update the score text
            gameInfo.updateScore(score);
            // render the engine
            engineControl.engineRender();
            // end timer
            gameTimer.tock();
            // Sleep to get a constant time/frame
            gameTimer.sleep();
        }
    }

    /**
     * Call the visualise method for every entity
     */
    public void visualiseEntities(){
        Stream.concat(positiveBonuses.stream(),Stream.concat(Stream.concat(Stream.concat(playerBullets.stream(), enemies.stream()),
                Stream.of(playerShip)), enemyBullets.stream())).forEach(Entity::visualise);
    }

    /**
     * Update all the movement components of all the entities
     */
    public void executeMovement(){
        Stream.concat(positiveBonuses.stream(), Stream.concat(Stream.concat(Stream.concat(playerBullets.stream(),
                enemies.stream()), Stream.of(playerShip)), enemyBullets.stream())).forEach(Entity ->
                Entity.setMovementComponent(MovementUpdater.update(Entity.getMovementComponent())));
    }

    /**
     * Deletes the off screen bullets to free up memory
     */
    public void bulletCleanUp(){
        playerBullets.removeIf(playerBullet -> playerBullet.getMovementComponent().getY() < 0);
        enemyBullets.removeIf(enemyBullet -> enemyBullet.getMovementComponent().getY() > gameHeight);
    }

    /**
     * Collision detection for the playerBullets with the enemies
     */
    public void checkEnemyHit(){
        Iterator<PlayerBullet> itBullet = playerBullets.listIterator();
        double esX = 0;
        double esY = 0;
        if(!enemies.isEmpty()){

            esX = EnemyShip.gameWidth;
            esY = EnemyShip.gameHeight;
        }
        Iterator<EnemyShip> itEnemyShip;
        // go over all the bullets and enemies and check if there are bullets in range of an enemy
        // if this is the case, update the score and delete both the bullet and the enemy
        while (itBullet.hasNext()){
            int hit = 0;
            MovementComponent mcBullet = itBullet.next().getMovementComponent();
            itEnemyShip = enemies.listIterator();
            while (itEnemyShip.hasNext()){
                MovementComponent mcEnemy = itEnemyShip.next().getMovementComponent();
                if (isInRange(mcEnemy, mcBullet,esX, esY)){
                    hit = 1;
                    score++;
                    break;
                }
            }

            if(hit>0){
                itBullet.remove();
                itEnemyShip.remove();
            }
        }
    }

    /**
     * Collision detection for the enemyBullets with the playerShip
     */
    public void checkPlayerHit(){
        Iterator<EnemyBullet> itBullet = enemyBullets.iterator();

        // Check all the enemy bullet and see if there is one in range of the playership, if
        // this is the case, decrease the score, decrease the health and delete the bullet
        while(itBullet.hasNext()){
            MovementComponent mcBullet = itBullet.next().getMovementComponent();
            if(isInRange(playerShip.getMovementComponent(), mcBullet, PlayerShip.gameWidth, PlayerShip.gameHeight)){
                playerShip.hit();
                score --;
                itBullet.remove();
                break;
            }
        }
    }

    /**
     * Collision detection for the positive bonusses
     */
    public void positiveBonusHit(){
        Iterator<PositiveBonus> itBonus = positiveBonuses.iterator();

        // Check all the bonusses and see if there is one in range of the playership, if
        // this is the case, increase the health and delete the bonus off screen
        while(itBonus.hasNext()){
            MovementComponent mcBullet = itBonus.next().getMovementComponent();
            if(isInRange(playerShip.getMovementComponent(), mcBullet, PlayerShip.gameWidth, PlayerShip.gameHeight)){
                playerShip.regeneration();
                score ++;
                itBonus.remove();
                break;
            }
        }
    }

    /**
     * Change the dx and dy for the whole group of enemies so they change direction as a group,
     * also check that the enemies are not to close to the player as this will stop the game
     */
    public void enemyMovement(){
        boolean change = false;
        double dx = 0;
        double dy = 0;
        for(EnemyShip eps: enemies){
            if(eps.getMovementComponent().getY() >= gameHeight-2){
                isRunning = false;
                gameInfo.showGameOverLose();
                break;
            }
            if(eps.getMovementComponent().getX() <= 0 & eps.getMovementComponent().getDx() < 0){
                dx = 0;
                dy = speedEnemyY; // 0.2
                change = true;
                break;
            }else if(eps.getMovementComponent().getX() <= 0 & eps.getMovementComponent().getDx() == 0){
                dx = speedEnemyX; // 0.07
                dy = 0;
                change = true;
                break;
            }else if(eps.getMovementComponent().getX() >= gameWidth - 1 & eps.getMovementComponent().getDx() > 0){
                dx = 0;
                dy = speedEnemyY;
                change = true;
                break;
            }else if(eps.getMovementComponent().getX() >= gameWidth -1 & eps.getMovementComponent().getDx() == 0){
                dx = -speedEnemyX;
                dy = 0;
                change = true;
                break;
            }
        }

        if(change){
            double finalDx = dx;
            double finalDy = dy;
            enemies.forEach(enemyShip -> enemyShip.setMovement(finalDx, finalDy));
        }
    }

    /**
     * Check if a bullet is in range of a target
     * @param mcTarget movement component of a target entity
     * @param mcBullet movement component of the bullet
     * @param esX the x size of the entity in game coordinates
     * @param esY the y size of the entity in game coordinates
     * @return boolean corresponding to a hit or not
     */
    public boolean isInRange(MovementComponent mcTarget, MovementComponent mcBullet, double esX, double esY){
        if (mcTarget.getX()- esX<= mcBullet.getX() && mcBullet.getX() <= mcTarget.getX()+esX){
            return mcTarget.getY() - esY <= mcBullet.getY() && mcBullet.getY() <= mcTarget.getY()+esY;
        }
        return false;
    }

    /**
     * Chose an enemy to shoot a bullet
     */
    public void enemyShoot(){
        // If there are enemies and if a random condition is met, chose a random enemy to shoot a bullet
        if(!enemies.isEmpty()){
            int randomNum = ThreadLocalRandom.current().nextInt(0, (int) Math.round(fps/2.0));

            if (randomNum==4){
                Collections.shuffle(enemies);
                MovementComponent mcE = enemies.get(0).getMovementComponent();
                enemyBullets.add(abs.createEnemyBullet(mcE.getX(), mcE.getY()));
            }
        }
    }

    /**
     * spawn a new bonus
     */
    public void spawnBonus(){
        if(!enemies.isEmpty()){
            if(positiveBonuses.size()==0){
                int randomNum = ThreadLocalRandom.current().nextInt(0, 50);

                if (randomNum==4){
                    Collections.shuffle(enemies);
                    MovementComponent mcE = enemies.get(0).getMovementComponent();
                    positiveBonuses.add(abs.createPositiveBonus(mcE.getX(), mcE.getY()));
                }
            }
        }
    }

    /**
     * Delete the bonuses if they are off screen
     */
    public void clearBonus(){
        if(!positiveBonuses.isEmpty()){
            if(positiveBonuses.get(0).getMovementComponent().getY()>gameHeight){
                positiveBonuses.clear();
            }
        }
    }

    /**
     * Read the property file to check if there are new levels
     * @return boolean if there are new levels
     */
    public boolean nextLevelAvailable(){
        try {
            Scanner propScan = new Scanner(properties);
            String level = "lvl"+lvlNr;
            boolean levelAvailable = false;
            String data;

            while(propScan.hasNext()) {
                data = propScan.nextLine();
                if (data.equals(level)) {
                    levelAvailable = true;
                    break;
                }
            }
            return levelAvailable;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Load the enemies to start a new level
     */
    public void loadNewLevel(){
        try{
            Scanner propScan = new Scanner(properties);
            String level = "lvl"+lvlNr;
            boolean levelAvailable = false;
            String data;
            List<String> str;

            while(propScan.hasNext()){
                data = propScan.nextLine();
                if(data.equals(level)){
                    levelAvailable = true;
                    data = propScan.nextLine();
                }

                if(levelAvailable){
                    if(data.equals("Q"))
                        break;
                    str = Arrays.asList(data.split(","));
                    enemies.add(abs.createEnemyShip(Integer.parseInt(str.get(0)), Integer.parseInt(str.get(1))));
                }
                isRunning = levelAvailable;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            isRunning = false;
        }
    }
}
