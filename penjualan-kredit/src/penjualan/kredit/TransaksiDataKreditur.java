/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.kredit;

import penjualan.kredit.config.Koneksi;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.io.*;
import java.util.logging.*;

/**
 *
 * @author Widi Ramadhan
 */
public class TransaksiDataKreditur extends javax.swing.JDialog {
    Koneksi kon = new Koneksi();
    public Transaksi trans = null;
    private Object [][] datakreditur=null;
    private String[]label={"Nama Lengkap","Nomor Identitas","Pekerjaan","Email","Nomor Telepon"};

    public TransaksiDataKreditur(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        kon.setKoneksi();
        setSize(670,400);
        BacaTabelKreditur();
    }
    
    public void BacaTabelKreditur(){
        try{
            String sql="select * from kreditur A, data_pekerjaan B WHERE A.id_kreditur=B.id_kreditur";
            kon.rs=kon.st.executeQuery(sql);
            ResultSetMetaData m=kon.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            datakreditur=new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                datakreditur[x][0]=kon.rs.getString("nama_lengkap_kreditur");
                datakreditur[x][1]=kon.rs.getString("no_identitas");
                datakreditur[x][2]=kon.rs.getString("nama_pekerjaan");
                datakreditur[x][3]=kon.rs.getString("email");
                datakreditur[x][4]=kon.rs.getString("no_telp");
                x++;
            }
            tabel_kreditur.setModel(new DefaultTableModel(datakreditur,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    private void Pencarian(){
        try{
            String sql="select * from kreditur A, data_pekerjaan B WHERE B.id_kreditur=A.id_kreditur AND A.nama_lengkap_kreditur like '%" +tcari.getText()+ "%' ";
            kon.rs=kon.st.executeQuery(sql);
            ResultSetMetaData m=kon.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            datakreditur=new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                datakreditur[x][0]=kon.rs.getString("nama_lengkap_kreditur");
                datakreditur[x][1]=kon.rs.getString("no_identitas");
                datakreditur[x][2]=kon.rs.getString("nama_pekerjaan");
                datakreditur[x][3]=kon.rs.getString("email");
                datakreditur[x][4]=kon.rs.getString("no_telp");
                x++;
            }
            tabel_kreditur.setModel(new DefaultTableModel(datakreditur,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tcari = new javax.swing.JTextField();
        bCari = new javax.swing.JButton();
        bAll = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_kreditur = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Cari berdasarkan Nama Kreditur :");

        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tcariKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tcariKeyTyped(evt);
            }
        });

        bCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/cari.png"))); // NOI18N
        bCari.setText("Cari");
        bCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCariActionPerformed(evt);
            }
        });

        bAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/refresh.png"))); // NOI18N
        bAll.setText("Tampilkan Semua");
        bAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAllActionPerformed(evt);
            }
        });

        tabel_kreditur.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_kreditur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_krediturMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_kreditur);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/manajemen_akun.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bCari)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bAll))
                        .addComponent(jSeparator1)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCari)
                    .addComponent(bAll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-695)/2, (screenSize.height-395)/2, 695, 395);
    }// </editor-fold>//GEN-END:initComponents

    private void tcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Pencarian();
        }
    }//GEN-LAST:event_tcariKeyPressed

    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariActionPerformed
        // TODO add your handling code here:
        Pencarian();
    }//GEN-LAST:event_bCariActionPerformed

    private void bAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAllActionPerformed
        // TODO add your handling code here:
        BacaTabelKreditur();
    }//GEN-LAST:event_bAllActionPerformed

    private void tabel_krediturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_krediturMouseClicked
        // TODO add your handling code here:
        int tblkreditur = tabel_kreditur.getSelectedRow();
        trans.namaKreditur = tabel_kreditur.getValueAt(tblkreditur, 0).toString();
        this.dispose();
    }//GEN-LAST:event_tabel_krediturMouseClicked

    private void tcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyTyped
       Pencarian();
    }//GEN-LAST:event_tcariKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TransaksiDataKreditur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransaksiDataKreditur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransaksiDataKreditur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiDataKreditur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               TransaksiDataKreditur dialog = new TransaksiDataKreditur(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAll;
    private javax.swing.JButton bCari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTable tabel_kreditur;
    private javax.swing.JTextField tcari;
    // End of variables declaration//GEN-END:variables
}
