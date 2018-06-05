/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Alexis
 */
public class Map {
    
    public static int size = 4;
    public static int[][] matriz = new int[168][168];
    
    
    public static void printlist(int list[]){
        int l = matriz.length;
        String st = "";
        for (int j = 0; j<l; ++j){
            int a = list[j];
            st += (a);
        }
        st += "\n";
        
        System.out.println(st);
    }
    
    public static void printmat(){
        int l = matriz.length;
        String st = "";
        for (int i = 0; i<l ; ++i){
            for (int j = 0; j<l; ++j){
                int a = matriz[i][j];
                st += (a);
            }
            st += "\n";
        }
        System.out.println(st);
    }
    
    public static void readmat(){
        // The name of the file to open.
        String fileName = "map.txt";
        // This will reference one line at a time
        String line = null;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            int i = 0;
            while((line = bufferedReader.readLine()) != null) {
                char [] x = line.toCharArray();
                int size = x.length;
                for (int j=0;j<size;++j){
                    matriz[i][j] = Character.getNumericValue(x[j]);
                }
                i+=1;
            }   
            printmat();
            printlist(matriz[35]);
            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    
    }
    
    
    public void render(){        
        //printmat();
        int z = matriz.length;
        
        for (int i = 0; i< z ; ++i){
            for (int j = 0; j<z ;++j){
                if (matriz[j][i] == 0 ){
                        Draw.rect(i*size,(z-j)*size,size,size);
                    }
            }
            /*
            for (int j = 0; j < z; ++j){               
                if (matriz[i][j] == 0 ){
                    Draw.rect(j*size,(z-i)*size,j*size+size,(z-i)*size+size);
                }
            }
            */
        }
    }    
    
    
    
}
