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
public class Pembayaran extends javax.swing.JDialog {
    Koneksi kon = new Koneksi();
    public MenuUtama mu = null;

    public Pembayaran(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        kon.setKoneksi();
        ceklogin();
        nonAktif();
        lblIdPengguna.setVisible(false);
        lblTanggal1.setVisible(false);
        
        Date skrg = new Date();
        SimpleDateFormat notrans=new SimpleDateFormat("ddMMyyyyHms");
        txtNoPembayaran.setText(notrans.format(skrg));      
    }
    
    public String noTrans;

    public String getNoTrans() {
        return noTrans;
    }
    
    private void ceklogin(){
        try{
            String sql="select * from pengguna where status='0'";
            kon.rs=kon.st.executeQuery(sql);
            if (kon.rs.next()){
                String id=kon.rs.getString("id_pengguna");
                String nama=kon.rs.getString("nama");
                lblUser.setText(nama);
                lblIdPengguna.setText(id);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void nonAktif(){
        txtAngsuran1.setEnabled(false);
        txtAngsuranKe.setEnabled(false);
        txtDenda1.setEnabled(false);
        txtKembali.setEnabled(false);
        txtNamaKreditur.setEnabled(false);
        txtTglJatuhTempo.setEnabled(false);
    }
    
    private String angsuranKe(){
        String Next_nobp;
        String no=null;
        int Next_nobp_int=0;
        
        try{
            String sql = "Select * from pembayaran ";
            ResultSet rs = kon.st.executeQuery(sql);
            if(rs.last()){
                Next_nobp=rs.getString("angsuran_ke");
                Next_nobp_int=Integer.parseInt(Next_nobp) + 1;
                no=String.valueOf(Next_nobp_int);
                txtAngsuranKe.setText(no);
            }else{
                no=String.valueOf(1);
                txtAngsuranKe.setText(no);
            }
        }catch (Exception e){   
            JOptionPane.showMessageDialog(null,"Error"+e);
        }return no;
    }

    public void setTransaksi(){
        try{
            String sql="select * from transaksi A, kreditur B, produk C WHERE A.id_kreditur=B.id_kreditur AND A.kode_produk=C.kode_produk AND A.no_transaksi='"+txtNoTransaksi.getText()+"'";
            kon.rs=kon.st.executeQuery(sql);
            if (kon.rs.next()){
                String noTrans=kon.rs.getString("id_kreditur");
                String namaKreditur=kon.rs.getString("nama_lengkap_kreditur");
                String jatuhTempo=kon.rs.getString("tanggal_transaksi");
                String angsuran=kon.rs.getString("angsuran");
                float denda=0;
                float total=Float.parseFloat(angsuran)+denda;
                txtNoTransaksi.setText(noTrans);
                txtNamaKreditur.setText(namaKreditur);
                angsuranKe();
                txtTglJatuhTempo.setText(jatuhTempo);
                txtAngsuran1.setText(angsuran);
                txtDenda1.setText("0");
                lblTotal.setText(Float.toString(total));
                txtNoTransaksi.setEnabled(false);
                txtUangBayar.requestFocus();
                btnNoTransaksi.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(this,"Nama yang anda cari tidak terdaftar","Informasi", JOptionPane.INFORMATION_MESSAGE);
                txtNoTransaksi.setText("");
                txtNamaKreditur.setText("");
                txtAngsuranKe.setText("");
                txtTglJatuhTempo.setText("");
                txtAngsuran1.setText("");
                txtDenda1.setText("");
                lblTotal.setText("0,000,000");
                txtNamaKreditur.requestFocus();
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void simpanData(){
        try{
            String simpan="insert into pembayaran values('"+txtNoPembayaran.getText()+"','"+txtNoTransaksi.getText()+"','"+lblTanggal1.getText()+"','"+txtTglJatuhTempo.getText()+"','"+txtAngsuranKe.getText()+"','0','"+txtDenda1.getText()+"','"+lblTotal.getText()+"')";
            kon.st.executeUpdate(simpan);
            JOptionPane.showMessageDialog(null, "Transaksi Pembayaran Berhasil di Proses");
        }catch(Exception e){
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
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtNoTransaksi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNamaKreditur = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtAngsuranKe = new javax.swing.JTextField();
        btnNoTransaksi = new javax.swing.JButton();
        txtTglJatuhTempo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtAngsuran1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtDenda1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtUangBayar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtKembali = new javax.swing.JTextField();
        btnTutup = new javax.swing.JButton();
        btnProses = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtNoPembayaran = new javax.swing.JTextField();
        lblTanggal = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblIdPengguna = new javax.swing.JLabel();
        lblTanggal1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Informasi Kreditur"));

        jLabel4.setText("Nomor Transaksi");

        txtNoTransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNoTransaksiKeyPressed(evt);
            }
        });

        jLabel5.setText("Nama Kreditur");

        jLabel6.setText("Angsuran ke");

        btnNoTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/cari.png"))); // NOI18N
        btnNoTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoTransaksiActionPerformed(evt);
            }
        });

        jLabel18.setText("Tanggal Jatuh Tempo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel18))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTglJatuhTempo, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(txtNamaKreditur)
                    .addComponent(txtAngsuranKe)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNoTransaksi)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNamaKreditur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAngsuranKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTglJatuhTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jLabel10.setText("Tanggal Pembayaran");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Rincian Pembayaran"));

        jLabel23.setText("Angsuran");

        jLabel24.setText("Denda");

        jLabel25.setText("Total Pembayaran");

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText("Rp.");

        lblTotal.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lblTotal.setText("0,000,000");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addComponent(txtDenda1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAngsuran1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtAngsuran1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(txtDenda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel1))
                .addGap(2, 2, 2)
                .addComponent(lblTotal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setText("Masukan Uang Bayar");

        txtUangBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUangBayarKeyPressed(evt);
            }
        });

