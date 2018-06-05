package game;
import org.lwjgl.opengl.Display;


public class Player extends GameObject {

    private static final int SIZEY = 4;
    private static final int SIZEX = 4;
    private static float Acceleration = 0.01f;
    private static float MaxSpeed = 2f;
    private static float SPEED = 1;
    private static int hearts = 3;
    private static int Matx;
    private static int Maty;
    
    public Player(String nombre,  int ammo, float x, float y) {
            
        this.x = x;
        this.y = y;
        this.sx = SIZEX;
        this.sy = SIZEY;
    }

    
    
    @Override
    public void update(){
        if (SPEED < MaxSpeed)
            SPEED += Acceleration;
        
        Matx = (int) x/Map.size;
        Maty = (int) (Map.matriz.length - y/Map.size);
        
        if (Map.matriz[Maty][Matx] == 1)
            SPEED = 0.5f;
    }    
    public void render(){
        Draw.rect(x,y,sx,sy,0,0,0);
    }
    
    public void moveY(float mag)
    {
        if (mag > 0)
            if (y < ((Display.getHeight() )- this.sy * 1.2))
                y += SPEED * mag;
        if (mag <0)
            if (y>0)
                y += SPEED * mag;
    }
    public void moveX(float mag)
    {
        if (mag > 0)
        {
            if (x+sx < Display.getWidth())
            {
                x += SPEED * mag;
            }  
        }
        if (mag < 0)
        {
            if (x>0)
            {
                x += SPEED * mag;
            }                
        }                
    }
    
}

        
        
        
        


