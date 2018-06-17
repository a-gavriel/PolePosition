package game;

/**
 * Es la clase padre de todos los objetos del juego. Hereda todos sus atributos y métodos
 */
public abstract class GameObject {

    /**
     * Posicion en X
     */
    protected float x;
    /**
     * Posicion en Y
     */
    protected float y;
    /**
     * Posicion en X
     */
    protected float sx;
    /**
     * Posicion en Y
     */
    protected float sy;
    /**
     * Atributo de vida
     */
    protected int life;
    /**
     * Rotación de la camara
     */
    protected float rot;

    /**
     * Método que se encargará de actualizar las variables
     */
    abstract void update();

    /**
     * Método de dibujo
     */
    public void render(){
        Draw.rect(x,y,sx,sy,1f,1f,1f);
    }

    /**
     * Obtiene la posición en X
     * @return posicion en X
     */
    public float getX (){
        return x;
    }

    /**
     * Obtiene la posición en Y
     * @return posicion en Y
     */
    public float getY(){
        return y;
    }

    /**
     * Obtiene la posición en X
     * @return posicion en X
     */
    public float getSX(){
        return sx;
    }

    /**
     * Obtiene la posición en Y
     * @return posicion en Y
     */
    public float getSY(){
        return sy;
    }

    /**
     * Obtiene el centro en Y
     * @return Centro en Y
     */
    public float getCenterY(){
        return y + sy/2;
     
    }

    /**
     * Obtiene el centro en X
     * @return Centro en X
     */
    public float getCenterX(){
        return x + sx/2;
    }

    /**
     * Retorna la vida
     * @return vida
     */
    public int getLife(){
        return life;
    }

    /**
     * Aumenta la vida en un punto
     * @param mag
     */
    public void addLife(int mag){
        life += mag;
    }

    /**
     * Disminuye la vida en un punto
     */
    public void minusLife(){
        life -=1;
    }
    
}

