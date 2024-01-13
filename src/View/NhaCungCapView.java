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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Dao.LaptopDAO;
import Dao.NhaCungCapDAO;
import Dao.ProductDAO;
import Model.NhaCungCap;
import Model.Product;

public class NhaCungCapView extends JPanel implements ActionListener {

	private JPanel panelAction;
	private JPanel panelSearch;
	private JTable table;
	private DefaultTableModel tableModel;
	private String key;
	private boolean rowSelected;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnExcel;
	private JTextField txtSearch;
	private JButton btnReset;
	private JFrame frame;
	private JComboBox<String> comboBox;
	
	public ArrayList<NhaCungCap> listNCC = NhaCungCapDAO.getInstance().selectAll();
	public NhaCungCapView(JFrame frame) {
		this.frame = frame;
		setSize(1088, 763);
		setLayout(null);

		initTable();

		displayTable();
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

		panelAction = new JPanel();
		panelAction.setBounds(30, 26, 365, 92);
		panelAction.setLayout(null);
		add(panelAction);

		panelAction.setBorder(
				new TitledBorder(null, "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		ImageIcon iconAdd = new ImageIcon(
				"src/Assets/Icon/icons8-add-40.png");
		btnAdd = new JButton("Thêm", iconAdd);
		btnAdd.setBounds(10, 22, 70, 60);
		btnAdd.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAdd.setHorizontalTextPosition(SwingConstants.CENTER);

		setButtonTransparent(btnAdd);

		panelAction.add(btnAdd);

		ImageIcon iconEdit = new ImageIcon(
				"src/Assets/Icon/icons8-edit-40.png");
		btnEdit = new JButton("Sửa", iconEdit);
		btnEdit.setBounds(85, 22, 75, 60);
		btnEdit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEdit.setHorizontalTextPosition(SwingConstants.CENTER);
		setButtonTransparent(btnEdit);

		panelAction.add(btnEdit);

		ImageIcon iconDelete = new ImageIcon(
				"src/Assets/Icon/icons8-delete-40.png");
		btnDelete = new JButton("Xóa", iconDelete);
		btnDelete.setBounds(170, 22, 75, 60);
		btnDelete.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
		setButtonTransparent(btnDelete);

		panelAction.add(btnDelete);

		ImageIcon iconExcel = new ImageIcon(
				"src/Assets/Icon/icons8-excel-40.png");
		btnExcel = new JButton("Xuất Excel", iconExcel);
		btnExcel.setBounds(250, 22, 95, 60);
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
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Liên hệ", "Ngừng liên hệ" }));
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
				filterNCC();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				filterNCC();
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
		btnDelete.addActionListener(this);
		btnEdit.addActionListener(this);
		btnExcel.addActionListener(this);
		btnReset.addActionListener(this);

	}

	public void loadDataTable(ArrayList<NhaCungCap> list) {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		int stt = 1;
		for (NhaCungCap n : list) {
			Object[] rowData = { stt, n.getMaNCC(), n.getTenNhaCungCap(), n.getSdt(), n.getDiaChi() };
			tableModel.addRow(rowData);
			stt++;
		}

	}

	public void initTable() {
		String[] columnNames = { "STT", "Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Địa chỉ"};
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		
		int[] columnWidths = {30, 50, 250, 60, 250};
	    TableColumnModel columnModel = table.getColumnModel();

	    for (int i = 0; i < columnWidths.length && i < columnModel.getColumnCount(); i++) {
	        TableColumn column = columnModel.getColumn(i);
	        column.setPreferredWidth(columnWidths[i]);
	    }
	}

	private void setButtonTransparent(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
	}

	public void displayTable() {

		ArrayList<NhaCungCap> list = (ArrayList<NhaCungCap>) NhaCungCapDAO.getInstance().selectAll().stream().filter(item -> !item.isTrangThai()).collect(Collectors.toList());
		loadDataTable(list);
	}

	public void addNCC() {
		AddNhaCungCap addNCC = new AddNhaCungCap(frame, this);
	}

	public void editNCC() {

		if (rowSelected) {

			NhaCungCap ncc = NhaCungCapDAO.getInstance().selectById(key);
			UpdateNhaCungCap uNCC = new UpdateNhaCungCap(frame, this);
			uNCC.txtMaNCC.setText(ncc.getMaNCC());
			uNCC.txtTenNCC.setText(ncc.getTenNhaCungCap());
			uNCC.txtSDT.setText(ncc.getSdt());
			uNCC.txtDiaChi.setText(ncc.getDiaChi());

		} else {
			JOptionPane.showMessageDialog(this, "CHỌN THÔNG TIN SỬA ĐỔI", "WARNING", JOptionPane.WARNING_MESSAGE);
		}

	}

	public void deleteNCC() {
		if (rowSelected) {
			int confirmResult = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa",
					JOptionPane.OK_CANCEL_OPTION);

			if (confirmResult == JOptionPane.OK_OPTION) {
				// Người dùng đã chọn "OK"
				NhaCungCapDAO.getInstance().deleteTrangThai(key);
				JOptionPane.showMessageDialog(this, "XÓA THÀNH CÔNG", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				displayTable();
			}
		} else {
			JOptionPane.showMessageDialog(this, "CHỌN THÔNG TIN CẦN XÓA", "WARNING", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void xuatExcel() {
		Controller.ExcelController.exportToExcel(table);
	}

	public void reset() {
		txtSearch.setText("");
	}
	public void filterNCC()
	{
		String search = txtSearch.getText().toLowerCase();
		ArrayList<NhaCungCap> filterList = new ArrayList<>();
		for (NhaCungCap p : listNCC)
		{
			if(containsSearch(p.getMaNCC(), search))
			{
				filterList.add(p);
			}
		}
		loadDataTable(filterList);
		
	}
	private boolean containsSearch(String fieldValue, String searchText) {
        return fieldValue.toLowerCase().contains(searchText);
    }

	public void filter() {
		   
	    ArrayList<NhaCungCap> listNew = null;

	    String selectedOption = (String) comboBox.getSelectedItem();

	     if ("Liên hệ".equals(selectedOption)) {
	        listNew =  (ArrayList<NhaCungCap>) NhaCungCapDAO.getInstance().selectAll().stream().filter(p -> p.isTrangThai()==false).collect(Collectors.toList());
	    } else if ("Ngừng liên hệ".equals(selectedOption)) {
	        listNew = (ArrayList<NhaCungCap>)  NhaCungCapDAO.getInstance().selectAll().stream().filter(p -> p.isTrangThai()==true).collect(Collectors.toList());
	    }
	    loadDataTable(listNew);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton) e.getSource();
		if (button.equals(btnAdd)) {
			addNCC();
		} else if (button.equals(btnEdit)) {
			editNCC();
		} else if (button.equals(btnDelete)) {
			deleteNCC();
		} else if (button.equals(btnExcel)) {
			xuatExcel();
		} else if (button.equals(btnReset)) {
			reset();
		}

	}
}
