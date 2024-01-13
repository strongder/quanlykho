package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.EmailSender;
import Controller.LoginController;
import Dao.AccountDAO;
import Dao.LoginDAO;
import Model.Account;

public class ForgotPassword extends JDialog implements ActionListener {
	private JTextField txtEmail;
	private JButton btnCancel;
	private JButton btnSubmit;
	ArrayList<Account> list = AccountDAO.getInstance().selectAll();

	public ForgotPassword() {
		this.setSize(517, 262);
		this.setLayout(null);
		JLabel title = new JLabel("Quên mật khẩu");
		title.setFont(new Font("Tahoma", Font.PLAIN, 24));
		title.setBounds(0, 0, 503, 53);
		title.setOpaque(true);
		title.setBackground(Color.CYAN);
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		this.add(title);
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(42, 114, 47, 27);
		this.add(lblNewLabel);

		txtEmail = new JTextField();
		txtEmail.setBounds(99, 111, 289, 34);
		this.add(txtEmail);

		JLabel lblNewLabel_1 = new JLabel("Nhập lại địa chỉ email để lây lại mật khẩu");
		lblNewLabel_1.setBounds(42, 78, 250, 13);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Quên mật khẩu");
		lblNewLabel_2.setBounds(0, 0, 525, 45);
		this.add(lblNewLabel_2);

		btnCancel = new JButton("Hủy");

		btnCancel.setBounds(72, 164, 95, 34);
		btnCancel.setBackground(Color.red);
		this.add(btnCancel);

		btnSubmit = new JButton("Xác nhận");
		btnSubmit.setBackground(Color.cyan);
		btnSubmit.setBounds(344, 164, 95, 34);
		this.add(btnSubmit);
		btnSubmit.addActionListener(this);
		btnCancel.addActionListener(this);

		setVisible(true);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnCancel)) {
			LoginController login = new LoginController();
			this.dispose();
		} else if (e.getSource().equals(btnSubmit)) {
			if (txtEmail.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập email", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				boolean flag = false;
				for (Account a : list) {
					if (a.getEmail().equals(txtEmail.getText())) {
						flag = true;
					}
				}
				if (!flag) {
					JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} else {

					EmailSender email = new EmailSender();
					String newPass = email.sendEmail(txtEmail.getText()) + "";
					if (newPass != null) {
						AccountDAO.getInstance().changePassByEmail(newPass, txtEmail.getText());
						JOptionPane.showMessageDialog(this, "Mật khẩu đã được cấp lại", "SUCCESS",
								JOptionPane.INFORMATION_MESSAGE);
						LoginController login = new LoginController();
						this.dispose();
					}
				}
			}

		}
	}

}
