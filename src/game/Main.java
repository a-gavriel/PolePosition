package game;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 *
 * @author Alexis
 */
public class Main{
    private static Game game;
    private static Object gl;
    public static Camera cam;
    
    public static void main (String[] args){   
        Map.readmat();
        initDisplay();
        initGL();  
        initGame();        
        gameLoop();
        cleanUp();       
    }
    
    private static void initGame(){
        cam = new Camera(70,(float) Display.getWidth() /(float)Display.getHeight(),0.3f,1000);
        cam.rotateX(-60f);
        game = new Game();
    }
    
    private static void getInput(){
       game.getInput();
    }
    
    private static void update() {
       //game.recreate();
       game.update();
    }
    
    private static void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
        cam.useView();
        game.render();        
        Display.update();
        Display.sync(60);
    }
    
    
    //la imagen debe ser cuadrada y tener dimensiones de 2^n
    public static Texture loadTexture(String key){
        try {
            return TextureLoader.getTexture(".png", new FileInputStream(new File("res/" + key + ".png")));
        } 
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;                
    }
    
    
    private static void gameLoop(){
        //Camera cam = new Camera(70,(float) Display.getWidth() /(float)Display.getHeight(),0.3f,1000);
        
        while (!Display.isCloseRequested()) {        
            getInput();  
            update();
            render();        
        }
    }

            
    
    private static void initGL(){
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,Display.getWidth(),0,Display.getHeight(),-1,1);
        glMatrixMode(GL_MODELVIEW);        
        glClearColor(0,0,0,1);
        glDisable(GL_DEPTH_TEST);
    }
    
    private static void cleanUp(){
        Display.destroy();
        Keyboard.destroy();
        game.tim.cancel();
        game.task01.cancel();
        game.tim.purge();
    }
    
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
}