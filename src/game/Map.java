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
    
    public static void printmat(int [][] mat){
        int l = mat.length;
        String st = "";
        for (int i = 0; i<l ; ++i){
            for (int j = 0; j<l; ++j){
                int a = mat[i][j];
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
            System.out.println("Matrix Read");            
            //printmat();
            //printlist(matriz[35]);
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
        /// bloques 5 7 y 9 están sin utilizar (huecos, vidas, turbos)
        for (int i = 0; i< z ; ++i){
            for (int j = 0; j<z ;++j){
                if (matriz[j][i] == 0 ){
                        Draw.rect(i*size,(z-j)*size,size,size,0.6f,0.57f,0.4f);
                    }                
                if (matriz[j][i] == 1 ){
                        Draw.rect(i*size,(z-j)*size,size,size,1f,0f,0f);                        
                    }
                if (matriz[j][i] == 2 ){
                        Draw.rect(i*size,(z-j)*size,size,size,0.25f,0.25f,0.25f);                        
                    }
                if (matriz[j][i] == 3 ){
                        Draw.rect(i*size,(z-j)*size,size,size,1f,1f,1f);                        
                    }
                if (matriz[j][i] == 4 ){
                        Draw.rect(i*size,(z-j)*size,size,size,0.4f,0.4f,0.4f);                        
                    }
                if (matriz[j][i] == 5 ){
                        Draw.rect(i*size,(z-j)*size,size,size,0.17f,0.62f,0.1f);                        
                    }
                if (matriz[j][i] == 6 ){
                        Draw.rect(i*size,(z-j)*size,size,size,0.3f,0.96f,0f);                        
                    }
                if (matriz[j][i] == 7 ){
                        Draw.rect(i*size,(z-j)*size,size,size,0.6f,0.6f,0.6f);                        
                    }
                if (matriz[j][i] == 8 ){
                        Draw.rect(i*size,(z-j)*size,size,size,0.75f,0.75f,0.75f);                        
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

    public void render(Player pl){        
        //printmat();
        int x = pl.getMatx();
        int y = pl.getMaty();
        int newMat[][] = {        
                   
        {matriz[y-3][x-2],matriz[y-3][x-1],matriz[y-3][x],matriz[y-3][x+1],matriz[y-3][x+2]},
        {matriz[y-2][x-2],matriz[y-2][x-1],matriz[y-2][x],matriz[y-2][x+1],matriz[y-2][x+2]},
        {matriz[y-1][x-2],matriz[y-1][x-1],matriz[y-1][x],matriz[y-1][x+1],matriz[y-1][x+2]},
        {matriz[ y ][x-2],matriz[ y ][x-1],matriz[ y ][x],matriz[ y ][x+1],matriz[ y ][x+2]},
        {matriz[y+1][x-2],matriz[y+1][x-1],matriz[y+1][x],matriz[y+1][x+1],matriz[y+1][x+2]}
                
                };
   
        int size2 = 40;
        printmat(newMat);
        
        System.out.println("--+++----");
        
        
        String sb = "";
        int ijmat = 0;
        
        for (int i = y-3; i < y + 2; i++) {
            for (int j = x-2; j < x + 3; j++){
                ijmat = matriz[i][j];
                sb += (ijmat);
            }
            sb+=("\n");
        }
        System.out.println(sb);
        System.out.println("---------");
        
        int z = newMat.length;
        /// bloques 5 7 y 9 están sin utilizar (huecos, vidas, turbos)
        for (int i = 0; i<z ; ++i){
            for (int j = 0; j<z ;++j){
                if (newMat[j][i] == 0 ){
                        Draw.rect(i*size2,(z-j)*size2,size2,size2,0.6f,0.57f,0.4f);
                    }                
                if (newMat[j][i] == 1 ){
                        Draw.rect(i*size2,(z-j)*size2,size2,size2,1f,0f,0f);                        
                    }
                if (newMat[j][i] == 2 ){
                        Draw.rect(i*size2,(z-j)*size2,size2,size2,0.25f,0.25f,0.25f);                        
                    }
                if (newMat[j][i] == 3 ){
                        Draw.rect(i*size2,(z-j)*size2,size2,size2,1f,1f,1f);                        
                    }
                if (newMat[j][i] == 4 ){
                        Draw.rect(i*size2,(z-j)*size2,size2,size2,0.4f,0.4f,0.4f);                        
                    }
                if (newMat[j][i] == 5 ){
                        Draw.rect(i*size2,(z-j)*size2,size2,size2,0.17f,0.62f,0.1f);                        
                    }
                if (newMat[j][i] == 6 ){
                        Draw.rect(i*size2,(z-j)*size2,size2,size2,0.3f,0.96f,0f);                        
                    }
                if (newMat[j][i] == 7 ){
                        Draw.rect(i*size2,(z-j)*size2,size2,size2,0.6f,0.6f,0.6f);                        
                    }
                if (newMat[j][i] == 8 ){
                        Draw.rect(i*size2,(z-j)*size2,size2,size2,0.75f,0.75f,0.75f);                        
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
