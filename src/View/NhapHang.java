package View;

import java.awt.Color;
import java.util.StringJoiner;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Controller.PDFController;
import Dao.AccountDAO;
import Dao.ChiTietPhieuNhapDAO;
import Dao.LaptopDAO;
import Dao.NhaCungCapDAO;
import Dao.PhieuNhapDAO;
import Dao.ProductDAO;
import Model.Account;
import Model.ChiTietPhieu;
import Model.NhaCungCap;
import Model.PhieuNhap;
import Model.Product;

public class NhapHang extends JPanel implements ActionListener {

	private JPanel panelAction;
	private JPanel panelSearch;
	private JTable table1;
	private JTable table2;
	private DefaultTableModel tableModel1;
	private DefaultTableModel tableModel2;

	private JTextField txtMaPhieu;
	private JTextField txtNguoiTao;

	private JComboBox<String> comboBoxNCC;
	private JButton btnExcel;
	private JButton btnNhapHang;
	private JButton btnEdit;
	private JButton btnDelete;
	private JTextField txtTotal;
	private String key1;
	private boolean rowSelected1;
	private String key2;
	private boolean rowSelected2;
	// variant left
	private JTextField txtSearch;
	private JButton btnAdd;
	private JButton btnReset;
	private JTextField txtSoLuong;
	private static String usernameCurrent;
	private ArrayList<Product> listProduct = ProductDAO.getInstance().selectAll();
	private ArrayList<Product> listTable2 = new ArrayList<>();

	public NhapHang(String usernameCurrent) {
		this.usernameCurrent = usernameCurrent;
		setBounds(0, 0, 1088, 763);
		setLayout(null);

		initRightPanel();
		initLeftPanel();
		btnExcel.addActionListener(this);
		btnNhapHang.addActionListener(this);
		btnEdit.addActionListener(this);
		btnDelete.addActionListener(this);
		btnAdd.addActionListener(this);
		btnReset.addActionListener(this);

	}

