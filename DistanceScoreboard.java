import greenfoot.GreenfootImage;
import greenfoot.Actor;
import greenfoot.Font;
import greenfoot.Color;


public class DistanceScoreboard extends Actor {
    private GreenfootImage image;
    private int distance;

    public DistanceScoreboard() {
        image = new GreenfootImage(200, 50);
        setImage(image);
    }

    public void addedToWorld(MyWorld world) {
        updateScore(0); // Call updateScore here once the object is added to the world
    }

    public void updateScore(int distance) {
        this.distance = distance;
        MyWorld world = (MyWorld) getWorld();
        if (world != null && world.isGameOver()) {
            // Game is over, stop updating the score
            image.setColor(Color.RED);
        } else {
            image.setColor(Color.WHITE);
        }
        image.clear();
        image.setFont(new Font("Arial", true, false, 24));
        image.drawString("Distance: " + distance, 20, 30);
        setImage(image);
    }
}
