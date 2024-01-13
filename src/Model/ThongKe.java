package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThongKe {
	private String maMay;
    private String tenMay;
    private int slNhap;
    private int slXuat;
}
