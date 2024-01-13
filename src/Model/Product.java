package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	private String maMay;
	private String tenMay;
	private int soLuong;
	private double giaNhap;
	private double giaXuat;
	private String tenCpu;
	private String ram;
	private String xuatXu;
	private String cardManHinh;
	private String rom;
	private boolean trangThai;
	
	
}
