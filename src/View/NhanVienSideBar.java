package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import Controller.LoginController;

public class NhanVienSideBar extends SideBar implements ActionListener{
	private JButton btnSP;
	private JButton btnNH;
	private JButton btnXH;
	private JButton btnPX;
	private JButton btnPN;
	private JButton btnNCC;
	private JButton btnTK;
	private JButton btnThongKe;
	private JButton btnThongTin;
	private JButton btnDangXuat;
	
	private JPanel mainContent;
	private JFrame frame;
	private String usernameCurrent;
	
	private JButton lastSelectedButton;
	public NhanVienSideBar (JPanel mainConPanel, JFrame frame,String username) {
		
		super();
		this.usernameCurrent = username;
		this.mainContent = mainConPanel;
		this.frame = frame;
		Font customFont = new Font("Tahoma", Font.BOLD, 16);
		Color white = Color.WHITE;
		
		JLabel lbHelo = new JLabel("Xin chào !!!");
		lbHelo.setBounds(100, 30, 124, 44);
		lbHelo.setFont(customFont);
		lbHelo.setForeground(white);
        add(lbHelo);
        
        JLabel lbUserName = new JLabel(usernameCurrent);
        lbUserName.setBounds(100, 65, 238, 55);
        lbUserName.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbUserName.setForeground(white);
        add(lbUserName);			
		ImageIcon iconNH= new  ImageIcon("C:\\Users\\Admin\\Desktop\\PTPMUD\\QuanLyKho\\src\\Assets\\Icon\\icons8-export-40.png");
		 btnNH = new JButton("NHẬP HÀNG", iconNH);
		btnNH.setBounds(0, 135, 300, 55);
		btnNH.setFont(customFont);
		btnNH.setForeground(white);
		add(btnNH);
		setButtonTransparent(btnNH);
		
		ImageIcon iconXH= new  ImageIcon("C:\\Users\\Admin\\Desktop\\PTPMUD\\QuanLyKho\\src\\Assets\\Icon\\icons8-import-40.png");
	    btnXH = new JButton("XUẤT HÀNG", iconXH);
		btnXH.setBounds(0, 187, 300, 55);
		btnXH.setFont(customFont);
		btnXH.setForeground(white);
		add(btnXH);
		setButtonTransparent(btnXH);
		
		ImageIcon iconPN= new  ImageIcon("C:\\Users\\Admin\\Desktop\\PTPMUD\\QuanLyKho\\src\\Assets\\Icon\\icons8-PhieuNhap-40.png");
	    btnPN = new JButton("PHIẾU NHẬP", iconPN);
		btnPN.setBounds(0, 241, 300, 55);
		btnPN.setFont(customFont);
		btnPN.setForeground(white);
		add(btnPN);
		setButtonTransparent(btnPN);
		
		
		ImageIcon iconPX= new  ImageIcon("C:\\Users\\Admin\\Desktop\\PTPMUD\\QuanLyKho\\src\\Assets\\Icon\\icons8-receipt-40.png");
	    btnPX = new JButton("PHIẾU XUẤT", iconPX);
		btnPX.setBounds(0, 294, 300, 55);
		btnPX.setFont(customFont);
		btnPX.setForeground(white);
		add(btnPX);
		setButtonTransparent(btnPX);
		
		ImageIcon iconInfo= new  ImageIcon("C:\\Users\\Admin\\Desktop\\PTPMUD\\QuanLyKho\\src\\Assets\\Icon\\icons8-edit-40.png");
	    btnThongTin= new JButton("ĐỔI THÔNG TIN", iconInfo);
		btnThongTin.setBounds(0, 636, 300, 55);
		btnThongTin.setFont(customFont);
		btnThongTin.setForeground(white);
		add(btnThongTin);
		setButtonTransparent(btnThongTin);
		
		
		ImageIcon iconLogout= new  ImageIcon("C:\\Users\\Admin\\Desktop\\PTPMUD\\QuanLyKho\\src\\Assets\\Icon\\icons8-logout-40.png");
	    btnDangXuat = new JButton("ĐĂNG XUẤT", iconLogout);
		btnDangXuat.setBounds(0, 690, 300, 55);
		btnDangXuat.setFont(customFont);
		btnDangXuat.setForeground(white);
		add(btnDangXuat);
		setButtonTransparent(btnDangXuat);
		
        btnNH.addActionListener(this);
        btnXH.addActionListener(this);
        btnPX.addActionListener(this);
        btnPN.addActionListener(this);
        btnThongTin.addActionListener(this);
        btnDangXuat.addActionListener(this);
        
	}
	
	private void setButtonTransparent(JButton button) {
//        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setMargin(new Insets(2, 50, 2, 14));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorderPainted(false);
        button.setFocusable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand())
		{
		
		case "XUẤT HÀNG":
			switchContent(new XuatHang(usernameCurrent), btnXH);
			break;			
		case "NHẬP HÀNG":
			switchContent(new NhapHang(usernameCurrent), btnNH);
			break;	
		case "PHIẾU XUẤT":
			switchContent(new PhieuXuatView(), btnPX);
			break;
		case "PHIẾU NHẬP":
			switchContent(new PhieuNhapView(), btnPN);
			break;
		case "ĐỔI THÔNG TIN":
			changeInfo();
			break;
		case "ĐĂNG XUẤT":
			logout();
			break;	
		}
		
		
	}
	public void changeInfo()
	{
		ChangeInfo changeInfo = new ChangeInfo(usernameCurrent);
	}
	
	public void logout() {
		frame.setVisible(false);
		LoginController login = new LoginController();
	}
	
	private void switchContent(Component newContent, JButton button) {
        mainContent.removeAll();
        mainContent.add(newContent);
        mainContent.revalidate();
        mainContent.repaint();
        button.setContentAreaFilled(true);
        button.setBackground(Color.BLACK);
        if (lastSelectedButton != null && lastSelectedButton != button) {
        	lastSelectedButton.setContentAreaFilled(false);
            lastSelectedButton.setBackground(UIManager.getColor("Button.background"));
        }
        lastSelectedButton = button;  
    }

}
