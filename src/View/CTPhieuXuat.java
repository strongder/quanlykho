package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Controller.PDFController;
import Dao.ChiTietPhieuNhapDAO;
import Dao.ChiTietPhieuXuatDAO;
import Dao.ProductDAO;
import Model.ChiTietPhieu;
import Model.Product;

public class CTPhieuXuat extends JDialog implements ActionListener{
	
	JTextField txtMa;
	 JTextField txtNguoiTao;
	 JTextField txtDate;
	 JTextField txtNCC;
	private String maPhieu;
	private JTable table;
	private JButton btnPDF;
	private  DefaultTableModel tableModel;
	 JLabel total;
  
	public CTPhieuXuat(String maPhieu)
	{	
		this.maPhieu = maPhieu;
		setBounds(100, 100, 754, 521);
		getContentPane().setLayout(null);
		
		JLabel title = new JLabel("CHI TIẾT PHIẾU XUẤT");
		title.setFont(new Font("Tahoma", Font.PLAIN, 24));
		title.setBounds(0, 0, 745, 53);
		title.setOpaque(true);
		title.setBackground(Color.CYAN);
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		getContentPane().add(title);
		
		
		initTable();
		
		displayTable();
		table.enable(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 163, 716, 256);
		getContentPane().add(scrollPane);
		
		JLabel lbMa = new JLabel("Mã phiếu");
		lbMa.setBounds(67, 78, 93, 20);
		getContentPane().add(lbMa);
		
		JLabel lbNT = new JLabel("Người tạo");
		lbNT.setBounds(67, 124, 93, 20);
		getContentPane().add(lbNT);
		
		JLabel lbTime = new JLabel("Thời gian");
		lbTime.setBounds(396, 78, 93, 20);
		getContentPane().add(lbTime);
		
		
		
		
		txtMa = new JTextField();
		txtMa.setBounds(150, 74, 133, 30);
		txtMa.setEditable(false);
		getContentPane().add(txtMa);
		
		txtNguoiTao = new JTextField();
		txtNguoiTao.setBounds(150, 120, 133, 30);
		txtNguoiTao.setEditable(false);
		getContentPane().add(txtNguoiTao);
		
		txtDate = new JTextField();
		txtDate.setBounds(499, 74, 154, 30);
		txtDate.setEditable(false);
		getContentPane().add(txtDate);
		
		
		JLabel lblNewLabel_2 = new JLabel("Tổng tiền");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setForeground(Color.red);
		lblNewLabel_2.setBounds(27, 439, 93, 26);
		getContentPane().add(lblNewLabel_2);
		
		
		ImageIcon iconPDF = new ImageIcon("C:\\Users\\Admin\\Desktop\\PTPMUD\\QuanLyKho\\src\\Assets\\Icon\\icons8-pdf-30.png");
		 btnPDF = new JButton("Xuất phiếu", iconPDF);
		btnPDF.setBounds(570, 429, 130, 44);
		btnPDF.setBackground(Color.cyan);
		getContentPane().add(btnPDF);
		
		btnPDF.addActionListener(this);
		
		 total= new JLabel("..VND");
		total.setFont(new Font("Tahoma", Font.PLAIN, 18));
		total.setBounds(125, 436, 200, 32);
		getContentPane().add(total);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public void initTable()
	{
		String[] columnNames = {"STT","Mã máy", "Tên máy","Số lượng","Đơn giá", "Thành tiền"};
      tableModel = new DefaultTableModel(columnNames, 0);
       table = new JTable(tableModel); 
	}
	public void loadDataTable(List<Product> list) {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		int stt = 1;
		for (Product p : list) {
			String gia = String.format("%.1f", p.getGiaXuat());
			String total = String.format("%.1f",p.getGiaXuat()*p.getSoLuong()) ;

			Object[] rowData = { stt, p.getMaMay(), p.getTenMay(), p.getSoLuong(), gia , total};
			tableModel.addRow(rowData);
			stt++;
		}
	}
	public void displayTable()
	{
		ArrayList<ChiTietPhieu> listCTP = ChiTietPhieuXuatDAO.getInstance().selectByMaPhieu(maPhieu);
		ArrayList<Product> list = new ArrayList<>();
		
		for(ChiTietPhieu ctp : listCTP)
		{
			Product p = ProductDAO.getInstance().selectById(ctp.getMaMay());
			p.setSoLuong(ctp.getSoLuong());
			list.add(p);
		}
		loadDataTable(list);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnPDF))
		{
			PDFController pdf = new PDFController();
			pdf.xuatPhieuXuat(maPhieu);
		}
		
	}

}