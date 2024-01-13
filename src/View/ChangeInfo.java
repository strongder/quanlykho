package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import Dao.AccountDAO;
import Model.Account;

public class ChangeInfo extends JDialog implements ActionListener {
	private JTextField txtFullName;
	private JTextField txtUserName;
	private JTextField txtEmail;
	private JComboBox<String> role;
	private JButton submit;
	private JButton cancel;
	private static String usernameCurrent;
	private JButton submitMK;
	private JButton cancelMK;
	private JPasswordField txtPassNew;
	private JPasswordField txtConfirm;
	private JPasswordField txtPassOld;

	public ChangeInfo(String usernameCurrent) {
		this.usernameCurrent = usernameCurrent;
		setSize(470, 560);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 65, 470, 428);
		JPanel infoPane = new JPanel();
		infoPane.setLayout(null);
		tabbedPane.addTab("Thông tin", null, infoPane, null);
		Font label = new Font("Tahoma", Font.PLAIN, 13);
		Font textField = new Font("Tahoma", Font.PLAIN, 14);
		Font button = new Font("Tahoma", Font.BOLD, 13);
		JLabel title = new JLabel("SỬA THÔNG TIN TÀI KHOẢN");
		title.setBounds(0, 0, 470, 60);
		title.setFont(new Font("Tahoma", Font.BOLD, 24));
		title.setOpaque(true);
		title.setBackground(Color.CYAN);
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		txtFullName = new JTextField();
		txtFullName.setBounds(101, 145, 252, 32);
		txtFullName.setFont(textField);
		txtEmail = new JTextField();
		txtEmail.setBounds(101, 225, 252, 32);
		txtEmail.setFont(textField);
		txtUserName = new JTextField();
		txtUserName.setBounds(101, 65, 252, 32);
		JLabel lb1 = new JLabel("Họ tên");
		lb1.setFont(label);
		lb1.setBounds(101, 120, 100, 22);
		JLabel lb2 = new JLabel("Email");
		lb2.setBounds(101, 200, 100, 22);
		lb2.setFont(label);
		JLabel lb3 = new JLabel("Tên đăng nhập");
		lb3.setBounds(101, 40, 100, 22);
		lb3.setFont(label);
		submit = new JButton("Xác nhận");
		cancel = new JButton("Hủy");
		cancel.setBounds(66, 300, 93, 32);
		cancel.setFont(button);
		submit.setBounds(293, 300, 93, 32);
		submit.setFont(button);
		infoPane.add(lb2);
		infoPane.add(lb3);
		infoPane.add(lb1);
		infoPane.add(txtEmail);
		infoPane.add(txtFullName);
		infoPane.add(txtUserName);
		infoPane.add(submit);
		infoPane.add(cancel);

		JPanel changePassPane = new JPanel();
		changePassPane.setLayout(null);
		tabbedPane.addTab("Mật khẩu", null, changePassPane, null);

		txtPassOld = new JPasswordField();
		txtPassOld.setBounds(101, 65, 252, 32);
		txtPassNew = new JPasswordField();
		txtPassNew.setBounds(101, 145, 252, 32);
		txtConfirm = new JPasswordField();
		txtConfirm.setBounds(101, 225, 252, 32);
		JLabel lb4 = new JLabel("Mật khẩu cũ");
		lb4.setFont(label);
		lb4.setBounds(101, 40, 100, 22);

		JLabel lb5 = new JLabel("Mật Khẩu mới");
		lb5.setBounds(101, 120, 100, 22);
		lb5.setFont(label);
		JLabel lb6 = new JLabel("Nhập lại mật khẩu");
		lb6.setBounds(101, 200, 120, 22);
		lb6.setFont(label);

		submitMK = new JButton("Xác nhận");
		cancelMK = new JButton("Hủy");
		cancelMK.setBounds(66, 300, 93, 32);
		submitMK.setFont(button);
		submitMK.setBounds(293, 300, 93, 32);
		cancelMK.setFont(button);

		changePassPane.add(lb5);
		changePassPane.add(lb4);
		changePassPane.add(lb6);
		changePassPane.add(txtPassOld);
		changePassPane.add(txtPassNew);
		changePassPane.add(txtConfirm);
		changePassPane.add(submitMK);
		changePassPane.add(cancelMK);
		JPanel panel = new JPanel(null);
		panel.add(tabbedPane);
		panel.add(title);

		displayInfo();
		setContentPane(panel);
		setTitle("Thêm tài khoản");
		setLocationRelativeTo(null);
		setVisible(true);

		submit.addActionListener(this);
		cancel.addActionListener(this);
		submitMK.addActionListener(this);
		cancelMK.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.equals(submit)) {
			submit();
		} else if (button.equals(cancel)) {
			this.dispose();
			;
		} else if (button.equals(submitMK)) {
			submitMK();
		} else {
			this.dispose();
		}

	}

	private void submit() {
		Account account = AccountDAO.getInstance().selectById(usernameCurrent);
		if (txtEmail.getText().isEmpty() || txtFullName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nhập đủ thông tin", "warning", JOptionPane.WARNING_MESSAGE);
		} else {
			account.setEmail(txtEmail.getText());
			account.setFullName(txtFullName.getText());
			int check = AccountDAO.getInstance().update(account);
			if (check != 0) {
				JOptionPane.showMessageDialog(this, "Lưu thông tin thành công!!", "SUCCESS",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Lỗi!! không đổi được thông tin ", "ERROR", JOptionPane.ERROR);
			}
		}
	}

	private void submitMK() {
		Account account = AccountDAO.getInstance().selectById(usernameCurrent);
		if (txtPassNew.getText().isEmpty() || txtPassOld.getText().isEmpty() ||  txtConfirm.getText().isEmpty() ) {
			JOptionPane.showMessageDialog(this, "Nhập đủ thông tin", "warning", JOptionPane.WARNING_MESSAGE);
		} else {
			if(account.getPassword().equals(txtPassOld.getText())){
				if(txtPassNew.getText().equals( txtConfirm.getText())) {
					account.setPassword(txtPassNew.getText());
					int check = AccountDAO.getInstance().update(account);
					if (check != 0) {
						JOptionPane.showMessageDialog(this, "Lưu thông tin thành công!!", "SUCCESS",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(this, "Lỗi!! không đổi được thông tin ", "ERROR", JOptionPane.ERROR);
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "Mật khẩu mới không khớp", "warning", JOptionPane.WARNING_MESSAGE);
				}
				
			}else {
				JOptionPane.showMessageDialog(this, "Mật khẩu cũ không chính xác", "warning", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public void displayInfo() {
		Account account = AccountDAO.getInstance().selectById(usernameCurrent);
		txtFullName.setText(account.getFullName());
		txtEmail.setText(account.getEmail());
		txtUserName.setText(account.getUsername());
		txtUserName.setEditable(false);
	}

}