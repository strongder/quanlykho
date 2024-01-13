package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PC extends Product{
	
	private int congSuatNguon;
	private String mainBoard;
	public PC(String maMay, String tenMay, int soLuong, double giaNhap,double giaXuat, String tenCpu, String ram, String xuatXu,
			String cardManHinh, String Rom, boolean trangThai, int congSuatNguon, String mainBoard) {
		super(maMay, tenMay, soLuong, giaNhap, giaXuat, tenCpu, ram, xuatXu, cardManHinh, Rom, trangThai);
		this.congSuatNguon = congSuatNguon;
		this.mainBoard = mainBoard;
	}

	
}
