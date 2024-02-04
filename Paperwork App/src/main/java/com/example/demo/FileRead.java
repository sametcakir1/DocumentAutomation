package com.example.demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileRead {
    public static ArrayList<Document> fileReading(String fileName)  {
        ArrayList<Document> docList = new ArrayList<>();

        try( BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line ;
            while ((line = reader.readLine()) != null){
                String[] value = line.split(",");
                if (value.length == 7) {
                    int serialNo = Integer.parseInt(value[0]);
                    int code = Integer.parseInt(value[1]);
                    String category = value[2];
                    String date = value[3];
                    boolean validity = Boolean.parseBoolean(value[4]);
                    String isim = value[5];
                    Document doc = new Document( serialNo, code,  category,  date,  validity,isim);
                    docList.add(doc);
                }
            }

        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Dosyadan veri okuma işlemi başarısız oldu: " + e.getMessage());
        }
        return docList;
    }}
