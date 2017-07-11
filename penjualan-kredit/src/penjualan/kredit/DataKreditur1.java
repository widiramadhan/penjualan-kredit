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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Widi Ramadhan
 */
public class DataKreditur1 extends javax.swing.JDialog {
    Koneksi kon = new Koneksi();
    public MenuUtama mu = null;

    public DataKreditur1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        kon.setKoneksi();
        btnNextDataPribadi.setEnabled(false);
        btnNextPekerjaan.setEnabled(false);
        btnNextPasangan.setEnabled(false);
        btnNextAnak.setEnabled(false);
        btnSave.setEnabled(false);
        
        tabDataKreditur.setEnabledAt(1, false);
        tabDataKreditur.setEnabledAt(2, false);
        tabDataKreditur.setEnabledAt(3, false);
        tabDataKreditur.setEnabledAt(4, false);
        tabDataKreditur.setEnabledAt(tabDataKreditur.getTabCount() - 1, false);
        
        txtIdKreditur.setText(idKreditur());
        txtIdKreditur.setVisible(false);
        
        txtIdPekerjaan.setText(noOtomatisPekerjaan());
        txtIdPekerjaan.setVisible(false);
        
        txtIdPasangan.setText(noOtomatisPasangan());
        txtIdPasangan.setVisible(false);
    }
    
    
    //========== START DATA PRIBADI PEMOHON ==========//
    private String idKreditur(){
        String Next_nobp;
        String no=null;
        int Next_nobp_int=0;
        
        try{
            String sql = "Select * from kreditur ";
            ResultSet rs = kon.st.executeQuery(sql);
            if(rs.last()){
                Next_nobp=rs.getString("id_kreditur");
                Next_nobp_int=Integer.parseInt(Next_nobp) + 1;
                no=String.valueOf(Next_nobp_int);
                txtIdKreditur.setText(no);
            }else{
                no=String.valueOf(1);
                txtIdKreditur.setText(no);
            }
        }catch (Exception e){   
            JOptionPane.showMessageDialog(null,"Error"+e);
        }return no;
    }
    
    public void cekFormDataPribadi(){
        if(txtNamaLengkap.getText().isEmpty() || txtNamaPanggilan.getText().isEmpty() || txtNomorIdentitas.getText().isEmpty() || txtAlamat.getText().isEmpty() || txtKelurahan.getText().isEmpty() || txtKecamatan.getText().isEmpty() || txtKota.getText().isEmpty() || txtProvinsi.getText().isEmpty() || txtKodePos.getText().isEmpty() || txtNoTelepon.getText().isEmpty() || txtEmail.getText().isEmpty() || txtNomorNPWP.getText().isEmpty() || txtNomorMeteranListrik.getText().isEmpty() || txtNamaIbuKandung.getText().isEmpty()){
            btnNextDataPribadi.setEnabled(false);
        }else if(txtNamaLengkap.getText().equals("") || txtNamaPanggilan.getText().equals("") || cmbJenisIdentitas.getSelectedItem().equals("- PILIH JENIS IDENTITAS -") || txtNomorIdentitas.getText().equals("") || txtAlamat.getText().equals("") || txtKelurahan.getText().equals("") || txtKecamatan.getText().equals("") || txtKota.getText().equals("") || txtProvinsi.getText().equals("") || txtKodePos.getText().equals("") || txtNoTelepon.getText().equals("") || txtEmail.getText().equals("") || txtNomorNPWP.getText().equals("") || txtNomorMeteranListrik.getText().equals("") || txtNamaIbuKandung.getText().equals("")){
            btnNextDataPribadi.setEnabled(false);
        }else{
            btnNextDataPribadi.setEnabled(true);
        }
    }
    
    public void bersihDataPribadiPemohon(){
        txtNamaLengkap.setText("");
        txtNamaPanggilan.setText("");
        cmbJenisIdentitas.setSelectedItem("- PILIH JENIS IDENTITAS -");
        txtNomorIdentitas.setText("");
        txtAlamat.setText("");
        txtKelurahan.setText("");
        txtKecamatan.setText("");
        txtKota.setText("");
        txtProvinsi.setText("");
        txtKodePos.setText("");
        txtNoTelepon.setText("");
        txtEmail.setText("");
        txtNomorNPWP.setText("");
        txtNomorMeteranListrik.setText("");
        txtNamaIbuKandung.setText("");
    }
    
    public void simpanPemohon(){
        try{
            String simpanPemohon="insert into kreditur values('"+txtIdKreditur.getText()+"','"+txtNamaLengkap.getText()+"','"+txtNamaPanggilan.getText()+"','"+cmbJenisIdentitas.getSelectedItem()+"','"+txtNomorIdentitas.getText()+"','"+txtAlamat.getText()+"','"+txtKelurahan.getText()+"','"+txtKecamatan.getText()+"','"+txtKota.getText()+"','"+txtProvinsi.getText()+"','"+txtKodePos.getText()+"','"+txtNoTelepon.getText()+"','"+txtEmail.getText()+"','"+txtNomorNPWP.getText()+"','"+txtNomorMeteranListrik.getText()+"','"+txtNamaIbuKandung.getText()+"')";
            kon.st.executeUpdate(simpanPemohon);
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    //========== END DATA PRIBADI PEMOHON ==========//

    //========== START DATA PEKERJAAN ==========//
    private String noOtomatisPekerjaan(){
        String Next_nobp;
        String no=null;
        int Next_nobp_int=0;
        
        try{
            String sql = "Select * from data_pekerjaan ";
            ResultSet rs = kon.st.executeQuery(sql);
            if(rs.last()){
                Next_nobp=rs.getString("id_pekerjaan");
                Next_nobp_int=Integer.parseInt(Next_nobp) + 1;
                no=String.valueOf(Next_nobp_int);
                txtIdPekerjaan.setText(no);
            }else{
                no=String.valueOf(1);
                txtIdPekerjaan.setText(no);
            }
        }catch (Exception e){   
            JOptionPane.showMessageDialog(null,"Error"+e);
        }return no;
    }
    
    public void cekFormPekerjaan(){
        if(txtNamaPekerjaan.getText().isEmpty() || txtNamaPerusahaan.getText().isEmpty() || txtBidangUsaha.getText().isEmpty() || txtAlamatPerusahaan.getText().isEmpty() || txtTelpPerusahaan.getText().isEmpty() || txtJabatan.getText().isEmpty() || txtJumlahPenghasilan.getText().isEmpty() || txtJumlahPenghasilanLain.getText().isEmpty() || txtPengeluaran.getText().isEmpty() || txtLamaBekerja.getText().isEmpty()){
            btnNextPekerjaan.setEnabled(false);
        }else if(txtNamaPekerjaan.getText().equals("") || txtNamaPerusahaan.getText().equals("") || txtBidangUsaha.getText().equals("") || txtAlamatPerusahaan.getText().equals("") || txtTelpPerusahaan.getText().equals("") || txtJabatan.getText().equals("") || txtJumlahPenghasilan.getText().equals("") || txtJumlahPenghasilanLain.getText().equals("") || txtPengeluaran.getText().equals("") || txtLamaBekerja.getText().equals("")){
            btnNextPekerjaan.setEnabled(false);
        }else{
            btnNextPekerjaan.setEnabled(true);
        }
    }
    
    public void bersihPekerjaan(){
        txtNamaPekerjaan.setText("");
        txtNamaPerusahaan.setText("");
        txtBidangUsaha.setText("");
        txtAlamatPerusahaan.setText("");
        txtTelpPerusahaan.setText("");
        txtJabatan.setText("");
        txtJumlahPenghasilan.setText("");
        txtJumlahPenghasilanLain.setText("");
        txtPengeluaran.setText("");
        txtLamaBekerja.setText("");
    }
    
    public void simpanPekerjaan(){
        try{
            String simpanPekerjaan="insert into data_pekerjaan values('"+txtIdPekerjaan.getText()+"','"+txtIdKreditur.getText()+"','"+txtNamaPekerjaan.getText()+"','"+txtNamaPerusahaan.getText()+"','"+txtBidangUsaha.getText()+"','"+txtAlamatPerusahaan.getText()+"','"+txtTelpPerusahaan.getText()+"','"+txtJabatan.getText()+"','"+txtJumlahPenghasilan.getText()+"','"+txtJumlahPenghasilanLain.getText()+"','"+txtPengeluaran.getText()+"','"+txtLamaBekerja.getText()+"')";
            kon.st.executeUpdate(simpanPekerjaan);
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    //========== END DATA PEKERJAAN ==========//
    
    //========== START DATA PASANGAN ==========//
        public void cekFormPasangan(){
            if(txtNamaPasangan.getText().isEmpty() || txtNomorIdentitasPasangan.getText().isEmpty() || txtAlamatPasangan.getText().isEmpty() || txtUmurPasangan.getText().isEmpty()){
                btnNextPasangan.setEnabled(false);
            }else if(txtNamaPasangan.getText().equals("") || txtNomorIdentitasPasangan.getText().equals("") || txtAlamatPasangan.getText().equals("") || txtUmurPasangan.getText().equals("")){
                btnNextPasangan.setEnabled(false);
            }else{
                btnNextPasangan.setEnabled(true);
            }
        }
        
        public void BersihPasangan(){
            txtNamaPasangan.setText("");
            txtNomorIdentitasPasangan.setText("");
            txtAlamatPasangan.setText("");
            txtUmurPasangan.setText("");
        }
        
        private String noOtomatisPasangan(){
        String Next_nobp;
        String no=null;
        int Next_nobp_int=0;
        
        try{
            String sql = "Select * from data_pasangan ";
            ResultSet rs = kon.st.executeQuery(sql);
            if(rs.last()){
                Next_nobp=rs.getString("id_data_pasangan");
                Next_nobp_int=Integer.parseInt(Next_nobp) + 1;
                no=String.valueOf(Next_nobp_int);
                txtIdPekerjaan.setText(no);
            }else{
                no=String.valueOf(1);
                txtIdPekerjaan.setText(no);
            }
        }catch (Exception e){   
            JOptionPane.showMessageDialog(null,"Error"+e);
        }return no;
    }
        
    public void simpanPasangan(){
        try{
            String simpanPasangan="insert into data_pasangan values('"+txtIdPasangan.getText()+"','"+txtIdKreditur.getText()+"','"+txtNamaPasangan.getText()+"','"+txtNomorIdentitasPasangan.getText()+"','"+txtAlamatPasangan.getText()+"','"+txtUmurPasangan.getText()+"')";
            kon.st.executeUpdate(simpanPasangan);
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    //========== END DATA PASANGAN ==========//
    
    //========== END DATA ANAK ==========//    
        public void cekFormAnak(){
            if(txtNamaAnak.getText().isEmpty() || txtTempatLahir.getText().isEmpty() || txtTanggalLahir.getText().isEmpty() || txtAlamatAnak.getText().isEmpty() || txtUmurAnak.getText().isEmpty() || txtPekerjaanAnak.getText().isEmpty()){
                btnNextAnak.setEnabled(false);
            }else if(txtNamaAnak.getText().equals("") || txtTempatLahir.getText().equals("") || txtTanggalLahir.getText().equals("") || txtAlamatAnak.getText().equals("") || txtUmurAnak.getText().equals("") || txtPekerjaanAnak.getText().equals("")){
                btnNextAnak.setEnabled(false);
            }else{
                btnNextAnak.setEnabled(true);
            }
        }
        
        public void bersihAnak(){
            txtNamaAnak.setText("");
            txtTempatLahir.setText("");
            txtTanggalLahir.setText("");
            txtAlamatAnak.setText("");
            txtUmurAnak.setText("");
            txtPekerjaanAnak.setText("");
        }
        
        public void simpanAnak(){
        try{
            String simpanAnak="insert into data_anak values('"+txtIdKreditur.getText()+"','"+txtNamaAnak.getText()+"','"+txtTempatLahir.getText()+"','"+txtTanggalLahir.getText()+"','"+txtAlamat.getText()+"','"+txtUmurAnak.getText()+"','"+txtPekerjaanAnak.getText()+"')";
            kon.st.executeUpdate(simpanAnak);
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    //========== END DATA ANAK ==========//
        
    //========== START DATA REFERENSI ==========//
    public void cekFormReferensi(){
        if(txtNamaReferensi.getText().isEmpty() || txtHubungan.getText().isEmpty() || txtPekerjaanReferensi.getText().isEmpty() || txtAlamatReferensi.getText().isEmpty() || txtNoTelpReferensi.getText().isEmpty()){
           btnSave.setEnabled(false);
        }if(txtNamaReferensi.getText().equals("") || txtHubungan.getText().equals("") || txtPekerjaanReferensi.getText().equals("") || txtAlamatReferensi.getText().equals("") || txtNoTelpReferensi.getText().equals("")){
           btnSave.setEnabled(false); 
        }else{
            btnSave.setEnabled(true);
        }
    }
    
    public void bersihReferensi(){
        txtNamaReferensi.setText("");
        txtHubungan.setText("");
        txtPekerjaanReferensi.setText("");
        txtAlamatReferensi.setText("");
        txtNoTelpReferensi.setText("");
    }
    
    public void simpanReferensi(){
        try{
            String simpanReferensi="insert into data_referensi values('"+txtIdKreditur.getText()+"','"+txtNamaReferensi.getText()+"','"+txtHubungan.getText()+"','"+txtPekerjaanReferensi.getText()+"','"+txtAlamatReferensi.getText()+"','"+txtNoTelpReferensi.getText()+"')";
            kon.st.executeUpdate(simpanReferensi);
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    //========== END DATA REFERENSI ==========//
        
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
        jSeparator1 = new javax.swing.JSeparator();
        tabDataKreditur = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        btnNextDataPribadi = new javax.swing.JButton();
        txtNamaLengkap = new javax.swing.JTextField();
        txtNamaPanggilan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbJenisIdentitas = new javax.swing.JComboBox<>();
        txtNomorIdentitas = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        txtKelurahan = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtKota = new javax.swing.JTextField();
        txtProvinsi = new javax.swing.JTextField();
        txtKodePos = new javax.swing.JTextField();
        txtNoTelepon = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtNomorNPWP = new javax.swing.JTextField();
        txtNomorMeteranListrik = new javax.swing.JTextField();
        txtNamaIbuKandung = new javax.swing.JTextField();
        txtKecamatan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnBatalDataPribadi = new javax.swing.JButton();
        txtIdKreditur = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnNextPekerjaan = new javax.swing.JButton();
        btnPreviousPekerjaan = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtNamaPekerjaan = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtNamaPerusahaan = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtBidangUsaha = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTelpPerusahaan = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtJabatan = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtJumlahPenghasilan = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAlamatPerusahaan = new javax.swing.JTextArea();
        txtJumlahPenghasilanLain = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtPengeluaran = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtLamaBekerja = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        btnBatalPekerjaan = new javax.swing.JButton();
        txtIdPekerjaan = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        txtNamaPasangan = new javax.swing.JTextField();
        txtUmurPasangan = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtNomorIdentitasPasangan = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAlamatPasangan = new javax.swing.JTextArea();
        jLabel32 = new javax.swing.JLabel();
        btnBatalPasangan = new javax.swing.JButton();
        btnNextPasangan = new javax.swing.JButton();
        btnPreviousPasangan = new javax.swing.JButton();
        txtIdPasangan = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txtNamaAnak = new javax.swing.JTextField();
        txtTempatLahir = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtPekerjaanAnak = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtTanggalLahir = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtUmurAnak = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAlamatAnak = new javax.swing.JTextArea();
        btnBatalAnak = new javax.swing.JButton();
        btnNextAnak = new javax.swing.JButton();
        btnPreviousAnak = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        txtNamaReferensi = new javax.swing.JTextField();
        txtHubungan = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtPekerjaanReferensi = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtAlamatReferensi = new javax.swing.JTextArea();
        jLabel43 = new javax.swing.JLabel();
        txtNoTelpReferensi = new javax.swing.JTextField();
        btnBatalReferensi = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnPreviousReferensi = new javax.swing.JButton();
        bKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/data_kreditur.png"))); // NOI18N

        btnNextDataPribadi.setText("Selanjutnya");
        btnNextDataPribadi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextDataPribadiActionPerformed(evt);
            }
        });

        txtNamaLengkap.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtNamaLengkapInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        txtNamaLengkap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaLengkapKeyTyped(evt);
            }
        });

        txtNamaPanggilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaPanggilanKeyTyped(evt);
            }
        });

        jLabel2.setText("Nama Lengkap");

        jLabel3.setText("Nama Panggilan");

        jLabel4.setText("Jenis Identitas");

        jLabel5.setText("Nomor Identitas");

        cmbJenisIdentitas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- PILIH JENIS IDENTITAS -", "KTP", "SIM", "PASSPORT", "LAINNYA" }));
        cmbJenisIdentitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJenisIdentitasActionPerformed(evt);
            }
        });

        txtNomorIdentitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomorIdentitasKeyTyped(evt);
            }
        });

        jLabel6.setText("Alamat");

        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        txtAlamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlamatKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtAlamat);

        txtKelurahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKelurahanKeyTyped(evt);
            }
        });

        jLabel7.setText("Kelurahan");

        jLabel8.setText("Kota");

        txtKota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKotaKeyTyped(evt);
            }
        });

        txtProvinsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProvinsiKeyTyped(evt);
            }
        });

        txtKodePos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKodePosKeyTyped(evt);
            }
        });

        txtNoTelepon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoTeleponKeyTyped(evt);
            }
        });

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailKeyTyped(evt);
            }
        });

        txtNomorNPWP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomorNPWPKeyTyped(evt);
            }
        });

        txtNomorMeteranListrik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomorMeteranListrikKeyTyped(evt);
            }
        });

        txtNamaIbuKandung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaIbuKandungKeyTyped(evt);
            }
        });

        txtKecamatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKecamatanKeyTyped(evt);
            }
        });

        jLabel9.setText("Kecamatan");

        jLabel10.setText("Provinsi");

        jLabel11.setText("Kode Pos");

        jLabel12.setText("No Telepon");

        jLabel13.setText("Email");

        jLabel14.setText("Nomor NPWP");

        jLabel15.setText("No Meteran Listrik");

        jLabel16.setText("Nama Ibu Kandung");

        btnBatalDataPribadi.setText("Batal");
        btnBatalDataPribadi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalDataPribadiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtIdKreditur, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNextDataPribadi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBatalDataPribadi))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbJenisIdentitas, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNomorIdentitas, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNamaPanggilan)
                            .addComponent(txtNamaLengkap)
                            .addComponent(txtKelurahan))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNamaIbuKandung, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNomorMeteranListrik, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNomorNPWP, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNoTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtKodePos, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtProvinsi, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtKecamatan, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtKota, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNamaLengkap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNamaPanggilan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbJenisIdentitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtNomorIdentitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jLabel16))
                            .addComponent(txtKelurahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtKecamatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProvinsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKodePos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNoTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNomorNPWP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNomorMeteranListrik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamaIbuKandung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNextDataPribadi)
                    .addComponent(btnBatalDataPribadi)
                    .addComponent(txtIdKreditur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        tabDataKreditur.addTab("Data Pribadi Pemohon", jPanel2);

        btnNextPekerjaan.setText("Selanjutnya");
        btnNextPekerjaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextPekerjaanActionPerformed(evt);
            }
        });

        btnPreviousPekerjaan.setText("Sebelumnya");
        btnPreviousPekerjaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousPekerjaanActionPerformed(evt);
            }
        });

        jLabel17.setText("Nama Pekerjaan");

        txtNamaPekerjaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaPekerjaanKeyTyped(evt);
            }
        });

        jLabel18.setText("Nama Perusahaan");

        txtNamaPerusahaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaPerusahaanKeyTyped(evt);
            }
        });

        jLabel19.setText("Bidang Usaha");

        txtBidangUsaha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBidangUsahaKeyTyped(evt);
            }
        });

        jLabel20.setText("Telepon Perusahaan");

        txtTelpPerusahaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelpPerusahaanKeyTyped(evt);
            }
        });

        jLabel21.setText("Jabatan");

        txtJabatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJabatanKeyTyped(evt);
            }
        });

        jLabel22.setText("Jumlah Penghasilan");

        txtJumlahPenghasilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJumlahPenghasilanKeyTyped(evt);
            }
        });

        jLabel23.setText("Alamat Perusahaan");

        txtAlamatPerusahaan.setColumns(20);
        txtAlamatPerusahaan.setRows(5);
        txtAlamatPerusahaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlamatPerusahaanKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(txtAlamatPerusahaan);

        txtJumlahPenghasilanLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJumlahPenghasilanLainKeyTyped(evt);
            }
        });

        jLabel24.setText("Jumlah Penghasilan Lain");

        txtPengeluaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPengeluaranKeyTyped(evt);
            }
        });

        jLabel25.setText("Pengeluaran Dalam Sebulan");

        txtLamaBekerja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLamaBekerjaKeyTyped(evt);
            }
        });

        jLabel26.setText("Lama Bekerja Dalam Bulan");

        jLabel27.setText("Bulan");

        btnBatalPekerjaan.setText("Batal");
        btnBatalPekerjaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalPekerjaanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtIdPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPreviousPekerjaan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNextPekerjaan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBatalPekerjaan))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtBidangUsaha, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel19)
                                    .addGap(220, 220, 220))
                                .addComponent(txtNamaPekerjaan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNamaPerusahaan, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(18, 22, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(txtTelpPerusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)
                            .addComponent(txtJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(txtJumlahPenghasilan, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24)
                            .addComponent(txtJumlahPenghasilanLain, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel26)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(txtLamaBekerja, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel27))))))
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelpPerusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addGap(4, 4, 4)
                        .addComponent(txtJumlahPenghasilan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtJumlahPenghasilanLain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLamaBekerja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamaPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamaPerusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBidangUsaha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNextPekerjaan)
                            .addComponent(btnPreviousPekerjaan)
                            .addComponent(btnBatalPekerjaan)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtIdPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );

        tabDataKreditur.addTab("Data Pekerjaan", jPanel3);

        jLabel28.setText("Nama Pasangan");

        txtNamaPasangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaPasanganKeyTyped(evt);
            }
        });

        txtUmurPasangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUmurPasanganKeyTyped(evt);
            }
        });

        jLabel29.setText("Umur");

        txtNomorIdentitasPasangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomorIdentitasPasanganKeyTyped(evt);
            }
        });

        jLabel30.setText("Nomor Identitas");

        jLabel31.setText("Alamat");

        txtAlamatPasangan.setColumns(20);
        txtAlamatPasangan.setRows(5);
        txtAlamatPasangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlamatPasanganKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(txtAlamatPasangan);

        jLabel32.setText("Tahun");

        btnBatalPasangan.setText("Batal");
        btnBatalPasangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalPasanganActionPerformed(evt);
            }
        });

        btnNextPasangan.setText("Selanjutnya");
        btnNextPasangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextPasanganActionPerformed(evt);
            }
        });

        btnPreviousPasangan.setText("Sebelumnya");
        btnPreviousPasangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousPasanganActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnPreviousPasangan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNextPasangan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBatalPasangan))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel28)
                            .addComponent(txtNamaPasangan)
                            .addComponent(jLabel31)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(txtNomorIdentitasPasangan, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtUmurPasangan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel32))))
                    .addComponent(txtIdPasangan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamaPasangan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomorIdentitasPasangan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtUmurPasangan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32)))
                .addGap(36, 36, 36)
                .addComponent(txtIdPasangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBatalPasangan)
                    .addComponent(btnNextPasangan)
                    .addComponent(btnPreviousPasangan))
                .addContainerGap())
        );

        tabDataKreditur.addTab("Data Pasangan", jPanel4);

        jLabel33.setText("Nama Anak");

        txtNamaAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaAnakKeyTyped(evt);
            }
        });

        txtTempatLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTempatLahirKeyTyped(evt);
            }
        });

        jLabel34.setText("Tempat Lahir");

        txtPekerjaanAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPekerjaanAnakKeyTyped(evt);
            }
        });

        jLabel35.setText("Pekerjaan");

        txtTanggalLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTanggalLahirKeyTyped(evt);
            }
        });

        jLabel36.setText("Tanggal Lahir");

        txtUmurAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUmurAnakKeyTyped(evt);
            }
        });

        jLabel37.setText("Umur");

        jLabel38.setText("Alamat");

        txtAlamatAnak.setColumns(20);
        txtAlamatAnak.setRows(5);
        txtAlamatAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlamatAnakKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(txtAlamatAnak);

        btnBatalAnak.setText("Batal");
        btnBatalAnak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalAnakActionPerformed(evt);
            }
        });

        btnNextAnak.setText("Selanjutnya");
        btnNextAnak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextAnakActionPerformed(evt);
            }
        });

        btnPreviousAnak.setText("Sebelumnya");
        btnPreviousAnak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousAnakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPreviousAnak)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNextAnak)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBatalAnak))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel33)
                            .addComponent(txtNamaAnak)
                            .addComponent(jLabel34)
                            .addComponent(txtTempatLahir, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(jLabel38)
                            .addComponent(jScrollPane4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(txtPekerjaanAnak, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36)
                            .addComponent(txtTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37)
                            .addComponent(txtUmurAnak, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(37, 37, 37))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNamaAnak, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUmurAnak, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPekerjaanAnak, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBatalAnak)
                    .addComponent(btnNextAnak)
                    .addComponent(btnPreviousAnak))
                .addGap(20, 20, 20))
        );

        tabDataKreditur.addTab("Data Anak", jPanel5);

        jLabel39.setText("Nama Referensi");

        txtNamaReferensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaReferensiKeyTyped(evt);
            }
        });

        txtHubungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHubunganKeyTyped(evt);
            }
        });

        jLabel40.setText("Hubungan");

        txtPekerjaanReferensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPekerjaanReferensiKeyTyped(evt);
            }
        });

        jLabel41.setText("Pekerjaan");

        jLabel42.setText("Alamat");

        txtAlamatReferensi.setColumns(20);
        txtAlamatReferensi.setRows(5);
        txtAlamatReferensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlamatReferensiKeyTyped(evt);
            }
        });
        jScrollPane5.setViewportView(txtAlamatReferensi);

        jLabel43.setText("Nomor Telepon");

        txtNoTelpReferensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoTelpReferensiKeyTyped(evt);
            }
        });

        btnBatalReferensi.setText("Batal");
        btnBatalReferensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalReferensiActionPerformed(evt);
            }
        });

        btnSave.setText("Simpan");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnPreviousReferensi.setText("Sebelumnya");
        btnPreviousReferensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousReferensiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(jLabel41)
                    .addComponent(txtPekerjaanReferensi, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(txtHubungan, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaReferensi, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel42)
                    .addComponent(jLabel43)
                    .addComponent(txtNoTelpReferensi, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPreviousReferensi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBatalReferensi)
                .addGap(29, 29, 29))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtNamaReferensi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHubungan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPekerjaanReferensi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNoTelpReferensi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBatalReferensi)
                    .addComponent(btnSave)
                    .addComponent(btnPreviousReferensi))
                .addGap(19, 19, 19))
        );

        tabDataKreditur.addTab("Data Referensi", jPanel6);

        bKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/logout.png"))); // NOI18N
        bKeluar.setText("Keluar");
        bKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tabDataKreditur)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bKeluar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabDataKreditur, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(695, 582));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextDataPribadiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextDataPribadiActionPerformed
        String email = txtEmail.getText();
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);
        boolean matchFound = m.matches();
        if (matchFound){
          tabDataKreditur.setEnabledAt(0, false);
          tabDataKreditur.setEnabledAt(1, true);
          tabDataKreditur.setEnabledAt(2, false);
          tabDataKreditur.setEnabledAt(3, false);
          tabDataKreditur.setEnabledAt(4, false);
          tabDataKreditur.setEnabledAt(tabDataKreditur.getTabCount() - 1, false);
          tabDataKreditur.setSelectedIndex(1);
        }else{
          JOptionPane.showMessageDialog(null,"Alamat Email tidak Valid");
        }
    }//GEN-LAST:event_btnNextDataPribadiActionPerformed

    private void txtNamaLengkapKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaLengkapKeyTyped
       cekFormDataPribadi();
    }//GEN-LAST:event_txtNamaLengkapKeyTyped

    private void txtNamaPanggilanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaPanggilanKeyTyped
        cekFormDataPribadi();
    }//GEN-LAST:event_txtNamaPanggilanKeyTyped

    private void txtNamaLengkapInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtNamaLengkapInputMethodTextChanged
       cekFormDataPribadi();
    }//GEN-LAST:event_txtNamaLengkapInputMethodTextChanged

    private void btnPreviousPekerjaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousPekerjaanActionPerformed
        tabDataKreditur.setEnabledAt(0, true);
        tabDataKreditur.setEnabledAt(1, false);
        tabDataKreditur.setEnabledAt(2, false);
        tabDataKreditur.setEnabledAt(3, false);
        tabDataKreditur.setEnabledAt(4, false);
        tabDataKreditur.setEnabledAt(tabDataKreditur.getTabCount() - 1, false);
        tabDataKreditur.setSelectedIndex(0);
    }//GEN-LAST:event_btnPreviousPekerjaanActionPerformed

    private void bKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKeluarActionPerformed
            this.dispose();
    }//GEN-LAST:event_bKeluarActionPerformed

    private void cmbJenisIdentitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJenisIdentitasActionPerformed
        cekFormDataPribadi();
    }//GEN-LAST:event_cmbJenisIdentitasActionPerformed

    private void txtNomorIdentitasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomorIdentitasKeyTyped
        cekFormDataPribadi();
    }//GEN-LAST:event_txtNomorIdentitasKeyTyped

    private void txtAlamatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlamatKeyTyped
        cekFormDataPribadi();
    }//GEN-LAST:event_txtAlamatKeyTyped

    private void txtKelurahanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKelurahanKeyTyped
        cekFormDataPribadi();
    }//GEN-LAST:event_txtKelurahanKeyTyped

    private void txtKecamatanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKecamatanKeyTyped
        cekFormDataPribadi();
    }//GEN-LAST:event_txtKecamatanKeyTyped

    private void txtKotaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKotaKeyTyped
        cekFormDataPribadi();
    }//GEN-LAST:event_txtKotaKeyTyped

    private void txtProvinsiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProvinsiKeyTyped
        cekFormDataPribadi();
    }//GEN-LAST:event_txtProvinsiKeyTyped

    private void txtKodePosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKodePosKeyTyped
        cekFormDataPribadi();
    }//GEN-LAST:event_txtKodePosKeyTyped

    private void txtNoTeleponKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoTeleponKeyTyped
        cekFormDataPribadi();
    }//GEN-LAST:event_txtNoTeleponKeyTyped

    private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
       cekFormDataPribadi();
    }//GEN-LAST:event_txtEmailKeyTyped

    private void txtNomorNPWPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomorNPWPKeyTyped
        cekFormDataPribadi();
    }//GEN-LAST:event_txtNomorNPWPKeyTyped

    private void txtNomorMeteranListrikKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomorMeteranListrikKeyTyped
        cekFormDataPribadi();
    }//GEN-LAST:event_txtNomorMeteranListrikKeyTyped

    private void txtNamaIbuKandungKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaIbuKandungKeyTyped
       cekFormDataPribadi();
    }//GEN-LAST:event_txtNamaIbuKandungKeyTyped

    private void btnBatalDataPribadiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalDataPribadiActionPerformed
        bersihDataPribadiPemohon();
    }//GEN-LAST:event_btnBatalDataPribadiActionPerformed

    private void txtNamaPekerjaanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaPekerjaanKeyTyped
        cekFormPekerjaan();
    }//GEN-LAST:event_txtNamaPekerjaanKeyTyped

    private void txtNamaPerusahaanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaPerusahaanKeyTyped
        cekFormPekerjaan();
    }//GEN-LAST:event_txtNamaPerusahaanKeyTyped

    private void txtBidangUsahaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBidangUsahaKeyTyped
        cekFormPekerjaan();
    }//GEN-LAST:event_txtBidangUsahaKeyTyped

    private void txtAlamatPerusahaanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlamatPerusahaanKeyTyped
        cekFormPekerjaan();
    }//GEN-LAST:event_txtAlamatPerusahaanKeyTyped

    private void txtTelpPerusahaanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelpPerusahaanKeyTyped
        cekFormPekerjaan();
    }//GEN-LAST:event_txtTelpPerusahaanKeyTyped

    private void txtJabatanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJabatanKeyTyped
        cekFormPekerjaan();
    }//GEN-LAST:event_txtJabatanKeyTyped

    private void txtJumlahPenghasilanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahPenghasilanKeyTyped
        cekFormPekerjaan();
    }//GEN-LAST:event_txtJumlahPenghasilanKeyTyped

    private void txtJumlahPenghasilanLainKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahPenghasilanLainKeyTyped
        cekFormPekerjaan();
    }//GEN-LAST:event_txtJumlahPenghasilanLainKeyTyped

    private void txtPengeluaranKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPengeluaranKeyTyped
        cekFormPekerjaan();
    }//GEN-LAST:event_txtPengeluaranKeyTyped

    private void txtLamaBekerjaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLamaBekerjaKeyTyped
        cekFormPekerjaan();
    }//GEN-LAST:event_txtLamaBekerjaKeyTyped

    private void btnBatalPekerjaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalPekerjaanActionPerformed
        bersihPekerjaan();
    }//GEN-LAST:event_btnBatalPekerjaanActionPerformed

    private void btnNextPekerjaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextPekerjaanActionPerformed
          tabDataKreditur.setEnabledAt(0, false);
          tabDataKreditur.setEnabledAt(1, false);
          tabDataKreditur.setEnabledAt(2, true);
          tabDataKreditur.setEnabledAt(3, false);
          tabDataKreditur.setEnabledAt(4, false);
          tabDataKreditur.setEnabledAt(tabDataKreditur.getTabCount() - 1, false);
          tabDataKreditur.setSelectedIndex(2);
    }//GEN-LAST:event_btnNextPekerjaanActionPerformed

    private void btnPreviousPasanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousPasanganActionPerformed
        tabDataKreditur.setEnabledAt(0, false);
        tabDataKreditur.setEnabledAt(1, true);
        tabDataKreditur.setEnabledAt(2, false);
        tabDataKreditur.setEnabledAt(3, false);
        tabDataKreditur.setEnabledAt(4, false);
        tabDataKreditur.setEnabledAt(tabDataKreditur.getTabCount() - 1, false);
        tabDataKreditur.setSelectedIndex(1);
    }//GEN-LAST:event_btnPreviousPasanganActionPerformed

    private void btnBatalPasanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalPasanganActionPerformed
        BersihPasangan();
    }//GEN-LAST:event_btnBatalPasanganActionPerformed

    private void btnNextPasanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextPasanganActionPerformed
        tabDataKreditur.setEnabledAt(0, false);
        tabDataKreditur.setEnabledAt(1, false);
        tabDataKreditur.setEnabledAt(2, false);
        tabDataKreditur.setEnabledAt(3, true);
        tabDataKreditur.setEnabledAt(4, false);
        tabDataKreditur.setEnabledAt(tabDataKreditur.getTabCount() - 1, false);
        tabDataKreditur.setSelectedIndex(3);
    }//GEN-LAST:event_btnNextPasanganActionPerformed

    private void btnBatalAnakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalAnakActionPerformed
        bersihAnak();
    }//GEN-LAST:event_btnBatalAnakActionPerformed

    private void btnPreviousAnakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousAnakActionPerformed
        tabDataKreditur.setEnabledAt(0, false);
        tabDataKreditur.setEnabledAt(1, false);
        tabDataKreditur.setEnabledAt(2, true);
        tabDataKreditur.setEnabledAt(3, false);
        tabDataKreditur.setEnabledAt(4, false);
        tabDataKreditur.setEnabledAt(tabDataKreditur.getTabCount() - 1, false);
        tabDataKreditur.setSelectedIndex(2);
    }//GEN-LAST:event_btnPreviousAnakActionPerformed

    private void btnNextAnakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextAnakActionPerformed
        tabDataKreditur.setEnabledAt(0, false);
        tabDataKreditur.setEnabledAt(1, false);
        tabDataKreditur.setEnabledAt(2, false);
        tabDataKreditur.setEnabledAt(3, false);
        tabDataKreditur.setEnabledAt(4, true);
        tabDataKreditur.setEnabledAt(tabDataKreditur.getTabCount() - 1, false);
        tabDataKreditur.setSelectedIndex(4);
    }//GEN-LAST:event_btnNextAnakActionPerformed

    private void txtNamaAnakKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaAnakKeyTyped
        cekFormAnak();
    }//GEN-LAST:event_txtNamaAnakKeyTyped

    private void txtTempatLahirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTempatLahirKeyTyped
        cekFormAnak();
    }//GEN-LAST:event_txtTempatLahirKeyTyped

    private void txtAlamatAnakKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlamatAnakKeyTyped
       cekFormAnak();
    }//GEN-LAST:event_txtAlamatAnakKeyTyped

    private void txtTanggalLahirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTanggalLahirKeyTyped
       cekFormAnak();
    }//GEN-LAST:event_txtTanggalLahirKeyTyped

    private void txtUmurAnakKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUmurAnakKeyTyped
        cekFormAnak();
    }//GEN-LAST:event_txtUmurAnakKeyTyped

    private void txtPekerjaanAnakKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPekerjaanAnakKeyTyped
        cekFormAnak();
    }//GEN-LAST:event_txtPekerjaanAnakKeyTyped

    private void btnBatalReferensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalReferensiActionPerformed
        bersihReferensi();
    }//GEN-LAST:event_btnBatalReferensiActionPerformed

    private void btnPreviousReferensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousReferensiActionPerformed
        tabDataKreditur.setEnabledAt(0, false);
        tabDataKreditur.setEnabledAt(1, false);
        tabDataKreditur.setEnabledAt(2, false);
        tabDataKreditur.setEnabledAt(3, true);
        tabDataKreditur.setEnabledAt(4, false);
        tabDataKreditur.setEnabledAt(tabDataKreditur.getTabCount() - 1, false);
        tabDataKreditur.setSelectedIndex(3);
    }//GEN-LAST:event_btnPreviousReferensiActionPerformed

    private void txtNamaReferensiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaReferensiKeyTyped
        cekFormReferensi();
    }//GEN-LAST:event_txtNamaReferensiKeyTyped

    private void txtHubunganKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHubunganKeyTyped
        cekFormReferensi();
    }//GEN-LAST:event_txtHubunganKeyTyped

    private void txtPekerjaanReferensiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPekerjaanReferensiKeyTyped
        cekFormReferensi();
    }//GEN-LAST:event_txtPekerjaanReferensiKeyTyped

    private void txtAlamatReferensiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlamatReferensiKeyTyped
        cekFormReferensi();
    }//GEN-LAST:event_txtAlamatReferensiKeyTyped

    private void txtNoTelpReferensiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoTelpReferensiKeyTyped
        cekFormReferensi();
    }//GEN-LAST:event_txtNoTelpReferensiKeyTyped

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try{
            simpanPemohon();
            simpanPekerjaan();
            simpanPasangan();
            simpanAnak();
            simpanReferensi();
            JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtUmurPasanganKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUmurPasanganKeyTyped
        cekFormPasangan();
    }//GEN-LAST:event_txtUmurPasanganKeyTyped

    private void txtNomorIdentitasPasanganKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomorIdentitasPasanganKeyTyped
        cekFormPasangan();
    }//GEN-LAST:event_txtNomorIdentitasPasanganKeyTyped

    private void txtNamaPasanganKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaPasanganKeyTyped
        cekFormPasangan();
    }//GEN-LAST:event_txtNamaPasanganKeyTyped

    private void txtAlamatPasanganKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlamatPasanganKeyTyped
        cekFormPasangan();
    }//GEN-LAST:event_txtAlamatPasanganKeyTyped

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
            java.util.logging.Logger.getLogger(DataKreditur1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataKreditur1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataKreditur1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataKreditur1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               DataKreditur1 dialog = new DataKreditur1(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton bKeluar;
    private javax.swing.JButton btnBatalAnak;
    private javax.swing.JButton btnBatalDataPribadi;
    private javax.swing.JButton btnBatalPasangan;
    private javax.swing.JButton btnBatalPekerjaan;
    private javax.swing.JButton btnBatalReferensi;
    private javax.swing.JButton btnNextAnak;
    private javax.swing.JButton btnNextDataPribadi;
    private javax.swing.JButton btnNextPasangan;
    private javax.swing.JButton btnNextPekerjaan;
    private javax.swing.JButton btnPreviousAnak;
    private javax.swing.JButton btnPreviousPasangan;
    private javax.swing.JButton btnPreviousPekerjaan;
    private javax.swing.JButton btnPreviousReferensi;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbJenisIdentitas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane tabDataKreditur;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextArea txtAlamatAnak;
    private javax.swing.JTextArea txtAlamatPasangan;
    private javax.swing.JTextArea txtAlamatPerusahaan;
    private javax.swing.JTextArea txtAlamatReferensi;
    private javax.swing.JTextField txtBidangUsaha;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHubungan;
    private javax.swing.JTextField txtIdKreditur;
    private javax.swing.JTextField txtIdPasangan;
    private javax.swing.JTextField txtIdPekerjaan;
    private javax.swing.JTextField txtJabatan;
    private javax.swing.JTextField txtJumlahPenghasilan;
    private javax.swing.JTextField txtJumlahPenghasilanLain;
    private javax.swing.JTextField txtKecamatan;
    private javax.swing.JTextField txtKelurahan;
    private javax.swing.JTextField txtKodePos;
    private javax.swing.JTextField txtKota;
    private javax.swing.JTextField txtLamaBekerja;
    private javax.swing.JTextField txtNamaAnak;
    private javax.swing.JTextField txtNamaIbuKandung;
    private javax.swing.JTextField txtNamaLengkap;
    private javax.swing.JTextField txtNamaPanggilan;
    private javax.swing.JTextField txtNamaPasangan;
    private javax.swing.JTextField txtNamaPekerjaan;
    private javax.swing.JTextField txtNamaPerusahaan;
    private javax.swing.JTextField txtNamaReferensi;
    private javax.swing.JTextField txtNoTelepon;
    private javax.swing.JTextField txtNoTelpReferensi;
    private javax.swing.JTextField txtNomorIdentitas;
    private javax.swing.JTextField txtNomorIdentitasPasangan;
    private javax.swing.JTextField txtNomorMeteranListrik;
    private javax.swing.JTextField txtNomorNPWP;
    private javax.swing.JTextField txtPekerjaanAnak;
    private javax.swing.JTextField txtPekerjaanReferensi;
    private javax.swing.JTextField txtPengeluaran;
    private javax.swing.JTextField txtProvinsi;
    private javax.swing.JTextField txtTanggalLahir;
    private javax.swing.JTextField txtTelpPerusahaan;
    private javax.swing.JTextField txtTempatLahir;
    private javax.swing.JTextField txtUmurAnak;
    private javax.swing.JTextField txtUmurPasangan;
    // End of variables declaration//GEN-END:variables
}
