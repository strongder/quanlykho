package View;

import javax.swing.*;

import Dao.LaptopDAO;
import Dao.NhaCungCapDAO;
import Dao.PCDAO;
import Dao.ProductDAO;
import Model.Laptop;
import Model.NhaCungCap;
import Model.PC;
import Model.Product;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddNhaCungCap extends JDialog implements ActionListener {

	private JTextField txtMaNCC;
	private JTextField txtTenNCC;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JButton addButton;
	private JButton cancelButton;
	private JPanel panel;

	public AddNhaCungCap(JFrame parent, JPanel panel) {
		this.panel = panel;
		txtMaNCC = new JTextField();
		txtTenNCC = new JTextField();
		txtDiaChi = new JTextField();
		txtSDT = new JTextField();

		JLabel title = new JLabel("Thêm nhà cung cấp");
		title.setFont(new Font("Tahoma", Font.BOLD, 24));
		title.setOpaque(true);
		title.setBackground(Color.CYAN);
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		JLabel lb1 = new JLabel("Mã nhà cung cấp");
		JLabel lb2 = new JLabel("Tên nhà cung cấp");
		JLabel lb3 = new JLabel("Địa chỉ");
		JLabel lb4 = new JLabel("Số điện thoại");

		addButton = new JButton("Thêm");
		cancelButton = new JButton("Hủy");
		JPanel content = new JPanel();
		content.setLayout(null);

		content.add(title);
		content.add(lb1);
		content.add(lb2);
		content.add(lb3);
		content.add(lb4);

		content.add(txtMaNCC);
		content.add(txtTenNCC);
		content.add(txtDiaChi);
		content.add(txtSDT);
		content.add(addButton);
		content.add(cancelButton);

		// Set bounds for labels and text fields
		title.setBounds(0, 0, 386, 80);
		lb1.setBounds(12, 111, 100, 28);
		lb2.setBounds(12, 162, 110, 28);
		lb3.setBounds(12, 216, 100, 28);
		lb4.setBounds(12, 270, 100, 28);

		txtMaNCC.setBounds(135, 110, 237, 31);
		txtTenNCC.setBounds(135, 161, 237, 31);
		txtDiaChi.setBounds(135, 215, 237, 31);
		txtSDT.setBounds(135, 269, 237, 31);

		// Set bounds for buttons
		addButton.setBounds(233, 384, 85, 39);
		addButton.setBackground(Color.CYAN);
		cancelButton.setBounds(70, 384, 85, 39);
		cancelButton.setBackground(Color.red);

		// Add action listeners

		addButton.addActionListener(this);
		cancelButton.addActionListener(this);

		setContentPane(content);
		setTitle("Thêm nhà cung cấp");
		setSize(400, 500);
		setLocationRelativeTo(parent);

		setVisible(true);
	}

	public void submit() {
		boolean flag = false;
		boolean exist = false;
		ArrayList<NhaCungCap> list = NhaCungCapDAO.getInstance().selectAll();
		for (NhaCungCap n : list) {
			if (txtMaNCC.getText().equalsIgnoreCase(n.getMaNCC()))
				exist = true;
		}
		if (txtMaNCC.getText().isEmpty()||txtTenNCC.getText().isEmpty()||txtSDT.getText().isEmpty()||txtDiaChi.getText().isEmpty()) {
			flag = true;
		}
		if (!exist && !flag) {

			NhaCungCap n = new NhaCungCap();
			n.setMaNCC(txtMaNCC.getText());
			n.setTenNhaCungCap(txtTenNCC.getText());
			n.setDiaChi(txtDiaChi.getText());
			n.setSdt(txtSDT.getText());

			int check = NhaCungCapDAO.getInstance().insert(n);
			if (check == 0) {
				JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thất bại", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else {

				JOptionPane.showMessageDialog(this, "Lưu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				cancel();
				((NhaCungCapView) panel).displayTable();
			}

		} else if (flag) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin", "ERROR", JOptionPane.ERROR_MESSAGE);

		} else {
			JOptionPane.showMessageDialog(this, "Lưu thành thất bại nhà cung cấp đã tồn tại", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void cancel() {
		dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.equals(addButton)) {
			submit();
		} else {
			cancel();
		}

	}
}
