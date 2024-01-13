package View;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Dao.AccountDAO;
import Model.Account;

public class MainFrame {
	private JPanel sideBarPanel;
	private JPanel mainContent;
	JFrame frame;

	private static String usernameCurrent;

	public MainFrame(String usernameCurrent) {
		this.usernameCurrent = usernameCurrent;
		frame = new JFrame();
		frame.setSize(1400, 800);
		frame.setLayout(null);

		mainContent = new JPanel();
		mainContent.setBounds(300, 0, 1088, 763);
		mainContent.setLayout(null);
		ProductView p = new ProductView();
		NhapHang nh = new NhapHang(usernameCurrent);
		SideBar sideBar = null;
		if (role().equals("ADMIN")) {
			 sideBar = new AdminSideBar(mainContent, frame, usernameCurrent);
			 mainContent.add(p);
		} else if (role().equals("QUANLY")) {
 			sideBar = new QuanLySideBar(mainContent, frame, usernameCurrent);
			mainContent.add(p);
		} else {
			sideBar = new NhanVienSideBar(mainContent, frame, usernameCurrent);
			mainContent.add(nh);
		}
		sideBarPanel = sideBar;
		frame.add(sideBarPanel);
		frame.getContentPane().add(mainContent);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public String role() {
		Account acc = AccountDAO.getInstance().selectById(usernameCurrent);
		return acc.getRole();
	}
}
