package game;
import org.lwjgl.opengl.Display;




public class Player extends GameObject {

    private static final int SIZEY = 4;
    private static final int SIZEX = 4;
    private static float Acceleration = 0.04f;
    private static float MaxSpeed = 2.8f;
    private float SPEED = 0;
    private boolean hearts = true;
    private int Matx;
    private int Maty;
    private float angle = 90 ;
    private String name;
   
   
    
    public Player(String nombre,  int ammo, float x, float y) {
        this.name = nombre;
        this.x = x;
        this.y = y;
        this.sx = SIZEX;
        this.sy = SIZEY;
    }
     
    @Override
    public void update(){
        if(hearts){
            Matx = (int) x/Map.size;
            Maty = (int) (Map.matriz.length - y/Map.size);


            if (Map.matriz[Maty][Matx] == 1)
                SPEED = 0.5f;

            if (Map.matriz[Maty][Matx] == 7)
                SPEED = 0.5f;


            SPEED -= 0.03f;
            if(SPEED >= MaxSpeed)
                SPEED -= Acceleration;

            if (Map.matriz[Maty][Matx] == 5)
                SPEED = 1.5f*MaxSpeed;

            if(SPEED >= 0){
                if (y < ((Display.getHeight() )- this.sy * 1.2)){

                    y += SPEED * Math.sin(Math.toRadians(angle));
                    x += SPEED * Math.cos(Math.toRadians(angle));
                }
            }
            if (SPEED <=0)
                SPEED = 0;
        }
        
        
        
        
       
        
        
        
    }    
    @Override
    public void render(){
        
        System.out.println(Main.color);
        
       if(Main.color.equals("1")){
            Draw.cube(x,y,sx,sy,angle-90,1,0,0);
       }
        if(Main.color.equals("2")){
            Draw.cube(x,y,sx,sy,angle-90,0,1,0);
       }
        if(Main.color.equals("3")){
            Draw.cube(x,y,sx,sy,angle-90,0,0,1);
       }
        if(Main.color.equals("4")){
            Draw.cube(x,y,sx,sy,angle-90,1,1,0);
       }
        else{
            Draw.cube(x,y,sx,sy,angle-90,0,0,0);
        }
       
       
        
    }
  
    public void moveY(float mag){
        if (mag > 0)
            if (y < ((Display.getHeight() )- this.sy * 1.2)){
                SPEED+=Acceleration;
                if(SPEED >= MaxSpeed)
                    SPEED = MaxSpeed;
                
                y += SPEED * mag * Math.sin(Math.toRadians(angle));
                x += SPEED * Math.cos(Math.toRadians(angle));
            }
        if (mag <0)
            if (y>0){
                SPEED-=Acceleration;
            }
    }
    
    public void moveX(float mag){
        angle -= mag * 2;
        //Main.cam.rotateY(2*mag);
        Main.cam.rotateZ(2*mag);
    }
    
    public int getMatx(){
        return Matx;
    }
    public int getMaty(){
        return Maty;
    }
    public float getAngle(){
        return angle;
    }
    public boolean getHeart(){
        return this.hearts;
    }
    public String getName(){
        return name;
    }
}

        
        
        
        


