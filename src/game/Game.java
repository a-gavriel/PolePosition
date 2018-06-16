package game;



import java.util.Random;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import static game.Main.loadTexture;
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
    public static Player player = new Player( "nombre", 3, 150*size,(168-100)*size);
    
    
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
            if (milis%200 == 1){
                    bandera = true; }                
        }                         
    };
        
    		
    public void start(){
    	tim.schedule(task01, 1000,1);    	
    }
    
    
    public Game(){        
    	this.start();
        //Random random = new Random();
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
                    //Shoot disparo;
                    Shoot disparo = new Shoot(player.getX()+player.getSX()/2,player.getY()+player.getSY(),"heroe");
                    proyectiles.insertFirst(disparo);
                    bandera = false;                
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
        glTranslatef(0, 0, -50);
        glTranslatef(-1*(player.x+player.sx/2),0, 0);
        glTranslatef(0, -1*(player.y-30), 0);
        player.render();
        map.render();
        
        
        if( proyectiles != null){
        	proyectiles.Renderall();
        	  }
    }
}