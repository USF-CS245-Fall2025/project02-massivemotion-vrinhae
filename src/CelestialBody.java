import java.awt.Color;

/**
 * Represents a celestial body in the simulation with postion, velocity, size, and color.
 * 
 * @author Valarie Trinh
 */
public class CelestialBody {
    private double x;
    private double y;
    private int velocityX;
    private int velocityY;
    private int size;
    private Color color;

     /**
     * Constructs a celestial body with specified properties.
     * @param x initial x-coordinate position
     * @param y initial y-coordinate position
     * @param velocityX velocity in the x direction
     * @param velocityY velocity in the y direction
     * @param size radius of the celestial body
     * @param color color to render the body
     */
    public CelestialBody(double x, double y, int velocityX, int velocityY, int size, Color color) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.size = size;
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }
    
    /**
     * Updates the position of this celestial body based on its velocity.
     */
    public void move() {
        x += velocityX;
        y += velocityY;
    }

}
