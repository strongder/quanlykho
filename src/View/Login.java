package View;

import javax.swing.*;

import lombok.Data;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Data
public class Login extends JFrame {

	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private JButton btnForgotPassword;

	public Login() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Đăng nhập");
		setSize(375, 500);
		setLayout(null);

		JLabel title = new JLabel("Đăng nhập");
		title.setBounds(0, 0, 361, 65);
		title.setFont(new Font("Tahoma", Font.BOLD, 24));
		title.setOpaque(true);
		title.setBackground(Color.CYAN);
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		JLabel lblUsername = new JLabel("Tên đăng nhập");
		lblUsername.setBounds(32, 123, 130, 23);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtUsername = new JTextField();
		txtUsername.setBounds(32, 156, 290, 39);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lblPassword = new JLabel("Mật khẩu");
		lblPassword.setBounds(32, 216, 73, 23);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtPassword = new JPasswordField();
		txtPassword.setBounds(32, 246, 290, 39);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin = new JButton("Đăng nhập");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setBounds(32, 306, 290, 39);
		btnLogin.setBackground(Color.CYAN);
		btnForgotPassword = new JButton("Quên mật khẩu?");
		btnForgotPassword.setBounds(32, 358, 290, 39);
		setButtonTransparent(btnForgotPassword);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(title);
		panel.add(lblUsername);
		panel.add(txtUsername);
		panel.add(lblPassword);
		panel.add(txtPassword);
		panel.add(btnLogin);
		panel.add(btnForgotPassword);

		setContentPane(panel);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void setButtonTransparent(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusable(false);
	}
}
