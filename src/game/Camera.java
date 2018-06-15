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
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;


public class Camera {
    private float x;
    private float y;
    private float z;
    private float rx;
    private float ry;
    private float rz;
    
    private float fov;
    private float aspect;
    private float near;
    private float far;
    
    
    public Camera(float fov, float aspect, float near, float far){
        x  = 0;
        y  = 0;
        z  = 0;
        rx = 0;
        ry = 0;
        rz = 0;
        
        this.fov = fov;
        this.aspect = aspect;
        this.far = far;
        this.near = near;
        initProjection();
    }
    
    private void initProjection(){
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(fov,aspect,near,far);
        glMatrixMode(GL_MODELVIEW);        
    }
    
    public void useView(){
        glRotatef(rx,1,0,0);
        glRotatef(ry,0,1,0);
        glRotatef(rz,0,0,1);
        glTranslatef(x, y, z);        
    }

    public void move(float amt, float dir){
        z += amt * Math.sin(Math.toRadians(ry + 90 * dir));
        x += amt * Math.cos(Math.toRadians(ry + 90 * dir));
    }

    public void rotateY(float amt){
        ry += amt;
    }




    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getRx() {
        return rx;
    }

    public void setRx(float rx) {
        this.rx = rx;
    }


    public float getRy() {
        return ry;
    }

    public void setRy(float ry) {
        this.ry = ry;
    }


    public float getRz() {
        return rz;
    }

    public void setRz(float rz) {
        this.rz = rz;
    }

    
}