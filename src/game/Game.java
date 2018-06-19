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
    public static Player[] Jugadores = new Player[4];
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
    public void startTimer(){
    	tim.schedule(task01, 1000,1);        
    }


    /**
     * Constructor inicia el thread de los disparos e inicializa los clientes
     */
    public Game(){        
    	this.startTimer();
        //player = new Player( Main.color, 3, 143*size,(168-97)*size);
        Jugadores[0] = new Player ("1", 3, 143*size , (168-97)*size);
        Jugadores[1] = new Player ("2", 3, 153*size , (168-100)*size);
        Jugadores[2] = new Player ("3", 3, 143*size , (168-103)*size); 
        Jugadores[3] = new Player ("4", 3, 153*size , (168-106)*size);  
        int temp = Integer.parseInt(Main.cliente.get_number().trim())-1;
        player = Jugadores[temp];
        //System.out.println(Main.cliente.get_number());
        //int temp = Integer.parseInt(Main.cliente.get_number().trim()) - 1;
        //Jugadores[temp] = new Player( Main.color, 3, 143*size,(168-97)*size);
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
        
        for (int i = 0; i< 4 ; ++i)
            if (i != Integer.parseInt(Main.cliente.get_number().trim()) -1)
                Jugadores[i].update();
        
        if( proyectiles.getSize() != 0){
        	proyectiles.Updateall();
                
                NodoDoble current =  proyectiles.getHead();
        	while (current != null){
                    if ((Physics.checkwithArray(current.getData(), Jugadores)) != -1)
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
        	  
        for (int i = 0; i< 4 ; ++i)
            if (i != Integer.parseInt(Main.cliente.get_number().trim()) -1)
                Jugadores[i].render();
    }
    
    
    /**
     * Funcion para mover los jugadores externos cuando se recibe información del servidor
     */
    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(100);
                Main.cliente.sendStringToServer("m,"+Main.cliente.get_number().trim()+","+player.getMatx()+","+player.getMaty()+",");
                
                if (Main.cliente.obtenerMovimientoJugadores() != null) {
                    System.err.println(Main.cliente.obtenerMovimientoJugadores());
                    if (!Main.cliente.obtenerMovimientoJugadores()[1].equals(Main.cliente.getName())) {
                        int temp = Integer.parseInt(Main.cliente.obtenerMovimientoJugadores()[1]) - 1;
                        Jugadores[temp].y = Float.parseFloat(Main.cliente.obtenerMovimientoJugadores()[2]);
                        Jugadores[temp].x = Float.parseFloat(Main.cliente.obtenerMovimientoJugadores()[3]);
                    }
                }
                
                
            }catch(InterruptedException e){}
        
            
        }
        
    }
}
