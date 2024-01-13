package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Laptop extends Product{
	
	private double kichThuoc;
	private String dungLuongPin;
	public Laptop(String maMay, String tenMay, int soLuong, double giaNhap,double giaXuat, String tenCpu, String ram, String xuatXu,
			String cardManHinh, String Rom, boolean trangThai, double kichThuoc, String dungLuongPin) {
		super(maMay, tenMay, soLuong, giaNhap,giaXuat, tenCpu, ram, xuatXu, cardManHinh, Rom, trangThai);
		this.kichThuoc = kichThuoc;
		this.dungLuongPin = dungLuongPin;
	}
	
	

}
