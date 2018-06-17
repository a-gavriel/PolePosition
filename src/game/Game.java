package game;



import java.util.Random;
import org.lwjgl.input.Keyboard;
import java.util.Timer;
import java.util.TimerTask;
import static org.lwjgl.opengl.GL11.glTranslatef;

/**
 *
 * @author Alexis
 */

public class Game {    
    public static int size = Map.size;
    public static Map map = new Map();
    public static Player player = new Player( "nombre", 3, 143*size,(168-97)*size);
    public static ListaDoble JugadoresE = new ListaDoble();
    
    private int milis =0;
    ListaDoble proyectiles =new ListaDoble();
    
    Random randomx = new Random();
    
    private boolean bandera = true;
    private boolean disparos = true;
    Timer tim=new Timer();
    //Torre t1;
    //ShootAimed s1;
	
    TimerTask task01=new TimerTask(){
        public void run(){
            milis++;    		
            if (milis%500 == 1){
                    bandera = true; }                
        }                         
    };
        
    		
    public void start(){
    	tim.schedule(task01, 1000,1);    	
    }
    
    
    public Game(){        
    	this.start();
        
        int yy = 100;
        for (int i = 0; i < 3  ; ++i){
            Player Nuevo;
            if (i%2 ==1)
                Nuevo = new Player ("nombre" , 3 , 143*size,(168-yy)*size);
            else
                Nuevo = new Player ("nombre", 3, 153*size , (168-yy)*size);
            JugadoresE.insertFirst(Nuevo);
            yy+=3;
        }
    }
        

        
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
    
    public void update(){        
        player.update();      
        if (player.getLife() <= 0){                
            //System.out.println("dead");
        }

        
        
        if( proyectiles.getSize() != 0){
        	proyectiles.Updateall();
                /*
                NodoDoble current =  proyectiles.getHead();
        	while (current != null){
                    if (Physics.checkwithListaDoble(current.getData(), Lista))
                        proyectiles.delete(current);
                    current = current.getNext();
                }      
                */
        }
        
    }
    
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
        	  
        if( JugadoresE != null)
            JugadoresE.Renderall();
        	  
    }
}