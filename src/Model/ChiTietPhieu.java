package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietPhieu {
	private String maPhieu;
	private String maMay;
	private int soLuong;
	private double donGia;

}
