package Main;

import Components.MovementComponent;
import Entities.Abstract.*;
import Factories.AbstractFactory;
import Interaction.Input;
import System.MovementUpdater;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class Game {
    private final AbstractFactory abs;
    private final GameTimer gameTimer;
    private final Input input;

    private final int gameWidth = 9;
    private final int gameHeight = 7;

    private boolean isRunning = false;

    private int score = 0;

    private boolean lvls = false;
    private int lvlNr = 1;

    private final ArrayList<EnemyShip> enemies = new ArrayList<>();
    private final ArrayList<PlayerBullet> playerBullets = new ArrayList<>();
    private final ArrayList<EnemyBullet> enemyBullets = new ArrayList<>();
    private PlayerShip playerShip;

    private File properties;

    public Game(AbstractFactory abs, File properties){
        this.abs = abs;
        abs.createEngine();
        this.input = abs.createInput();
        this.gameTimer = new GameTimer(16);
        this.properties = properties;
    }

    /**
     * Initialise the engine, entities, render the screen and start the main loop
     */
    public void start(){
        abs.engineStart();

        try {
            Scanner propReader = new Scanner(properties);
            String data = propReader.nextLine();
            List<String> str = Arrays.asList(data.split(","));

            abs.engineSetGameDimensions(Integer.parseInt(str.get(0)), Integer.parseInt(str.get(1)));
            if(propReader.nextLine().equals("lvl1")){
                lvls = true;
                while(propReader.hasNext()){
                    data = propReader.nextLine();
                    if(data.equals("Q"))
                        break;
                    str = Arrays.asList(data.split(","));
                    enemies.add(abs.createEnemyShip(Integer.parseInt(str.get(0)), Integer.parseInt(str.get(1))));
                }
            }

        } catch (FileNotFoundException e){
            System.out.println(Arrays.toString(e.getStackTrace()));

            // x:[-4;4] y:[-3;3]
            abs.engineSetGameDimensions(gameWidth, gameHeight);

            // Default values if the property file cannot be found
            enemies.add(abs.createEnemyShip(-3, -3));
            enemies.add(abs.createEnemyShip(-2, -3));
            enemies.add(abs.createEnemyShip(-1, -3));
            enemies.add(abs.createEnemyShip(0, -3));
            enemies.add(abs.createEnemyShip(1, -3));
            enemies.add(abs.createEnemyShip(2, -3));
            enemies.add(abs.createEnemyShip(3, -3));
            enemies.add(abs.createEnemyShip(-2, -2));
            enemies.add(abs.createEnemyShip(-1, -2));
            enemies.add(abs.createEnemyShip(0, -2));
            enemies.add(abs.createEnemyShip(1, -2));
            enemies.add(abs.createEnemyShip(2, -2));
        }

        playerShip = abs.createPlayerShip();

        // Make the entities visible
        abs.engineRender();
        // Execute the main loop
        run();
    }

    /**
     * Main Game loop
     */
    public void run(){
        isRunning = true;
        Input.Inputs direction;

        while (isRunning){
            // Start timer
            gameTimer.tick();

            if(enemies.isEmpty()){
                if(!lvls){
                    abs.gameOverWin();
                    isRunning = false;
                } else {
                    lvlNr ++;
                    loadNewLevel();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        System.out.println(e.getMessage());
                    }
                }
            }

            if (playerShip.getHealth() == 0){
                abs.gameOverLose();
                isRunning = false;
            }

            // default movement = stay still
            playerShip.setDirection(0, 0);

            if(input.inputAvailable()){
                direction = input.getInput();
                if(direction == Input.Inputs.LEFT){
                    if(playerShip.getMovementComponent().getX() > 0) {
                        playerShip.setDirection(-0.4, 0);
                    }
                } else if(direction == Input.Inputs.RIGHT){
                    if(playerShip.getMovementComponent().getX() < gameWidth-1){
                        playerShip.setDirection(0.4, 0);
                    }
                } else if(direction == Input.Inputs.SHOOT){
                    playerBullets.add(abs.createPlayerBullet(playerShip.getMovementComponent().getX(), playerShip.getMovementComponent().getY()));
                } else if(direction == Input.Inputs.STOP){
                    isRunning = false;
                    System.exit(0);
                }
            }

            // Set the direction of the enemies
            enemyMovement();
            // Visualise the entities
            Stream.concat(Stream.concat(Stream.concat(playerBullets.stream(), enemies.stream()), Stream.of(playerShip)), enemyBullets.stream())
                    .forEach(Entity::visualise);
            // Move the entities
            Stream.concat(Stream.concat(Stream.concat(playerBullets.stream(), enemies.stream()), Stream.of(playerShip)), enemyBullets.stream())
                    .forEach(Entity -> Entity.setMovementComponent(MovementUpdater.update(Entity.getMovementComponent())));

            enemyShoot();
            checkEnemyHit();
            checkPlayerHit();
            bulletCleanUp();

            abs.updateScore(score);
            abs.engineRender();
            // end timer
            gameTimer.tock();
            // Sleep to get a constant time/frame
            gameTimer.sleep();
        }
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
        Iterator<EnemyShip> itEnemyShip;
        while (itBullet.hasNext()){
            int hit = 0;
            MovementComponent mcBullet = itBullet.next().getMovementComponent();
            itEnemyShip = enemies.listIterator();
            while (itEnemyShip.hasNext()){
                MovementComponent mcEnemy = itEnemyShip.next().getMovementComponent();
                if (isInRange(mcEnemy, mcBullet)){
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

        while(itBullet.hasNext()){
            MovementComponent mcBullet = itBullet.next().getMovementComponent();
            if(isInRange(playerShip.getMovementComponent(), mcBullet)){
                playerShip.hit();
                score --;
                itBullet.remove();
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
                abs.gameOverLose();
                break;
            }
            if(eps.getMovementComponent().getX() <= 0 & eps.getMovementComponent().getDx() < 0){
                dx = 0;
                dy = 0.2;
                change = true;
                break;
            }else if(eps.getMovementComponent().getX() <= 0 & eps.getMovementComponent().getDx() == 0){
                dx = 0.07;
                dy = 0;
                change = true;
                break;
            }else if(eps.getMovementComponent().getX() >= gameWidth - 1 & eps.getMovementComponent().getDx() > 0){
                dx = 0;
                dy = 0.2;
                change = true;
                break;
            }else if(eps.getMovementComponent().getX() >= gameWidth -1 & eps.getMovementComponent().getDx() == 0){
                dx = -0.07;
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
     * @return boolean corresponding to a hit or not
     */
    public boolean isInRange(MovementComponent mcTarget, MovementComponent mcBullet){
        double es = abs.getEntitySize()*0.4;
        if (mcTarget.getX()- es<= mcBullet.getX() && mcBullet.getX() <= mcTarget.getX()+es){
            return mcTarget.getY() - es <= mcBullet.getY() && mcBullet.getY() <= mcTarget.getY()+es;
        }
        return false;
    }

    /**
     * Chose an enemy to shoot a bullet
     */
    public void enemyShoot(){
        if(!enemies.isEmpty()){
            int randomNum = ThreadLocalRandom.current().nextInt(0, 13 + 1);

            if (randomNum==4){
                Collections.shuffle(enemies);
                MovementComponent mcE = enemies.get(0).getMovementComponent();
                enemyBullets.add(abs.createEnemyBullet(mcE.getX(), mcE.getY()));
            }
        }
    }

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
