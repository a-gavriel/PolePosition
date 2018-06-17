/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Rectangle;

/**
 *
 * @author Alexis
 */
public class Physics {
    public static boolean checkCollisions(GameObject go1, GameObject go2)
    {
        Rectangle r1 = new Rectangle((int)go1.getX(),(int)go1.getY(),(int)go1.getSX(),(int)go1.getSY());
        Rectangle r2 = new Rectangle((int)go2.getX(),(int)go2.getY(),(int)go2.getSX(),(int)go2.getSY());
        
        return r1.intersects(r2)|| checkIntersect(go1,  go2);
    }
    
    
    public static boolean checkIntersect(GameObject go1, GameObject go2){
        //p1 =   X   , Y
        //p2 =   X+sx, Y
        //p3 =   X+sx,Y+sy
        //p4 =   X   , sy
        float X1 = go1.getX();
        float X2 = X1 + go1.getSX();
        float Y1 = go1.getY();
        float Y2 = Y1 + go1.getSY();
        
        float A1 = go2.getX();
        float A2 = A1 + go2.getSX();
        float B1 = go2.getY();
        float B2 = B1 + go2.getSY();
            
        if(( X1 < A1 && A1 < X2 ) && (( Y1 < B1  && B1 < Y2)))
            return true;
        if(( X1 < A2 && A2 < X2 ) && (( Y1 < B1  && B1 < Y2)))
            return true;
        if(( X1 < A2 && A2 < X2 ) && (( Y1 < B2  && B2 < Y2)))
            return true;
        if(( X1 < A1 && A1 < X2 ) && (( Y1 < B2  && B2 < Y2)))
            return true; 
        if((A1 < X1 && X1 < A2 ) && (( B1 < Y1  && Y1 < B2)))
            return true;
        if((A1 < X2 && X2 < A2 ) && (( B1 < Y1  && Y1 < B2)))
            return true;
        if((A1 < X2 && X2 < A2 ) && (( B1 < Y2  && Y2 < B2)))
            return true;
        if((A1 < X1 && X1 < A2 ) && (( B1 < Y2  && Y2 < B2)))
            return true; 
        else
            return false;
    }
    
    
    public static int checkwithArray (GameObject go, GameObject[] array){
        for (int i =0; i<3 ; ++i)
            if (checkCollisions(go, array[i])){
                System.out.println("killed: "+ i);
                return i;                
            }
        return 0;
    }
    
    public static boolean checkwithListaDoble (GameObject go, ListaDoble lista )
    {
        NodoDoble current = lista.getHead();
        while (current != null)
        {
            if (checkCollisions( go,  current.getData()))
            {
                System.out.println("Mate a: " + ((Player)(current.getData())).getName());
                return true;
            }
            
            current = current.getNext();
        }
        return false;
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
