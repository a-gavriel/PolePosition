
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
           

            glEnd();
        }
        glPopMatrix();
    }
    
    public static void cube(float x, float y, float width, float height,float rot,float red,float green,float blue)
    {
        glPushMatrix();
        {
            //red-green-blue
            //glColor3f(red,green,blue);
            glTranslatef(x,y,0);
            glRotatef(rot,0,0,1);
            glBegin(GL_QUADS);
            /*{
                
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
             */
            {
//front face
                    glColor3f(red, green, blue);
                    glVertex3f(0, height+3, 1);
                    glVertex3f(0, height+3, 5);
                    glVertex3f(width,height+3, 5);
                    glVertex3f(width, height+3, 1);
                    

//BackFace
                    glColor3f(red, green, blue);
                    glVertex3f(0, 0, 1);
                    glVertex3f(0, 0, 5);
                    glVertex3f(width,0, 5);
                    glVertex3f(width, 0, 1);
                   

//BottomFace
                    glColor3f(red,green,blue);                
                    glVertex3f(0,0,1);
                    glVertex3f(0,height+3,1);
                    glVertex3f(width,height+3,1);
                    glVertex3f(width,0,1);  
                    

//TopFace
                    glColor3f(red, green,blue);
                    glVertex3f(0,0,5);
                    glVertex3f(0,height+3,5);
                    glVertex3f(width,height+3,5);
                    glVertex3f(width,0,5); 
                    

//LeftFace
                    glColor3f(red, green, blue);
                    glVertex3f(0, 0, 1);                    
                    glVertex3f(0, 0, 5);
                    glVertex3f(0,height+3, 5);
                    glVertex3f(0, height+3, 1);
                    

//Right Face
                    glColor3f(red, green, blue);
                    glVertex3f(width, 0, 1);                    
                    glVertex3f(width, 0, 5);                    
                    glVertex3f(width,height+3, 5);
                    glVertex3f(width, height+3, 1);
            }
            

            glEnd();
        }
        glPopMatrix();
    }
    public static void drawBullet(float x, float y, float width, float height,float rot){
        glPushMatrix();
        {
            //red-green-blue
            glTranslatef(x,y,0);
            glRotatef(rot,0,0,1);
            glBegin(GL_QUADS);
            /*{
                
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
             */
            {
//front face
                    glColor3f(0, 0, 0);
                    glVertex3f(0, height, 2);
                    glVertex3f(0, height, 3);
                    glVertex3f(width,height, 3);
                    glVertex3f(width, height, 3);
                    

//BackFace
                    glColor3f(0, 0, 0);
                    glVertex3f(0, 0, 2);
                    glVertex3f(0, 0, 2);
                    glVertex3f(width,0, 3);
                    glVertex3f(width, 0, 2);
                   

//BottomFace
                    glColor3f(0,0,0);                
                    glVertex3f(0,0,2);
                    glVertex3f(0,height,2);
                    glVertex3f(width,height,2);
                    glVertex3f(width,0,2);  
                    

//TopFace
                    glColor3f(0, 0,0);
                    glVertex3f(0,0,3);
                    glVertex3f(0,height,3);
                    glVertex3f(width,height,3);
                    glVertex3f(width,0,3); 
                    

//LeftFace
                    glColor3f(0, 0, 0);
                    glVertex3f(0, 0, 2);                    
                    glVertex3f(0, 0, 3);
                    glVertex3f(0,height, 3);
                    glVertex3f(0, height, 2);
                    

//Right Face
                    glColor3f(0, 0, 0);
                    glVertex3f(width, 0, 2);                    
                    glVertex3f(width, 0, 3);                    
                    glVertex3f(width,height, 3);
                    glVertex3f(width, height, 2);
            }
            

            glEnd();
        }
        glPopMatrix();
        
    }
    
    
}
