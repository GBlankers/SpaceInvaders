package be.uantwerpen.fti.ei.geavanceerde.space.Game.Components;

/**
 * Class to represent the coordinates and movement of the entities
 */
public class MovementComponent {
    /**
     * x Coordinate
     */
    private double x;
    /**
     * y coordinate
     */
    private double y;

    /**
     * Speed in the x direction
     */
    private double dx;
    /**
     * Speed in the y direction
     */
    private double dy;

    /**
     * Default constructor, initialise everything 0
     */
    public MovementComponent(){
        x = 0;
        y = 0;
        dx = 0;
        dy = 0;
    }

    /**
     * get the x coordinate of the entity
     * @return the x coordinate of the entity
     */
    public double getX() {
        return x;
    }

    /**
     * get the y coordinate of the entity
     * @return the y coordinate of the entity
     */
    public double getY() {
        return y;
    }

    /**
     * get the speed in the x direction
     * @return the speed in the x direction
     */
    public double getDx() {
        return dx;
    }

    /**
     * get the speed in the y direction
     * @return the speed in the y direction
     */
    public double getDy() {
        return dy;
    }

    /**
     * Set the x coordinate of the entity
     * @param x the x coordinate of the entity
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Set the y coordinate of the entity
     * @param y the y coordinate of the entity
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Set the speed in the x direction
     * @param dx the speed in the x direction
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Set the speed in the y direction
     * @param dy the speed in the y direction
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
}
