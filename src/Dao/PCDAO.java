package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.PC;
import Util.JDBCUtil;

public class PCDAO implements DAOInterface<PC> {

	public static PCDAO getInstance() {
		return new PCDAO();
	}

	@Override
	public int insert(PC t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "INSERT INTO MayTinh (maMay, tenMay, tenCpu, ram, cardManHinh, giaNhap,giaXuat, mainBoard, congSuatNguon, xuatXu, loaiMay, rom, trangThai) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getMaMay());
			pst.setString(2, t.getTenMay());
			pst.setString(3, t.getTenCpu());
			pst.setString(4, t.getRam());
			pst.setString(5, t.getCardManHinh());
			pst.setDouble(6, t.getGiaNhap());
			pst.setDouble(7, t.getGiaNhap());
			pst.setString(8, t.getMainBoard());
			pst.setInt(9, t.getCongSuatNguon());
			pst.setString(10, t.getXuatXu());
			pst.setString(11, "PC - Lắp ráp");
			pst.setString(12, t.getRom());
			pst.setInt(13, 0);
			ketQua = pst.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int update(PC t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			// String sql = "INSERT INTO MayTinh (maMay, tenMay, soLuong, tenCpu, ram,
			// cardManHinh, gia, dungLuongPin, dungLuongPin, dungLuongPin, loaiMay, rom)
			// VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			String sql = "UPDATE MayTinh SET maMay=?, tenMay=?, tenCpu=?, ram=?, cardManHinh=?, giaNhap=?,giaXuat=?, mainBoard=?, congSuatNguon=?, xuatXu=?, loaiMay = ?, rom = ?  WHERE maMay= ? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getMaMay());
			pst.setString(2, t.getTenMay());
			pst.setString(3, t.getTenCpu());
			pst.setString(4, t.getRam());
			pst.setString(5, t.getCardManHinh());
			pst.setDouble(6, t.getGiaNhap());
			pst.setDouble(7, t.getGiaNhap());
			pst.setString(8, t.getMainBoard());
			pst.setInt(9, t.getCongSuatNguon());
			pst.setString(10, t.getXuatXu());
			pst.setString(11, "PC - Lắp ráp");
			pst.setString(12, t.getRom());
			pst.setString(13, t.getMaMay());
			ketQua = pst.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int delete(PC t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "DELETE FROM MayTinh WHERE maMay=? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getMaMay());
			ketQua = pst.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public ArrayList<PC> selectAll() {
		ArrayList<PC> ketQua = new ArrayList<PC>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM MayTinh";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String maMay = rs.getString("maMay");
				String tenMay = rs.getString("tenMay");
				int soLuong = rs.getInt("soLuong");
				String tenCpu = rs.getString("tenCpu");
				String ram = rs.getString("ram");
				String cardManHinh = rs.getString("cardManHinh");
				double giaNhap = rs.getDouble("giaNhap");
				double giaXuat = rs.getDouble("giaXuat");
				String mainBoard = rs.getString("mainBoard");
				int congSuatNguon = rs.getInt("congSuatNguon");
				String rom = rs.getString("rom");
				String xuatXu = rs.getString("xuatXu");
				boolean trangThai = rs.getInt("trangThai") == 1 ? true : false;
				PC mt = new PC(maMay, tenMay, soLuong, giaNhap, giaXuat, tenCpu, ram, xuatXu, cardManHinh, rom,
						trangThai, congSuatNguon, mainBoard);
				ketQua.add(mt);
			}JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public PC selectById(String t) {
		PC kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM MayTinh WHERE maMay=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String maMay = rs.getString("maMay");
				String tenMay = rs.getString("tenMay");
				int soLuong = rs.getInt("soLuong");
				String tenCpu = rs.getString("tenCpu");
				String ram = rs.getString("ram");
				String cardManHinh = rs.getString("cardManHinh");
				double giaNhap = rs.getDouble("giaNhap");
				double giaXuat = rs.getDouble("giaXuat");
				String mainBoard = rs.getString("mainBoard");
				int congSuatNguon = rs.getInt("congSuatNguon");
				String rom = rs.getString("rom");
				String xuatXu = rs.getString("xuatXu");
				boolean trangThai = rs.getInt("trangThai") == 1 ? true : false;
				kq = new PC(maMay, tenMay, soLuong, giaNhap, giaXuat, tenCpu, ram, xuatXu, cardManHinh, rom, trangThai,
						congSuatNguon, mainBoard);
			}JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}
}
