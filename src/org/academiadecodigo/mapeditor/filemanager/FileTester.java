package org.academiadecodigo.mapeditor.filemanager;

import java.io.IOException;

/**
 * Created by codecadet on 25/10/16.
 */
public class FileTester {

    public static void main(String[] args) {

        FileManager fm = new FileManager();

        int[][] matrix = new int[4][4];
        int[][] newMatrix = new int[4][4];
        int[] intArray;
        char[] text;
        String output = "";
        char[] test;
        int[] newArray;

        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                matrix[i][j] = (int)(Math.random() * 2);

        printMatrix(matrix);

        intArray = matrixToArray(matrix);
        text = arrayToChar(intArray);

        System.out.println(intArray.length);
        System.out.println((int)Math.sqrt(intArray.length));

        try {
            //fm.writeFile("map.txt", text);
            output = fm.readFile("map.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        test = output.toCharArray();

        System.out.print("outp: " + output);
        System.out.print("test: ");

        newArray = charToInt(test);

        for(int i = 0; i < test.length; i++)
            System.out.print(test[i]);

        System.out.print("newA: ");
        for(int i = 0; i < newArray.length; i++)
            System.out.print(newArray[i]);

        newMatrix = arrayToMatrix(newArray);

        System.out.println();
        printMatrix(newMatrix);
    }

    public static int[] matrixToArray(int[][] matrix){
        int[] intArray = new int[matrix.length*matrix.length];

        for(int i = 0; i < matrix.length; i ++) {
            for(int j = 0; j < matrix.length; j ++) {
                intArray[(i * matrix.length) + j] = matrix[i][j];
            }
        }

        return intArray;
    }


    public static char[] arrayToChar(int[] array){

        char[] text = new char[array.length];

        for(int i = 0; i < array.length; i++){
            text[i] = Integer.toString(array[i]).charAt(0);
        }

        return text;
    }

    public static int[] charToInt(char[] text){

        //the -1 is to ignore the \n at the end of the char array that came from the string conversion

        int[] array = new int[text.length-1];


        for (int i = 0; i < text.length-1; i++){
            array[i] = Character.getNumericValue(text[i]);
        }

        return array;
    }

    public static int[][] arrayToMatrix(int[] array){

        int size = (int)Math.sqrt(array.length);
        int[][] matrix = new int[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                matrix[i][j] = array[(i*size) + j];
            }
        }

        return matrix;
    }

    public static void printMatrix(int[][] matrix){

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0;  j < matrix.length; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
