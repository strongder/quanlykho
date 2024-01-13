package Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelController {
	public static void exportToExcel(JTable table) {
        try {
            // Đường dẫn mặc định
            String defaultPath = "C:\\Users\\Admin\\Desktop\\PTPMUD\\QuanLyKho\\Excel\\";
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

            // Tạo tên tệp với định dạng "Export_yyyyMMdd_HHmmss.xlsx"
            String fileName = "Export_" + dateFormat.format(new Date()) + ".xlsx";

            String filePath = defaultPath + fileName;

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Data");

                // Copy data from JTable to Excel sheet
                for (int i = 0; i < table.getRowCount(); i++) {
                    Row row = sheet.createRow(i);
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
                    }
                }

                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                    System.out.println("Excel file has been created successfully!");
                    JOptionPane.showMessageDialog(null, "Xuất Excel thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi xuất Excel", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
