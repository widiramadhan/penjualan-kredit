/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.Session;

/**
 *
 * @author WIDI
 */
public class SessionUtil {
    Koneksi kon=new Koneksi();
    
    public SessionUtil() {
        kon.setKoneksi();
    }
    
    public void doLogin(String username, String password){
        try{
            String query="select * from pengguna where username='"+username+"' AND password='"+password+"'";
            kon.rs=kon.st.executeQuery(query);   
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }    
    }
    
    public void setSession(String username) {
        try{
            String status="update pengguna set status='0' where username='"+username+"'";
            kon.st.executeUpdate(status);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void doLogout() {
        try{
            String status="update pengguna set status='1'";
            kon.st.executeUpdate(status);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void getSession(){
        try{
            String sql="select * from pengguna A, hak_akses B where A.id_hak_akses=B.id_hak_akses AND A.status='0'";
            kon.rs=kon.st.executeQuery(sql);

            if (kon.rs.next()){
                Session ss = new Session();
                ss.setId_pengguna(kon.rs.getString("id_pengguna"));
                ss.setNama_pengguna(kon.rs.getString("nama"));
                ss.setEmail(kon.rs.getString("email"));
                ss.setHak_akses(kon.rs.getString("nama_hak_akses"));
                ss.setUsername(kon.rs.getString("username"));
                ss.setPassword(kon.rs.getString("password"));
                ss.setStatus(kon.rs.getString("status"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
