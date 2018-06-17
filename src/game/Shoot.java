package game;



public class Shoot extends GameObject
{
    private static final int SIZEX = 2;
    private static final int SIZEY = 2;
    private static final float SPEED = 11f;
    private String type;
    private int dmg;
    
    public Shoot (float x , float y, String type, float rot1)
    {
        this.x = x;
        this.y = y;
        this.sx = SIZEX;
        this.sy = SIZEY;
        this.type=type;
        this.life =1;
        this.dmg = 1;
        this.rot = rot1;
    }
    
    
@Override
    public void update()
    {
	if(type.equalsIgnoreCase("Heroe")){
            y += SPEED* Math.sin(Math.toRadians(rot));
            x += SPEED*Math.cos(Math.toRadians(rot));
        }
        
	if(type.equalsIgnoreCase("enemigo")){
        y -= SPEED;
        x += SPEED;
        }
    }
    @Override
    public void render(){
        Draw.drawBullet(x,y,sx/2,sy/2, rot);
    }
    
}
