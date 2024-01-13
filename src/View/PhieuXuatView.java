
package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

import com.toedter.calendar.JDateChooser;

import Dao.NhaCungCapDAO;
import Dao.PhieuNhapDAO;
import Dao.PhieuXuatDAO;
import Model.ChiTietPhieu;
import Model.NhaCungCap;
import Model.PhieuNhap;
import Model.PhieuXuat;
import Model.Product;

public class PhieuXuatView extends JPanel implements ActionListener {

	private JPanel panelAction;
	private JPanel panelSearch;
	private JTextField txtSearch;
	private JPanel filterDay;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnExcel;
	private JButton btnReset;
	private JButton btnDetail;
	private JButton btnSearch;
	private JTable table;
	private DefaultTableModel tableModel;
	JDateChooser dateFrom ;
	JDateChooser dateTo; 
	private String key;
	private boolean rowSelected;
	private JFrame frame;
	
	private ArrayList<Product> list = new ArrayList<>();
	public PhieuXuatView() {
		setSize(1088, 763);
		setLayout(null);
		
		
		JPanel filterDay = new JPanel();
		filterDay.setBounds(33,130, 550, 80);
		filterDay.setLayout(null);
		filterDay.setBorder(new TitledBorder(null, "Lọc theo ngày", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(filterDay);
		
		JLabel lb1_1 = new JLabel("Từ");
		lb1_1.setBounds(10, 30, 30, 30);
		filterDay.add(lb1_1);
		 dateFrom  = new JDateChooser();
		dateFrom.setBounds(50, 30, 130, 30);
		dateFrom.setDate(new Date());
		filterDay.add(dateFrom);
		
		JLabel lb1_2 = new JLabel("Đến");
		lb1_2.setBounds(230, 30, 30, 30);
		filterDay.add(lb1_2);
		 dateTo  = new JDateChooser();
		dateTo.setBounds(270, 30, 130, 30);
		dateTo.setDate(new Date());
		filterDay.add(dateTo);
		
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
		scrollPane.setBounds(33, 230, 1018, 510);
		add(scrollPane);

		panelAction = new JPanel();
		panelAction.setBounds(33, 26, 380, 92);
		panelAction.setLayout(null);
		add(panelAction);
		
		
		panelAction.setBorder(new TitledBorder(null, "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		ImageIcon iconAdd = new ImageIcon("C:\\\\Users\\\\Admin\\\\Desktop\\\\PTPMUD\\\\QuanLyKho\\\\src\\\\Assets\\\\Icon\\\\icons8-add-40.png");
		JButton btnAdd = new JButton("Thêm", iconAdd);
		btnAdd.setBounds(10, 22, 70, 60);
		btnAdd.setEnabled(false);
		btnAdd.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAdd.setHorizontalTextPosition(SwingConstants.CENTER);
		
		setButtonTransparent(btnAdd);

		panelAction.add(btnAdd);
		
		ImageIcon iconEdit = new ImageIcon("C:\\\\Users\\\\Admin\\\\Desktop\\\\PTPMUD\\\\QuanLyKho\\\\src\\\\Assets\\\\Icon\\\\icons8-edit-40.png");
		 btnEdit = new JButton("Sửa", iconEdit);
		btnEdit.setBounds(85, 22, 75, 60);
		btnEdit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEdit.setHorizontalTextPosition(SwingConstants.CENTER);
		setButtonTransparent(btnEdit);

		panelAction.add(btnEdit);
		
		
		ImageIcon iconDetail = new ImageIcon("C:\\\\Users\\\\Admin\\\\Desktop\\\\PTPMUD\\\\QuanLyKho\\\\src\\\\Assets\\\\Icon\\\\icons8-detail-40.png");
		 btnDetail = new JButton("Chi tiết", iconDetail);
		btnDetail.setBounds(170, 22, 75, 60);
		btnDetail.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDetail.setHorizontalTextPosition(SwingConstants.CENTER);
		setButtonTransparent(btnDetail);

		panelAction.add(btnDetail);
		
		ImageIcon iconExcel = new ImageIcon("C:\\\\Users\\\\Admin\\\\Desktop\\\\PTPMUD\\\\QuanLyKho\\\\src\\\\Assets\\\\Icon\\\\icons8-excel-40.png");
		btnExcel = new JButton("Xuất Excel", iconExcel);
		btnExcel.setBounds(250, 22, 95, 60);
		btnExcel.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnExcel.setHorizontalTextPosition(SwingConstants.CENTER);
		setButtonTransparent(btnExcel);
		panelAction.add(btnExcel);

		panelSearch = new JPanel();
		panelSearch.setBounds(600, 26, 449, 92);
		panelSearch.setLayout(null);
		panelSearch.setBorder(new TitledBorder(null, "Tìm kiếm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelSearch);
		
		
		txtSearch = new JTextField();
		txtSearch.setBounds(30, 23, 284, 48);
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				filterPhieuXuat();

			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				filterPhieuXuat();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});
		panelSearch.add(txtSearch);
		
		ImageIcon iconReset = new ImageIcon("C:\\\\Users\\\\Admin\\\\Desktop\\\\PTPMUD\\\\QuanLyKho\\\\src\\\\Assets\\\\Icon\\\\icons8-reset-40.png");
		 btnReset = new JButton("Lảm mới", iconReset);
		btnReset.setBounds(340, 23, 95, 60);
		btnReset.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnReset.setHorizontalTextPosition(SwingConstants.CENTER);
		setButtonTransparent(btnReset);
		panelSearch.add(btnReset);
		
		ImageIcon iconSearch= new ImageIcon("C:\\\\Users\\\\Admin\\\\Desktop\\\\PTPMUD\\\\QuanLyKho\\\\src\\\\Assets\\\\Icon\\\\icons8-search-30.png");
		 btnSearch = new JButton("Tìm kiếm", iconSearch);
			btnSearch.setBounds(440, 15, 95, 60);
			btnSearch.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnSearch.setHorizontalTextPosition(SwingConstants.CENTER);
			setButtonTransparent(btnSearch);
			filterDay.add(btnSearch);
		
		
		btnDetail.addActionListener(this);
		btnExcel.addActionListener(this);
		btnEdit.addActionListener(this);
		btnReset.addActionListener(this);
		btnAdd.addActionListener(this);
		btnSearch.addActionListener(this);

	}
	public void loadDataTable(ArrayList<PhieuXuat> list) {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		int stt = 1;
		for (PhieuXuat px : list) {
			String tongtien = String.format("%.1f", px.getTongTien());
			Object[] rowData = { stt,px.getMaPhieu(),px.getNguoiTao(),px.getThoiGianTao(), tongtien };
			tableModel.addRow(rowData);
			stt++;
		}

	}

	public void initTable() {
		String[] columnNames = {"STT","Mã phiếu xuất", "Người tạo","Thời gian tạo","Tổng tiền"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel); 
	}

	private void setButtonTransparent(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
	}

	public void displayTable() {

		ArrayList<PhieuXuat> list = PhieuXuatDAO.getInstance().selectAll();
		loadDataTable(list);
	}

	public void editPN() {

		if (rowSelected) {			
			PhieuXuat px = PhieuXuatDAO.getInstance().selectById(key);
			UpdatePhieuXuat upx = new UpdatePhieuXuat(this, key);
			upx.txtMaPhieu.setText(px.getMaPhieu());
			upx.txtNguoiTao.setText(px.getNguoiTao());
			upx.txtTotal.setText( String.format("%.1f",  px.getTongTien()));
			
		} else {
			JOptionPane.showMessageDialog(this, "CHỌN THÔNG TIN SỬA ĐỔI", "WARNING", JOptionPane.WARNING_MESSAGE);
		}

	}

	public void xuatExcel() {
		Controller.ExcelController.exportToExcel(table);
	}

	public void reset() {
		txtSearch.setText("");
	}
	public void detailPN() {
		if (rowSelected) {
			
			CTPhieuXuat ctp = new CTPhieuXuat(key);
			PhieuXuat px = new PhieuXuatDAO().getInstance().selectById(key);
			ctp.txtMa.setText(px.getMaPhieu());
			ctp.txtDate.setText(px.getThoiGianTao().toString());
			ctp.txtNguoiTao.setText(px.getNguoiTao());
			ctp.total.setText(String.format("%.1f VND", px.getTongTien()));
		} else {
			JOptionPane.showMessageDialog(this, "Chọn phiếu trước", "WARNING", JOptionPane.WARNING_MESSAGE);
		}
	}
	private static LocalDateTime convertToDateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
	public void filterDay() {
		
		LocalDateTime start = convertToDateToLocalDateTime(dateFrom.getDate());
		LocalDateTime end = convertToDateToLocalDateTime(dateTo.getDate());
		ArrayList<PhieuXuat> list = PhieuXuatDAO.getInstance().selectAll();
		
		ArrayList <PhieuXuat> newList = new ArrayList<>();
		for (PhieuXuat pn : list)
		{
			if(pn.getThoiGianTao()!=null)
			{
			if (pn.getThoiGianTao().isBefore(end)&&pn.getThoiGianTao().isAfter(start))
			{
				newList.add(pn);
			}}
		}
								
		loadDataTable(newList);
		
	}
	public void filterPhieuXuat()
	{
		String search = txtSearch.getText().toLowerCase();
		
		ArrayList<PhieuXuat> listPX = PhieuXuatDAO.getInstance().selectAll();
		ArrayList<PhieuXuat> filterList = new ArrayList<>();
		for (PhieuXuat p : listPX)
		{
			if(containsSearch(p.getNguoiTao(), search))
			{
				filterList.add(p);
			}
		}
		loadDataTable(filterList);
		
	}
	private boolean containsSearch(String fieldValue, String searchText) {
        return fieldValue.toLowerCase().contains(searchText);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		 if (button.equals(btnEdit)) {
			editPN();
		}  else if (button.equals(btnExcel)) {
			xuatExcel();
		} else if (button.equals(btnReset)) {
			reset();
		}else if(button.equals(btnDetail)){
			detailPN();
		}else if (button.equals(btnSearch)) {
			filterDay();
		}
	}
	
	
}
