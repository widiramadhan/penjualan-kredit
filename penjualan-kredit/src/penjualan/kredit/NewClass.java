/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.kredit;

/**
 *
 * @author WIDI
 */
public class NewClass {
    private String NoOtomatis()
    {
         String Next_nobp;
         String no=null;
         int Next_nobp_int=0;
    try{
        String sql = "Select *from kategori ";
        ResultSet rs = kon.st.executeQuery(sql);
         if(rs.last()){
                Next_nobp=rs.getString("id_kategori");
                Next_nobp_int=Integer.parseInt(Next_nobp) + 1;
                no=String.valueOf(Next_nobp_int);
                tid_kategori.setText(no);
        }else{
             no=String.valueOf(1);
             tid_kategori.setText(no);
         }
    }catch (Exception e){     
    }return no;
}
