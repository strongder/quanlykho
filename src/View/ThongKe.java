package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import Dao.AccountDAO;
import Dao.ChiTietPhieuNhapDAO;
import Dao.ChiTietPhieuXuatDAO;
import Dao.LaptopDAO;
import Dao.NhaCungCapDAO;
import Dao.PhieuNhapDAO;
import Dao.PhieuXuatDAO;
import Dao.ProductDAO;
import Model.Account;
import Model.Phieu;
import Model.PhieuNhap;
import Model.PhieuXuat;
import Model.Product;

public class ThongKe extends JPanel {
	private JTabbedPane tabbedPane;
	private JPanel panelSanPham;
	private JPanel panelPhieu;
	private JPanel panelTK;
	private JDateChooser dateFrom;
	private JDateChooser dateTo;

	public ArrayList<Product> listProduct = ProductDAO.getInstance().selectAll();
	public ArrayList<PhieuNhap> listPN = PhieuNhapDAO.getInstance().selectAll();
	public ArrayList<PhieuXuat> listPX = PhieuXuatDAO.getInstance().selectAll();
	public ArrayList<Account> listAccount = AccountDAO.getInstance().selectAll();

	public ThongKe(JFrame frame) {
		setSize(1088, 763);
		setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 145, 1088, 618);
		this.add(tabbedPane);

