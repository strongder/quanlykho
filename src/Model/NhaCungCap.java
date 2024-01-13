package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor	
public class NhaCungCap {
	
	private String maNCC;
	private String tenNhaCungCap;
	private String sdt;
	private String diaChi;
	private boolean trangThai;
}
