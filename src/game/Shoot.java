package game;

/**
 * Clase encargada de los disparos de los jugadores
 */
public class Shoot extends GameObject
{
    /**
     * Tamaño en X de la bala
     */
    private static final int SIZEX = 2;
    /**
     * Tamaño en Y de la bala
     */
    private static final int SIZEY = 2;
    /**
     * Velocidad de la bala
     */
    private static final float SPEED = 11f;
    /**
     * Tipo de la bala
     */
    private String type;
    /**
     * Daño de la bala
     */
    private int dmg;

    /**
     * Incia los parametros
     * @param x Posicion en X
     * @param y Posicion en Y
     * @param type Tipo de bala
     * @param rot1 Rotacion
     */
    public Shoot (float x , float y, String type, float rot1)
    {
        this.x = x;
        this.y = y;
        this.sx = SIZEX;
        this.sy = SIZEY;
        this.type=type;
        this.life =1;
        this.dmg = 1;
        this.rot = rot1;
    }

    /**
     * Actualiza los atributos de las balas
     */
    @Override
    public void update()
    {
	if(type.equalsIgnoreCase("Heroe")){
            y += SPEED* Math.sin(Math.toRadians(rot));
            x += SPEED*Math.cos(Math.toRadians(rot));
        }
        
	if(type.equalsIgnoreCase("enemigo")){
        y -= SPEED;
        x += SPEED;
        }
    }

    /**
     * Dibuja las balas
     */
    @Override
    public void render() {
        Draw.drawBullet(x,y,sx/2,sy/2, rot);
    }
    
}
