package Main;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * The GameTimer class will provide a way to have a constant time per frame
 */
public class GameTimer {
    private final long timePerFrame;
    private long start, last;

    /**
     * Constructor for the Game Timer class,
     * will initialize variables
     * @param fps The wanted frames per seconds
     */
    public GameTimer(long fps){
        this.timePerFrame = TimeUnit.SECONDS.toMillis(1)/fps;
    }

    /**
     * Use the tick method at the start of the frame
     * This way we know the start point of the frame
     */
    public void tick(){
        start = System.currentTimeMillis();
    }

    /**
     * Use the tock method at the end of the frame
     * This way we know how long the frame has been running
     */
    public void tock(){
        last = System.currentTimeMillis();
    }

    /**
     * The sleep method will suspend the loop to get the desired time per frame
     * and to get the right amount of frames per second
     */
    public void sleep(){
        try {
            // If the amount of sleep is negative then we do not have to sleep
            if (timePerFrame - (last-start) > 0)
                Thread.sleep((timePerFrame - (last-start)));
        } catch (InterruptedException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
