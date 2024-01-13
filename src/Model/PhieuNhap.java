package Model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhieuNhap extends Phieu {
	
	private String maNhaCungCap;

	
	public PhieuNhap(String maPhieu, LocalDateTime thoiGianTao, String nguoiTao, double tongTien, String maNhaCungCap) {
		super(maPhieu, thoiGianTao, nguoiTao, tongTien);
		this.maNhaCungCap = maNhaCungCap;
	}
	
}
