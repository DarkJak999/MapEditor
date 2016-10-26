package org.academiadecodigo.mapeditor;

import javafx.scene.control.*;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 25/10/16.
 */
public class Grid {

    private static final int CELL_SIZE = 40;
    private static final int PADDING = 10;
    private int cols;
    private int rows;
    private int[][] matrix;
    private Color currentColor;
    private Ellipse blue;
    private Ellipse black;
    private Ellipse red;
    private Picture eraser;
    private Ellipse green;


    public Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        this.matrix = new int[cols][rows];
        currentColor = Color.BLACK;
    }

    public void drawClearGrid(){

        Rectangle rect;

        for(int i = 0; i < cols; i++){
            for(int j = 0; j < rows; j++){
                matrix[i][j] = 0;
                rect = new Rectangle(i*CELL_SIZE+PADDING, j*CELL_SIZE+PADDING, CELL_SIZE, CELL_SIZE);
                rect.draw();
            }
        }

        drawColors();
    }

    public void drawPaintedGrid(int[][] matrix){

        Rectangle rect;

        for(int i = 0; i < cols; i++){
            for(int j = 0; j < rows; j++){
                rect = new Rectangle(i*CELL_SIZE+PADDING, j*CELL_SIZE+PADDING, CELL_SIZE, CELL_SIZE);
                
                if(matrix[i][j] == 0) {
                    rect.setColor(Color.BLACK);
                    rect.draw();
                }else{
                    rect.setColor(getColor(matrix[i][j]));
                    rect.fill();
                }
            }
        }
    }

    public Color getColor(int number){

        switch(number){

            case 0:
                return Color.WHITE;
            case 1:
                return Color.BLACK;
            case 2:
                return Color.RED;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.GREEN;

        }

        return Color.WHITE;

    }

    public void drawColors(){

        blue = new Ellipse(cols * CELL_SIZE + PADDING*2, 0 + PADDING,20,20);
        blue.setColor(Color.BLUE);
        blue.fill();

        black = new Ellipse(cols * CELL_SIZE + PADDING*2, CELL_SIZE, 20, 20);
        black.setColor(Color.BLACK);
        black.fill();

        eraser = new Picture(cols * CELL_SIZE + PADDING*2, CELL_SIZE * 1.5, "3715-200.png");
        eraser.draw();

    }

    public void paintCell(int x, int y){

        Rectangle rect = new Rectangle(x + PADDING, y + PADDING, CELL_SIZE, CELL_SIZE);
        Rectangle clear = new Rectangle(x + PADDING, y + PADDING, CELL_SIZE, CELL_SIZE);
        rect.setColor(currentColor);
        clear.setColor(Color.WHITE);

        int matrixX = x/CELL_SIZE;
        int matrixY = y/CELL_SIZE;

        if(matrix[matrixX][matrixY] == 0) {
            matrix[matrixX][matrixY] = 1;
            rect.fill();
        }else{
            clear.fill();
            matrix[matrixY][matrixY] = 0;
            rect.setColor(Color.BLACK);
            rect.draw();
        }

    }

    public void printMatrix(){

        for(int i = 0; i < cols; i++){
            for(int j = 0; j < rows; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    public void deleteGrid(){

        Rectangle rect = new Rectangle(PADDING, PADDING, cols*CELL_SIZE, cols*CELL_SIZE);
        rect.setColor(Color.WHITE);
        rect.fill();
    }

    public int[][] getClearMatrix(){

        for(int i = 0; i < cols; i ++){
            for (int j = 0; j < rows; j++)
                matrix[i][j] = 0;
        }

        return matrix;
    }

    public int getWidth(){
        return cols * CELL_SIZE + PADDING;
    }

    public int getHeigth(){
        return rows * CELL_SIZE + PADDING;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getBlueX(){
        return blue.getX();
    }

    public int getBlueY(){
        return blue.getY() + 25;
    }

    public int getBlackX(){
        return black.getX();
    }

    public int getBlackY(){
        return black.getY() + 25;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }
}
