package Controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Dao.ChiTietPhieuNhapDAO;
import Dao.ChiTietPhieuXuatDAO;
import Dao.NhaCungCapDAO;
import Dao.PhieuNhapDAO;
import Dao.PhieuXuatDAO;
import Dao.ProductDAO;
import Model.ChiTietPhieu;
import Model.NhaCungCap;
import Model.PhieuNhap;
import Model.PhieuXuat;
import Model.Product;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class PDFController {

	public String filePath = "C:\\Users\\Admin\\Desktop\\PTPMUD\\QuanLyKho\\Pdf\\";
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	Font fontData;
	Font fontTitle;
	Font fontHeader;

	public PDFController() {
		try {
			fontData = new Font(
					BaseFont.createFont("lib/Roboto/Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 11,
					Font.NORMAL);
			fontTitle = new Font(
					BaseFont.createFont("lib/Roboto/Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 24,
					Font.NORMAL);
			fontHeader = new Font(
					BaseFont.createFont("lib/Roboto/Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 11,
					Font.NORMAL);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void xuatPhieuNhap(String maPhieu) {
		try {

			PhieuNhap pn = PhieuNhapDAO.getInstance().selectById(maPhieu);
			// Xác định tên file kèm theo đường dẫn thư mục
			String fileName = "PhieuNhap_" + maPhieu + ".pdf";
			String filePathWithName = filePath + fileName;

			// Tạo đối tượng Document và PdfWriter
			Document document = new Document();
			document.setMargins(60, 60, 40, 50);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePathWithName));

			// Mở tài liệu để bắt đầu ghi
			document.open();

			// Tạo tiêu đề của phiếu nhập
			Paragraph title = new Paragraph("CHI TIẾT PHIẾU NHẬP", fontTitle);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);

			document.add(Chunk.NEWLINE);
			Date  convertDate= Date.from(pn.getThoiGianTao().atZone(ZoneId.systemDefault()).toInstant());
			String date = sdf.format(convertDate);

			NhaCungCap ncc = new NhaCungCapDAO().getInstance().selectById(pn.getMaNhaCungCap());
			
			document.add(new Paragraph("Mã phiếu: " + maPhieu, fontData));
			document.add(new Paragraph("Người tạo: " + pn.getNguoiTao(), fontData));
			document.add(new Paragraph("Thời gian tạo: " + date, fontData));
			document.add(new Paragraph("Nhà cung cấp: " + ncc.getTenNhaCungCap(), fontData));

			document.add(Chunk.NEWLINE);
			// Tạo bảng chứa dữ liệu của phiếu
			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 5, 10, 40, 15, 10, 20 });
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10f);

			// Thêm header cho bảng
			table.addCell(new PdfPCell(new Phrase("STT", fontHeader)));
			table.addCell(new PdfPCell(new Phrase("Mã máy", fontHeader)));
			table.addCell(new PdfPCell(new Phrase("Tên máy", fontHeader)));
			table.addCell(new PdfPCell(new Phrase("Đơn giá", fontHeader)));
			table.addCell(new PdfPCell(new Phrase("Số lượng", fontHeader)));
			table.addCell(new PdfPCell(new Phrase("Thành tiền", fontHeader)));

			// Thêm dữ liệu cho bảng (Ví dụ)
			ArrayList<ChiTietPhieu> list = ChiTietPhieuNhapDAO.getInstance().selectAllById(maPhieu);
			int stt = 1;
			for (ChiTietPhieu ctp : list) {
				Product p = ProductDAO.getInstance().selectById(ctp.getMaMay());

				String donGia = String.format("%.1f", ctp.getDonGia());
				String total = String.format("%.1f", ctp.getDonGia() * ctp.getSoLuong());
				table.addCell(new PdfPCell(new Phrase(stt + "", fontData)));
				table.addCell(new PdfPCell(new Phrase(ctp.getMaMay(), fontData)));
				table.addCell(new PdfPCell(new Phrase(p.getTenMay(), fontData)));
				table.addCell(new PdfPCell(new Phrase(donGia, fontData)));
				table.addCell(new PdfPCell(new Phrase(ctp.getSoLuong() + "", fontData)));
				table.addCell(new PdfPCell(new Phrase(total, fontData)));

				stt++;
			}

			// Thêm bảng vào tài liệu
			document.add(table);

			document.add(Chunk.NEWLINE);

			String tongTien = String.format("%.1f", pn.getTongTien());
			Paragraph totalAmount = new Paragraph("Tổng giá: " + tongTien +" VND", fontData);
			totalAmount.setAlignment(Element.ALIGN_RIGHT);
			document.add(totalAmount);

			// Đóng tài liệu sau khi hoàn thành ghi
			document.close();

			System.out.println("File PDF đã được tạo thành công: " + filePathWithName);

		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
	}

	public void xuatPhieuXuat(String maPhieu) {
		try {

			PhieuXuat pn = PhieuXuatDAO.getInstance().selectById(maPhieu);
			// Xác định tên file kèm theo đường dẫn thư mục
			String fileName = "PhieuXuat_" + maPhieu + ".pdf";
			String filePathWithName = filePath + fileName;

			// Tạo đối tượng Document và PdfWriter
			Document document = new Document();
			document.setMargins(60, 60, 40, 50);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePathWithName));

			// Mở tài liệu để bắt đầu ghi
			document.open();

			// Tạo tiêu đề của phiếu nhập
			Paragraph title = new Paragraph("CHI TIẾT PHIẾU XUẤT", fontTitle);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);

			document.add(Chunk.NEWLINE);
			
			Date  convertDate= Date.from(pn.getThoiGianTao().atZone(ZoneId.systemDefault()).toInstant());
			String date = sdf.format(convertDate);

			
			document.add(new Paragraph("Mã phiếu: " + maPhieu, fontData));
			document.add(new Paragraph("Người tạo: " + pn.getNguoiTao(), fontData));
			document.add(new Paragraph("Thời gian tạo: " + date, fontData));

			document.add(Chunk.NEWLINE);
			// Tạo bảng chứa dữ liệu của phiếu
			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 5, 10, 40, 15, 10, 20 });
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10f);

			// Thêm header cho bảng
			table.addCell(new PdfPCell(new Phrase("STT", fontHeader)));
			table.addCell(new PdfPCell(new Phrase("Mã máy", fontHeader)));
			table.addCell(new PdfPCell(new Phrase("Tên máy", fontHeader)));
			table.addCell(new PdfPCell(new Phrase("Đơn giá", fontHeader)));
			table.addCell(new PdfPCell(new Phrase("Số lượng", fontHeader)));
			table.addCell(new PdfPCell(new Phrase("Thành tiền", fontHeader)));

			// Thêm dữ liệu cho bảng (Ví dụ)
			ArrayList<ChiTietPhieu> list = ChiTietPhieuXuatDAO.getInstance().selectByMaPhieu(maPhieu);
			int stt = 1;
			for (ChiTietPhieu ctp : list) {
				Product p = ProductDAO.getInstance().selectById(ctp.getMaMay());

				String donGia = String.format("%.1f", ctp.getDonGia());
				String total = String.format("%.1f", ctp.getDonGia() *ctp.getSoLuong());
				table.addCell(new PdfPCell(new Phrase(stt + "", fontData)));
				table.addCell(new PdfPCell(new Phrase(ctp.getMaMay(), fontData)));
				table.addCell(new PdfPCell(new Phrase(p.getTenMay(), fontData)));
				table.addCell(new PdfPCell(new Phrase(donGia, fontData)));
				table.addCell(new PdfPCell(new Phrase(ctp.getSoLuong() + "", fontData)));
				table.addCell(new PdfPCell(new Phrase(total, fontData)));

				stt++;
			}

			// Thêm bảng vào tài liệu
			document.add(table);

			document.add(Chunk.NEWLINE);

			String tongTien = String.format("%.1f", pn.getTongTien());
			Paragraph totalAmount = new Paragraph("Tổng giá: " + tongTien+" VND", fontData);
			totalAmount.setAlignment(Element.ALIGN_RIGHT);
			document.add(totalAmount);

			// Đóng tài liệu sau khi hoàn thành ghi
			document.close();

			System.out.println("File PDF đã được tạo thành công: " + filePathWithName);

		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
	}
}
