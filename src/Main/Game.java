package Main;

import Components.MovementComponent;
import Entities.Abstract.EnemyShip;
import Entities.Abstract.PlayerBullet;
import Entities.Abstract.PlayerShip;
import Factories.AbstractFactory;
import Interaction.Input;
import System.MovementUpdater;

import java.util.ArrayList;
import java.util.Iterator;

public class Game {
    private final AbstractFactory abs;
    private final GameTimer gameTimer;
    private final Input input;

    private final int gameWidth = 9;
    private final int gameHeight = 7;

    private boolean isRunning = false;

    private int score = 0;

    private final ArrayList<EnemyShip> enemies = new ArrayList<>();
    private final ArrayList<PlayerBullet> bullets = new ArrayList<>();
    private PlayerShip playerShip;

    public Game(AbstractFactory abs){
        this.abs = abs;
        abs.createEngine();
        this.input = abs.createInput();
        this.gameTimer = new GameTimer(10);
    }

    /**
     * Initialise the engine, entities, render the screen and start the main loop
     */
    public void start(){
        abs.engineStart();
        // x:[-4;4] y:[-3;3]
        abs.engineSetGameDimensions(gameWidth, gameHeight);

        // Create some entities for poc
        playerShip = abs.createPlayerShip();
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
        int enemySpeedDivider = 0;

        while (isRunning){
            // Start timer
            gameTimer.tick();
            // Make the player ship visible
            playerShip.visualise();

            if(enemies.isEmpty()){
                abs.gameOverWin();
                isRunning = false;
            }

            // default movement = stay still
            playerShip.setDirection(0, 0);

            if(input.inputAvailable()){
                direction = input.getInput();
                if(direction == Input.Inputs.LEFT){
                    if(playerShip.getMovementComponent().getX() > 0) {
                        playerShip.setDirection(-1, 0);
                    }
                } else if(direction == Input.Inputs.RIGHT){
                    if(playerShip.getMovementComponent().getX() < gameWidth-1){
                        playerShip.setDirection(1, 0);
                    }
                } else if(direction == Input.Inputs.SHOOT){
                    bullets.add(abs.createPlayerBullet(playerShip.getMovementComponent().getX(), playerShip.getMovementComponent().getY()));
                } else if(direction == Input.Inputs.STOP){
                    isRunning = false;
                    System.exit(0);
                }
            }

            playerShip.setMovementComponent(MovementUpdater.update(playerShip.getMovementComponent()));
            for(PlayerBullet b: bullets){
                b.visualise();
                b.setMovementComponent(MovementUpdater.update(b.getMovementComponent()));
            }

            if(enemySpeedDivider == 16){
                enemySpeedDivider = 0;
                enemyMovement();
            } else {
                enemySpeedDivider++;
            }


            for(EnemyShip e: enemies){
                e.visualise();
                if(enemySpeedDivider == 10){

                    e.setMovementComponent(MovementUpdater.update(e.getMovementComponent()));
                }
            }

            checkEnemyHit();
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
        bullets.removeIf(playerBullet -> playerBullet.getMovementComponent().getY() < 0);
    }

    /**
     * Collision detection for the player bullets
     */
    public void checkEnemyHit(){
        Iterator<PlayerBullet> itBullet = bullets.listIterator();
        Iterator<EnemyShip> itEnemyShip;
        while (itBullet.hasNext()){
            int hit = 0;
            MovementComponent mcBullet = itBullet.next().getMovementComponent();
            itEnemyShip = enemies.listIterator();
            while (itEnemyShip.hasNext()){
                MovementComponent mcEnemy = itEnemyShip.next().getMovementComponent();
                if (mcEnemy.getX() == mcBullet.getX() && mcBullet.getY()==mcEnemy.getY()){
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
     * Change the dx and dy for the whole group of enemies so they change direction as a group,
     * also check that the enemies are not to close to the player as this will stop the game
     */
    public void enemyMovement(){
        boolean change = false;
        double dx = 0;
        double dy = 0;
        for(EnemyShip eps: enemies){
            if(eps.getMovementComponent().getY() == gameHeight-2){
                isRunning = false;
                abs.gameOverLose();
                break;
            }
            if(eps.getMovementComponent().getX() == 0 & eps.getMovementComponent().getDx() == -1){
                dx = 0;
                dy = 1;
                change = true;
                break;
            }else if(eps.getMovementComponent().getX() == 0 & eps.getMovementComponent().getDx() == 0){
                dx = 1;
                dy = 0;
                change = true;
                break;
            }else if(eps.getMovementComponent().getX() == gameWidth-1 & eps.getMovementComponent().getDx() == 1){
                dx = 0;
                dy = 1;
                change = true;
                break;
            }else if(eps.getMovementComponent().getX() == gameWidth-1 & eps.getMovementComponent().getDx() == 0){
                dx = -1;
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

}
