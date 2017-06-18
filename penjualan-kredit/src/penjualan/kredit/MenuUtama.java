/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.kredit;

import java.awt.ComponentOrientation;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import penjualan.kredit.config.Koneksi;
import penjualan.kredit.config.SessionUtil;

/**
 *
 * @author Hp
 */
public class MenuUtama extends javax.swing.JFrame {
    SessionUtil sessionUtil = new SessionUtil();
    Koneksi kon=new Koneksi();
    /**
     * Creates new form MenuUtama1
     */
    public MenuUtama() {
        initComponents();
        kon.setKoneksi();
        cekLogin();
    }
    
    public void cekLogin(){
        try{
            String sql="select * from pengguna where status='0'";
            kon.rs=kon.st.executeQuery(sql);
            
            if (kon.rs.next()){
                String nama = kon.rs.getString("nama");
                
                //set menu di kiri
                jMenuBar2.add(Box.createHorizontalGlue()); 
                jMenuBar2.add(jMenu1);
                
                //custom menu
                jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/user.png"))); // NOI18N
                jMenu1.setText(nama);
                jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jMenu1.setMargin(new java.awt.Insets(10, 5, 5, 5));
                jMenu1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                
                //menambahkan menu item
                JMenuItem mnGantiPassword = new JMenuItem("Ganti Password");
                mnGantiPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/key.png")));
                JMenuItem mnLogout = new JMenuItem("Keluar Akun");
                mnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/logout.png")));
                JMenuItem mnExit = new JMenuItem("Keluar Program");
                mnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/remove.png")));
                jMenu1.add(mnGantiPassword);
                jMenu1.add(mnLogout);
                jMenu1.add(mnExit);
            }
        }catch(SQLException e){
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

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        mnFile = new javax.swing.JMenu();
        mnProduk = new javax.swing.JMenu();
        mnDataProduk = new javax.swing.JMenuItem();
        mnDataKategori = new javax.swing.JMenuItem();
        mnDataMerk = new javax.swing.JMenuItem();
        mnTransaksi = new javax.swing.JMenu();
        mnTransaksiKredit = new javax.swing.JMenuItem();
        mnPembayaranKredit = new javax.swing.JMenuItem();
        mnAkun = new javax.swing.JMenu();
        mnManajemenAkun = new javax.swing.JMenuItem();
        mnManajemenHakAkses = new javax.swing.JMenuItem();
        mnHakPenggunaAkun = new javax.swing.JMenuItem();
        mnLaporan = new javax.swing.JMenu();
        mnLaporanDataProduk = new javax.swing.JMenuItem();
        mnLaporanDataTransaksi = new javax.swing.JMenuItem();
        mnLaporanTransaksiKredit = new javax.swing.JMenuItem();
        mnLaporanDataReturProduk = new javax.swing.JMenuItem();
        mnPengaturan = new javax.swing.JMenu();
        mnPengaturanSistem = new javax.swing.JMenuItem();
        mnDatabase = new javax.swing.JMenu();
        mnBackupDatabase = new javax.swing.JMenuItem();
        mnRestoreDatabase = new javax.swing.JMenuItem();
        mnTentangToko = new javax.swing.JMenuItem();
        mnTentangAplikasi = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Halaman Utama - Aplikasi Penjualan Krdit CV. Takaidetama Indonesia");

        mnFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/home.png"))); // NOI18N
        mnFile.setText("File");
        mnFile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnFile.setMargin(new java.awt.Insets(10, 5, 5, 5));
        mnFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jMenuBar2.add(mnFile);

        mnProduk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/cart.png"))); // NOI18N
        mnProduk.setText("Produk");
        mnProduk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnProduk.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnProduk.setMargin(new java.awt.Insets(10, 5, 5, 5));
        mnProduk.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        mnDataProduk.setText("Data Produk");
        mnDataProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnDataProdukActionPerformed(evt);
            }
        });
        mnProduk.add(mnDataProduk);

        mnDataKategori.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/list.png"))); // NOI18N
        mnDataKategori.setText("Data Kategori");
        mnDataKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnDataKategoriActionPerformed(evt);
            }
        });
        mnProduk.add(mnDataKategori);

        mnDataMerk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/price-tag.png"))); // NOI18N
        mnDataMerk.setText("Data Merk");
        mnDataMerk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnDataMerkActionPerformed(evt);
            }
        });
        mnProduk.add(mnDataMerk);

        jMenuBar2.add(mnProduk);

        mnTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/change.png"))); // NOI18N
        mnTransaksi.setText("Transaksi");
        mnTransaksi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnTransaksi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnTransaksi.setMargin(new java.awt.Insets(10, 5, 5, 5));
        mnTransaksi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        mnTransaksiKredit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/credit-card.png"))); // NOI18N
        mnTransaksiKredit.setText("Transaksi Kredit");
        mnTransaksi.add(mnTransaksiKredit);

        mnPembayaranKredit.setText("Pembayaran Kredit");
        mnTransaksi.add(mnPembayaranKredit);

        jMenuBar2.add(mnTransaksi);

        mnAkun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/akun.png"))); // NOI18N
        mnAkun.setText("Akun");
        mnAkun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnAkun.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnAkun.setMargin(new java.awt.Insets(10, 5, 5, 5));
        mnAkun.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        mnManajemenAkun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/users.png"))); // NOI18N
        mnManajemenAkun.setText("Manajemen Akun");
        mnManajemenAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnManajemenAkunActionPerformed(evt);
            }
        });
        mnAkun.add(mnManajemenAkun);

        mnManajemenHakAkses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/antivirus.png"))); // NOI18N
        mnManajemenHakAkses.setText("Manajemen Hak Akses");
        mnManajemenHakAkses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnManajemenHakAksesActionPerformed(evt);
            }
        });
        mnAkun.add(mnManajemenHakAkses);

        mnHakPenggunaAkun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/padlock.png"))); // NOI18N
        mnHakPenggunaAkun.setText("Hak Pengguna Akun");
        mnAkun.add(mnHakPenggunaAkun);

        jMenuBar2.add(mnAkun);

        mnLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/notepad.png"))); // NOI18N
        mnLaporan.setText("Laporan");
        mnLaporan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnLaporan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnLaporan.setMargin(new java.awt.Insets(10, 5, 5, 5));
        mnLaporan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        mnLaporanDataProduk.setText("Laporan Data Produk");
        mnLaporan.add(mnLaporanDataProduk);

        mnLaporanDataTransaksi.setText("Laporan Data Transaksi");
        mnLaporan.add(mnLaporanDataTransaksi);

        mnLaporanTransaksiKredit.setText("Laporan Transaksi Kredit");
        mnLaporan.add(mnLaporanTransaksiKredit);

        mnLaporanDataReturProduk.setText("Laporan Data Retur Produk");
        mnLaporan.add(mnLaporanDataReturProduk);

        jMenuBar2.add(mnLaporan);

        mnPengaturan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/pengaturan.png"))); // NOI18N
        mnPengaturan.setText("Pengaturan");
        mnPengaturan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnPengaturan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnPengaturan.setMargin(new java.awt.Insets(10, 5, 5, 5));
        mnPengaturan.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        mnPengaturan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        mnPengaturanSistem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/settings.png"))); // NOI18N
        mnPengaturanSistem.setText("Pengaturan Sistem");
        mnPengaturanSistem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnPengaturanSistemActionPerformed(evt);
            }
        });
        mnPengaturan.add(mnPengaturanSistem);

        mnDatabase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/database.png"))); // NOI18N
        mnDatabase.setText("Database");

        mnBackupDatabase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/backup_db.png"))); // NOI18N
        mnBackupDatabase.setText("Cadangkan Database");
        mnBackupDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnBackupDatabaseActionPerformed(evt);
            }
        });
        mnDatabase.add(mnBackupDatabase);

        mnRestoreDatabase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan/kredit/gambar/restore_db.png"))); // NOI18N
        mnRestoreDatabase.setText("Pulihkan Database");
        mnRestoreDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnRestoreDatabaseActionPerformed(evt);
            }
        });
        mnDatabase.add(mnRestoreDatabase);

        mnPengaturan.add(mnDatabase);

        mnTentangToko.setText("Tentang Toko");
        mnTentangToko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnTentangTokoActionPerformed(evt);
            }
        });
        mnPengaturan.add(mnTentangToko);

        mnTentangAplikasi.setText("Tentang Aplikasi");
        mnPengaturan.add(mnTentangAplikasi);

        jMenuBar2.add(mnPengaturan);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 564, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 233, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnGantiPasswordActionPerformed(java.awt.event.ActionEvent evt) {                                                
        boolean closable = true;
        ManajemenAkun manajemenAkun = new ManajemenAkun(null, closable);
        manajemenAkun.mu = this;
        manajemenAkun.setVisible(true);
    } 
    
    private void mnLogoutActionPerformed(java.awt.event.ActionEvent evt) {                                                
        boolean closable = true;
        ManajemenAkun manajemenAkun = new ManajemenAkun(null, closable);
        manajemenAkun.mu = this;
        manajemenAkun.setVisible(true);
    } 
    
    private void mnExitActionPerformed(java.awt.event.ActionEvent evt) {                                                
        System.exit(0);
    } 
    
    private void mnPengaturanSistemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnPengaturanSistemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnPengaturanSistemActionPerformed

    private void mnTentangTokoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnTentangTokoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnTentangTokoActionPerformed

    private void mnDataProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnDataProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnDataProdukActionPerformed

    private void mnManajemenAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnManajemenAkunActionPerformed
        boolean closable = true;
        ManajemenAkun manajemenAkun = new ManajemenAkun(null, closable);
        manajemenAkun.mu = this;
        manajemenAkun.setVisible(true);
    }//GEN-LAST:event_mnManajemenAkunActionPerformed

    private void mnManajemenHakAksesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnManajemenHakAksesActionPerformed
        boolean closable = true;
        ManajemenHakAkses manajemenHakAkses = new ManajemenHakAkses(null, closable);
        manajemenHakAkses.mu = this;
        manajemenHakAkses.setVisible(true);
    }//GEN-LAST:event_mnManajemenHakAksesActionPerformed

    private void mnBackupDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnBackupDatabaseActionPerformed
        boolean closable = true;
        BackupDatabase backup = new BackupDatabase(null, closable);
        backup.mu = this;
        backup.setVisible(true);
    }//GEN-LAST:event_mnBackupDatabaseActionPerformed

    private void mnRestoreDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnRestoreDatabaseActionPerformed
        boolean closable = true;
        RestoreDatabase restore = new RestoreDatabase(null, closable);
        restore.mu = this;
        restore.setVisible(true);
    }//GEN-LAST:event_mnRestoreDatabaseActionPerformed

    private void mnDataKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnDataKategoriActionPerformed
        boolean closable = true;
        DataKategori dk = new DataKategori(null, closable);
        dk.mu = this;
        dk.setVisible(true);
    }//GEN-LAST:event_mnDataKategoriActionPerformed

    private void mnDataMerkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnDataMerkActionPerformed
        boolean closable = true;
        DataMerk dm = new DataMerk(null, closable);
        dm.mu = this;
        dm.setVisible(true);
    }//GEN-LAST:event_mnDataMerkActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MenuUtama().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenu mnAkun;
    private javax.swing.JMenuItem mnBackupDatabase;
    private javax.swing.JMenuItem mnDataKategori;
    private javax.swing.JMenuItem mnDataMerk;
    private javax.swing.JMenuItem mnDataProduk;
    private javax.swing.JMenu mnDatabase;
    private javax.swing.JMenu mnFile;
    private javax.swing.JMenuItem mnHakPenggunaAkun;
    private javax.swing.JMenu mnLaporan;
    private javax.swing.JMenuItem mnLaporanDataProduk;
    private javax.swing.JMenuItem mnLaporanDataReturProduk;
    private javax.swing.JMenuItem mnLaporanDataTransaksi;
    private javax.swing.JMenuItem mnLaporanTransaksiKredit;
    private javax.swing.JMenuItem mnManajemenAkun;
    private javax.swing.JMenuItem mnManajemenHakAkses;
    private javax.swing.JMenuItem mnPembayaranKredit;
    private javax.swing.JMenu mnPengaturan;
    private javax.swing.JMenuItem mnPengaturanSistem;
    private javax.swing.JMenu mnProduk;
    private javax.swing.JMenuItem mnRestoreDatabase;
    private javax.swing.JMenuItem mnTentangAplikasi;
    private javax.swing.JMenuItem mnTentangToko;
    private javax.swing.JMenu mnTransaksi;
    private javax.swing.JMenuItem mnTransaksiKredit;
    // End of variables declaration//GEN-END:variables
}
