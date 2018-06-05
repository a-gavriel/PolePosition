package game;
import org.lwjgl.opengl.Display;


public class Player extends GameObject {

    private static final int SIZEY = 4;
    private static final int SIZEX = 4;
    private static float Acceleration = 0.01f;
    private static float MaxSpeed = 2f;
    private static float SPEED = 0;
    private static int hearts = 3;
    
    public Player(String nombre,  int ammo, float x, float y) {
            
        this.x = x;
        this.y = y;
        this.sx = SIZEX;
        this.sy = SIZEY;
    }

    
    
    @Override
    public void update()
    {
        if (SPEED < MaxSpeed)
            SPEED += Acceleration;
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

        
        
        
        


