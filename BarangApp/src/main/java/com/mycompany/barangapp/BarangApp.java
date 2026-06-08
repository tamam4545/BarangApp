package com.mycompany.barangapp;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BarangApp extends JFrame {

    JLabel lblNama = new JLabel("Nama Barang");
    JLabel lblHarga = new JLabel("Harga");
    JLabel lblJumlah = new JLabel("Jumlah");
    JLabel lblJenis = new JLabel("Jenis");

    JTextField txtNama = new JTextField();
    JTextField txtHarga = new JTextField();
    JTextField txtJumlah = new JTextField();

    String[] jenisBarang = {"Makanan", "Minuman"};

    JComboBox<String> cmbJenis =
            new JComboBox<>(jenisBarang);

    JButton btnTambah =
            new JButton("Tambah");

    JButton btnTotal =
            new JButton("Hitung Total");

    JTable table;
    DefaultTableModel model;

    JLabel lblTotal =
            new JLabel("Total Bayar : Rp 0");

    ArrayList<Barang> daftarBarang =
            new ArrayList<>();

    double grandTotal = 0;

    public BarangApp() {

        setTitle("Aplikasi Kasir");
        setSize(800,600);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        lblNama.setBounds(30,30,100,25);
        txtNama.setBounds(150,30,180,25);

        lblHarga.setBounds(30,70,100,25);
        txtHarga.setBounds(150,70,180,25);

        lblJumlah.setBounds(30,110,100,25);
        txtJumlah.setBounds(150,110,180,25);

        lblJenis.setBounds(30,150,100,25);
        cmbJenis.setBounds(150,150,180,25);

        btnTambah.setBounds(30,200,120,30);
        btnTotal.setBounds(180,200,150,30);

        model = new DefaultTableModel();

        model.addColumn("Nama");
        model.addColumn("Jenis");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Diskon");
        model.addColumn("Total");

        table = new JTable(model);

        JScrollPane pane =
                new JScrollPane(table);

        pane.setBounds(30,260,700,200);

        lblTotal.setBounds(30,490,300,30);

        add(lblNama);
        add(txtNama);

        add(lblHarga);
        add(txtHarga);

        add(lblJumlah);
        add(txtJumlah);

        add(lblJenis);
        add(cmbJenis);

        add(btnTambah);
        add(btnTotal);

        add(pane);
        add(lblTotal);

        btnTambah.addActionListener(
                e -> tambahData());

        btnTotal.addActionListener(e -> {

            lblTotal.setText(
                    "Total Bayar : Rp " +
                    grandTotal);

            JOptionPane.showMessageDialog(
                    this,
                    "=== STRUK ===\n" +
                    "Total Bayar : Rp " +
                    grandTotal
            );

        });

        setVisible(true);
    }

    private void tambahData() {

        try {

            String nama =
                    txtNama.getText();

            double harga =
                    Double.parseDouble(
                    txtHarga.getText());

            int jumlah =
                    Integer.parseInt(
                    txtJumlah.getText());

            Barang barang;

            String jenis =
                    cmbJenis.getSelectedItem()
                    .toString();

            if(jenis.equals("Makanan")) {

                barang =
                        new Makanan(
                                nama,
                                harga,
                                jumlah);

            } else {

                barang =
                        new Minuman(
                                nama,
                                harga,
                                jumlah);

            }

            daftarBarang.add(barang);

            double diskon =
                    barang.hitungDiskon();

            double total =
                    barang.hitungTotal()
                    - diskon;

            grandTotal += total;

            model.addRow(new Object[]{

                    barang.getNamaBarang(),
                    jenis,
                    harga,
                    jumlah,
                    diskon,
                    total

            });

            txtNama.setText("");
            txtHarga.setText("");
            txtJumlah.setText("");

        }

        catch(Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    "Input salah!"
            );

        }

    }

    public static void main(String[] args) {

        new BarangApp();

    }

}

abstract class Barang {

    private String namaBarang;
    private double hargaBarang;
    private int jumlah;

    public Barang(
            String namaBarang,
            double hargaBarang,
            int jumlah) {

        this.namaBarang =
                namaBarang;

        this.hargaBarang =
                hargaBarang;

        this.jumlah =
                jumlah;
    }

    public String getNamaBarang() {

        return namaBarang;

    }

    public double getHargaBarang() {

        return hargaBarang;

    }

    public int getJumlah() {

        return jumlah;

    }

    public double hitungTotal() {

        return hargaBarang * jumlah;

    }

    public abstract double hitungDiskon();

}

class Makanan extends Barang {

    public Makanan(
            String nama,
            double harga,
            int jumlah) {

        super(
                nama,
                harga,
                jumlah);

    }

    @Override
    public double hitungDiskon() {

        return hitungTotal() * 0.10;

    }

}

class Minuman extends Barang {

    public Minuman(
            String nama,
            double harga,
            int jumlah) {

        super(
                nama,
                harga,
                jumlah);

    }

    @Override
    public double hitungDiskon() {

        return hitungTotal() * 0.05;

    }

}