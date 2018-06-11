/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Alexis
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
*
* @author Benny
*/
public class Prueba3D {
    public static void main(String[] args) {
        initDisplay();

        gameLoop();
        cleanUp();
    }

    public static void gameLoop(){
        Camera cam = new Camera(70,(float)Display.getWidth()/(float)Display.getHeight(),0.3f,1000);
        float x = 0;

        boolean temp = false;

        while(!Display.isCloseRequested()){
            boolean forward = Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP);
            boolean backward = Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN);
            boolean left = Keyboard.isKeyDown(Keyboard.KEY_A);
            boolean right = Keyboard.isKeyDown(Keyboard.KEY_D);

            if(forward)
                cam.move(0.01f,1);
            if(backward)
                cam.move(-0.01f,1);
            if(left)
                cam.move(0.01f,0);//cam.rotateY(-0.1f);
            if(right)
                cam.move(-0.01f,0);//cam.rotateY(0.1f);

            if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
                cam.rotateY(-0.1f);
            if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
                cam.rotateY(0.1f);



            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glLoadIdentity();
            cam.useView();

            glPushMatrix();
            {
                glColor3f(1.0f,0.5f,0f);
                glTranslatef(0,0,-10);
                //glRotatef(x,1,1,0);

                if((forward && left) || (forward && right) || (backward && left) || (backward && right))
                    glRotatef(45,0,1,0);//temp = true;

                if(temp)
                    glRotatef(45,0,1,0);

                glBegin(GL_QUADS);
                {
                    //FrontFace
                    glColor3f(1f,0f,0f);
                    glVertex3f(-1,-1,1);
                    glVertex3f(1,-1,1);
                    glVertex3f(1,1,1);
                    glVertex3f(-1,1,1);

                    //BackFace
                    glColor3f(0f,1f,0f);
                    glVertex3f(-1,-1,-1);
                    glVertex3f(-1,1,-1);
                    glVertex3f(1,1,-1);
                    glVertex3f(1,-1,-1);

                    //BottomFace
                    glColor3f(0f,0f,1f);
                    glVertex3f(-1,-1,-1);
                    glVertex3f(-1,-1,1);
                    glVertex3f(-1,1,1);
                    glVertex3f(-1,1,-1);

                    //TopFace
                    glColor3f(1f,1f,0f);
                    glVertex3f(1,-1,-1);
                    glVertex3f(1,-1,1);
                    glVertex3f(1,1,1);
                    glVertex3f(1,1,-1);

                    //LeftFace
                    glColor3f(0f,1f,1f);
                    glVertex3f(-1,-1,-1);
                    glVertex3f(1,-1,-1);
                    glVertex3f(1,-1,1);
                    glVertex3f(-1,-1,1);

                    //Right Face
                    glColor3f(1f,0f,1f);
                    glVertex3f(-1,1,-1);
                    glVertex3f(1,1,-1);
                    glVertex3f(1,1,1);
                    glVertex3f(-1,1,1);
                }
                glEnd();
            }
            glPopMatrix();
            x += 0.1f;
            Display.update();

        }
    }

    public static void cleanUp(){
        Display.destroy();
    }

    public static void initDisplay(){
        try    {
            Display.setDisplayMode(new DisplayMode(800,600));
            Display.create();
        }
        catch (LWJGLException ex){
            Logger.getLogger(Prueba3D.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
