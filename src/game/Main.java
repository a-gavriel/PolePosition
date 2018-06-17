package game;
import networking.Client;
import Interfaces.InitInterface;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 * Clase encargada de inciar el programa
 */

public class Main {

    /**
     * Juego
     */
    private static Game game;
    /**
     * Objeto de OpenGl
     */
    private static Object gl;
    /**
     * Clase cámara
     */
    public static Camera cam;
    /**
     * Estado
     */
    private static boolean abierto;
    /**
     * 
     */
    private static String color;
    /**
     * Cliente encargado de la conexión
     */
    public static Client cliente;

    /**
     * Método de inicio
     * @param args
     */
    
    public static void main (String[] args){ 
        
        abierto = true;
        Interfaces.InitInterface.InitInterface1();
        while(abierto){
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){}
        }
        abierto = true;
        Interfaces.InitInterface2.initInterface();
        while(abierto){
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){}
        }
        System.out.print(color);
        cliente.sendStringToServer("c,"+color);

        Map.readmat();
        initDisplay();
        initGL();  
        initGame();        
        gameLoop();
        cleanUp();       
    }

    /**
     * Inicia e instancia las clases necesarias. Rota la camara para que esté detrás del jugador
     */
    private static void initGame(){
        cam = new Camera(70,(float) Display.getWidth() /(float)Display.getHeight(),0.3f,1000);
        cam.rotateX(-60f);
        game = new Game();
    }

    /**
     * Obtiene los inputs del usuario
     */
    private static void getInput(){
       game.getInput();
    }

    /**
     * Actualiza las variables y la lógica del juego
     */
    private static void update() {
       //game.recreate();
       game.update();
    }

    /**
     * Dibuja los objetos en pantalla.
     */
    private static void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
        cam.useView();
        game.render();        
        Display.update();
        Display.sync(60);
    }

    /**
     * Lee las texturas del juego
     * la imagen debe ser cuadrada y tener dimensiones de 2^n
     * @param key Nombre de la textura
     * @return La textura cargada
     */
    public static Texture loadTexture(String key){
        try {
            return TextureLoader.getTexture(".png", new FileInputStream(new File("res/" + key + ".png")));
        } 
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;                
    }


    /**
     * Bucle principal del juego
     */
    private static void gameLoop(){
        
        while (!Display.isCloseRequested()) {        
            getInput();  
            update();
            render();        
        }
    }


    /**
     * Inicia los componentes necesarios para trabajar en OpenGL
     */
    private static void initGL(){
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,Display.getWidth(),0,Display.getHeight(),-1,1);
        glMatrixMode(GL_MODELVIEW);        
        glClearColor(0,0,0,1);
        glDisable(GL_DEPTH_TEST);
    }

    /**
     * Limpia y destruye todos los componentes de OpenGL
     */
    private static void cleanUp(){
        Display.destroy();
        Keyboard.destroy();
        game.tim.cancel();
        game.task01.cancel();
        game.tim.purge();
    }

    /**
     * Crea la ventana de OpenGL
     */
    private static void initDisplay(){
        try {
            Display.setDisplayMode(new DisplayMode(672,672));
            Display.create();
            Display.setLocation(Display.getX(), Display.getY() - 50);
            Display.setVSyncEnabled(true);
            Keyboard.create();
        } catch (LWJGLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }


    /**
     * Setea el estado de la ventana abierto o cerrado
     * @param ab Estado, abierto o cerrado
     */
    public static void setInterState(boolean ab)
    {
        abierto = ab; 
    }

    /**
     * Crea el cliente que se encarga de las conexiones con el servidor
     * @param ip IP a la que se conectará
     * @param port Puerto del servidor
     */
    public static void crearCLiente(String ip, Integer port)
    {
        cliente = new Client(port, ip);
        cliente.start();
    }

    /**
     * Cambia el color
     * @param color nuevo color
     */
    public static void setColor(String color){
        color = color;
    } 
   
}
