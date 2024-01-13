package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Dao.LoginDAO;
import View.ForgotPassword;
import View.Login;
import View.MainFrame;

public class LoginController {

	public String userNameCurrent;
	public Login loginView;

	public LoginController() {
		loginView = new Login();

		loginView.getBtnLogin().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = loginView.getTxtUsername().getText();
				String password = loginView.getTxtPassword().getText();

				if (userName.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(loginView, "Hãy điền đủ thông tin", "WARNing",
							JOptionPane.WARNING_MESSAGE);
				} else {

					if (LoginDAO.getInstance().authen(userName, password)) {
						JOptionPane.showMessageDialog(loginView, "Đăng nhập thành công!!!", "SUCCESS",
								JOptionPane.INFORMATION_MESSAGE);
						userNameCurrent = userName;
						MainFrame t = new MainFrame(userNameCurrent);
						loginView.dispose();
					} else {
						JOptionPane.showMessageDialog(loginView, "Tài khoản hoặc mật khẩu không chính xác!!!",
								"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		loginView.getBtnForgotPassword().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ForgotPassword forgotPassword  = new ForgotPassword();
				loginView.dispose();
			}
		});

	}

}
