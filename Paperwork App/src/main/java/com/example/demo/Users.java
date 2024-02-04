package com.example.demo;

import java.io.*;
import java.util.ArrayList;

public class Users {
    public static String firstName;
    public String lastName;
    public  String username;
    public String password;
    public String tcNo;
    public String employee;

    public Users(String firstName,String lastName,String tcNo,String employee,String username,String password){
        this.firstName=firstName;
        this.lastName=lastName;
        this.tcNo=tcNo;
        this.username=username;
        this.password=password;
        this.employee=employee;
    }
    public  void UsersFile() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("users.txt"));
        StringBuilder exitData = new StringBuilder();
        String line ;

        while ((line = reader.readLine()) != null){
            exitData.append(line);
            exitData.append("\n");
        }

        reader.close();
        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter("users.txt"));
        writer.write(exitData.toString());
        writer.write("\n"+"Firstname= "+ firstName+ "\n"+
                "Lastname= "+ lastName+"\n"+
                "TcNo= "+tcNo+"\n"+
                employee+"-"+
                username+"-"+password
                );
        writer.newLine();
        writer.close();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTcNo() {
        return tcNo;
    }

    public  String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
