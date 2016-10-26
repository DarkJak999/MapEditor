package org.academiadecodigo.mapeditor;

import org.academiadecodigo.mapeditor.filemanager.FileManager;
import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.io.IOException;

/**
 * Created by codecadet on 25/10/16.
 */
public class MapEditor{

    private Grid grid;
    private FileManager fm;
    private Cursor cursor;
    private int cursorLastX;
    private int cursorLastY;
    private int[][] matrix;
    KeyEvents key;

    public MapEditor(int cols, int rows) {
        this.grid = new Grid(cols, rows);
        this.fm = new FileManager();
        this.cursor = new Cursor(0,0,this.grid);
        this.matrix = grid.getClearMatrix();
        this.key = new KeyEvents(this);

    }

    public void init() {
        this.grid.drawClearGrid();
    }

    public void drawFromFile(){

        try {
            matrix = arrayToMatrix(charToInt(stringToChar(fm.readFile("map.txt"))));

            System.out.println("drawing from file");

            this.grid = new Grid(matrix.length, matrix.length);
            grid.drawPaintedGrid(matrix);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static char[] stringToChar(String text) {

        return text.toCharArray();
    }

    public static int[] matrixToArray(int[][] matrix) {
        int[] intArray = new int[matrix.length * matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                intArray[(i * matrix.length) + j] = matrix[i][j];
            }
        }

        return intArray;
    }


    public static char[] arrayToChar(int[] array) {

        char[] text = new char[array.length];

        for (int i = 0; i < array.length; i++) {
            text[i] = Integer.toString(array[i]).charAt(0);
        }

        return text;
    }

    public static int[] charToInt(char[] text) {

        //the -1 is to ignore the \n at the end of the char array that came from the string conversion

        int[] array = new int[text.length - 1];


        for (int i = 0; i < text.length - 1; i++) {
            array[i] = Character.getNumericValue(text[i]);
        }

        return array;
    }

    public static int[][] arrayToMatrix(int[] array) {

        int size = (int) Math.sqrt(array.length);
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = array[(i * size) + j];
            }
        }

        return matrix;
    }

    public static void printMatrix(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    public void saveToFile(){

        //conver the matrix to a char array in order to save it

        try {
            this.matrix = grid.getMatrix();
            this.fm.writeFile("map.txt", arrayToChar(matrixToArray(matrix)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCursor(){
        setCursorLastX(cursor.getX());
        setGetCursorLastY(cursor.getY());
        cursor.delete();
    }

    public void drawCursor(){
        cursor = new Cursor(cursorLastX, cursorLastY, grid);
    }
    public Grid getGrid() {
        return grid;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursorLastX(int cursorLastX) {
        this.cursorLastX = cursorLastX;
    }

    public void setGetCursorLastY(int getCursorLastY) {
        this.cursorLastY = getCursorLastY;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
}
