package game;




import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

/**
 * La clase cámara permite ver el mapa, losjugadores y los disparos desde una perspectiva
 * Esta perspectiva puede desplazarse y rotarse
 * 
 * @author Alexis
 */

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

    /**
     * 
     * @param fov angulo desde el punto de vista
     * @param aspect relacion largo/alto
     * @param near near clipping, no se dibuja algo más cercano de este valor  
     * @param far far clipping, no se dibuja algo más lejano de este valor
     */
    public Camera(float fov, float aspect, float near, float far) {
        x = 0;
        y = 0;
        z = 0;
        rx = 0;
        ry = 0;
        rz = 0;

        this.fov = fov;
        this.aspect = aspect;
        this.near = near;
        this.far = far;
        initProjection();
    }

    /**
     * Se inicializa la proyeccion de la cámara en las matrices correspondientes
     */
    private void initProjection() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(fov, aspect, near, far);
        glMatrixMode(GL_MODELVIEW);

        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);
    }
    
    /**
     * Se utiliza la vista de la cámara
     */
    public void useView() {
        glRotatef(rx, 1, 0, 0);
        glRotatef(ry, 0, 1, 0);
        glRotatef(rz, 0, 0, 1);
        glTranslatef(x, y, z);
    }
    
    /**
     * 
     * @return posicion X
     */
    public float getX() {
        return x;
    }
    /**
     * 
     * @return posicion Y
     */
    public float getY() {
        return y;
    }

    /**
     * 
     * @return posicion Z
     */
    public float getZ() {
        return z;
    }

    /**
     * cambia la posición en X  
     * @param x 
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * cambia la posición en Y
     * @param y 
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * cambia la posición en Z
     * @param z 
     */
    public void setZ(float z) {
        this.z = z;
    }

    /**
     * 
     * @return rotación en X
     */
    public float getRX() {
        return rx;
    }

    /**
     * 
     * @return rotación en Y
     */
    public float getRY() {
        return ry;
    }

    /**
     * 
     * @return rotación en Z
     */
    public float getRZ() {
        return rz;
    }

    /**
     * cambia la rotación en X
     * @param rx 
     */
    public void setRX(float rx) {
        this.rx = rx;
    }

    /**
     * cambia la rotación en Y
     * @param ry 
     */
    public void setRY(float ry) {
        this.ry = ry;
    }

    /**
     * cambia la rotación en Z
     * @param rz 
     */
    public void setRZ(float rz) {
        this.rz = rz;
    }

    /**
     * Gira la cámara en Z desde una misma posición, depende de la rotación en Y
     * @param amt 
     */
    public void moveZ(float amt) {
        z += amt * Math.sin(Math.toRadians(ry + 90));// * Math.sin(Math.toRadians(rx + 90));
        x += amt * Math.cos(Math.toRadians(ry + 90));
    }

    /**
     * Gira la cámara en Z desde una misma posición, depende de la roación en Y
     * @param amt 
     */
    public void moveX(float amt) {
        z += amt * Math.sin(Math.toRadians(ry));
        x += amt * Math.cos(Math.toRadians(ry));
    }
    
    /**
     * Desplaza la cámara en X
     * @param amt 
     */
    public void moveDX(float amt){
        x += amt;
    }
    
    /**
     *  Desplaza la cámara en Y
     * @param amt 
     */
    public void moveDY(float amt){
        y += amt;
    }
    
    /**
     *  Desplaza la cámara en Z
     * @param amt 
     */
    public void moveDZ(float amt){
        z += amt;
    }

    /**
     * Incrementa la rotación en Y
     * @param amt 
     */
    public void rotateY(float amt) {
        ry += amt;
    }

    /**
     * Incrementa la rotación en X
     * @param amt 
     */
    public void rotateX(float amt) {
        rx += amt;
    }

    /**
     * Incrementa la rotación en Z
     * @param amt 
     */
    public void rotateZ(float amt) {
        rz += amt;
    }
}

