/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Widi Ramadhan
 */
public class Koneksi {
    public Connection conn; 
    public Statement st; 
    public ResultSet rs;
    
public Connection setKoneksi() 
{ 
    try{ 
        Class.forName("com.mysql.jdbc.Driver"); 
        //conn=DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net/sql12179880","sql12179880","1mXZWhMqea");
        conn=DriverManager.getConnection("jdbc:mysql://localhost/penjualan-kredit","root","");
        st=conn.createStatement(); 
    } 
    catch(Exception e){ 
        JOptionPane.showMessageDialog(null,"Koneksi ke database gagal"); 
    } 
    return conn; 
}

//public static void main(String args[]){
//    koneksi kon = new koneksi();
//    System.out.printlln("Test db online -> "+kon.setKoneksi);
//}
}
