package Model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phieu {
	 	private String maPhieu;
	    private LocalDateTime thoiGianTao;
	    private String nguoiTao;
	    private double tongTien;
	    
}
