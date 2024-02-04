package com.example.demo;

import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;


public class Document {
    Random rnd = new Random();

    public static ArrayList<Document> documentList = new ArrayList<>();
    static private int no =0;
    private int serialNo;
    private String category;
    private String added_by;
    protected int code;
    private Users signatory;
    private boolean validity;
    public String isim;
    private final String date;

    public Document(String category, Users added_by) {
        for (int i=0;i<documentList.size();i++) {
            if (documentList.get(i).getSerialNo()==no) {
                no++;
                i=0;
            }
        }
        int tmp = rnd.nextInt(10000)+1;
        if (no == 0)
            this.code=tmp;
        else {
            for (int i =0;i<documentList.size();i++) {
                if (documentList.get(i).getCode() == tmp) {
                    tmp = rnd.nextInt(10000)+1;
                    i=0;
                }
                if (i==documentList.size()-1) {
                    this.code=tmp;
                    break;
                }
            }
        }
        this.date = String.valueOf(LocalDate.now());
        this.serialNo =no++;
        this.category = category;
        this.added_by = currentUser.username;
        this.validity = true;
        this.signatory=null;

    }

    public Document(int serialNo,int code, String category, String date, boolean validity,String isim) {
        this.serialNo = serialNo; // seri numarası
        this.code = code; // doğrulama kodu gibi bir şey
        this.category = category; // kategorisi
        this.added_by = null; // ekleyen
        this.date=date;  // tarih
        this.signatory = null; // imzalayan
        this.validity = validity; // geçerlilik
        this.isim=isim;
        no = serialNo+1; // otomatik seri no su atamak için
    }



    public void DocFile() throws IOException{
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("documents.txt"));
        StringBuilder exitData = new StringBuilder();
        String line ;

        while ((line = reader.readLine()) != null){
            exitData.append(line);
            exitData.append("\n");
        }

        reader.close();
        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter("documents.txt"));
        writer.write(exitData.toString());

        writer.write( serialNo+","+ + code+","+ category+","+ date+","+ validity+","+ currentUser.username+","+ signatory+"\n"
        );
        writer.newLine();
        writer.close();
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }
    public boolean isValidity() {
        return validity;
    }
    public static ArrayList<Document> getDocumentList() {
        return documentList;
    }
    public int getCode() {
        return code;
    }
    public String getIsım(){
        return  currentUser.username;
    }

    public int getSerialNo() {
        return serialNo;
    }
    public String getCategory() {
        return category;
    }
    public Users getSignatory() {
        return signatory;
    }
    public String getDate() {
        return date;
    }





    public void setCategory(String category) {
        this.category = category;
    }
    public static void setDocumentList(ArrayList<Document> documentList) {
        Document.documentList = documentList;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public static void setNo(int no) {
        Document.no = no;
    }
    public void setSignatory(Users signatory) {
        this.signatory = signatory;
    }
    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }



}
