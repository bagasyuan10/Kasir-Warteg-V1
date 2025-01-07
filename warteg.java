import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Warteg extends JFrame {
    private List<MenuItem> orderItems;
    private JTextField nameField, priceField, quantityField, paymentField;
    private JTextArea orderArea, receiptArea;
    private double totalAmount;

    public Warteg() {
        orderItems = new ArrayList<>();
        totalAmount = 0;

        // Pengaturan JFrame
        setTitle("Sistem Kasir Warteg");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama dengan BorderLayout
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("path_to_background_image.jpg"); // Ganti dengan path gambar latar belakang yang benar
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setBackground(Color.WHITE);

        // Panel Input Menu dengan bayangan dan border radius
        JPanel inputPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(255, 255, 255, 200)); // Warna putih dengan transparansi
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        inputPanel.setOpaque(false);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Spasi di sekeliling panel

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nameLabel = new JLabel("Nama Menu:");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameLabel.setForeground(new Color(34, 193, 195));

        nameField = new JTextField(20);
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
        nameField.setCaretColor(new Color(34, 193, 195));

        JLabel priceLabel = new JLabel("Harga Menu (Rp):");
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        priceLabel.setForeground(new Color(34, 193, 195));

        priceField = new JTextField(10);
        priceField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        priceField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
        priceField.setCaretColor(new Color(34, 193, 195));

        JLabel quantityLabel = new JLabel("Jumlah:");
        quantityLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        quantityLabel.setForeground(new Color(34, 193, 195));

        quantityField = new JTextField(5);
        quantityField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        quantityField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
        quantityField.setCaretColor(new Color(34, 193, 195));

        // Tombol untuk menambah menu
        JButton addButton = new JButton("Tambah Menu");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addButton.setBackground(new Color(34, 193, 195));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createBevelBorder(1));
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addButton.setBackground(new Color(253, 187, 45));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addButton.setBackground(new Color(34, 193, 195));
            }
        });

        // Menata komponen dengan GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(priceLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(quantityLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(quantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        inputPanel.add(addButton, gbc);

        // Panel untuk Menampilkan Order
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(new Color(255, 255, 255, 200)); // Warna putih dengan transparansi
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        orderArea = new JTextArea(15, 40);
        orderArea.setEditable(false);
        orderArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        orderArea.setBackground(Color.WHITE);
        orderArea.setForeground(Color.BLACK);
        orderArea.setLineWrap(true);
        JScrollPane orderScrollPane = new JScrollPane(orderArea);

        // Panel untuk Menampilkan Struk Pembayaran
        JPanel receiptPanel = new JPanel(new BorderLayout());
        receiptPanel.setBackground(new Color(255, 255, 255, 200)); // Warna putih dengan transparansi
        receiptPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        receiptArea = new JTextArea(10, 40);
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        receiptArea.setBackground(Color.WHITE);
        receiptArea.setForeground(Color.BLACK);
        receiptArea.setLineWrap(true);
        JScrollPane receiptScrollPane = new JScrollPane(receiptArea);

        // Panel untuk Pembayaran
        JPanel paymentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        paymentPanel.setBackground(new Color(255, 255, 255, 200)); // Warna putih dengan transparansi

        JLabel paymentLabel = new JLabel("Bayar (Rp):");
        paymentLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        paymentLabel.setForeground(new Color(34, 193, 195));

        paymentField = new JTextField(15);
        paymentField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        paymentField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
        paymentField.setCaretColor(new Color(34, 193, 195));

        // Tombol untuk proses pembayaran
        JButton payButton = new JButton("Proses Pembayaran");
        payButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        payButton.setBackground(new Color(34, 193, 195));
        payButton.setForeground(Color.WHITE);
        payButton.setFocusPainted(false);
        payButton.setBorder(BorderFactory.createBevelBorder(1));
        payButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        payButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                payButton.setBackground(new Color(253, 187, 45));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                payButton.setBackground(new Color(34, 193, 195));
            }
        });

        paymentPanel.add(paymentLabel);
        paymentPanel.add(paymentField);
        paymentPanel.add(payButton);

        // Menggabungkan Panel untuk Input, Order, dan Pembayaran
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(orderScrollPane, BorderLayout.CENTER);
        panel.add(paymentPanel, BorderLayout.SOUTH);

        // Action Listener untuk tombol Tambah Menu
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());

                    // Menambahkan MenuItem baru ke daftar pesanan
                    MenuItem newItem = new MenuItem(name, price, quantity);
                    orderItems.add(newItem);

                    // Memperbarui tampilan order
                    orderArea.setText(getOrderDetails());

                    // Reset field input
                    nameField.setText("");
                    priceField.setText("");
                    quantityField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Harga dan Jumlah harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action Listener untuk tombol Proses Pembayaran
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double payment = Double.parseDouble(paymentField.getText());

                    // Pilihan metode pembayaran: Tunai atau QRIS
                    String[] options = {"Tunai", "QRIS"};
                    int choice = JOptionPane.showOptionDialog(
                            null,
                            "Pilih metode pembayaran",
                            "Metode Pembayaran",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            options,
                            options[0]
                    );

                    if (choice == 0) {
                        double change = payment - totalAmount;
                        if (change >= 0) {
                            receiptArea.setText(generateReceipt(payment, change));
                        } else {
                            JOptionPane.showMessageDialog(null, "Pembayaran tidak cukup!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (choice == 1) {
                        // Menampilkan gambar QRIS (simulasi gambar QRIS)
                        ImageIcon qrisImage = new ImageIcon("path_to_qris_image.png"); // Ganti dengan path gambar QRIS yang benar
                        JOptionPane.showMessageDialog(null, "", "QRIS Pembayaran", JOptionPane.INFORMATION_MESSAGE, qrisImage);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Masukkan jumlah pembayaran yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Menambahkan Panel untuk Struk Pembayaran ke JFrame
        add(receiptScrollPane, BorderLayout.EAST);
        // Menambahkan Panel ke JFrame
        add(panel);
    }

    // Method untuk mendapatkan detail pesanan
    private String getOrderDetails() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        StringBuilder sb = new StringBuilder("Pesanan:\n\n");
        totalAmount = 0;

        for (MenuItem item : orderItems) {
            sb.append(item.toString()).append("\n");
            totalAmount += item.getTotalPrice();
        }

        sb.append("\n---------------------------------\nTotal: Rp. ").append(df.format(totalAmount));
        return sb.toString();
    }

    // Method untuk generate struk pembayaran
    private String generateReceipt(double payment, double change) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        StringBuilder sb = new StringBuilder("Struk Pembayaran:\n\n");

        sb.append(getOrderDetails()).append("\n");
        sb.append("\nPembayaran: Rp. ").append(df.format(payment));
        sb.append("\nKembalian: Rp. ").append(df.format(change));
        sb.append("\nTerima kasih telah berbelanja!");
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Warteg().setVisible(true);
            }
        });
    }
}

class MenuItem {
    private String name;
    private double price;
    private int quantity;

    public MenuItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return name + ": Rp. " + df.format(price) + " x " + quantity + " = Rp. " + df.format(getTotalPrice());
    }
}