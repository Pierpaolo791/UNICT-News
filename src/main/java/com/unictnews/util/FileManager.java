
package com.unictnews.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileManager {
    
    private static final String PATH = "newsunict.txt";
    
    public static String read() {
        try {
            return new String(Files.readAllBytes(Paths.get(PATH)));
        } catch (IOException ex) {
            try {
                Files.write(Paths.get(PATH), "".getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex1) {
                
            }
            
        }
        return null;
    }
    public static void write(String str) {
        try {
            Files.write(Paths.get(PATH), "\n".concat(str).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
        }
    }
    
}