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
public class Matrix {
    int[][] data;
    int x, y, columns, rows;

    public Matrix(int[][] data) {
        this(data, 0, 0, data.length, data[0].length);
    }

    private Matrix(int[][] data, int x, int y, int columns, int rows) {
        this.data = data;
        this.x = x;
        this.y = y;
        this.columns = columns;
        this.rows = rows;
    }

    public Matrix getSubMatrix(int x, int y, int columns, int rows) {
        return new Matrix(data, this.x + x , this.y + y, columns, rows);
    }
    
    public Matrix getArMatrix(int x, int y, int columns, int rows) {
        return new Matrix(data, this.x + x - columns, this.y + y - rows, 2*columns+1, 2*rows+1);
    }

    public String toString() {

        StringBuffer sb = new StringBuffer();

        for (int i = y; i < y + rows; i++) {
            for (int j = x; j < x + columns; j++)
                sb.append(data[i][j]).append(" ");

            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);

        return sb.toString();
    }
}
