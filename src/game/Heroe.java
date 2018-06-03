package game;
import org.lwjgl.opengl.Display;


public class Heroe extends GameObject {

    private static final int SIZEY = 20;
    private static final int SIZEX = SIZEY * 2;
    private static final float SPEED = 6f;
    private static int hearts = 3;
    private static int nivel = 1;
    
    public Heroe(String nombre,  int ammo, float x, float y) {
            
        this.x = x;
        this.y = y;
        this.sx = SIZEX;
        this.sy = SIZEY;
        this.life = 10;
        this.dmg = 1;
        
    }
    public void addHeart(int mag)
    {
        hearts += mag;
    }
    public int getheart ()
    {
        return hearts;
    }
    public void nextlevel()
    {
        nivel +=1;
        System.out.println("nextlvl");
    }
    
    
    
    @Override
    public void update()
    {
        //if (Physics.checkCollisions(this, ball))
        //    ball.reverseX(getCenterY());
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

        
        
        
        


