package game;

import org.lwjgl.opengl.Display;

/**
 * Clase encargada de guardar información en la lista enlazada
 */
public class ListaDoble {

    /**
     * Cabeza de la lista
     */
    private NodoDoble head;
    /**
     * Cola de la lista
     */
    private NodoDoble last;
    /**
     * Tamaño de la lista
     */
    private int size;


    /**
     * Constructor de la clase inicia los atributos
     */
    public ListaDoble(){
        this.last = null;
        this.head = null;
        this.size = 0;
    }

    /**
     * Verifica si la lista es vacía
     * @return Si la lista en vacía
     */
    public boolean isEmpty(){
        return head == null;
    }

    /**
     * Retorna la cabeza de la lista
     * @return Cabeza de la lista
     */
    public NodoDoble getHead() {
        return head;
    }

    /**
     * Retorna el tamaño de la lista
     * @return Tamaño de la lista
     */
    public int getSize(){
        return size;
    }

    /**
     * Inserta un elemento al inicio de la lista
     * @param data Elemento a ingresar
     */
    public void insertFirst(GameObject data){
        if (size != 0)
        {
            NodoDoble newNode = new NodoDoble(data);
            newNode.setNext (head);
            head = newNode;
            head.getNext().setPrev(newNode);
            
            size++;
        }
        else
        {
            NodoDoble newNode = new NodoDoble(data);
            last = newNode;
            head = newNode;
            size++;
        }
    }

    /**
     * Obtiene y elimina el primer elemnto de la lista
     * @return Primer elemento de la lista
     */
    public NodoDoble TakeFirst(){
        if (size > 1){
            NodoDoble temp = head;
            head.getNext().setPrev(null);
            head = head.getNext();
            size--;
            return temp;
        }
        if (size == 1)
        {
            NodoDoble temp = head;
            head = null;
            size--;
            return temp;
        }
        else{
            return null;
        }
        
    }

    /**
     * Recorre la lista y dibuja cada uno de sus elementos
     */
    public void Renderall()
    {
        NodoDoble Current = head;
        while (Current != null)
        {
            Current.getData().render();
            Current = Current.getNext();
        }
        
    }

    /**
     * Recorre la lista y actualiza cada uno de sus elementos
     */
    public void Updateall()
    {
        NodoDoble Current = head;
        while (Current!= null)
        {
            Current.getData().update();
            if ((Current.getData().getY() < 0)|| (Current.getData().getY() > (Display.getHeight() + 10) ))
            {
                delete(Current);
            }
            
            Current = Current.getNext();
        }
        
    }

    /**
     * Obtiene la cabeza de la lista
     * @return Cabeza de la lista
     */
    public NodoDoble peek()
    {
        return head;
    }

    /**
     * Establece la cabeza de la lista
     * @param newhead Elemento a establecer como cabeza
     */
    public void setHead(NodoDoble newhead)
    {
        head = newhead;
    }

    /**
     * Obtiene la cola de la lista
     * @return Cola de la lista
     */
    public NodoDoble getLast()
    {
        return last;
    }

    /**
     * Establece la cola de la lista
     * @param newlast Elemento a establecer como cola
     */
    public void setLast(NodoDoble newlast)
    {
        last = newlast;
    }

    /**
     * Disminuye el tamaño en una unidad
     */
    public void reducir()
    {
        size -= 1;
    }

    /**
     * Elimina un elemento de la lista
     * @param objeto Elemento a eliminar
     */
    public void delete (NodoDoble objeto)
    {
        if (size ==1)
        {
            head = null;
            last = null;
            size = 0;
        }
        else if (objeto == head )
        {
            head = objeto.getNext();
            objeto.getNext().setPrev();
            size-=1;

        }   
        else if (objeto == last)   
        {
            last = objeto.getPrev();
            objeto.getPrev().setNext();
            size-=1;
        }
        else   
        {
            objeto.getPrev().setNext(objeto.getNext());
            objeto.getNext().setPrev(objeto.getPrev());
            size-=1;
        }
    }
}
