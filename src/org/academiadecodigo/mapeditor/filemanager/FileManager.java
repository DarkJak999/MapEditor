package org.academiadecodigo.mapeditor.filemanager;

import java.io.*;

/**
 * Created by codecadet on 25/10/16.
 */
public class FileManager {

    //this is the file manager class that will read and write a file that can host a saved pattern


    //write into the file
    public static void writeFile(String path, char[] text) throws IOException {

        // create a new writer - remember that not adding the append option will result on the default: false;
        // wrap the file writer using a buffered writer to add more functionality
        BufferedWriter bWriter = new BufferedWriter(new FileWriter(path));

        bWriter.write(text);

        bWriter.flush(); //remember to flush so that the information is written on the file.

        bWriter.close(); //auto-flush is also done on close

        //comment the flush and close and see what happens to the file.

    }

    //read from file
    public static String readFile(String path) throws IOException {

        BufferedReader bReader = new BufferedReader(new FileReader(path));

        String line = "";
        String result = "";

        // using the buffered reader we can read lines
        while((line = bReader.readLine()) != null) {
            result += line + "\n";
        }

        bReader.close();


        return result;
    }
}
