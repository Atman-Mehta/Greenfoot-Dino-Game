import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class MyWorld extends World
{
    private int counter = 0;
    private int counterDif = 0;
    private int counterGround = 0;
    private int worldSpeed = 50;
    private int random;
    static public int difficulty = 0;
    private boolean startTime = true;
    private int score = 0;
    private int distanceTraveledScore = 0;
    private int birdHitScore = 0;
    private boolean gameOver = false; // Declare and initialize gameOver variable

    public boolean isGameOver() {
        return gameOver;
    }   
    
    public void act(){
        if (!gameOver) { // Add a condition to check if the game is not over
        // Update distance traveled score
            distanceTraveledScore++;
        }
        // Check for collisions between dino and cactus/bird
        checkCollisions();

        counter++;
        counterDif++;
        counterGround++;
         // Update scoreboards
        updateScoreboards();
        if (startTime){
            difficulty = 0;
            if (counter >= 180){
                counter = 0;
                counterGround = 0;
                random = Greenfoot.getRandomNumber(15);
                startTime = false;
                addObject(new cactus(), 900,280);
            }
        } else if (counter >= (90+random)-(int)(1.7*(double)difficulty)){
            counter = 0;
            random = Greenfoot.getRandomNumber(15);
            if (Greenfoot.getRandomNumber(3) == 0){
                addObject(new bird(), 900,Greenfoot.getRandomNumber(190)+80);
            } else{
                addObject(new cactus(), 900,280);
            }
        }
                
        if (counterDif >= 10){
            difficulty++;
            if (difficulty > 5){
                difficulty = 7;
            }
            counterDif = 0;
        }
    }

    public MyWorld()
    {    
        super(900, 400, 1); 
        prepare();
        setBackground("background.jpg");
        Greenfoot.setSpeed(worldSpeed);
        // Add scoreboard
        addObject(new score(), 100, 50);
                // Add distance traveled scoreboard on left upper corner
        addObject(new DistanceScoreboard(), 100, 50);

        // Add bird hit scoreboard on right upper corner
        addObject(new BirdHitScoreboard(), getWidth() - 100, 50);
    }    
    
    public void increaseScore() {
        score++;
        updateScoreboards();
    }

    private void updateScoreboards() {
        DistanceScoreboard distanceScoreboard = (DistanceScoreboard) getObjects(DistanceScoreboard.class).get(0);
        BirdHitScoreboard birdHitScoreboard = (BirdHitScoreboard) getObjects(BirdHitScoreboard.class).get(0);
        distanceScoreboard.updateScore(distanceTraveledScore);
        birdHitScoreboard.updateScore(birdHitScore);
    }


    public void increaseBirdHitScore() {
        birdHitScore++;
        updateBirdHitScoreboard();
    }

    private void updateBirdHitScoreboard() {
        BirdHitScoreboard scoreboard = (BirdHitScoreboard) getObjects(BirdHitScoreboard.class).get(0);
        scoreboard.updateScore(birdHitScore);
    }

    private void updateDistanceScoreboard() {
        DistanceScoreboard scoreboard = (DistanceScoreboard) getObjects(DistanceScoreboard.class).get(0);
        scoreboard.updateScore(distanceTraveledScore);
    }
    
    private void prepare()
    {
        dino dino = new dino();
        moon moon = new moon();
        addObject(dino,70,279);
        addObject(moon,724,112);
        addObject(new score(), 820, 35);
        addObject(new ground(), 376, 303);
        addObject(new ground(), 800, 303);
        for (int i=0; i<5; i++){
            addObject(new star(),Greenfoot.getRandomNumber(900), Greenfoot.getRandomNumber(170)+50);
        }
        for (int i=0; i<2; i++){
            addObject(new cloud(),Greenfoot.getRandomNumber(900), Greenfoot.getRandomNumber(170)+50);
        }
        setPaintOrder(gameOver.class,BirdHitScoreboard.class, DistanceScoreboard.class, dino.class, score.class, bird.class, cactus.class,Bullet.class, 
                       ground.class, cloud.class, moon.class, star.class);
    }
    
    private void checkCollisions() 
    {
        // Get a reference to the dino
        dino dino = (dino) getObjects(dino.class).get(0);

        // Check for collision with cacti
        List<cactus> cacti = getObjects(cactus.class);
        for (cactus cactus : cacti) {
            if (isCollision(dino, cactus)) {
                setGameOver(true);
                return; // Exit method if collision detected
            }
        }

        // Check for collision with birds
        List<bird> birds = getObjects(bird.class);
        for (bird bird : birds) {
            if (isCollision(dino, bird)) {
                setGameOver(true);
                return; // Exit method if collision detected
            }
        }
}

    private boolean isCollision(Actor actor1, Actor actor2) 
    {
        // Get the bounding boxes of the actors
        int ax1 = actor1.getX();
        int ay1 = actor1.getY();
        int ax2 = ax1 + actor1.getImage().getWidth() - 1;
        int ay2 = ay1 + actor1.getImage().getHeight() - 1;

        int bx1 = actor2.getX();
        int by1 = actor2.getY();
        int bx2 = bx1 + actor2.getImage().getWidth() - 1;
        int by2 = by1 + actor2.getImage().getHeight() - 1;

        // Check for overlap of bounding boxes
        return (ax1 <= bx2 && ax2 >= bx1 && ay1 <= by2 && ay2 >= by1);
    }

    
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
