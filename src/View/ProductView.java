
package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Controller.ExcelController;
import Dao.LaptopDAO;
import Dao.PCDAO;
import Dao.ProductDAO;
import Model.Laptop;
import Model.PC;
import Model.Product;

public class ProductView extends JPanel implements ActionListener {

	private JPanel panelAction;
	private JPanel panelSearch;
	private JTable table;
	DefaultTableModel tableModel;
	JButton btnAdd;
	JButton btnEdit;
	JButton btnDelete;
	JButton btnDetail;
	JButton btnExcel;
	JButton btnReset;
	JComboBox comboBox;
	private JTextField txtSearch;
	JFrame frame;
	private String key;

	private ArrayList<Product> listProducts = ProductDAO.getInstance().selectAll();

	public ProductView() {

		setSize(1088, 763);
		setLayout(null);

		initTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					key = table.getValueAt(selectedRow, 1) + "";
					System.out.println(key);
					rowSelected = true;
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(33, 147, 1018, 580);
		add(scrollPane);
 
		disPlayTable();
		panelAction = new JPanel();
		panelAction.setBounds(30, 26, 413, 92);
		panelAction.setLayout(null);
		add(panelAction);

		panelAction.setBorder(new TitledBorder(null, "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		ImageIcon iconAdd = new ImageIcon("src/Assets/Icon/icons8-add-40.png");
		btnAdd = new JButton("Thêm", iconAdd);
		btnAdd.setBounds(10, 22, 70, 60);
		btnAdd.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAdd.setHorizontalTextPosition(SwingConstants.CENTER);

		setButtonTransparent(btnAdd);

		panelAction.add(btnAdd);

		ImageIcon iconEdit = new ImageIcon("src/Assets/Icon/icons8-edit-40.png");
		btnEdit = new JButton("Sửa", iconEdit);
		btnEdit.setBounds(85, 22, 75, 60);
		btnEdit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEdit.setHorizontalTextPosition(SwingConstants.CENTER);
		setButtonTransparent(btnEdit);

		panelAction.add(btnEdit);

		ImageIcon iconDelete = new ImageIcon("src/Assets/Icon/icons8-delete-40.png");
		btnDelete = new JButton("Xóa", iconDelete);
		btnDelete.setBounds(170, 22, 75, 60);
		btnDelete.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
		setButtonTransparent(btnDelete);

		panelAction.add(btnDelete);

		ImageIcon iconDetail = new ImageIcon("src/Assets/Icon/icons8-detail-40.png");
		btnDetail = new JButton("Chi tiết", iconDetail);
		btnDetail.setBounds(250, 22, 75, 60);
		btnDetail.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDetail.setHorizontalTextPosition(SwingConstants.CENTER);
		setButtonTransparent(btnDetail);

		panelAction.add(btnDetail);

		ImageIcon iconExcel = new ImageIcon("src/Assets/Icon/icons8-excel-40.png");
		btnExcel = new JButton("Xuất Excel", iconExcel);
		btnExcel.setBounds(325, 22, 95, 60);
		btnExcel.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnExcel.setHorizontalTextPosition(SwingConstants.CENTER);
		setButtonTransparent(btnExcel);

		panelAction.add(btnExcel);

		panelSearch = new JPanel();
		panelSearch.setBounds(500, 26, 547, 92);
		panelSearch.setLayout(null);
		panelSearch.setBorder(new TitledBorder(null, "Tìm kiếm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelSearch);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Còn bán", "Ngưng bán" }));
		comboBox.setBounds(10, 23, 128, 48);
		panelSearch.add(comboBox);

		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filter();
				}
			}
		});

		txtSearch = new JTextField();
		txtSearch.setBounds(147, 23, 284, 48);
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
		panelSearch.add(txtSearch);

		ImageIcon iconReset = new ImageIcon(
				"src/Assets/Icon/icons8-reset-40.png");
		btnReset = new JButton("Lảm mới", iconReset);
		btnReset.setBounds(441, 23, 95, 60);
		btnReset.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnReset.setHorizontalTextPosition(SwingConstants.CENTER);
		setButtonTransparent(btnReset);
		panelSearch.add(btnReset);

		btnAdd.addActionListener(this);
		btnEdit.addActionListener(this);
		btnDelete.addActionListener(this);
		btnDetail.addActionListener(this);
		btnExcel.addActionListener(this);
		btnReset.addActionListener(this);

	}

	private void setButtonTransparent(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
	}

	public void initTable() {
		String[] columnNames = { "STT", "Mã máy", "Tên máy", "Số lượng", "Giá nhập", "Giá xuất", "Bộ xử lý", "Ram",
				"Bộ nhớ", "Loại máy" };
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		
		 int[] columnWidths = { 30, 60, 200, 60, 100, 100, 120, 80, 80, 100 };
		    TableColumnModel columnModel = table.getColumnModel();

		    for (int i = 0; i < columnWidths.length && i < columnModel.getColumnCount(); i++) {
		        TableColumn column = columnModel.getColumn(i);
		        column.setPreferredWidth(columnWidths[i]);
		    }
	}

	public void loadDataTable(ArrayList<Product> list) {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		int stt = 1;
		for (Product p : list) {
			String giaNhap = String.format("%.1f", p.getGiaNhap());
			String giaXuat = String.format("%.1f", p.getGiaXuat());
			String loaiMay;
			if (LaptopDAO.getInstance().isLaptop(p.getMaMay()) == true) {
				loaiMay = "Laptop";
			} else {
				loaiMay = "PC/Case";
			}

			Object[] rowData = { stt, p.getMaMay(), p.getTenMay(), p.getSoLuong(), giaNhap, giaXuat, p.getTenCpu(),
					p.getRam(), p.getRom(), loaiMay };
			tableModel.addRow(rowData);
			stt++;
		}

	}

	public void addProduct() {
		AddProductDialog addProduct = new AddProductDialog(frame);
	}

	public void deleteProduct() {

		if (rowSelected) {
			int confirmResult = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa",
					JOptionPane.OK_CANCEL_OPTION);

			if (confirmResult == JOptionPane.OK_OPTION) {
				// Người dùng đã chọn "OK"
				ProductDAO.getInstance().deleteTrangThai(key);
				JOptionPane.showMessageDialog(this, "XÓA THÀNH CÔNG", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				rowSelected = false;
				disPlayTable();
			}
		} else {
			JOptionPane.showMessageDialog(this, "CHỌN THÔNG TIN CẦN XÓA", "WARNING", JOptionPane.WARNING_MESSAGE);
		}

	}

	public void detailProduct() {
		if (rowSelected) {
			if (LaptopDAO.getInstance().isLaptop(key) == true) {
				Laptop lt = LaptopDAO.getInstance().selectById(key);
				ProductDetail u = new ProductDetail(frame);
				u.txtMaSP.setText(lt.getMaMay());
				u.txtTenSP.setText(lt.getTenMay());
				u.txtCard.setText(lt.getCardManHinh());
				String giaNhap = String.format("%.1f", lt.getGiaNhap());
				String giaXuat = String.format("%.1f", lt.getGiaXuat());
				u.txtGiaNhap.setText(giaNhap);
				u.txtGiaXuat.setText(giaXuat);
				u.txtDungLuong.setText(lt.getRom());
				u.txtKichThuoc.setText(lt.getKichThuoc() + "");
				u.txtPin.setText(lt.getDungLuongPin());
				u.txtRAM.setText(lt.getRam());
				u.txtCPU.setText(lt.getTenCpu());
				u.txtSoLuong.setText(lt.getSoLuong() + "");
				u.txtXuatXu.setText(lt.getXuatXu());

			} else {
				PC pc = PCDAO.getInstance().selectById(key);
				ProductDetail u = new ProductDetail(frame);
				u.txtMaSP.setText(pc.getMaMay());
				u.txtTenSP.setText(pc.getTenMay());
				u.txtCard.setText(pc.getCardManHinh());
				String giaNhap = String.format("%.1f", pc.getGiaNhap());
				String giaXuat = String.format("%.1f", pc.getGiaXuat());
				u.txtGiaNhap.setText(giaNhap);
				u.txtGiaXuat.setText(giaXuat);
				u.txtKichThuoc.setText(pc.getCongSuatNguon() + "");
				u.txtPin.setText(pc.getMainBoard());
				u.txtDungLuong.setText(pc.getRom());
				u.txtCPU.setText(pc.getTenCpu());
				u.txtRAM.setText(pc.getRam());
				u.txtSoLuong.setText(pc.getSoLuong() + "");
				u.txtXuatXu.setText(pc.getXuatXu());
				String sp = "PC";
				u.loaiSP.setSelectedItem(sp);
			}

		} else {
			JOptionPane.showMessageDialog(this, "Chọn sản phẩm", "warning", JOptionPane.WARNING_MESSAGE);
		}

	}

	public void xuatExcel() {
		ExcelController.exportToExcel(table);
	}

	public void reset() {
		txtSearch.setText("");
		disPlayTable();
	}

	private boolean rowSelected = false;

	public void editProduct() {

		if (rowSelected) {
			if (LaptopDAO.getInstance().isLaptop(key) == true) {
				System.out.println("true");
				Laptop lt = LaptopDAO.getInstance().selectById(key);
				System.out.println(lt.getDungLuongPin());
				UpdateProductDialog u = new UpdateProductDialog(frame, this);
				u.txtMaSP.setText(lt.getMaMay());
				u.txtTenSP.setText(lt.getTenMay());
				u.txtCard.setText(lt.getCardManHinh());
				String giaNhap = String.format("%.1f", lt.getGiaNhap());
				String giaXuat = String.format("%.1f", lt.getGiaXuat());
				u.txtGiaNhap.setText(giaNhap);
				u.txtGiaXuat.setText(giaXuat);
				u.txtDungLuong.setText(lt.getRom());
				u.txtKichThuoc.setText(lt.getKichThuoc() + "");
				u.txtPin.setText(lt.getDungLuongPin());
				u.txtRAM.setText(lt.getRam());
				u.txtCPU.setText(lt.getTenCpu());
				u.txtXuatXu.setText(lt.getXuatXu());
			} else {
				System.out.println("false");
				PC pc = PCDAO.getInstance().selectById(key);
				UpdateProductDialog u = new UpdateProductDialog(frame, this);
				u.txtMaSP.setText(pc.getMaMay());
				u.txtTenSP.setText(pc.getTenMay());
				u.txtCard.setText(pc.getCardManHinh());
				String giaNhap = String.format("%.1f", pc.getGiaNhap());
				String giaXuat = String.format("%.1f", pc.getGiaXuat());
				u.txtGiaNhap.setText(giaNhap);
				u.txtGiaXuat.setText(giaXuat);
				u.txtDungLuong.setText(pc.getRom());
				u.txtKichThuoc.setText(pc.getCongSuatNguon() + "");
				u.txtPin.setText(pc.getMainBoard());
				u.txtCPU.setText(pc.getTenCpu());
				u.txtRAM.setText(pc.getRam());
				u.txtXuatXu.setText(pc.getXuatXu());
				String sp = "PC";
				u.loaiSP.setSelectedItem(sp);
			}

		} else {
			JOptionPane.showMessageDialog(this, "Chọn sản phẩm", "warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton) e.getSource();
		if (button.equals(btnAdd)) {
			addProduct();
		} else if (button.equals(btnEdit)) {
			editProduct();
		} else if (button.equals(btnDelete)) {
			deleteProduct();
		} else if (button.equals(btnDetail)) {
			detailProduct();
		} else if (button.equals(btnExcel)) {
			xuatExcel();
		} else if (button.equals(btnReset)) {
			reset();
		}

	}

	public void filter() {

		ArrayList<Product> listNew = null;

		String selectedOption = (String) comboBox.getSelectedItem();

		if ("Còn bán".equals(selectedOption)) {
			listNew = (ArrayList<Product>) ProductDAO.getInstance().selectAll().stream().filter(p -> p.isTrangThai() == false)
					.collect(Collectors.toList());
			System.out.println("heoolo");
		} else if ("Ngưng bán".equals(selectedOption)) {
			listNew = (ArrayList<Product>) ProductDAO.getInstance().selectAll().stream().filter(p -> p.isTrangThai() == true)
					.collect(Collectors.toList());
		}

		// Load dữ liệu mới vào DataTable
		loadDataTable(listNew);
	}

	public void filterProduct() {
		String search = txtSearch.getText().toLowerCase();

		ArrayList<Product> filterList = new ArrayList<>();
		for (Product p : listProducts) {
			if (productContainsSearch(p.getTenMay(), search)) {
				filterList.add(p);
			}
		}
		loadDataTable(filterList);

	}

	private boolean productContainsSearch(String fieldValue, String searchText) {
		return fieldValue.toLowerCase().contains(searchText);
	}

	public void disPlayTable() {

		ArrayList<Product> list = (ArrayList<Product>) ProductDAO.getInstance().selectAll().stream().filter(item -> !item.isTrangThai()).collect(Collectors.toList());
		loadDataTable(list);
	}
}
