
package game;

import static org.lwjgl.opengl.GL11.*;



public class Draw {
    
    public static void rect(float x, float y, float width, float height)
    {
        rect( x,  y,  width,  height, 0);
    }
    
    public static void rect(float x, float y, float width, float height, float rot)
    {
        glPushMatrix();
        {
            glTranslatef(x,y,0);
            glRotatef(rot,0,0,1);
            glBegin(GL_QUADS);
            {
                glVertex3f(0,0,1);
                glVertex3f(0,height,1);
                glVertex3f(width,height,1);
                glVertex3f(width,0,1);      
            }

            glEnd();
        }
        glPopMatrix();
    }     
    
    
    public static void rect(float x, float y, float width, float height, float red,float green,float blue)
    {
        rect( x,  y,  width,  height, 0, red,green,blue);
        
    }
    
    public static void drawGrass(){
        
    }
    
    public static void rect(float x, float y, float width, float height, float rot,float red,float green,float blue)
    {
        glPushMatrix();
        {
            //red-green-blue
            glColor3f(red,green,blue);
            glTranslatef(x,y,-0.1f);
            glRotatef(rot,0,0,1);
            glBegin(GL_QUADS);
            {
                glVertex3f(0,0,1);
                glVertex3f(0,height,1);
                glVertex3f(width,height,1);
                glVertex3f(width,0,1);       
            }
            /*
            {
                glColor3f(1f, 0f, 0f);
                    glVertex3f(-1, -1, 1);
                    glVertex3f(1, -1, 1);
                    glVertex3f(1, 1, 1);
                    glVertex3f(-1, 1, 1);
                    

//BackFace
                    glColor3f(0f, 1f, 0f);
                    glVertex3f(-1, -1, -1);
                    glVertex3f(-1, -1, -1);
                    glVertex3f(1, 1, -1);
                    glVertex3f(1, -1, -1);
                   

//BottomFace
                    glColor3f(0f, 0f, 1f);
                    glTexCoord2f(1, 1); glVertex3f(-1, -1, -1);
                    glTexCoord2f(1, 0); glVertex3f(-1, -1, 1);
                    glTexCoord2f(0, 0); glVertex3f(-1, 1, 1);
                    glTexCoord2f(0, 1); glVertex3f(-1, 1, -1);
                    

//TopFace
                    glColor3f(1f, 1f, 0f);
                    glTexCoord2f(1, 1); glVertex3f(1, -1, -1);
                    glTexCoord2f(1, 0);  glVertex3f(1, -1, 1);
                    glTexCoord2f(0, 0); glVertex3f(1, 1, 1);
                    glTexCoord2f(0, 1); glVertex3f(1, 1, -1);
                    

//LeftFace
                    glColor3f(0f, 1f, 1f);
                    glTexCoord2f(1, 1); glVertex3f(-1, -1, -1);                    
                    glTexCoord2f(1, 0); glVertex3f(1, -1, -1);
                    glTexCoord2f(0, 0); glVertex3f(1, -1, 1);
                    glTexCoord2f(0, 1); glVertex3f(-1, -1, 1);
                    

//Right Face
                    glColor3f(1f, 0f, 1f);
                    glTexCoord2f(1, 1); glVertex3f(-1, 1, -1);                    
                    glTexCoord2f(1, 0); glVertex3f(1, 1, -1);                    
                    glTexCoord2f(0, 0); glVertex3f(1, 1, 1);
                    glTexCoord2f(0, 1); glVertex3f(-1, 1, 1);
            }
            */

            glEnd();
        }
        glPopMatrix();
    }
    
    public static void cube(float x, float y, float width, float height,float rot,float red,float green,float blue)
    {
        glPushMatrix();
        {
            //red-green-blue
            glColor3f(red,green,blue);
            glTranslatef(x,y,0);
            glRotatef(rot,0,0,1);
            glBegin(GL_QUADS);
            {
                
                glColor3f(0,0,1);                
                glVertex3f(0,0,1);
                glVertex3f(0,height,1);
                glVertex3f(width,height,1);
                glVertex3f(width,0,1);   
                
                glColor3f(1, 0,0);
                glVertex3f(0,0,5);
                glVertex3f(0,height,5);
                glVertex3f(width,height,5);
                glVertex3f(width,0,5);  
                
            }

            glEnd();
        }
        glPopMatrix();
    }
    
    
}
