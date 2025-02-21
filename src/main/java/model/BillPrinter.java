package main.java.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BillPrinter implements BillPrinterInterface {

    public BillPrinter (){}

    @Override
    public void printBillFromFile (String filePath) {
        try (FileInputStream fi = new FileInputStream(filePath)){
            BufferedInputStream bi = new BufferedInputStream(fi);
            int c;
            StringBuilder sb = new StringBuilder();
            while ((c = bi.read()) != -1){
                sb.append((char) c);
            }

            System.out.println(sb);
        }
        catch (IOException e){
            System.out.println("Error during reading file!");
        }
    }
}
