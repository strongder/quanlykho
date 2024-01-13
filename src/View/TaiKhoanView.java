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

import Dao.AccountDAO;
import Dao.NhaCungCapDAO;
import Model.Account;
import Model.NhaCungCap;

public class TaiKhoanView extends JPanel implements ActionListener{

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
	private JComboBox<String> comboBox;	
	private JFrame frame;
	private ArrayList<Account> list = AccountDAO.getInstance().selectAll();
	public TaiKhoanView(JFrame frame) {

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
		
		
		panelAction.setBorder(new TitledBorder(null, "Ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
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
		
		ImageIcon iconExcel = new ImageIcon("src/Assets/Icon/icons8-excel-40.png");
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
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Hoạt động", "Đã xóa"}));
		comboBox.setBounds(10, 23, 128, 48);
		comboBox.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            filter();
		        }
		    }
		});
		panelSearch.add(comboBox);
		
		 txtSearch = new JTextField();
		txtSearch.setBounds(147, 23, 284, 48);
		panelSearch.add(txtSearch);
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				filterAccount();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				
				filterAccount();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				
				
			}
		});
		
		ImageIcon iconReset = new ImageIcon("src/Assets/Icon/icons8-reset-40.png");
		 btnReset = new JButton("Lảm mới", iconReset);
		btnReset.setBounds(441, 23, 95, 60);
		btnReset.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnReset
		.setHorizontalTextPosition(SwingConstants.CENTER);
		setButtonTransparent(btnReset);
		panelSearch.add(btnReset);
		
		btnAdd.addActionListener(this);
		btnDelete.addActionListener(this);
		btnEdit.addActionListener(this);
		btnExcel.addActionListener(this);
		btnReset.addActionListener(this);
		

	}
	private void setButtonTransparent(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }
	
	public void loadDataTable(ArrayList<Account> list) {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		int stt = 1;
		for (Account a : list) {
			Object[] rowData = { stt, a.getUsername(), a.getFullName(), a.getEmail() , a.getRole() };
			tableModel.addRow(rowData);
			stt++;
		}

	}

	public void initTable() {
		String[] columnNames = {"STT","Tên Tài khoản","Họ tên", "Email", "Vai trò"};
		tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel); 
	}

	

	public void displayTable() {
		ArrayList<Account> list = (ArrayList<Account>) AccountDAO.getInstance().selectAll().stream().filter(item -> !item.isStatus()).collect(Collectors.toList());
		loadDataTable(list);
	}

	public void addAccout() {
		AddAccount addNCC = new AddAccount(frame, this);
	}

	public void editAccout() {
		if (rowSelected) {
			Account a = AccountDAO.getInstance().selectById(key);
			UpdateAccount uA = new UpdateAccount(frame, this);
			uA.txtEmail.setText(a.getEmail());
			uA.txtFullName.setText(a.getFullName());
			uA.txtUserName.setText(a.getUsername());
			uA.txtPassword.setText(a.getPassword());
			uA.role.setSelectedItem(a.getRole());
			
		} else {
			JOptionPane.showMessageDialog(this, "CHỌN THÔNG TIN SỬA ĐỔI", "WARNING", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void deleteAccount() {
		if (rowSelected) {
			int confirmResult = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa",
					JOptionPane.OK_CANCEL_OPTION);

			if (confirmResult == JOptionPane.OK_OPTION) {
				AccountDAO.getInstance().deleteTrangThai(key);
				JOptionPane.showMessageDialog(this, "XÓA THÀNH CÔNG", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				ArrayList<Account> newList = (ArrayList<Account>) AccountDAO.getInstance().selectAll().stream()
						.filter(item -> !item.isStatus()).collect(Collectors.toList());
				loadDataTable(newList);
			}
		} else {
			JOptionPane.showMessageDialog(this, "CHỌN THÔNG TIN CẦN XÓA", "WARNING", JOptionPane.WARNING_MESSAGE);
		}
	}
	public void filterAccount()
	{
		String search = txtSearch.getText().toLowerCase();
		ArrayList<Account> filterList = new ArrayList<>();
		for (Account a : list)
		{
			if(containsSearch(a.getUsername(), search)||containsSearch(a.getFullName(), search))
			{
				filterList.add(a);
			}
		}
		loadDataTable(filterList);
		
	}
	private boolean containsSearch(String fieldValue, String searchText) {
        return fieldValue.toLowerCase().contains(searchText);
    }
	public void filter() {
		   
	    ArrayList<Account> listNew = null;

	    String selectedOption = (String) comboBox.getSelectedItem();

	     if ("Hoạt động".equals(selectedOption)) {
	        listNew =  (ArrayList<Account>) AccountDAO.getInstance().selectAll().stream().filter(p -> p.isStatus() == false).collect(Collectors.toList());
	    } else if ("Đã xóa".equals(selectedOption)) {
	        listNew = (ArrayList<Account>) AccountDAO.getInstance().selectAll().stream().filter(p -> p.isStatus() ==true).collect(Collectors.toList());
	    }
	    loadDataTable(listNew);
	}
	public void xuatExcel() {
		Controller.ExcelController.exportToExcel(table);
	}
	public void reset() {
		txtSearch.setText("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton) e.getSource();
		if (button.equals(btnAdd)) {
			addAccout();
		} else if (button.equals(btnEdit)) {
			editAccout();
		} else if (button.equals(btnDelete)) {
			deleteAccount();
		} else if (button.equals(btnExcel)) {
			xuatExcel();
		} else if (button.equals(btnReset)) {
			reset();
		}

	}
}

