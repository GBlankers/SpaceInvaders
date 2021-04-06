package Components;

public class MovementComponent {
    private double x;
    private double y;

    private double dx;
    private double dy;

    public MovementComponent(){
        x = 0;
        y = 0;
        dx = 0;
        dy = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }
}
