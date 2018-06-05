package game;



import java.util.Random;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import static game.Main.loadTexture;
import java.util.Timer;
import java.util.TimerTask;

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
        

        
/*
    public void recreate(){
        if ((Lista.getSize() < 4) && (milis % 10 == 1)) {         
                if (!Cola.isEmpty() ){
                    int x = Cola.desencolar().getData();          
                    int randomx_pos =  randomx.nextInt(600)+100;             
                    if (x==0) {
                        Jet jet1;
                        jet1 = new Jet(  randomx_pos  , Display.getHeight()); 
                        Lista.insertFirst(jet1);     
                    }
                    else if (x==1)  {
                        Kamikaze kami1;
                        kami1 = new Kamikaze (randomx_pos  , Display.getHeight(), player.getCenterX(), player.getY()); 
                        Lista.insertFirst(kami1);      
                    }
                    else if (x==2) {
                        Bombardero bomb1;
                        bomb1 = new Bombardero (randomx_pos  , Display.getHeight()); 
                        Lista.insertFirst(bomb1);
                        //System.out.println("bomb1 ins");
                    }
                    else if (x==3){
                        Boss jefe1;
                        jefe1 = new Boss (level); 
                        Lista.insertFirst(jefe1);             
                    }
                }
            }    
    }
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
        map.render();
        player.render();
        if( proyectiles != null){
        	proyectiles.Renderall();
        	  }
    }
}