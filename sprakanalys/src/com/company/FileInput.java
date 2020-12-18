package com.company;

import java.io.*;
import java.nio.charset.Charset;

public class FileInput {
    String URL = "/Deutch.txt";
    public static String readFile(String URL) {
        String text = new String();
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(URL), "UTF-8"));

            int tempChar;

            while ((tempChar = in.read()) != -1) {
                text += String.valueOf((char) tempChar);

            }

        } catch (FileNotFoundException ex) {
            System.out.println("File was not found: " + ex);
        }catch (IOException ex){
            System.out.println("IOExcpetion occured:" + ex);
        }

        return  text;
    }
}