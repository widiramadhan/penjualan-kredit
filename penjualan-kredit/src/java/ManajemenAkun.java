/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java;

import config.Koneksi;
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
public class ManajemenAkun extends javax.swing.JDialog {
    Koneksi kon = new Koneksi();
    public MenuUtama mu = null;
    private Object [][] datapengguna=null;
    private String[]label={"Id Pengguna","Nama","Email","Username","Akses Level"};

    public ManajemenAkun(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        kon.setKoneksi();      
        this.setResizable(false);
        tampilHakAkses();
    }
    
    private void validasiemail(){
          String email = txtEmail.getText();
          Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
          Matcher m = p.matcher(email);
          boolean matchFound = m.matches();
          if (matchFound)
            SimpanData();
          else
            JOptionPane.showMessageDialog(null,"Alamat Email tidak Valid");
    }
    
    private String no_otomatis(){
        String no=null;
        try{
            String sql = "Select right(id_pengguna,3)+1 from pengguna ";
            ResultSet rs = kon.st.executeQuery(sql);
            if (rs.next()){
                rs.last();
                no = rs.getString(1);
                while (no.length()<5){
                    no="00"+no;
                    no="ADM-"+no;
                txtIdPengguna.setText(no);    
                }
            }else{
                no="ADM-001";
                txtIdPengguna.setText(no);    
            }
        }catch (Exception e){ 
            JOptionPane.showMessageDialog(null, "No Otomatis Error");
        }return no;
    }
    
    private void Bersih(){
        txtIdPengguna.setText("");
        txtNamaPengguna.setText("");
        txtEmail.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        cmbHakAkses.setSelectedItem("- PILIH HAK AKSES -");
    }
    
    private void aktif(){
        txtNamaPengguna.setEnabled(true);
        txtEmail.setEnabled(true);
        txtUsername.setEnabled(true);
        txtPassword.setEnabled(true);
        cmbHakAkses.setEnabled(true);
    }
    
    private void nonaktif(){
        txtIdPengguna.setEnabled(false);
        txtNamaPengguna.setEnabled(false);
        txtEmail.setEnabled(false);
        txtUsername.setEnabled(false);
        txtPassword.setEnabled(false);
        cmbHakAkses.setEnabled(false);
    }
    
    private void tampilHakAkses(){
        try{
            String sql="select * from hak_akses order by nama_hak_akses asc";
            kon.rs=kon.st.executeQuery(sql);
            while(kon.rs.next()){
                cmbHakAkses.addItem(kon.rs.getString("nama_hak_akses"));
            }
        }catch(Exception e){
            
        }
    }
    
