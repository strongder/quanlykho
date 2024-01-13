package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Dao.LaptopDAO;
import Dao.PCDAO;
import Dao.ProductDAO;
import Model.Laptop;
import Model.PC;
import Model.Product;

public class UpdateProductDialog extends JDialog implements ActionListener {

	JTextField txtMaSP;
	JTextField txtTenSP;
	JTextField txtGiaNhap;
	JTextField txtXuatXu;
	JTextField txtCPU;
	JTextField txtRAM;
	JTextField txtDungLuong;
	JTextField txtCard;
	JTextField txtKichThuoc;
	JTextField txtPin;
	JTextField txtGiaXuat;
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
	private JButton btnSubmit;

	JPanel panel;

	public UpdateProductDialog(JFrame parent, JPanel panel) {

		this.panel = panel;

		title = new JLabel("SỬA THÔNG TIN SẢN PHẨM");
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
		lb3 = new JLabel("Đơn giá");
		lb3.setBounds(20, 205, 90, 32);
		txtGiaNhap = new JTextField();
		txtGiaNhap.setBounds(110, 205, 190, 32);
		lb4 = new JLabel("Xuất xứ");
		lb4.setBounds(20, 265, 90, 32);
		txtXuatXu = new JTextField();
		txtXuatXu.setBounds(110, 265, 190, 32);
		lb5 = new JLabel("CPU");
		lb5.setBounds(20, 320, 90, 32);
		txtCPU = new JTextField();
		txtCPU.setBounds(110, 320, 190, 32);
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
		lb9.setBounds(390, 205, 100, 32);
		txtPin = new JTextField();
		txtPin.setBounds(496, 205, 190, 32);
		lb10 = new JLabel("Kích thước màn");
		lb10.setBounds(390, 265, 100, 32);
		txtKichThuoc = new JTextField();
		txtKichThuoc.setBounds(496, 265, 190, 32);
		lb11 = new JLabel("Loại sản phẩm");
		lb11.setBounds(390, 320, 90, 32);
		loaiSP = new JComboBox();
		loaiSP.setBounds(496, 320, 100, 32);
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

		lb12 = new JLabel("Giá xuât");
		lb12.setBounds(390, 370, 90, 32);
		txtGiaXuat = new JTextField();
		txtGiaXuat.setBounds(496, 370, 190, 32);

		btnCancel = new JButton("Thoát");
		btnCancel.setBounds(150, 428, 110, 38);

		btnSubmit = new JButton("Lưu");
		btnSubmit.setBounds(480, 428, 110, 38);

		JPanel mainContent = new JPanel(null);
		mainContent.add(lb1);
		mainContent.add(lb2);
		mainContent.add(lb3);
		mainContent.add(lb4);
		mainContent.add(lb5);
		mainContent.add(lb6);
		mainContent.add(lb7);
		mainContent.add(lb8);
		mainContent.add(lb9);
		mainContent.add(lb10);
		mainContent.add(lb11);
		mainContent.add(title);
		mainContent.add(txtMaSP);
		mainContent.add(txtTenSP);
		mainContent.add(txtCard);
		mainContent.add(txtCPU);
		mainContent.add(txtGiaNhap);
		mainContent.add(txtDungLuong);
		mainContent.add(txtKichThuoc);
		mainContent.add(txtPin);
		mainContent.add(txtRAM);
		mainContent.add(txtXuatXu);
		mainContent.add(loaiSP);
		mainContent.add(btnCancel);
		mainContent.add(btnSubmit);
		mainContent.add(lb12);
		mainContent.add(txtGiaXuat);

		btnSubmit.addActionListener(this);
		btnCancel.addActionListener(this);
		setTitle("Sửa thông tin sản phẩm");
		setSize(750, 540);
		setLocationRelativeTo(parent);
		setContentPane(mainContent);
		setVisible(true);
	}


	public void submit() {
		int check;
		if (loaiSP.getSelectedItem().equals("Laptop")) {
			Laptop lt = new Laptop();
			lt.setMaMay(txtMaSP.getText());
			lt.setTenMay(txtTenSP.getText());
			lt.setTenCpu(txtCPU.getText());
			lt.setCardManHinh(txtCard.getText());
			lt.setDungLuongPin(txtPin.getText());
			lt.setKichThuoc(Float.parseFloat(txtKichThuoc.getText()));
			String giaNhap = txtGiaNhap.getText().substring(0, txtGiaNhap.getText().length()-2);
			String giaXuat = txtGiaXuat.getText().substring(0, txtGiaXuat.getText().length()-2);
			lt.setGiaNhap(Double.parseDouble(giaNhap));
			lt.setGiaXuat(Double.parseDouble(giaXuat));
			lt.setRam(txtRAM.getText());
			lt.setRom(txtDungLuong.getText());
			lt.setXuatXu(txtXuatXu.getText());

			check = LaptopDAO.getInstance().update(lt);
		} else {
			PC pc = new PC();
			pc.setMaMay(txtMaSP.getText());
			pc.setTenMay(txtTenSP.getText());
			pc.setTenCpu(txtCPU.getText());
			pc.setCardManHinh(txtCard.getText());
			pc.setCongSuatNguon(Integer.parseInt(txtKichThuoc.getText()));
			pc.setMainBoard(txtPin.getText());
			String giaNhap = txtGiaNhap.getText().substring(0, txtGiaNhap.getText().length()-2);
			String giaXuat = txtGiaXuat.getText().substring(0, txtGiaXuat.getText().length()-2);
			pc.setGiaNhap(Double.parseDouble(giaNhap));
			pc.setGiaXuat(Double.parseDouble(giaXuat));
			pc.setRam(txtRAM.getText());
			pc.setRom(txtDungLuong.getText());
			pc.setXuatXu(txtXuatXu.getText());

			check = PCDAO.getInstance().update(pc);
		}
		if (check != 0) {
			showSaveSuccessMessage();
			cancel();
			((ProductView) panel).disPlayTable();
		}else
			JOptionPane.showMessageDialog(this, "Lưu thất bại", "ERROR", JOptionPane.ERROR_MESSAGE);
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
}
