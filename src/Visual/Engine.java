package Visual;

import javax.swing.*;
import java.awt.*;

public abstract class Engine {

    public abstract void start();
    public abstract Graphics2D getG2d();
    public abstract JFrame getFrame();
    public abstract int getSize();
    public abstract void render();
    public abstract void setGameDimensions(int gameWidth, int gameHeight);
}
