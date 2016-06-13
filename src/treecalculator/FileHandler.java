/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treecalculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Ricardo
 */
public class FileHandler {
     public FileHandler(){

    }
    /**
     * 
     * @param file
     * @return 
     */
    public ArrayList read(String file){
        ArrayList<String> matrix = new ArrayList();
        try{
            Scanner scanner = new Scanner(new File(file));
            scanner.useDelimiter("\n");

            while (scanner.hasNext()){
                String line = scanner.next();
                matrix.add(line);
            }
            scanner.close();

        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return matrix;
    }
    /**
     * 
     * @param file
     * @param str
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException 
     */
    public void write(String file, String str) throws FileNotFoundException, UnsupportedEncodingException{
        File f = new File(file);
        if(f.exists()) { 
               PrintWriter writer = new PrintWriter(new FileOutputStream(new File(file), true));
               writer.append(str);
               writer.append("\n");
               writer.close();
        }else{
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.println(str);
            writer.close();
        }
    }
}
