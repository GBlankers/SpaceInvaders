package Main;

import Factories.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        AbstractFactory f = new J2DFactory();
        Game g = new Game(f);
        g.start();
    }
}
