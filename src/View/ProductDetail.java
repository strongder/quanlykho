package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ProductDetail extends JDialog{
	 JTextField txtMaSP;
	 JTextField txtTenSP;
	 JTextField txtGiaNhap;
	 JTextField txtGiaXuat;
	 JTextField txtXuatXu;
	 JTextField txtCPU;
	 JTextField txtRAM;
	 JTextField txtDungLuong;
	 JTextField txtCard;
	 JTextField txtKichThuoc;
	 JTextField txtPin;
	 JTextField txtSoLuong;
	 JComboBox<String> loaiSP;
	private JLabel title;
	private JLabel lb1;
	private JLabel lb2;

	private JLabel lb3;
	private JLabel lb4;
	private JLabel lb5;
	private JLabel lb6;
	private JLabel lb7;
	private JLabel lb8;
	private JLabel lb9;
	private JLabel lb10;
	private JLabel lb11;
	private JLabel lb12;
	

	private JButton btnCancel;

	public ProductDetail(JFrame parent) {

		title = new JLabel("Thông tin sản phẩm");
		title.setBounds(0, 0, 750, 71);
		title.setFont(new Font("Tahoma", Font.BOLD, 24));
		title.setOpaque(true);
		title.setBackground(Color.CYAN);
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		lb1 = new JLabel("Mã sản phẩm");
		lb1.setBounds(20, 85, 90, 32);
		txtMaSP = new JTextField();
		txtMaSP.setBounds(110, 85, 190, 32);
		lb2 = new JLabel("Tên sản phẩm");
		lb2.setBounds(20, 145, 90, 32);
		txtTenSP = new JTextField();
		txtTenSP.setBounds(110, 145, 190, 32);
		lb3 = new JLabel("Giá nhập");
		lb3.setBounds(20, 205, 90, 32);
		txtGiaNhap = new JTextField();
		txtGiaNhap.setBounds(110, 205, 190, 32);
		lb4 = new JLabel("Xuất xứ");
		lb4.setBounds(20, 265, 90, 32);
		txtXuatXu = new JTextField();
		txtXuatXu.setBounds(110, 265, 190, 32);
		lb5 = new JLabel("CPU");
		lb5.setBounds(20, 325, 90, 32);
		txtCPU = new JTextField();
		txtCPU.setBounds(110, 325, 190, 32);
		lb6 = new JLabel("RAM");
		lb6.setBounds(20, 385, 90, 32);
		txtRAM = new JTextField();
		txtRAM.setBounds(110, 385, 190, 32);
		
		JLabel lb13 = new JLabel("Giá xuất");
		lb13.setBounds(20, 445, 90, 32);
		txtGiaXuat = new JTextField();
		txtGiaXuat.setBounds(110, 445, 190, 32);
		
		lb7 = new JLabel("Dung lượng");
		lb7.setBounds(390, 85, 90, 32);
		txtDungLuong = new JTextField();
		txtDungLuong.setBounds(496, 85, 190, 32);
		lb8 = new JLabel("Card đồ họa");
		lb8.setBounds(390, 145, 90, 32);
		txtCard = new JTextField();
		txtCard.setBounds(496, 145, 190, 32);
		lb9 = new JLabel("Dung lượng pin");
		lb9.setBounds(390, 205, 90, 32);
		txtPin = new JTextField();
		txtPin.setBounds(496, 205, 190, 32);
		lb10 = new JLabel("Kích thước màn");
		lb10.setBounds(390, 265, 90, 32);
		txtKichThuoc = new JTextField();
		txtKichThuoc.setBounds(496, 265, 190, 32);
		lb11 = new JLabel("Loại sản phẩm");
		lb11.setBounds(390, 325, 90, 32);
		loaiSP = new JComboBox();
		loaiSP.setBounds(496, 325, 100, 32);
		loaiSP.setModel(new DefaultComboBoxModel(new String[] {"Laptop", "PC"}));;
		
		loaiSP.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String selectedDevice = (String) loaiSP.getSelectedItem();
					if ("PC".equals(selectedDevice)) {
						lb10.setText("Công Suất:");
						lb9.setText("Main Board:");
					} else if ("Laptop".equals(selectedDevice)) {
						lb10.setText("Kích thước màn:");
						lb9.setText("Dung lượng pin:");
					}
				}
			}
		});
		lb12 = new JLabel("Số lượng");
		lb12.setBounds(390, 385, 90, 32);
		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(496, 385, 190, 32);
	
		btnCancel = new JButton("Thoát");
		btnCancel.setBounds(310, 490, 110, 38);

		JPanel panel = new JPanel(null);
		panel.add(lb1);
		panel.add(lb2);
		panel.add(lb3);
		panel.add(lb4);
		panel.add(lb5);
		panel.add(lb6);
		panel.add(lb7);
		panel.add(lb8);
		panel.add(lb9);
		panel.add(lb10);
		panel.add(lb11);
		panel.add(lb13);
		panel.add(title);
		panel.add(txtMaSP);
		panel.add(txtTenSP);
		panel.add(txtCard);
		panel.add(txtCPU);
		panel.add(txtGiaNhap);
		panel.add(txtGiaXuat);
		panel.add(txtDungLuong);
		panel.add(txtKichThuoc);
		panel.add(txtPin);
		panel.add(txtRAM);
		panel.add(txtXuatXu);
		panel.add(loaiSP);
		panel.add(btnCancel);
		panel.add(lb12);panel.add(txtSoLuong);


		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		setTitle("Thông tin sản phẩm");
		setSize(750, 600);
		setLocationRelativeTo(parent);
		setContentPane(panel);
		setVisible(true);
	}

	private void handleSubmit() {
		dispose();
	}

}