	public void initRightPanel() {
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(510, 0, 579, 763);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.add(panel_3);
		panel_3.setLayout(null);

		txtMaPhieu = new JTextField();
		txtMaPhieu.setBounds(192, 30, 332, 38);
		txtMaPhieu.setText("PN" + (PhieuNhapDAO.getInstance().quantity() + 1) + "");
		txtMaPhieu.setEditable(false);
		panel_3.add(txtMaPhieu);

		JLabel lb2 = new JLabel("Mã phiếu");
		lb2.setBounds(38, 35, 98, 26);
		panel_3.add(lb2);

		JLabel lb3 = new JLabel("Nhà cung cấp");
		lb3.setBounds(38, 90, 98, 26);
		panel_3.add(lb3);

		JLabel lb4 = new JLabel("Người tạo");
		lb4.setBounds(38, 145, 98, 26);
		panel_3.add(lb4);

		ArrayList<NhaCungCap> list = NhaCungCapDAO.getInstance().selectAll();
		ArrayList<String> listName = new ArrayList<>();
		list.forEach(item -> listName.add(item.getTenNhaCungCap()));
		comboBoxNCC = new JComboBox<>(listName.toArray(new String[0]));
		comboBoxNCC.setBounds(192, 85, 332, 38);
		panel_3.add(comboBoxNCC);

		txtNguoiTao = new JTextField();
		txtNguoiTao.setBounds(192, 140, 332, 38);
		txtNguoiTao.setText(usernameCurrent);
		txtNguoiTao.setEditable(false);
		panel_3.add(txtNguoiTao);

		String[] columnNames = { "STT", "Mã máy", "Tên máy", "Số lượng", "Đơng giá" };
		tableModel2 = new DefaultTableModel(columnNames, 0);
		table2 = new JTable(tableModel2);
		int[] columnWidths = { 30, 60, 200, 60, 100};
	    TableColumnModel columnModel = table2.getColumnModel();

	    for (int i = 0; i < columnWidths.length && i < columnModel.getColumnCount(); i++) {
	        TableColumn column = columnModel.getColumn(i);
	        column.setPreferredWidth(columnWidths[i]);
	    }

		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table2.getSelectedRow();
				if (selectedRow != -1) {
					key2 = table2.getValueAt(selectedRow, 1) + "";
					System.out.println(key2);
					rowSelected2 = true;
				}
			}
		});

		JScrollPane scrollPane_1 = new JScrollPane(table2);
		scrollPane_1.setBounds(20, 216, 536, 393);
		panel_3.add(scrollPane_1);

		ImageIcon iconExcel = new ImageIcon(
				"src/Assets/Icon/icons8-excel-30.png");
		btnExcel = new JButton("Xuất exel", iconExcel);
		btnExcel.setBounds(75, 630, 119, 50);
		setButtonTransparent(btnExcel);
		panel_3.add(btnExcel);

		ImageIcon iconEdit = new ImageIcon(
				"src/Assets/Icon/icons8-edit-30.png");
		btnEdit = new JButton("Sửa số lượng", iconEdit);
		btnEdit.setBounds(235, 630, 130, 50);
		setButtonTransparent(btnEdit);
		panel_3.add(btnEdit);
		ImageIcon iconDelete = new ImageIcon(
				"src/Assets/Icon/icons8-remove-30.png");
		btnDelete = new JButton("Xóa", iconDelete);
		btnDelete.setBounds(397, 630, 119, 50);
		setButtonTransparent(btnDelete);
		panel_3.add(btnDelete);

		JLabel lb5 = new JLabel("TỔNG");
		lb5.setBounds(20, 706, 100, 47);
		lb5.setFont(new Font("Tahoma", Font.BOLD, 16));
		;

		lb5.setForeground(Color.RED);
		panel_3.add(lb5);

		txtTotal = new JTextField();
		txtTotal.setBounds(70, 710, 150, 40);
		txtTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_3.add(txtTotal);

		btnNhapHang = new JButton("Nhập hàng");
		btnNhapHang.setBounds(437, 706, 113, 44);
		btnNhapHang.setBackground(Color.cyan);
		panel_3.add(btnNhapHang);
	}

	public void initLeftPanel() {
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 510, 763);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.add(panel_2);
		panel_2.setLayout(null);

		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(null);
		searchPanel.setBorder(new TitledBorder(null, "Tìm kiếm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		searchPanel.setBounds(21, 29, 466, 86);
		panel_2.add(searchPanel);

		txtSearch = new JTextField();
		txtSearch.setBounds(20, 24, 333, 40);
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				filterProduct();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				filterProduct();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});
		searchPanel.add(txtSearch);

		ImageIcon iconReset = new ImageIcon(
				"src/Assets/Icon/icons8-reset-40.png");
		btnReset = new JButton("Làm mới", iconReset);
		btnReset.setBounds(363, 18, 85, 62);
		btnReset.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnReset.setHorizontalTextPosition(SwingConstants.CENTER);
		setButtonTransparent(btnReset);
		btnReset.setBorderPainted(false);
		searchPanel.add(btnReset);

		String[] columnNames = { "STT", "Mã máy", "Tên máy", "Số lượng", "Đơng giá" };
		tableModel1 = new DefaultTableModel(columnNames, 0);
		table1 = new JTable(tableModel1);
		
		int[] columnWidths = { 30, 60, 200, 60, 100};
	    TableColumnModel columnModel = table1.getColumnModel();

	    for (int i = 0; i < columnWidths.length && i < columnModel.getColumnCount(); i++) {
	        TableColumn column = columnModel.getColumn(i);
	        column.setPreferredWidth(columnWidths[i]);
	    }
		
		displayTableSP(tableModel1);
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table1.getSelectedRow();
				if (selectedRow != -1) {
					key1 = table1.getValueAt(selectedRow, 1) + "";
					System.out.println(key1);
					rowSelected1 = true;
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(table1);
		scrollPane.setBounds(21, 169, 466, 484);
		panel_2.add(scrollPane);

		JLabel lb1 = new JLabel("Số lượng:");
		lb1.setBounds(87, 679, 69, 40);
		panel_2.add(lb1);

		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(168, 680, 103, 40);
		panel_2.add(txtSoLuong);
		ImageIcon iconAdd = new ImageIcon(
				"src/Assets/Icon/icons8-add-30.png");
		btnAdd = new JButton("Thêm", iconAdd);
		btnAdd.setBounds(321, 676, 120, 46);
		btnAdd.setBackground(Color.CYAN);
		panel_2.add(btnAdd);
	}

	public void loadDataTable(List<Product> list, DefaultTableModel tableModel) {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		int stt = 1;
		for (Product p : list) {
			String gia = String.format("%.1f", p.getGiaNhap());

			Object[] rowData = { stt, p.getMaMay(), p.getTenMay(), p.getSoLuong(), gia };
			tableModel.addRow(rowData);
			stt++;
		}
	}

	public void displayTableSP(DefaultTableModel tableModel) {
		ArrayList<Product> list = (ArrayList<Product>) ProductDAO.getInstance().selectAll().stream()
				.filter(p -> ! p.isTrangThai()).collect(Collectors.toList());
		loadDataTable(list, tableModel);
	}

	private void setButtonTransparent(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		button.setBorderPainted(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton) e.getSource();
		if (button.equals(btnAdd)) {
			addProduct();
		} else if (button.equals(btnEdit)) {
			editSoLuong();
		} else if (button.equals(btnDelete)) {
			deleteProduct();
		} else if (button.equals(btnNhapHang)) {
			nhapHang();
		} else if (button.equals(btnExcel)) {
			xuatExcel();
		} else if (button.equals(btnReset)) {
			reset();
		}

	}

	private void deleteProduct() {
		if (rowSelected2) {
			for (Product p1 : listTable2) {
				if (key2.equals(p1.getMaMay())) {
					listTable2.remove(p1);
					break;
				}
			}
			setTotal();
		} else {
			JOptionPane.showMessageDialog(this, "Chọn sản phẩm trước", "warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void nhapHang() {
		if (table2.getModel().getRowCount() != 0) {

			PhieuNhap pn = new PhieuNhap();
			pn.setNguoiTao(txtNguoiTao.getText());
			String maNhaCungCap = NhaCungCapDAO.getInstance().selectIdByName(comboBoxNCC.getSelectedItem().toString());
			pn.setMaNhaCungCap(maNhaCungCap);
			pn.setMaPhieu(txtMaPhieu.getText());
			String gia = txtTotal.getText().replace(",", ".");
			pn.setTongTien(Double.parseDouble(gia));
			pn.setThoiGianTao(LocalDateTime.now());
			PhieuNhapDAO.getInstance().insert(pn);
			for (Product p : listTable2) {
				int soLuong = ProductDAO.getInstance().selectById(p.getMaMay()).getSoLuong() + p.getSoLuong();
				ProductDAO.getInstance().updateSoLuong(p.getMaMay(), soLuong);
				ChiTietPhieu ctp = new ChiTietPhieu();
				ctp.setMaPhieu(txtMaPhieu.getText());
				ctp.setMaMay(p.getMaMay());
				ctp.setSoLuong(p.getSoLuong());
				ctp.setDonGia(p.getGiaNhap());
				ChiTietPhieuNhapDAO.getInstance().insert(ctp);
			}
			 int result = JOptionPane.showConfirmDialog(
		                null,
		                "Bạn có muốn in phiếu nhập?",
		                "Xác nhận",
		                JOptionPane.YES_NO_OPTION);

		        // Xử lý lựa chọn của người dùng
		        if (result == JOptionPane.YES_OPTION) {
		           PDFController pdf = new PDFController();
		           pdf.xuatPhieuNhap(txtMaPhieu.getText());
		           JOptionPane.showMessageDialog(this, "Xuất file thành công", "Success", JOptionPane.INFORMATION_MESSAGE);
		        } 
			completeNhapHang();
			displayTableSP(tableModel1);
		} else {
			JOptionPane.showMessageDialog(this, "Không có sản phẩm nào", "warning", JOptionPane.WARNING_MESSAGE);
		}

	}

	private void reset() {
		txtSearch.setText("");
	}

	private void xuatExcel() {
		Controller.ExcelController.exportToExcel(table2);
	}

	private void editSoLuong() {
		int selectedRow = getSelectedRow();
		if (selectedRow != -1) {
			DialogEditQuantity edit = new DialogEditQuantity();
			edit.txtsoLuong.setText(table2.getValueAt(selectedRow, 3) + "");

			edit.submit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					for (Product p : listTable2) {
						if (p.getMaMay().equals(key2)) {
							p.setSoLuong(Integer.parseInt(edit.txtsoLuong.getText()));
						}
					}
					setTotal();
					edit.cancel();
				}
			});

		} else {
			JOptionPane.showMessageDialog(this, "Chọn sản phẩm trước", "warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	private int getSelectedRow() {
		int selectedRow = table2.getSelectedRow();
		return selectedRow;
	}

	private void addProduct() {
		int soLuong = Integer.parseInt(txtSoLuong.getText().isEmpty() ? "1" : txtSoLuong.getText());
		if (rowSelected1) {
			Product p = ProductDAO.getInstance().selectById(key1);
			p.setSoLuong(soLuong);

			boolean found = false;
			for (Product p1 : listTable2) {
				if (p.getMaMay().equals(p1.getMaMay())) {
					found = true;
					p1.setSoLuong(p1.getSoLuong() + soLuong);
					break;
				}
			}
			if (!found) {
				listTable2.add(p);
			}
			txtSoLuong.setText("");
			setTotal();
		} else {
			JOptionPane.showMessageDialog(this, "Chọn sản phẩm trước", "warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	private double calculateTotal() {
		double totalValue = 0;
		for (Product p : listTable2) {
			totalValue += p.getGiaNhap() * p.getSoLuong();
		}
		return totalValue;
	}

	public void completeNhapHang() {
		txtMaPhieu.setText("PN" + (PhieuNhapDAO.getInstance().quantity() + 1) + "");
		txtTotal.setText("");
		tableModel2.setRowCount(0);
		comboBoxNCC.setSelectedIndex(0);
		listTable2 = new ArrayList<>();
	}

	public void setTotal() {
		loadDataTable(listTable2, tableModel2);

		double total = calculateTotal();
		String gia = String.format("%.1f", total);
		txtTotal.setText(gia);

	}

	public void filterProduct() {
		String search = txtSearch.getText().toLowerCase();

		
		ArrayList<Product> filterList = new ArrayList<>();
		for (Product p : listProduct) {
			if (productContainsSearch(p.getTenMay(), search)) {
				filterList.add(p);
			}
		}
		loadDataTable(filterList, tableModel1);

	}

	private boolean productContainsSearch(String fieldValue, String searchText) {
		return fieldValue.toLowerCase().contains(searchText);
	}

}
