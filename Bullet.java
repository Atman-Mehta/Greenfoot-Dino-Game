import greenfoot.*;

public class Bullet extends Actor {
    private int speed = 5; // Adjust speed as needed
    
    public void act() {
        move(speed);

        // Check for collisions with bird
        bird bird = (bird) getOneIntersectingObject(bird.class);
        if (bird != null && !isAtEdge()) {
            // Update bird hit score when bullet hits bird
            MyWorld world = (MyWorld) getWorld();
            if (world != null) {
                world.increaseBirdHitScore();
            }
            // Remove the bird and the bullet
            getWorld().removeObject(bird);
            getWorld().removeObject(this);
            return; // Exit the act() method to avoid further processing
        }

        // Now check boundaries
        checkBoundaries();
    }

    private void checkBoundaries() {
        if (isAtEdge() || getY() >= getWorld().getHeight() - 1) {
            getWorld().removeObject(this);
        }
    }
}
