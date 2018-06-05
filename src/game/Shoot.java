package game;

public class Shoot extends GameObject
{
    private static final int SIZEX = 5;
    private static final int SIZEY = 20;
    private static final float SPEED = 4f;
    private String type;
    private int dmg;
    
    public Shoot (float x , float y, String type)
    {
        this.x = x;
        this.y = y;
        this.sx = SIZEX;
        this.sy = SIZEY;
        this.type=type;
        this.life =1;
        this.dmg = 1;
    }
    
    
@Override
    public void update()
    {
	if(type.equalsIgnoreCase("Heroe")){
        y += SPEED;}
	if(type.equalsIgnoreCase("enemigo")){
        y -= SPEED;}
    }
    
}
