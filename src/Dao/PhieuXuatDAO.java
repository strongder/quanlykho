package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Model.PhieuXuat;
import Util.JDBCUtil;

public class PhieuXuatDAO implements DAOInterface<PhieuXuat> {

	public static PhieuXuatDAO getInstance() {
		return new PhieuXuatDAO();
	}

	@Override
	public int insert(PhieuXuat t) {
		int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO PhieuXuat (maPhieu, thoiGianTao, nguoiTao,tongTien) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaPhieu());
            pst.setObject(2, t.getThoiGianTao());
            pst.setString(3, t.getNguoiTao());
            pst.setDouble(4, t.getTongTien());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
	}

	@Override
	public int update(PhieuXuat t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE PhieuXuat SET maPhieu=?, thoiGianTao=?, nguoiTao=?, tongTien = ? WHERE maPhieu=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getMaPhieu());
			pst.setObject(2, t.getThoiGianTao());
			pst.setString(3, t.getNguoiTao());
			pst.setDouble(4, t.getTongTien());
			pst.setString(5, t.getMaPhieu());
			ketQua = pst.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int delete(PhieuXuat t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "DELETE FROM PhieuXuat WHERE maPhieu=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getMaPhieu());
			ketQua = pst.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ketQua;
	}

	public int quantity() {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "Select count(maPhieu) as quantity from PhieuXuat";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				ketQua = rs.getInt("quantity");
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public ArrayList<PhieuXuat> selectAll() {
		ArrayList<PhieuXuat> ketQua = new ArrayList<PhieuXuat>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM PhieuXuat ORDER BY thoiGianTao DESC";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String maPhieu = rs.getString("maPhieu");
				LocalDateTime thoiGianTao = (LocalDateTime) rs.getObject("thoiGianTao");
				String nguoiTao = rs.getString("nguoiTao");
				double tongTien = rs.getDouble("tongTien");
				PhieuXuat p = new PhieuXuat(maPhieu, thoiGianTao, nguoiTao, tongTien);
				ketQua.add(p);
			}JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ketQua;
	}


    @Override
    public PhieuXuat selectById(String t) {
        PhieuXuat ketQua = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM PhieuXuat WHERE maPhieu=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            	String maPhieu = rs.getString("maPhieu");
				LocalDateTime thoiGianTao = (LocalDateTime) rs.getObject("thoiGianTao");
				String nguoiTao = rs.getString("nguoiTao");
				double tongTien = rs.getDouble("tongTien");
				
				ketQua = new PhieuXuat(maPhieu, thoiGianTao, nguoiTao, tongTien);
            }JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
}
