package View;

import javax.swing.*;

import Dao.LaptopDAO;
import Dao.PCDAO;
import Dao.ProductDAO;
import Model.Laptop;
import Model.PC;
import Model.Product;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductDialog extends JDialog implements ActionListener {

	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JTextField txtGiaNhap;
	private JTextField txtGiaXuat;
	private JTextField txtXuatXu;
	private JTextField txtCPU;
	private JTextField txtRAM;
	private JTextField txtDungLuong;
	private JTextField txtCard;
	private JTextField txtKichThuoc;
	private JTextField txtPin;
	private JComboBox<String> loaiSP;
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

	private JButton btnSubmit;
	private JButton btnCancel;
	private JPanel panel;

	public AddProductDialog(JFrame parent) {

		title = new JLabel("Thêm sản phẩm");
		title.setBounds(0, 0, 732, 71);
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
		lb12 = new JLabel("Giá xuât");
		lb12.setBounds(390, 370, 90, 32);
		txtGiaXuat = new JTextField();
		txtGiaXuat.setBounds(496, 370, 190, 32);
		lb4 = new JLabel("Xuất xứ");
		lb4.setBounds(20, 265, 90, 32);
		txtXuatXu = new JTextField();
		txtXuatXu.setBounds(110, 265, 190, 32);
		lb5 = new JLabel("CPU");
		lb5.setBounds(20, 318, 90, 32);
		txtCPU = new JTextField();
		txtCPU.setBounds(110, 318, 190, 32);
		lb6 = new JLabel("RAM");
		lb6.setBounds(20, 370, 90, 32);
		txtRAM = new JTextField();
		txtRAM.setBounds(110, 370, 190, 32);
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
		txtKichThuoc = new JTextField();
		txtKichThuoc.setBounds(496, 265, 190, 32);
		lb11 = new JLabel("Loại sản phẩm");
		lb11.setBounds(390, 318, 90, 32);
		loaiSP = new JComboBox();
		loaiSP.setBounds(496, 318, 100, 32);
		loaiSP.setModel(new DefaultComboBoxModel(new String[] { "Laptop", "PC" }));
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

		btnSubmit = new JButton("Xác nhận");
		btnSubmit.setBounds(458, 428, 110, 38);
		btnCancel = new JButton("Hủy");
		btnCancel.setBounds(203, 428, 110, 38);

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
		panel.add(lb12);
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
		panel.add(btnSubmit);

		btnSubmit.addActionListener(this);

		btnCancel.addActionListener(this);
		setTitle("Thêm sản phẩm");
		setSize(750, 540);
		setLocationRelativeTo(parent);
		setContentPane(panel);
		setVisible(true);
	}

	public void submit() {
		int check = 0;
		try {

			boolean exist = false;
			boolean flag = false;
			ArrayList<Product> list = ProductDAO.getInstance().selectAll();
			for (Product p : list) {
				if (txtMaSP.getText().equalsIgnoreCase(p.getMaMay()))
					exist = true;
			}
			if (!exist ) {
				if (loaiSP.getSelectedItem().equals("Laptop")) {
					Laptop lt = new Laptop();
					lt.setMaMay(txtMaSP.getText());
					lt.setTenMay(txtTenSP.getText());
					lt.setTenCpu(txtCPU.getText());
					lt.setCardManHinh(txtCard.getText());
					lt.setDungLuongPin(txtPin.getText());
					lt.setKichThuoc(Float.parseFloat(txtKichThuoc.getText()));
					lt.setGiaNhap(Double.parseDouble(txtGiaNhap.getText()));
					lt.setGiaXuat(Double.parseDouble(txtGiaXuat.getText()));
					lt.setRam(txtRAM.getText());
					lt.setRom(txtDungLuong.getText());
					lt.setXuatXu(txtXuatXu.getText());

					check =LaptopDAO.getInstance().insert(lt);

				} else {
					PC pc = new PC();
					pc.setMaMay(txtMaSP.getText());
					pc.setTenMay(txtTenSP.getText());
					pc.setTenCpu(txtCPU.getText());
					pc.setCardManHinh(txtCard.getText());
					pc.setCongSuatNguon(ABORT);
					pc.setMainBoard(txtPin.getText());
					pc.setGiaNhap(Double.parseDouble(txtGiaNhap.getText()));
					pc.setGiaXuat(Double.parseDouble(txtGiaXuat.getText()));
					pc.setRam(txtRAM.getText());
					pc.setRom(txtDungLuong.getText());
					pc.setXuatXu(txtXuatXu.getText());
					check = PCDAO.getInstance().insert(pc);

				}
				if (check!=0)
				{	
					showSaveSuccessMessage();
					cancel();
					((ProductView) panel).disPlayTable();
				}
				else {
					JOptionPane.showMessageDialog(this, "Lưu thất thất bại", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Lưu thất thất bại  sản phẩm bị trùng", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin", "ERROR", JOptionPane.ERROR_MESSAGE);

		}

	}

	public void showSaveSuccessMessage() {
		JOptionPane.showMessageDialog(this, "Lưu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	}

	public void cancel() {

		dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.equals(btnCancel)) {

			cancel();
		} else if (button.equals(btnSubmit)) {
			submit();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new AddProductDialog(new JFrame()));
	}
}
