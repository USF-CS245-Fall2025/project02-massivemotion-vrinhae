import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Simulation of celestial bodies (star and comets) moving across a canvas.
 * Bodies are generated randomly from edges and removed when they move off-screen.
 * Configuration is loaded from a properties file.
 * 
 * @author Valarie Trinh
 */
public class MassiveMotion extends JPanel implements ActionListener {

    protected Timer tm;
    protected String list;
    protected int bodySize;
    protected int windowSizeX;
    protected int windowSizeY;
    protected double genX;
    protected double genY;
    protected int bodyVelocity;
    protected int starPositionX;
    protected int starPositionY;
    protected int starSize;
    protected int starVelocityX;
    protected int starVelocityY;

    private List<CelestialBody> bodies;

    /**
     * Constructs a MassiveMotion simulation by loading configuration from the specified file.
     * Initializes the timer, creates the appropriate List implementation, and adds the star.
     * @param propfile path to the properties configuration file
     */
    public MassiveMotion(String propfile) {
        Properties props = new Properties();

        try(FileInputStream file = new FileInputStream(propfile)) {
            props.load(file);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        tm = new Timer(Integer.parseInt(props.getProperty("timerDelay")), this);
        list = props.getProperty("list");
        windowSizeX = Integer.parseInt(props.getProperty("windowSizeX"));
        windowSizeY = Integer.parseInt(props.getProperty("windowSizeY"));
        genX = Double.parseDouble(props.getProperty("genX"));
        genY = Double.parseDouble(props.getProperty("genY"));
        bodySize = Integer.parseInt(props.getProperty("bodySize"));
        bodyVelocity = Integer.parseInt(props.getProperty("bodyVelocity"));
        starPositionX = Integer.parseInt(props.getProperty("starPositionX"));
        starPositionY = Integer.parseInt(props.getProperty("starPositionY"));
        starSize = Integer.parseInt(props.getProperty("starSize"));
        starVelocityX = Integer.parseInt(props.getProperty("starVelocityX"));
        starVelocityY = Integer.parseInt(props.getProperty("starVelocityY"));

        //instantiate the appropriate List implementation based on config
        if(list.equals("arraylist")){
            bodies = new ArrayList<>();
        } else if(list.equals("single")){
            bodies = new LinkedList<>();
        } else if(list.equals("double")){
            bodies = new DoublyLinkedList<>();   
        } else if(list.equals("dummyhead")){
            bodies = new DummyHeadLinkedList<>();
        }

        CelestialBody star = new CelestialBody(starPositionX, starPositionY, starVelocityX, starVelocityY, starSize, Color.RED);
        bodies.add(star);
    }

    /**
     * Paints all celestial bodies in the simulation to the canvas.
     * Called automatically by Swing to render the graphics.
     * @param g the Graphics context used for painting
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int i = 0; i < bodies.size(); i++){
            CelestialBody body = bodies.get(i);
            g.setColor(body.getColor());
            g.fillOval((int)body.getX(), (int)body.getY(), body.getSize(), body.getSize());
        }

        tm.start();
    }

    /**
     * Called each time the timer fires. Generates new bodies randomly,
     * Updates positions of all bodies, and removes bodies that move off-screen.
     * @param actionEvent the event triggered by the timer
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        CelestialBody comet;

        //generate body from top/bottom
        if(Math.random() < genX){
            int velocityX = (int)(Math.random() * (2 * bodyVelocity + 1)) - bodyVelocity;
            if (velocityX == 0) velocityX = Math.random() < 0.5 ? -1 : 1;

            int velocityY = (int)(Math.random() * (2 * bodyVelocity + 1)) - bodyVelocity;
            if (velocityY == 0) velocityY = Math.random() < 0.5 ? -1 : 1;
            
            if(Math.random() < 0.5){
                comet = new CelestialBody(windowSizeX * Math.random(), 0, velocityX, velocityY, bodySize, Color.BLACK);
            } else{
                comet = new CelestialBody(windowSizeX * Math.random(), windowSizeY, velocityX, velocityY, bodySize, Color.BLACK);
            }
            bodies.add(comet);
        }
        
        //generate body from left/right
        if(Math.random() < genY){
            //generate random velocities in range [-bodyVelocity, +bodyVelocity], excluding 0
            int velocityX = (int)(Math.random() * (2 * bodyVelocity + 1)) - bodyVelocity;
            if (velocityX == 0) velocityX = Math.random() < 0.5 ? -1 : 1;

            int velocityY = (int)(Math.random() * (2 * bodyVelocity + 1)) - bodyVelocity;
            if (velocityY == 0) velocityY = Math.random() < 0.5 ? -1 : 1;
            
            //50% chance for top edge, 50% for bottom edge
            if(Math.random() < 0.5){
                comet = new CelestialBody(0, windowSizeY * Math.random(), velocityX, velocityY, bodySize, Color.BLACK);
            } else{
                comet = new CelestialBody(windowSizeX, windowSizeY * Math.random(), velocityX, velocityY, bodySize, Color.BLACK);
            }
            bodies.add(comet);
        }

        //update each body's position
        for(int i = 0; i < bodies.size(); i++){
            bodies.get(i).move();
        }

        //remove bodies that have moved off-screen (iterate backwards to avoid index issues)
        for(int i = bodies.size() - 1; i >= 0; i--){
            CelestialBody body = bodies.get(i);
            //check if off-screen
            if(body.getX() < 0 || body.getX() > windowSizeX || body.getY() < 0 || body.getY() > windowSizeY){
                bodies.remove(i);
            }
        }

        repaint();
    }

    /**
     * Main entry point for the Massive Motion simulation.
     * Validates command-line arguments, loads configuration, and launches the GUI.
     * @param args command-line arguments; args[0] should be the configuration file path
     */
    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("Please include a file.");
            return;
        }

        System.out.println("Massive Motion starting...");
        MassiveMotion mm = new MassiveMotion(args[0]);

        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(mm.windowSizeX, mm.windowSizeY);
        jf.add(mm);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
