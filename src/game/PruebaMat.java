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
public class PruebaMat {
    public static void main(String[] args)  {

    int[][] testData = new int[10][10];

    for (int i = 0; i < testData.length; i++) 
        for (int j = 0; j < testData[i].length; j++)
            testData[i][j] =   10+i+j;

    Matrix full = new Matrix(testData);

    System.out.println("Full test matrix:");
    System.out.println(full);

    System.out.println();

    int x = 3;
    int y = 2;
    int sx = 3;
    int sy = 2;
    System.out.println("Part of the matrix:");
    
    System.out.println(full.getSubMatrix(2, 3, 3, 4));

}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
