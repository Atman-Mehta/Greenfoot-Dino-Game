import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class noInternet extends Actor
{
static public boolean mainmenu = true;

public noInternet(){
    setImage("no-internet.png");
}

public void act() 
{
    if(mainmenu){
            setImage("no-internet.png");
            dino dino = new dino();
            if(Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("w")){
                dino.alive = true;
                Greenfoot.setWorld(new MyWorld());
            }
        }
    }    
}
