/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import org.lwjgl.opengl.Display;

/**
 *
 * @author Alexis
 */
public class ListaDoble {
    
    //private NodoDoble tail;
    private NodoDoble head;
    private NodoDoble last;
    private int size;
    

    public ListaDoble(){
        this.last = null;
        this.head = null;
        this.size = 0;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public NodoDoble getHead()
    {
        return head;
    }
    
    public int getSize(){
        return size;
    }
    
    
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
    
    public void Renderall()
    {
        NodoDoble Current = head;
        while (Current != null)
        {
            Current.getData().render();
            Current = Current.getNext();
        }
        
    }
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
    public NodoDoble peek()
    {
        return head;
    }
    public void setHead(NodoDoble newhead)
    {
        head = newhead;
    }
    
    public NodoDoble getLast()
    {
        return last;
    }
    
    public void setLast(NodoDoble newlast)
    {
        last = newlast;
    }
    public void reducir()
    {
        size -= 1;
    }
    
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