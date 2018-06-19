package game;
import org.lwjgl.opengl.Display;

/**
 * Clase que se encarga de las funciones de jugador
 */

public class Player extends GameObject {

    /**
     * Tamaño en Y
     */
    private static final int SIZEY = 4;
    /**
     * Tamaño en X
     */
    private static final int SIZEX = 4;
    /**
     * Aceleración del jugador
     */
    private static float Acceleration = 0.04f;
    /**
     * Velocidad máxima del jugador
     */
    private static float MaxSpeed = 2.8f;
    /**
     * Velocidad actual del jugador
     */
    private float SPEED = 0;
    /**
     * Vida del jugador
     */
    private boolean hearts = true;
    /**
     * Posición en la matriz
     */
    private int Matx;
    
    private int Maty;
    /**
     * Posición de la matriz
     */

    /**
     * Angulo de camara
     */
    private float angle = 90 ;
    /**
     * Nombre del jugador
     */
    private String name;

    /***
     * Inicia todo
     * @param nombre Nombre del jugador
     * @param ammo Munición del jugador
     * @param x Posición en X
     * @param y Posición en Y
     */
    public Player(String nombre,  int ammo, float x, float y) {
        this.name = nombre;
        this.x = x;
        this.y = y;
        this.sx = SIZEX;
        this.sy = SIZEY;
    }

    /**
     * Actualiza las variables del jugador
     */
    @Override
    public void update(){
        if(hearts){
            Matx = (int) x/Map.size;
            Maty = (int) (Map.matriz.length - y/Map.size);


            if (Map.matriz[Maty][Matx] == 1)
                SPEED = 0.5f;

            if (Map.matriz[Maty][Matx] == 7)
                SPEED = 0.5f;
            
            if (Map.matriz[Maty][Matx] == 9)
                Main.cliente.sendStringToServer("g," + Main.cliente.get_number());


            SPEED -= 0.03f;
            if(SPEED >= MaxSpeed)
                SPEED -= Acceleration;

            if (Map.matriz[Maty][Matx] == 5)
                SPEED = 0;
            
            if (Map.matriz[Maty][Matx] == 7)
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

    /**
     * Dibuja el jugador con su correspondiente color
     */
    @Override
    public void render(){
        
        //System.out.println(Main.color);
        
       if(this.name.equals("1")){
            Draw.cube(x,y,sx,sy,angle-90,1,0,0);
       }
        if(this.name.equals("2")){
            Draw.cube(x,y,sx,sy,angle-90,0,1,0);
       }
        if(this.name.equals("3")){
            Draw.cube(x,y,sx,sy,angle-90,0,0,1);
       }
        if(this.name.equals("4")){
            Draw.cube(x,y,sx,sy,angle-90,1,1,0);
       }
        else{
            Draw.cube(x,y,sx,sy,angle-90,0,0,0);
        }
     
        
    }

    /**
     * Mueve al jugador en Y
     * @param mag Cuanto se moverá
     */
    public void moveY(float mag){
        if (hearts){
            if (mag > 0){
                if (y < ((Display.getHeight() )- this.sy * 1.2)){
                    SPEED+=Acceleration;
                    if(SPEED >= MaxSpeed)
                        SPEED = MaxSpeed;

                    y += SPEED * mag * Math.sin(Math.toRadians(angle));
                    x += SPEED * Math.cos(Math.toRadians(angle));
                }
            }
            if (mag <0)
                if (y>0){
                    SPEED-=Acceleration;
                }
        }
    }

    /**
     * Mueve el jugador en X
     * @param mag Cuanto se moverá
     */
    public void moveX(float mag){
        angle -= mag * 2;
        //Main.cam.rotateY(2*mag);
        Main.cam.rotateZ(2*mag);
    }

    /**
     * Retorna posición en X
     * @return Posición en X
     */
    public int getMatx(){
        return Matx;
    }

    /**
     * Retorna posición en Y
     * @return Posición en Y
     */
    public int getMaty(){
        return Maty;
    }

    /**
     * Retorna el angulo del jugador
     * @return angulo del jugador
     */
    public float getAngle(){
        return angle;
    }

    /**
     * Set Vidas 
     * @param H 
     */
    public void setHeart(boolean H){
        hearts = H;
    }
    
    /**
     * Retorna las vidas del jugador
     * @return Vidas del jugador
     */
    public boolean getHeart(){
        return this.hearts;
    }

    /**
     * Retorna el nombre del jugador
     * @return Nombre del jugador
     */
    public String getName(){
        return name;
    }
    
    /**
     * Nuevo nombre
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    
}    
