package Model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PhieuXuat extends Phieu {
	
	public PhieuXuat()
	{
		
	}
	public PhieuXuat(String maPhieu, LocalDateTime thoiGianTao, String nguoiTao, double tongTien) {
		super(maPhieu, thoiGianTao, nguoiTao, tongTien);
	}
}
