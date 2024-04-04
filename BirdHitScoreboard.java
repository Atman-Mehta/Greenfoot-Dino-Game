import greenfoot.*;

public class BirdHitScoreboard extends Actor {
    private GreenfootImage image;

    public BirdHitScoreboard() {
        image = new GreenfootImage(200, 50);
        setImage(image);
        updateScore(0);
    }

    public void updateScore(int score) {
        image.clear();
        image.setColor(Color.WHITE);
        image.setFont(new Font("Arial", true, false, 24));
        image.drawString("Bird Hits: " + score, 20, 30);
        setImage(image);
    }
}