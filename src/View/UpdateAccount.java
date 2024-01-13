package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Dao.AccountDAO;
import Model.Account;

public class UpdateAccount extends JDialog implements ActionListener {
	JTextField txtFullName;
	JTextField txtUserName;
	JTextField txtEmail;
	JPasswordField txtPassword;
	JComboBox<String> role;
	private JButton submit;
	private JButton cancel;
	private JPanel panel;

	public UpdateAccount(JFrame parent, JPanel panel) {
		this.panel = panel;
		setSize(469, 560);
		Font label = new Font("Tahoma", Font.BOLD, 13);
		Font textField = new Font("Tahoma", Font.PLAIN, 14);
		Font button = new Font("Tahoma", Font.BOLD, 14);
		JLabel title = new JLabel("SỬA THÔNG TIN TÀI KHOẢN");
		title.setBounds(0, 0, 470, 60);
		title.setFont(new Font("Tahoma", Font.BOLD, 24));
		title.setOpaque(true);
		title.setBackground(Color.CYAN);
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		txtFullName = new JTextField();
		txtFullName.setBounds(101, 93, 252, 32);
		txtFullName.setFont(textField);
		txtUserName = new JTextField();
		txtUserName.setBounds(101, 160, 252, 32);
		txtUserName.setFont(textField);
		txtEmail = new JTextField();
		txtEmail.setBounds(101, 234, 252, 32);
		txtEmail.setFont(textField);
		txtUserName.setEditable(false);
		txtPassword = new JPasswordField();
		txtPassword.setEditable(false);
		txtPassword.setBounds(101, 300, 252, 32);
		JLabel lb1 = new JLabel("Họ tên");
		lb1.setFont(label);
		lb1.setBounds(101, 70, 100, 22);
		JLabel lb2 = new JLabel("Tên đăng nhập");
		lb2.setBounds(101, 138, 100, 22);
		lb2.setFont(label);
		JLabel lb3 = new JLabel("Email");
		lb3.setBounds(101, 210, 100, 22);
		lb3.setFont(label);
		JLabel lb4 = new JLabel("Mật khẩu");
		lb4.setBounds(101, 277, 100, 22);
		lb4.setFont(label);
		JLabel lb5 = new JLabel("Vai trò");
		lb5.setBounds(101, 345, 100, 22);
		lb5.setFont(label);
		role = new JComboBox();
		role.setBounds(101, 372, 252, 32);
		role.setModel(new DefaultComboBoxModel(new String[] { "ADMIN", "QUANLY", "NHANVIEN" }));

		submit = new JButton("Lưu");
		cancel = new JButton("Hủy");
		cancel.setBounds(66, 436, 93, 32);
		cancel.setFont(button);
		submit.setBounds(293, 436, 93, 32);
		submit.setFont(button);

		JPanel content = new JPanel(null);
		content.add(lb5);
		content.add(lb4);
		content.add(lb3);
		content.add(lb2);
		content.add(lb1);

		content.add(title);
		content.add(txtEmail);
		content.add(txtFullName);
		content.add(txtPassword);
		content.add(txtUserName);
		content.add(role);
		content.add(submit);
		content.add(cancel);

		submit.addActionListener(this);
		cancel.addActionListener(this);
		
		setContentPane(content);
		setTitle("Sửa thông tin");
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void submit() {
		boolean flag = false;
		ArrayList<Account> list = AccountDAO.getInstance().selectAll();

		if (txtEmail.getText().isEmpty() || txtUserName.getText().isEmpty() || txtFullName.getText().isEmpty()
				) {
			flag = true;
		}
		if (!flag) {

			Account a = new Account();
			a.setUsername(txtUserName.getText());
			a.setEmail(txtEmail.getText());
			a.setRole(role.getSelectedItem() + "");
			a.setPassword(txtPassword.getText());
			a.setFullName(txtFullName.getText());
			int check = AccountDAO.getInstance().update(a);
			if (check == 0) {
				JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thất bại", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else {

				JOptionPane.showMessageDialog(this, "Lưu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				cancel();
				((TaiKhoanView) panel).displayTable();
			}

		} else if (flag) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin", "ERROR", JOptionPane.ERROR_MESSAGE);

		}

	}

	public void cancel() {
		dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.equals(submit)) {
			submit();
		} else {
			cancel();
		}
	}
	

}