        private String simpanHakAkses(){
        String id=null;
        try{
            String hak_akses="select * from hak_akses where nama_hak_akses='"+cmbHakAkses.getSelectedItem()+"'";
            kon.rs = kon.st.executeQuery(hak_akses);
            while(kon.rs.next()){
            id = kon.rs.getString("id_hak_akses");
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }return id;
    }
    
    private void SimpanData(){
        try{
            String sql="insert into pengguna values('"+txtIdPengguna.getText()+"','"+txtNamaPengguna.getText()+"','"+txtEmail.getText()+"','"+txtUsername.getText()+"','"+txtPassword.getText()+"','"+simpanHakAkses()+"','1')";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
            Bersih();
            BacaTabelPengguna();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void UpdateData(){
        try{
            String sql="Update pengguna set id_pengguna='"+txtIdPengguna.getText()+"',nama='"+txtNamaPengguna.getText()+"',email='"+txtEmail.getText()+"',username='"+txtUsername.getText()+"',password='"+txtPassword.getText()+"' where id_pengguna='"+txtIdPengguna.getText()+"'";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil diupdate");
            Bersih();
            BacaTabelPengguna();
            }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }    
    }

        private void HapusData(){
        try{
            String sql="Delete from pengguna where id_pengguna='"+txtIdPengguna.getText()+"'";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
            Bersih();
            BacaTabelPengguna();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void BacaTabelPengguna(){
        try{
            String sql="Select * From pengguna A, hak_akses B where A.id_hak_akses=B.id_hak_akses order by A.id_pengguna";
            kon.rs=kon.st.executeQuery(sql);
            ResultSetMetaData m=kon.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            datapengguna=new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                datapengguna[x][0]=kon.rs.getString("id_pengguna");
                datapengguna[x][1]=kon.rs.getString("nama");
                datapengguna[x][2]=kon.rs.getString("email");
                datapengguna[x][3]=kon.rs.getString("username");
                datapengguna[x][4]=kon.rs.getString("nama_hak_akses");
                
                x++;
            }
            tabel_pengguna.setModel(new DefaultTableModel(datapengguna,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

     private void Pencarian(){
        try{
            String sql="select * from pengguna where nama like '%" +txtCari.getText()+ "%' ";
            kon.rs=kon.st.executeQuery(sql);
            ResultSetMetaData m=kon.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            datapengguna=new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                datapengguna[x][0]=kon.rs.getString("id_pengguna");
                datapengguna[x][1]=kon.rs.getString("nama");
                datapengguna[x][2]=kon.rs.getString("email");
                datapengguna[x][3]=kon.rs.getString("username");
                datapengguna[x][4]=kon.rs.getString("hak_akses");
                 x++;
            }
            tabel_pengguna.setModel(new DefaultTableModel(datapengguna,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
          
        private void setTable(){
            int row=tabel_pengguna.getSelectedRow();
            txtIdPengguna.setText((String)tabel_pengguna.getValueAt(row,0));
            txtNamaPengguna.setText((String)tabel_pengguna.getValueAt(row,1));
            txtEmail.setText((String)tabel_pengguna.getValueAt(row,2));
            txtUsername.setText((String)tabel_pengguna.getValueAt(row,3));
            txtPassword.setText((String)tabel_pengguna.getValueAt(row,4));
            cmbHakAkses.setSelectedItem((String)tabel_pengguna.getValueAt(row,4));
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
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtIdPengguna = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNamaPengguna = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbHakAkses = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        bCari = new javax.swing.JButton();
        bAll = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_pengguna = new javax.swing.JTable();
        btambah = new javax.swing.JButton();
        bsimpan = new javax.swing.JButton();
        bKeluar = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        bedit = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java/gambar/manajemen_akun.png"))); // NOI18N

        jLabel2.setText("Id Pengguna");

        jLabel3.setText("Nama Pengguna");

        jLabel4.setText("Email");

        jLabel5.setText("User Nama");

        jLabel6.setText("Kata Sandi");

        jLabel7.setText("Hak Akses");

        jLabel8.setText("Cari Berdasarkan Nama Pengguna :");

        bCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java/gambar/cari.png"))); // NOI18N
        bCari.setText("Cari");
        bCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCariActionPerformed(evt);
            }
        });

        bAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java/gambar/refresh.png"))); // NOI18N
        bAll.setText("Tampilkan Semua");
        bAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAllActionPerformed(evt);
            }
        });

        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariKeyPressed(evt);
            }
        });

        tabel_pengguna.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_pengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_penggunaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_pengguna);

        btambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java/gambar/tambah.png"))); // NOI18N
        btambah.setText("Tambah");
        btambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambahActionPerformed(evt);
            }
        });

        bsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java/gambar/simpan.png"))); // NOI18N
        bsimpan.setText("Simpan");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });

        bKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java/gambar/logout.png"))); // NOI18N
        bKeluar.setText("Keluar");
        bKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKeluarActionPerformed(evt);
            }
        });

        bhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java/gambar/remove.png"))); // NOI18N
        bhapus.setText("Hapus");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });

        bedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java/gambar/edit.png"))); // NOI18N
        bedit.setText("Edit");
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(txtNamaPengguna, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                .addComponent(txtIdPengguna)
                                .addComponent(jLabel6)
                                .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3)
                                .addComponent(cmbHakAkses, 0, 198, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addComponent(txtPassword))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(bCari)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(bAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel8)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bedit, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bKeluar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bCari)
                        .addComponent(bAll)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamaPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbHakAkses, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btambah, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bedit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(bKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(766, 605));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
 
    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariActionPerformed
        Pencarian();
    }//GEN-LAST:event_bCariActionPerformed

    private void bAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAllActionPerformed
        BacaTabelPengguna();
    }//GEN-LAST:event_bAllActionPerformed

    private void tabel_penggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_penggunaMouseClicked
        setTable();
        bKeluar.setText("Batal");
        bhapus.setEnabled(true);
        bedit.setEnabled(true);
        btambah.setEnabled(false);
    }//GEN-LAST:event_tabel_penggunaMouseClicked

    private void btambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambahActionPerformed
        aktif();
        txtNamaPengguna.requestFocus();
        bKeluar.setText("Batal");
        bsimpan.setEnabled(true);
        btambah.setEnabled(false);
        bedit.setEnabled(false);
        bhapus.setEnabled(false);
        txtCari.setEnabled(false);
        bCari.setEnabled(false);
        bAll.setEnabled(false);
        no_otomatis();
    }//GEN-LAST:event_btambahActionPerformed

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        String simpan=bsimpan.getText();
        if(simpan.equals("Simpan"))
        if (txtIdPengguna.getText().isEmpty() || txtNamaPengguna.getText().isEmpty() || txtEmail.getText().isEmpty() || txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty() || cmbHakAkses.equals("- PILIH HAK AKSES -")) {
            JOptionPane.showMessageDialog(this, "Lengkapi Data", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String email = txtEmail.getText();
            Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
            Matcher m = p.matcher(email);
            boolean matchFound = m.matches();
            if (matchFound){
                SimpanData();
                Bersih();
                nonaktif();
                bsimpan.setText("Simpan");
                bKeluar.setText("Keluar");
                btambah.setEnabled(true);
                bsimpan.setEnabled(false);
                bedit.setEnabled(false);
                bhapus.setEnabled(false);
                txtCari.setEnabled(true);
                bCari.setEnabled(true);
                bAll.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(null,"Alamat Email tidak Valid");
                txtEmail.setText("");
                txtEmail.requestFocus();
            }
        }else{
            String email = txtEmail.getText();
            Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
            Matcher m = p.matcher(email);
            boolean matchFound = m.matches();
            if (matchFound){
                UpdateData();
                Bersih();
                nonaktif();
                bsimpan.setText("Simpan");
                bKeluar.setText("Keluar");
                btambah.setEnabled(true);
                bsimpan.setEnabled(false);
                bedit.setEnabled(false);
                bhapus.setEnabled(false);
                txtCari.setEnabled(true);
                bCari.setEnabled(true);
                bAll.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(null,"Alamat Email tidak Valid");
            }
        }
    }//GEN-LAST:event_bsimpanActionPerformed

    private void bKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKeluarActionPerformed
        String keluar=bKeluar.getText();
        if(keluar.equals("Batal")){
            Bersih();
            nonaktif();
            bsimpan.setText("Simpan");
            bKeluar.setText("Keluar");
            btambah.setEnabled(true);
            bsimpan.setEnabled(false);
            bedit.setEnabled(false);
            bhapus.setEnabled(false);
            txtCari.setEnabled(true);
            bCari.setEnabled(true);
            bAll.setEnabled(true);
        }else{
            this.dispose();
        }
    }//GEN-LAST:event_bKeluarActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        if (JOptionPane.showConfirmDialog(this, "yakin mau dihapus?", "konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            HapusData();
            Bersih();
            nonaktif();
            bsimpan.setText("Simpan");
            bKeluar.setText("Keluar");
            btambah.setEnabled(true);
            bsimpan.setEnabled(false);
            bedit.setEnabled(false);
            bhapus.setEnabled(false);
            txtCari.setEnabled(true);
            bCari.setEnabled(true);
            bAll.setEnabled(true);
        } else {

            JOptionPane.showMessageDialog(this, "Data Batal Dihapus", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
            //btambah.setEnabled(true);
            return;
        }
    }//GEN-LAST:event_bhapusActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        aktif();
        bsimpan.setText("Update");
        bKeluar.setText("Batal");
        bsimpan.setEnabled(true);
        bedit.setEnabled(false);
        bhapus.setEnabled(false);
        txtCari.setEnabled(false);
        bCari.setEnabled(false);
        bAll.setEnabled(false);
    }//GEN-LAST:event_beditActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        BacaTabelPengguna();
        Bersih();
        nonaktif();
        bsimpan.setEnabled(false);
        bedit.setEnabled(false);
        bhapus.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Pencarian();
        }
    }//GEN-LAST:event_txtCariKeyPressed

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
            java.util.logging.Logger.getLogger(ManajemenAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManajemenAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManajemenAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManajemenAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
               ManajemenAkun dialog = new ManajemenAkun(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton bKeluar;
    private javax.swing.JButton bedit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton btambah;
    private javax.swing.JComboBox<String> cmbHakAkses;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabel_pengguna;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIdPengguna;
    private javax.swing.JTextField txtNamaPengguna;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
