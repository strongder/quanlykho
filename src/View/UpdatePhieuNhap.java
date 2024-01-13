package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import javax.swing.table.DefaultTableModel;

import Dao.ChiTietPhieuNhapDAO;
import Dao.NhaCungCapDAO;
import Dao.PhieuNhapDAO;
import Dao.ProductDAO;
import Model.ChiTietPhieu;
import Model.PhieuNhap;
import Model.Product;

public class UpdatePhieuNhap extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	JTextField txtSoLuong;
	JTextField txtSearch;
	JTextField txtMaPhieu;
	JTextField txtNguoiTao;
	JTextField txtNCC;
	JTextField txtTotal;
	private JPanel mainPanel;
	private JTable table1;
	private JTable table2;
	private JPanel panel;
	private DefaultTableModel tableModel1;
	private DefaultTableModel tableModel2;
	private String key1, key2;
	private boolean rowSelected1, rowSelected2;
	private JComboBox<String> comboBoxNCC;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnAdd;
	private JButton btnReset;
	private JButton btnSubmit;
	private ArrayList<Product> listTable2 = new ArrayList<>();
	 private ArrayList<Product> listCopy = new ArrayList<>();
	private String maPhieu;

	public UpdatePhieuNhap(JPanel panel, String maPhieu) {
		this.maPhieu = maPhieu;
		this.panel = panel;
		setSize(941, 662);
		getContentPane().setLayout(null);
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 927, 625);
		add(mainPanel);
		mainPanel.setLayout(null);

		initLeftPanel();
		initRightPanel();

		btnSubmit.addActionListener(this);
		btnEdit.addActionListener(this);
		btnDelete.addActionListener(this);
		btnAdd.addActionListener(this);
		btnReset.addActionListener(this);
		setVisible(true);
		setLocationRelativeTo(null);

	}

	public void initLeftPanel() {
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(0, 0, 448, 625);
		mainPanel.add(leftPanel);
		leftPanel.setLayout(null);

		String[] columnNames = { "STT", "Mã máy", "Tên máy", "Số lượng", "Đơng giá" };
		tableModel1 = new DefaultTableModel(columnNames, 0);
		table1 = new JTable(tableModel1);
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
		scrollPane.setBounds(10, 117, 427, 410);
		leftPanel.add(scrollPane);

		JLabel lblNewLabel = new JLabel("Số lượng");
		lblNewLabel.setBounds(38, 555, 84, 35);
		leftPanel.add(lblNewLabel);

		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(138, 555, 96, 35);
		leftPanel.add(txtSoLuong);

		ImageIcon iconAdd = new ImageIcon(
				"C:\\\\Users\\\\Admin\\\\Desktop\\\\PTPMUD\\\\QuanLyKho\\\\src\\\\Assets\\\\Icon\\\\icons8-add-30.png");
		btnAdd = new JButton("Thêm", iconAdd);
		btnAdd.setBounds(286, 555, 114, 35);
		leftPanel.add(btnAdd);
		btnAdd.setBackground(Color.CYAN);

		JPanel panelSearch = new JPanel();
		panelSearch.setBorder(new TitledBorder(null, "Tim kiem", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSearch.setBounds(10, 10, 428, 85);
		leftPanel.add(panelSearch);
		panelSearch.setLayout(null);

		ImageIcon iconReset = new ImageIcon(
				"C:\\\\Users\\\\Admin\\\\Desktop\\\\PTPMUD\\\\QuanLyKho\\\\src\\\\Assets\\\\Icon\\\\icons8-reset-40.png");
		btnReset = new JButton("Làm mới", iconReset);
		btnReset.setBounds(321, 16, 85, 63);
		btnReset.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnReset.setHorizontalTextPosition(SwingConstants.CENTER);
		setButtonTransparent(btnReset);
		btnReset.setBorderPainted(false);
		panelSearch.add(btnReset);

		txtSearch = new JTextField();
		txtSearch.setBounds(23, 20, 288, 44);
		panelSearch.add(txtSearch);

	}

	public void initRightPanel() {
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(448, 0, 479, 625);
		mainPanel.add(panel_2);
		panel_2.setLayout(null);

		txtMaPhieu = new JTextField();
		txtMaPhieu.setBounds(151, 22, 270, 30);
		panel_2.add(txtMaPhieu);

		txtNguoiTao = new JTextField();
		txtNguoiTao.setBounds(151, 62, 270, 30);
		panel_2.add(txtNguoiTao);

		txtNCC = new JTextField();
		txtNCC.setBounds(151, 102, 270, 30);
		panel_2.add(txtNCC);

		JLabel lblNewLabel_1 = new JLabel("Mã phiếu nhập");
		lblNewLabel_1.setBounds(24, 30, 80, 13);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Người tạo");
		lblNewLabel_1_1.setBounds(24, 70, 80, 13);
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Nhà cung cấp");
		lblNewLabel_1_2.setBounds(24, 110, 80, 13);
		panel_2.add(lblNewLabel_1_2);

		String[] columnNames = { "STT", "Mã máy", "Tên máy", "Số lượng", "Đơng giá" };
		tableModel2 = new DefaultTableModel(columnNames, 0);
		table2 = new JTable(tableModel2);
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
		scrollPane_1.setBounds(10, 142, 459, 336);
		panel_2.add(scrollPane_1);

		ImageIcon iconEdit = new ImageIcon(
				"C:\\\\Users\\\\Admin\\\\Desktop\\\\PTPMUD\\\\QuanLyKho\\\\src\\\\Assets\\\\Icon\\\\icons8-edit-30.png");
		btnEdit = new JButton("Sửa số lượng ", iconEdit);
		btnEdit.setBounds(71, 498, 120, 35);
		setButtonTransparent(btnEdit);
		panel_2.add(btnEdit);

		ImageIcon iconDelete = new ImageIcon(
				"C:\\\\Users\\\\Admin\\\\Desktop\\\\PTPMUD\\\\QuanLyKho\\\\src\\\\Assets\\\\Icon\\\\icons8-remove-30.png");
		btnDelete = new JButton("Xóa sản phẩm ", iconDelete);
		btnDelete.setBounds(293, 498, 120, 35);
		setButtonTransparent(btnDelete);
		panel_2.add(btnDelete);

		JLabel lblNewLabel_2 = new JLabel("TỔNG");
		lblNewLabel_2.setBounds(24, 556, 56, 48);
		panel_2.add(lblNewLabel_2);

		txtTotal = new JTextField();
		txtTotal.setBounds(90, 556, 96, 48);
		panel_2.add(txtTotal);

		btnSubmit = new JButton("Lưu thay đổi");
		btnSubmit.setBounds(358, 560, 104, 41);
		btnSubmit.setBackground(Color.CYAN);
		panel_2.add(btnSubmit);
		displayTable2(tableModel2);
	}

	private void setButtonTransparent(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		button.setBorderPainted(true);
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
				.filter(p -> p.isTrangThai()).collect(Collectors.toList());
		loadDataTable(list, tableModel);
	}

	public void displayTable2(DefaultTableModel tableModel) {
		ArrayList<ChiTietPhieu> listCTP = ChiTietPhieuNhapDAO.getInstance().selectAllById(maPhieu);
		for (ChiTietPhieu ctp : listCTP) {
			Product p = ProductDAO.getInstance().selectById(ctp.getMaMay());
			p.setSoLuong(ctp.getSoLuong());
			listTable2.add(p);
		}
		loadDataTable(listTable2, tableModel);
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
		} else if (button.equals(btnSubmit)) {
			submit();
		} else if (button.equals(btnReset)) {
			reset();
		}

	}
	private void addProduct() {
		int soLuong  = Integer.parseInt(txtSoLuong.getText().isEmpty()?"1": txtSoLuong.getText());
		if(rowSelected1)
		{
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
			
		}
		 else {
				JOptionPane.showMessageDialog(this, "Chọn sản phẩm trước", "warning", JOptionPane.WARNING_MESSAGE);
			}
	}
	private int getSelectedRow() {
		int selectedRow = table2.getSelectedRow();
		return selectedRow;
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

	private void submit() {
		if (table2.getModel().getRowCount() != 0) {	
			PhieuNhap pn = PhieuNhapDAO.getInstance().selectById(maPhieu);
			pn.setNguoiTao(txtNguoiTao.getText());
			pn.setMaNhaCungCap(txtNCC.getText());
			String gia = txtTotal.getText().replace(",", ".");
			pn.setTongTien(Double.parseDouble(gia));
			PhieuNhapDAO.getInstance().update(pn);
			ArrayList<ChiTietPhieu> listCTP = ChiTietPhieuNhapDAO.getInstance().selectAllById(maPhieu);
			for (Product p : listTable2) {
				int soLuong = ProductDAO.getInstance().selectById(p.getMaMay()).getSoLuong() + p.getSoLuong();
				ProductDAO.getInstance().updateSoLuong(p.getMaMay(), soLuong);
				boolean found = false;
				for (ChiTietPhieu c : listCTP)
				{
					if (p.getMaMay().equals(c.getMaMay()))
					{
						found = true;
					}
				}
				ChiTietPhieu ctp = new ChiTietPhieu();
				ctp.setMaPhieu(txtMaPhieu.getText());
				ctp.setMaMay(p.getMaMay());
				ctp.setSoLuong(p.getSoLuong());
				ctp.setDonGia(p.getGiaNhap());
				if(found)
				{					
					ChiTietPhieuNhapDAO.getInstance().update(ctp);
				}else
				{
					ChiTietPhieuNhapDAO.getInstance().insert(ctp);
				}
			}
			this.dispose();
			JOptionPane.showMessageDialog(this, "Lưu thành công", "Success", JOptionPane.INFORMATION_MESSAGE);
			((PhieuNhapView) panel).displayTable();
			displayTableSP(tableModel1);
		} else {
			JOptionPane.showMessageDialog(this, "Không có sản phẩm nào", "warning", JOptionPane.WARNING_MESSAGE);
		}
		
	}

	private double calculateTotal() {
		double totalValue = 0;
		for (Product p : listTable2) {
			totalValue += p.getGiaNhap() * p.getSoLuong();
		}
		return totalValue;
	}
	public void setTotal() {
		loadDataTable(listTable2, tableModel2);

		double total = calculateTotal();
		String gia = String.format("%.1f", total);
		txtTotal.setText(gia);

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
	private void reset() {
		txtSearch.setText("");
	}
}
