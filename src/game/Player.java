package game;
import org.lwjgl.opengl.Display;


public class Player extends GameObject {

    private static final int SIZEY = 4;
    private static final int SIZEX = 4;
    private static float Acceleration = 0.01f;
    private static float MaxSpeed = 2.5f;
    private float SPEED = 1;
    private int hearts = 3;
    private int Matx;
    private int Maty;
    
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
    public void render2(){
        Draw.rect(80,80,40,40,0,0,0);
    }
    
    public void moveY(float mag){
        if (mag > 0)
            if (y < ((Display.getHeight() )- this.sy * 1.2))
                y += SPEED * mag;
        if (mag <0)
            if (y>0)
                y += SPEED * mag;
    }
    
    public void moveX(float mag){
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
    
    public int getMatx(){
        return Matx;
    }
    public int getMaty(){
        return Maty;
    }
    
}

        
        
        
        


