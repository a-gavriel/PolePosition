package game;

/**
 * Clase encargada de guardar la información
 */
public class NodoDoble {
    /**
     * Información a guardar
     */
    private GameObject Data;
    /**
     * Siguiente elemento
     */
    private NodoDoble next;
    /**
     * Elemento anterior
     */
    private NodoDoble prev;

    /**
     * Constructor de la clase
     * @param Enemigo elemento a guardar
     */
    public NodoDoble(GameObject Enemigo){
        prev = null;
        next = null;
        this.Data = Enemigo;

    }

    /**
     * Obtiene la información que guarda el nodo
     * @return información que guarda el nodo
     */
    public GameObject getData(){
        return Data;

    }

    /**
     * Establace la inforación que guardará el nodo
     * @param Enemigo Elemento a guardar
     */
    public void setData(GameObject Enemigo){
        Data = Enemigo;
    }

    /**
     * Establece el siguiente nodo
     */
    public void setNext()
    {
        this.next = null;
    }

    /**
     * Obtiene el nodo siguiente
     * @return nodo siguiente
     */
    public NodoDoble getNext()
    {
        return next;
    }

    /**
     * Establece el nodo siguiente
     * @param nextNode nodo siguiente
     */
    public void setNext(NodoDoble nextNode)
    {
        this.next = nextNode;
    }

    /**
     * Obtiene el nodo anterior
     * @return nodo anterior
     */
    public NodoDoble getPrev()
    {
        return prev;
    }

    /**
     * Establece el nodo anterior
     */
    public void setPrev()
    {
        this.prev = null;
    }

    /**
     * Establece el nodo anterior
     * @param prevNode nodo anterior
     */
    public void setPrev(NodoDoble prevNode)
    {
        this.prev = prevNode;
    }     
}

