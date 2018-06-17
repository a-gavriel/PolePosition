package game;



import java.util.Random;
import org.lwjgl.input.Keyboard;
import java.util.Timer;
import java.util.TimerTask;
import static org.lwjgl.opengl.GL11.glTranslatef;

/**
 * Clase encargada de manejar la lógica del juego
 */

public class Game {
    /**
     * Tamaño del mapa
     */
    public static int size = Map.size;
    /**
     * Mapa de juego
     */
    public static Map map = new Map();
    /**
     * Jugador
     */
    public static Player player = new Player( "nombre1", 3, 143*size,(168-97)*size);
    /**
     * Jugadores del multijugador
     */
    public static Player[] Jugadores = new Player[3];
    /**
     * Milis
      */
    private int milis =0;
    /**
     * Lista de balas
     */
    ListaDoble proyectiles =new ListaDoble();
    /**
     * Elemento random
     */
    Random randomx = new Random();

    /**
     * Bandera
     */
    private boolean bandera = true;
    /**
     * Bandera de disparos
     */
    private boolean disparos = true;
    /**
     * Timer
     */
    Timer tim=new Timer();
    //Torre t1;
    //ShootAimed s1;

    /**
     * Thread de los disparos
     */
    TimerTask task01=new TimerTask(){
        public void run(){
            milis++;    		
            if (milis%500 == 1){
                    bandera = true; }                
        }                         
    };


    /**
     * Inica el thread de disparos
     */
    public void start(){
    	tim.schedule(task01, 1000,1);    	
    }


    /**
     * Constructor inicia el thread de los disparos e inicializa los clientes
     */
    public Game(){        
    	this.start();
        Jugadores[0] = new Player ("nombre2", 3, 153*size , (168-100)*size);
        Jugadores[1] = new Player ("nombre3", 3, 143*size , (168-103)*size);
        Jugadores[2] = new Player ("nombre4", 3, 153*size , (168-106)*size);
    }


    /**
     * Obtiene el input del usuario
      */
    public void getInput(){        
        if (Keyboard.isKeyDown(Keyboard.KEY_W) || (Keyboard.isKeyDown(Keyboard.KEY_UP)))
            player.moveY(1);
        if (Keyboard.isKeyDown(Keyboard.KEY_S) || (Keyboard.isKeyDown(Keyboard.KEY_DOWN)))
            player.moveY(-1);        
        if (Keyboard.isKeyDown(Keyboard.KEY_A) || (Keyboard.isKeyDown(Keyboard.KEY_LEFT)))
            player.moveX(-1);
        if (Keyboard.isKeyDown(Keyboard.KEY_D) || (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)))
            player.moveX(1);       
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)){         	
        	if( bandera ) {
                    if(player.getHeart()){
                        //Shoot disparo;
                        Shoot disparo = new Shoot(player.getX()+player.getSX()/2*((float)Math.cos(Math.toRadians(player.getAngle()-90))),
                                player.getY()+player.getSY()/2*((float)Math.sin(Math.toRadians(player.getAngle()-90))),
                                "heroe", player.getAngle());
                        proyectiles.insertFirst(disparo);
                        bandera = false;                
                }
            }
        }
                
    }

    /**
     * Actualiza los elementos y chequea colisiones
     */
    public void update(){        
        player.update();      
        
        for (Player i : Jugadores)
            i.update();
        
        if( proyectiles.getSize() != 0){
        	proyectiles.Updateall();
                
                NodoDoble current =  proyectiles.getHead();
        	while (current != null){
                    if ((Physics.checkwithArray(current.getData(), Jugadores)) != 0)
                        proyectiles.delete(current);
                    current = current.getNext();
                }      
                
        }
        
    }

    /**
     * Dibuja todos los elementos en pantalla
     */
    public void render(){
        //System.out.println(player.x + " + " + player.y);
        float vect = 40f;
        glTranslatef(0, 0, -50);
        glTranslatef((float) (-1*(player.x+player.sx/2 - vect* Math.cos(Math.toRadians(player.getAngle())))),0, 0);
        glTranslatef(0, (float) ( -1*(player.y+player.sy/2 - vect* Math.sin(Math.toRadians(player.getAngle() )))), 0);
        player.render();
        map.render();
        
        
        if( proyectiles != null)
            proyectiles.Renderall();
        	  
        for (Player i : Jugadores)
            i.render();
    }
}