		initHeader();
		initPanelSP();
		initPanelPhieu();
		initPanelTK();

	}

	public void initHeader() {
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(20, 10, 305, 111);
		this.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(
				"C:\\Users\\Admin\\Desktop\\PTPMUD\\QuanLyKho\\src\\Assets\\Icon\\icons8-product-70.png"));
		lblNewLabel_2.setBounds(22, 20, 70, 70);
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Số lượng sản phẩm");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(117, 65, 129, 36);
		panel_2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel();
		lblNewLabel_4.setText(ProductDAO.getInstance().getSl() + "");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(117, 20, 70, 35);
		panel_2.add(lblNewLabel_4);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBounds(386, 10, 305, 111);
		this.add(panel_2_1);

		JLabel lblNewLabel_2_1 = new JLabel("New label");
		lblNewLabel_2_1.setIcon(new ImageIcon(
				"src/Assets/Icon/icons8-provide-70.png"));
		lblNewLabel_2_1.setBounds(22, 20, 70, 70);
		panel_2_1.add(lblNewLabel_2_1);

		JLabel lblNewLabel_3_1 = new JLabel("Số lượng nhà cung cấp");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3_1.setBounds(117, 65, 140, 36);
		panel_2_1.add(lblNewLabel_3_1);

		JLabel lblNewLabel_4_1 = new JLabel(NhaCungCapDAO.getInstance().getSL() + "");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4_1.setBounds(117, 20, 70, 35);
		panel_2_1.add(lblNewLabel_4_1);

		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setLayout(null);
		panel_2_1_1.setBounds(756, 10, 305, 111);
		this.add(panel_2_1_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("New label");
		lblNewLabel_2_1_1.setIcon(
				new ImageIcon("src/Assets/Icon/icons8-user-70.png"));
		lblNewLabel_2_1_1.setBounds(22, 20, 70, 70);
		panel_2_1_1.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_3_1_1 = new JLabel("Số lượng tài khoản");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3_1_1.setBounds(117, 65, 129, 36);
		panel_2_1_1.add(lblNewLabel_3_1_1);

		JLabel lblNewLabel_4_1_1 = new JLabel(AccountDAO.getInstance().getSL() + "");
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4_1_1.setBounds(117, 20, 70, 35);
		panel_2_1_1.add(lblNewLabel_4_1_1);
	}

	public void initPanelSP() {
		panelSanPham = new JPanel();
		tabbedPane.addTab("Sản phẩm", null, panelSanPham, null);
		panelSanPham.setLayout(null);

		String[] columnNames = { "STT", "Mã máy", "Tên máy", "Số lượng nhập", "Số lượng xuất" };
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(tableModel);

		int[] columnWidths = {30, 60, 250, 60, 60};
	    TableColumnModel columnModel = table.getColumnModel();

	    for (int i = 0; i < columnWidths.length && i < columnModel.getColumnCount(); i++) {
	        TableColumn column = columnModel.getColumn(i);
	        column.setPreferredWidth(columnWidths[i]);
	    }
		
		loadDataSP(listProduct, tableModel);
		JScrollPane scrollPaneSP = new JScrollPane(table);
		scrollPaneSP.setBounds(23, 124, 1039, 446);
		panelSanPham.add(scrollPaneSP);

		JPanel panel = new JPanel();
		panel.setBounds(23, 27, 509, 73);
		panelSanPham.add(panel);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JTextField txtSearch = new JTextField();
		txtSearch.setBounds(160, 20, 250, 45);
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				filterProduct(txtSearch, tableModel);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {

				filterProduct(txtSearch, tableModel);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});
		panel.add(txtSearch);

		ImageIcon iconReset = new ImageIcon(
				"src/Assets/Icon/icons8-reset-40.png");
		JButton btnReset = new JButton(iconReset);
		btnReset.setBounds(414, 20, 85, 45);
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtSearch.setText("");
			}
		});
		panel.add(btnReset);
		setButtonTransparent(btnReset);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Tất cả","Còn bán", "Ngưng bán" }));
		comboBox.setBounds(10, 20, 130, 45);
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ArrayList<Product> listNew = null;

					String selectedOption = (String) comboBox.getSelectedItem();

					if("Tất cả".equals(selectedOption)) {
						listNew = listProduct;
					}
					else if ("Còn bán".equals(selectedOption)) {
						listNew = (ArrayList<Product>) listProduct.stream().filter(p -> p.isTrangThai() == false)
								.collect(Collectors.toList());
					} else if ("Ngưng bán".equals(selectedOption)) {
						listNew = (ArrayList<Product>) listProduct.stream().filter(p -> p.isTrangThai() == true)
								.collect(Collectors.toList());
					}
					
					loadDataSP(listNew, tableModel);
				}
			}
		});
		panel.add(comboBox);

	}

	public void initPanelPhieu() {
		panelPhieu = new JPanel();
		tabbedPane.addTab("Phieu", null, panelPhieu, null);
		panelPhieu.setLayout(null);

		String[] columnNames = { "STT", "Mã phiếu", "Người tạo", "Thời gian tạo", "Tổng tiền" };
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(tableModel);
		ArrayList<PhieuNhap> list = PhieuNhapDAO.getInstance().selectAll();
		loadDataPhieuNhap(list, tableModel);

		JScrollPane scrollPaneNCC = new JScrollPane(table);
		scrollPaneNCC.setBounds(23, 124, 1039, 446);
		panelPhieu.add(scrollPaneNCC);

		JPanel panel = new JPanel();
		panel.setBounds(23, 27, 509, 73);
		panelPhieu.add(panel);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		ImageIcon iconReset = new ImageIcon(
				"src/Assets/Icon/icons8-reset-40.png");

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Phiếu nhập", "Phiếu xuất" }));
		comboBox.setBounds(10, 20, 130, 45);
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (comboBox.getSelectedItem().equals("Phiếu nhập")) {
						loadDataPhieuNhap(listPN, tableModel);
					} else {
						loadDataPhieuXuat(listPX, tableModel);
					}
				}
			}
		});
		panel.add(comboBox);
		JTextField txtSearch = new JTextField();
		txtSearch.setBounds(160, 20, 250, 45);
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (comboBox.getSelectedItem().equals("Phiếu nhập")) {
					filterPN(txtSearch, tableModel);
				} else {
					filterPX(txtSearch, tableModel);
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (comboBox.getSelectedItem().equals("Phiếu nhập")) {
					filterPN(txtSearch, tableModel);
				} else {
					filterPX(txtSearch, tableModel);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});
		panel.add(txtSearch);

		JButton btnReset = new JButton(iconReset);
		btnReset.setBounds(414, 20, 85, 45);
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtSearch.setText("");
				if (comboBox.getSelectedItem().equals("Phiếu nhập")) {
					loadDataPhieuNhap(listPN, tableModel);
				} else {
					loadDataPhieuXuat(listPX, tableModel);
				}
			}
		});
		panel.add(btnReset);
		setButtonTransparent(btnReset);

		JPanel filterPanel = new JPanel();
		filterPanel.setLayout(null);
		filterPanel.setBounds(564, 27, 498, 73);
		panelPhieu.add(filterPanel);
		filterPanel
				.setBorder(new TitledBorder(null, "Lọc theo ngày", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel lblNewLabel = new JLabel("Từ");
		lblNewLabel.setBounds(10, 18, 34, 53);
		filterPanel.add(lblNewLabel);
		dateFrom = new JDateChooser();
		dateFrom.setBounds(50, 30, 130, 30);
		dateFrom.setDate(new Date());
		filterPanel.add(dateFrom);

		JLabel lbln = new JLabel("Đến");
		lbln.setBounds(210, 18, 34, 53);
		filterPanel.add(lbln);
		dateTo = new JDateChooser();
		dateTo.setBounds(250, 30, 130, 30);
		dateTo.setDate(new Date());
		filterPanel.add(dateTo);

		ImageIcon iconSearch = new ImageIcon(
				"src/Assets/Icon/icons8-search-30.png");
		JButton btnSearch = new JButton(iconSearch);
		btnSearch.setBounds(420, 30, 50, 30);
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().equals("Phiếu nhập")) {
					filterDayPN(tableModel);
				} else {
					filterPX(txtSearch, tableModel);
					filterDayPX(tableModel);
				}
			}
		});
		setButtonTransparent(btnSearch);
		filterPanel.add(btnSearch);

	}

	public void initPanelTK() {

		panelTK = new JPanel();

		panelTK.setLayout(null);

		String[] columnNames = { "STT", "Họ tên", "Email", "Tên người dùng", "Vai trò" };
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(tableModel);
		loadDataAccount(listAccount, tableModel);
		JScrollPane scrollPaneTK = new JScrollPane(table);
		scrollPaneTK.setBounds(23, 124, 1039, 446);
		panelTK.add(scrollPaneTK);

		JPanel panel = new JPanel();
		panel.setBounds(23, 27, 509, 73);
		panelTK.add(panel);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JTextField txtSearch = new JTextField();
		txtSearch.setBounds(160, 20, 250, 45);
		panel.add(txtSearch);
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				filterAccount(txtSearch, tableModel);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				filterAccount(txtSearch, tableModel);

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}
		});
		ImageIcon iconReset = new ImageIcon(
				"src/Assets/Icon/icons8-reset-40.png");
		JButton btnReset = new JButton(iconReset);
		btnReset.setBounds(414, 20, 85, 45);
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtSearch.setText("");
			}
		});
		panel.add(btnReset);
		setButtonTransparent(btnReset);
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Tất cả", "Hoạt động", "Đã xóa" }));
		comboBox.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		     		   
		        	    ArrayList<Account> listNew = null;

		        	    String selectedOption = (String) comboBox.getSelectedItem();

		        	    if("Tất cả".equals(selectedOption))
		        	    {
		        	    	listNew = listAccount;
		        	    }
		        	    else if ("Hoạt động".equals(selectedOption)) {
		        	        listNew =  (ArrayList<Account>) listAccount.stream().filter(p -> p.isStatus() == false).collect(Collectors.toList());
		        	    } else if ("Đã xóa".equals(selectedOption)) {
		        	        listNew = (ArrayList<Account>) listAccount.stream().filter(p -> p.isStatus() ==true).collect(Collectors.toList());
		        	    }
		        	    loadDataAccount(listNew, tableModel);
		        	}
		    }
		});
		comboBox.setBounds(10, 20, 130, 45);
		panel.add(comboBox);

		tabbedPane.addTab("tài khoản", null, panelTK, null);

	}

	private void setButtonTransparent(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
	}

	public void loadDataSP(ArrayList<Product> list, DefaultTableModel tableModel) {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		int stt = 1;
		for (Product p : list) {
			int soLuongNhap = ChiTietPhieuNhapDAO.getInstance().soLuongNhap(p.getMaMay());
			int soLuongXuat = ChiTietPhieuXuatDAO.getInstance().soLuongXuat(p.getMaMay());
			Object[] rowData = { stt, p.getMaMay(), p.getTenMay(), soLuongNhap, soLuongXuat };
			tableModel.addRow(rowData);
			stt++;
		}
	}

	public void loadDataPhieuNhap(ArrayList<PhieuNhap> list, DefaultTableModel tableModel) {

		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		int stt = 1;
		for (PhieuNhap pn : list) {
			String tongtien = String.format("%.1f", pn.getTongTien());
			Object[] rowData = { stt, pn.getMaPhieu(), pn.getNguoiTao(), pn.getThoiGianTao(), tongtien };
			tableModel.addRow(rowData);
			stt++;
		}

	}

	public void loadDataPhieuXuat(ArrayList<PhieuXuat> list, DefaultTableModel tableModel) {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		int stt = 1;
		for (PhieuXuat pn : list) {
			String tongtien = String.format("%.1f", pn.getTongTien());
			Object[] rowData = { stt, pn.getMaPhieu(), pn.getNguoiTao(), pn.getThoiGianTao(), tongtien };
			tableModel.addRow(rowData);
			stt++;
		}

	}

	public void loadDataAccount(ArrayList<Account> list, DefaultTableModel tableModel) {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		int stt = 1;
		for (Account a : list) {
			Object[] rowData = { stt, a.getUsername(), a.getFullName(), a.getEmail(), a.getRole(), a.isStatus() };
			tableModel.addRow(rowData);
			stt++;
		}

	}

	public void filterProduct(JTextField txtSearch, DefaultTableModel tableModel) {
		String search = txtSearch.getText().toLowerCase();

		ArrayList<Product> filterList = new ArrayList<>();
		for (Product p : listProduct) {
			if (containsSearch(p.getTenMay(), search)) {
				filterList.add(p);
			}
		}
		loadDataSP(filterList, tableModel);

	}
	
	public void filterPN(JTextField txtSearch, DefaultTableModel tableModel) {
		String search = txtSearch.getText().toLowerCase();

		ArrayList<PhieuNhap> filterList = new ArrayList<>();
		for (PhieuNhap p : listPN) {
			if (containsSearch(p.getNguoiTao(), search)) {
				filterList.add(p);
			}
		}
		loadDataPhieuNhap(filterList, tableModel);

	}

	public void filterPX(JTextField txtSearch, DefaultTableModel tableModel) {
		String search = txtSearch.getText().toLowerCase();

		ArrayList<PhieuXuat> filterList = new ArrayList<>();
		for (PhieuXuat p : listPX) {
			if (containsSearch(p.getNguoiTao(), search)) {
				filterList.add(p);
			}
		}
		loadDataPhieuXuat(filterList, tableModel);
	}

	public void filterAccount(JTextField txtSearch, DefaultTableModel tableModel) {
		String search = txtSearch.getText().toLowerCase();

		ArrayList<Account> filterList = new ArrayList<>();
		for (Account a : listAccount) {
			if (containsSearch(a.getFullName(), search) || containsSearch(a.getUsername(), search)) {
				filterList.add(a);
			}
		}
		loadDataAccount(filterList, tableModel);

	}

	private boolean containsSearch(String fieldValue, String searchText) {
		return fieldValue.toLowerCase().contains(searchText);
	}

	private static LocalDateTime convertToDateToLocalDateTime(Date date) {
		Instant instant = date.toInstant();
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	}

	public void filterDayPX(DefaultTableModel tableModel) {

		LocalDateTime start = convertToDateToLocalDateTime(dateFrom.getDate());
		LocalDateTime end = convertToDateToLocalDateTime(dateTo.getDate());
		ArrayList<PhieuXuat> newList = new ArrayList<>();
		for (PhieuXuat px : listPX) {
			if (px.getThoiGianTao() != null) {
				if (px.getThoiGianTao().isBefore(end) && px.getThoiGianTao().isAfter(start)) {
					newList.add(px);
				}
			}
		}

		loadDataPhieuXuat(newList, tableModel);

	}

	public void filterDayPN(DefaultTableModel tableModel) {

		LocalDateTime start = convertToDateToLocalDateTime(dateFrom.getDate());
		LocalDateTime end = convertToDateToLocalDateTime(dateTo.getDate());
		ArrayList<PhieuNhap> newList = new ArrayList<>();
		for (PhieuNhap pn : listPN) {
			if (pn.getThoiGianTao() != null) {
				if (pn.getThoiGianTao().isBefore(end) && pn.getThoiGianTao().isAfter(start)) {
					newList.add(pn);
				}
			}
		}
		loadDataPhieuNhap(newList, tableModel);

	}
}
