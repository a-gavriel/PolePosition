package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 * Clase encargada de cargar y modificar el mapa
 */
public class Map {
    
    public static int size = 4;
    public static int[][] matriz = new int[168][168];


    /**
     * Recorre y muestra en consola el mapa
     * @param list lista a mostrar
     */
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

    /**
     * Recorre y muestra en consola el mapa
     * @param mat matriz a mostrar
     */
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

    /**
     * Carga la textura
     * @param key nombre del archivo
     * @return textura cargada
     */
    public static Texture loadTexture(String key) {
        try {
            return TextureLoader.getTexture("png", new FileInputStream(new File("res/" + key + ".png")));
        } catch (IOException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }


    /**
     * Lee y carga el mapa en la matriz principal
     */
    public static void readmat() {
        String fileName = "map.txt";
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

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
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println( "Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    
    }

    /**
     * Recorre y dibuja los elementos de la matriz
      */
    public void render(){
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

    /**
     * Dibuja al jugador en el mapa
     * @param pl jugador a dibujar
     */
    public void render(Player pl){        
        //printmat();
        int x = pl.getMatx();
        int y = pl.getMaty();
        int sizeW = Display.getWidth() /5;
        int sizeH = Display.getHeight() /5;       
                
        String sb = "";
        int ijmat1 = 0;
        
        for (int i = y-4; i < y + 1; i++) {
            for (int j = x-2; j < x + 3; j++){
                ijmat1 = matriz[i][j];
                sb += (ijmat1);
            }
            sb+=("\n");
        }
        
        int z = matriz.length;
        /// bloques 5 7 y 9 están sin utilizar (huecos, vidas, turbos)
        int ijmat;
        int i = 4;
        int j = 0;
        for (int ii = y-4; ii < y + 1; ii++) {
            for (int jj = x-2; jj < x + 3; jj++){
                ijmat = matriz[ii][jj];
                
                if (ijmat == 0 ){
                        Draw.rect(j*sizeW,(i)*sizeH,sizeW,sizeH,0.6f,0.57f,0.4f);
                    }                
                if (ijmat == 1 ){
                        Draw.rect(j*sizeW,(i)*sizeH,sizeW,sizeH,1f,0f,0f);                        
                    }
                if (ijmat == 2 ){
                        Draw.rect(j*sizeW,(i)*sizeH,sizeW,sizeH,0.25f,0.25f,0.25f);                        
                    }
                if (ijmat == 3 ){
                        Draw.rect(j*sizeW,(i)*sizeH,sizeW,sizeH,1f,1f,1f);                        
                    }
                if (ijmat == 4 ){
                        Draw.rect(j*sizeW,(i)*sizeH,sizeW,sizeH,0.4f,0.4f,0.4f);                        
                    }
                if (ijmat == 5 ){
                        Draw.rect(j*sizeW,(i)*sizeH,sizeW,sizeH,0.17f,0.62f,0.1f);                        
                    }
                if (ijmat == 6 ){
                        Draw.rect(j*sizeW,(i)*sizeH,sizeW,sizeH,0.3f,0.96f,0f);                        
                    }
                if (ijmat == 7 ){
                        Draw.rect(j*sizeW,(i)*sizeH,sizeW,sizeH,0.6f,0.6f,0.6f);                        
                    }
                if (ijmat == 8 ){
                        Draw.rect(j*sizeW,(i)*sizeH,sizeW,sizeH,0.75f,0.75f,0.75f);                        
                    }
                ++j;
                
            }
            j=0;
            --i;
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