        jLabel8.setText("Kembali");

        btnTutup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/logout.png"))); // NOI18N
        btnTutup.setText("Tutup");
        btnTutup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTutupActionPerformed(evt);
            }
        });

        btnProses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/notes.png"))); // NOI18N
        btnProses.setText("Proses");
        btnProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsesActionPerformed(evt);
            }
        });

        jLabel9.setText("Nomor Pembayaran");

        txtNoPembayaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNoPembayaranKeyPressed(evt);
            }
        });

        lblTanggal.setText("tanggal");

        lblUser.setText("lbluser");

        lblIdPengguna.setText("lbliduser");

        lblTanggal1.setText("tanggal");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/manajemen_akun.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(59, 59, 59)
                        .addComponent(txtNoPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTanggal))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUangBayar, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                    .addComponent(txtKembali)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnProses)
                                .addGap(9, 9, 9)
                                .addComponent(btnTutup)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUser, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTanggal1)
                                .addComponent(lblIdPengguna)))))
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblTanggal1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIdPengguna)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUser))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(txtNoPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTanggal))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtUangBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTutup)
                    .addComponent(btnProses))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-735)/2, (screenSize.height-522)/2, 735, 522);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNoTransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoTransaksiKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            setTransaksi();
        }
    }//GEN-LAST:event_txtNoTransaksiKeyPressed

    private void btnNoTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoTransaksiActionPerformed
        boolean closable = true;
        TransaksiDataTransaksi dt = new TransaksiDataTransaksi(null, closable);
        dt.pembayaran = this;
        dt.setVisible(true);
        dt.setResizable(true);
        txtNoTransaksi.setText(noTrans);
        setTransaksi();
    }//GEN-LAST:event_btnNoTransaksiActionPerformed

    private void txtUangBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUangBayarKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
             float uangBayar = Float.parseFloat(txtUangBayar.getText());
             float totalBayar = Float.parseFloat(lblTotal.getText());
             if(uangBayar<totalBayar){
                 JOptionPane.showMessageDialog(null, "Uang Bayar Anda Kurang");
                 txtUangBayar.setText("");
                 txtUangBayar.requestFocus();
             }else{
                 txtKembali.setText(Float.toString(uangBayar-totalBayar));
                 btnProses.requestFocus();
             }
         }
    }//GEN-LAST:event_txtUangBayarKeyPressed

    private void btnTutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTutupActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnTutupActionPerformed

    private void btnProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsesActionPerformed
             float uangBayar = Float.parseFloat(txtUangBayar.getText());
             float totalBayar = Float.parseFloat(lblTotal.getText());
             if(uangBayar<totalBayar){
                 JOptionPane.showMessageDialog(null, "Uang Bayar Anda Kurang");
                 txtUangBayar.setText("");
                 txtUangBayar.requestFocus();
             }else{
                 txtKembali.setText(Float.toString(uangBayar-totalBayar));
                 simpanData();
             }
    }//GEN-LAST:event_btnProsesActionPerformed

    private void txtNoPembayaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoPembayaranKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoPembayaranKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Date skrg = new Date();
        SimpleDateFormat tgl=new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat tgl2=new SimpleDateFormat("yyyy-MM-dd");
        lblTanggal.setText(tgl.format(skrg));
        lblTanggal1.setText(tgl2.format(skrg));
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
               Pembayaran dialog = new Pembayaran(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnNoTransaksi;
    private javax.swing.JButton btnProses;
    private javax.swing.JButton btnTutup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblIdPengguna;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblTanggal1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblUser;
    private javax.swing.JTextField txtAngsuran1;
    private javax.swing.JTextField txtAngsuranKe;
    private javax.swing.JTextField txtDenda1;
    private javax.swing.JTextField txtKembali;
    private javax.swing.JTextField txtNamaKreditur;
    private javax.swing.JTextField txtNoPembayaran;
    private javax.swing.JTextField txtNoTransaksi;
    private javax.swing.JTextField txtTglJatuhTempo;
    private javax.swing.JTextField txtUangBayar;
    // End of variables declaration//GEN-END:variables
}
