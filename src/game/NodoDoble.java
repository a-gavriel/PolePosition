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
public class NodoDoble {
    

    private GameObject Data;
    private NodoDoble next;
    private NodoDoble prev;

    public NodoDoble(GameObject Enemigo){
        prev = null;
        next = null;
        this.Data = Enemigo;

    }
    public GameObject getData(){
        return Data;

    }
    public void setData(GameObject Enemigo){
        Data = Enemigo;
    }
    
    public void setNext()
    {
        this.next = null;
    }
    
    public NodoDoble getNext()
    {
        return next;
    }
   
    public void setNext(NodoDoble nextNode)
    {
        this.next = nextNode;
    }
    public NodoDoble getPrev()
    {
        return prev;
    }
    public void setPrev()
    {
        this.prev = null;
    }
    public void setPrev(NodoDoble prevNode)
    {
        this.prev = prevNode;
    }
    
    
    
    
}
