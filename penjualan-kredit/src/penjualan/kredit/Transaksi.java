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
import java.text.*;
import java.util.logging.*;

/**
 *
 * @author Widi Ramadhan
 */
public class Transaksi extends javax.swing.JDialog {
    Koneksi kon = new Koneksi();
    public MenuUtama mu = null;

    public Transaksi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        kon.setKoneksi();
        lblTanggal1.setVisible(false);
        ceklogin();
        nonAktif();
        lblIdPengguna.setVisible(false);
        txtIdKreditur.setVisible(false);
        
        Date skrg = new Date();
        SimpleDateFormat notrans=new SimpleDateFormat("ddMMyyyyHms");
        txtNoTransaksi.setText(notrans.format(skrg));
    }
    
    
    public String namaKreditur;

    public String getNamaKreditur() {
        return namaKreditur;
    }
    
    public String kodeProduk;

    public String getKodeProduk() {
        return kodeProduk;
    }
    
    public void nonAktif(){
        txtEmail.setEnabled(false);
        txtHarga.setEnabled(false);
        txtKategori.setEnabled(false);
        txtMerk.setEnabled(false);
        txtNamaProduk.setEnabled(false);
        txtNoTransaksi.setEnabled(false);
        txtNomorIdentitas.setEnabled(false);
        txtNomorTelepon.setEnabled(false);
        txtPekerjaan.setEnabled(false);
        rtiga.setEnabled(false);
        renam.setEnabled(false);
        rsetahun.setEnabled(false);
    }
    
     public void bersih(){
        txtEmail.setText("");
        txtHarga.setText("");
        txtKategori.setText("");
        txtMerk.setText("");
        txtNamaProduk.setText("");
        txtNoTransaksi.setText("");
        txtNomorIdentitas.setText("");
        txtNomorTelepon.setText("");
        txtPekerjaan.setText("");
        buttonGroup1.clearSelection();
    }
    
    public void hanyaAngka(java.awt.event.KeyEvent evt){
        char a = evt.getKeyChar();
        if(!((Character.isDigit(a) || (a==KeyEvent.VK_BACK_SPACE) || (a==KeyEvent.VK_DELETE))))
        {
            evt.consume();
        }
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
    
    public void setKreditur(){
        try{
            String sql="SELECT * FROM kreditur A, data_pekerjaan B WHERE B.id_kreditur=A.id_kreditur AND nama_lengkap_kreditur='"+txtNamaKreditur.getText()+"'";
            kon.rs=kon.st.executeQuery(sql);
            if (kon.rs.next()){
                String idKreditur=kon.rs.getString("id_kreditur");
                String nama=kon.rs.getString("nama_lengkap_kreditur");
                String noIdentitas=kon.rs.getString("no_identitas");
                String pekerjaan=kon.rs.getString("nama_pekerjaan");
                String email=kon.rs.getString("email");
                String no_telp=kon.rs.getString("no_telp");
                txtIdKreditur.setText(idKreditur);
                txtNamaKreditur.setText(nama);
                txtNomorIdentitas.setText(noIdentitas);
                txtPekerjaan.setText(pekerjaan);
                txtEmail.setText(email);
                txtNomorTelepon.setText(no_telp);
                txtNamaKreditur.setEnabled(false);
                btnCariKreditur.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(this,"Nama yang anda cari tidak terdaftar","Informasi", JOptionPane.INFORMATION_MESSAGE);
                txtNamaKreditur.setText("");
                txtNomorIdentitas.setText("");
                txtPekerjaan.setText("");
                txtEmail.setText("");
                txtNomorTelepon.setText("");
                txtNamaKreditur.requestFocus();
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }

    public void setKodeProduk(){
        try{
            String sql="SELECT * FROM produk A, kategori B, merk C WHERE A.id_kategori=B.id_kategori AND A.id_merk=C.id_merk AND kode_produk='"+txtKodeProduk.getText()+"'";
            kon.rs=kon.st.executeQuery(sql);
            if (kon.rs.next()){
                String kodeProduk=kon.rs.getString("kode_produk");
                String namaProduk=kon.rs.getString("nama_produk");
                String kategori=kon.rs.getString("nama_kategori");
                String merk=kon.rs.getString("nama_merk");
                String harga=kon.rs.getString("harga");
                txtKodeProduk.setText(kodeProduk);
                txtNamaProduk.setText(namaProduk);
                txtKategori.setText(kategori);
                txtMerk.setText(merk);
                txtHarga.setText(harga);
                txtKodeProduk.setEnabled(false);
                btnCariProduk.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(this,"Produk yang anda cari tidak terdaftar","Informasi", JOptionPane.INFORMATION_MESSAGE);
                txtKodeProduk.setText("");
                txtNamaProduk.setText("");
                txtKategori.setText("");
                txtMerk.setText("");
                txtHarga.setText("");
                txtKodeProduk.requestFocus();
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void SimpanData(){
        try{
            String tenor=null;
            if(rtiga.isSelected()){
                tenor="3";
            }else if(renam.isSelected()){ 
                tenor="6";
            }else if(rsetahun.isSelected()){ 
                tenor="12";
            }else{
                tenor="0";
            }
            float angsuran = Float.parseFloat(lblAngsuran.getText());
            float total = angsuran*(Float.parseFloat(tenor));
            
            String simpan="insert into transaksi values('"+txtNoTransaksi.getText()+"','"+lblUser.getText()+"','"+txtIdKreditur.getText()+"','"+txtKodeProduk.getText()+"','"+lblTanggal1.getText()+"','"+txtHarga.getText()+"','"+tenor+"','0','"+lblAngsuran.getText()+"','"+total+"')";
            kon.st.executeUpdate(simpan);
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtNamaKreditur = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNomorIdentitas = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPekerjaan = new javax.swing.JTextField();
        btnCariKreditur = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtNomorTelepon = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtKodeProduk = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNamaProduk = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtKategori = new javax.swing.JTextField();
        btnCariProduk = new javax.swing.JButton();
        txtMerk = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNoTransaksi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblTanggal = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblTanggal1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        rtiga = new javax.swing.JRadioButton();
        renam = new javax.swing.JRadioButton();
        rsetahun = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        lblAngsuran = new javax.swing.JLabel();
        btnBatal = new javax.swing.JButton();
        btnProses = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        txtIdKreditur = new javax.swing.JTextField();
        lblIdPengguna = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Kreditur"));

        jLabel4.setText("Nama Kreditur");

        txtNamaKreditur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNamaKrediturKeyPressed(evt);
            }
        });

        jLabel5.setText("Nomor Identitas");

        jLabel6.setText("Pekerjaan");

        btnCariKreditur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/cari.png"))); // NOI18N
        btnCariKreditur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariKrediturActionPerformed(evt);
            }
        });

        jLabel18.setText("Email");

        jLabel19.setText("Nomor Telepon");

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
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmail)
                    .addComponent(txtNomorIdentitas)
                    .addComponent(txtPekerjaan)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtNamaKreditur, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariKreditur, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNomorTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNamaKreditur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariKreditur))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNomorIdentitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(txtNomorTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Produk yag di Pesan"));

        jLabel7.setText("Kode Produk");

        txtKodeProduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKodeProdukKeyPressed(evt);
            }
        });

        jLabel8.setText("Nama Produk");

        jLabel9.setText("Kategori");

        btnCariProduk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/cari.png"))); // NOI18N
        btnCariProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariProdukActionPerformed(evt);
            }
        });

        jLabel20.setText("Merk");

        jLabel21.setText("Harga");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMerk)
                    .addComponent(txtNamaProduk)
                    .addComponent(txtKategori)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtKodeProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtKodeProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariProduk))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMerk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setText("Nomor Transaksi");

        jLabel3.setText("Tanggal Transaksi :");

        lblTanggal.setText("tanggal");

        lblUser.setText("lbluser");

        lblTanggal1.setText("tanggal");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Transaksi"));

        jLabel14.setText("Tenor");

        jLabel15.setText("Angsuran");

        buttonGroup1.add(rtiga);
        rtiga.setText("3 Bulan");
        rtiga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtigaActionPerformed(evt);
            }
        });

        buttonGroup1.add(renam);
        renam.setText("6 Bulan");
        renam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renamActionPerformed(evt);
            }
        });

        buttonGroup1.add(rsetahun);
        rsetahun.setText("12 Bulan");
        rsetahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsetahunActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText("Rp.");

        lblAngsuran.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        lblAngsuran.setText("0,000,000");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(35, 35, 35)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(rtiga)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(renam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rsetahun))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAngsuran, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(rtiga)
                    .addComponent(renam)
                    .addComponent(rsetahun))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblAngsuran, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/remove.png"))); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnProses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/credit-card.png"))); // NOI18N
        btnProses.setText("Proses");
        btnProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsesActionPerformed(evt);
            }
        });

        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/logout.png"))); // NOI18N
        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        lblIdPengguna.setText("lbliduser");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/manajemen_akun.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(41, 41, 41)
                                .addComponent(txtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblTanggal)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblUser, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblTanggal1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblIdPengguna, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtIdKreditur, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnProses, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(lblTanggal1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIdPengguna)
                        .addGap(7, 7, 7)
                        .addComponent(lblUser)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(lblTanggal))
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBatal)
                    .addComponent(btnProses)
                    .addComponent(txtIdKreditur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKeluar)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-709)/2, (screenSize.height-620)/2, 709, 620);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaKrediturKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaKrediturKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            setKreditur();
        }
    }//GEN-LAST:event_txtNamaKrediturKeyPressed

    private void btnCariKrediturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariKrediturActionPerformed
        boolean closable = true;
        TransaksiDataKreditur dk = new TransaksiDataKreditur(null, closable);
        dk.trans = this;
        dk.setVisible(true);
        dk.setResizable(true);
        txtNamaKreditur.setText(namaKreditur);
        setKreditur();
    }//GEN-LAST:event_btnCariKrediturActionPerformed

    private void txtKodeProdukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKodeProdukKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeProdukKeyPressed

    private void btnCariProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariProdukActionPerformed
        boolean closable = true;
        TransaksiDataProduk dp = new TransaksiDataProduk(null, closable);
        dp.trans = this;
        dp.setVisible(true);
        dp.setResizable(true);
        txtKodeProduk.setText(kodeProduk);
        setKodeProduk();
        
        rtiga.setEnabled(true);
        renam.setEnabled(true);
        rsetahun.setEnabled(true);
    }//GEN-LAST:event_btnCariProdukActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Date skrg = new Date();
        SimpleDateFormat tgl=new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat tgl2=new SimpleDateFormat("yyyy-MM-dd");
        lblTanggal.setText(tgl.format(skrg));
        lblTanggal1.setText(tgl2.format(skrg));
    }//GEN-LAST:event_formWindowOpened

    private void rsetahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsetahunActionPerformed
       float harga = Float.parseFloat(txtHarga.getText());
       float angsuran = harga/12;
       lblAngsuran.setText(Float.toString(angsuran));
    }//GEN-LAST:event_rsetahunActionPerformed

    private void rtigaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtigaActionPerformed
        float harga = Float.parseFloat(txtHarga.getText());
        float angsuran = harga/3;
        lblAngsuran.setText(Float.toString(angsuran));
    }//GEN-LAST:event_rtigaActionPerformed

    private void renamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renamActionPerformed
        float harga = Float.parseFloat(txtHarga.getText());
       float angsuran = harga/6;
       lblAngsuran.setText(Float.toString(angsuran));
    }//GEN-LAST:event_renamActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        bersih();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsesActionPerformed
        try{
            SimpanData();
            JOptionPane.showMessageDialog(null,"Data berhasil di proses");
            this.dispose();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btnProsesActionPerformed

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
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               Transaksi dialog = new Transaksi(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCariKreditur;
    private javax.swing.JButton btnCariProduk;
    private javax.swing.JButton btnCariProduk1;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnProses;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAngsuran;
    private javax.swing.JLabel lblIdPengguna;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblTanggal1;
    private javax.swing.JLabel lblUser;
    private javax.swing.JRadioButton renam;
    private javax.swing.JRadioButton rsetahun;
    private javax.swing.JRadioButton rtiga;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtHarga1;
    private javax.swing.JTextField txtIdKreditur;
    private javax.swing.JTextField txtKategori;
    private javax.swing.JTextField txtKategori1;
    private javax.swing.JTextField txtKodeProduk;
    private javax.swing.JTextField txtKodeProduk1;
    private javax.swing.JTextField txtMerk;
    private javax.swing.JTextField txtMerk1;
    private javax.swing.JTextField txtNamaKreditur;
    private javax.swing.JTextField txtNamaProduk;
    private javax.swing.JTextField txtNamaProduk1;
    private javax.swing.JTextField txtNoTransaksi;
    private javax.swing.JTextField txtNomorIdentitas;
    private javax.swing.JTextField txtNomorTelepon;
    private javax.swing.JTextField txtPekerjaan;
    // End of variables declaration//GEN-END:variables
}
