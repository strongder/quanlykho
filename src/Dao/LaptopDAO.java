package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Laptop;
import Model.Product;
import Util.JDBCUtil;

public class LaptopDAO implements DAOInterface<Laptop> {

	public static LaptopDAO getInstance() {
		return new LaptopDAO();
	}

	@Override
	public int insert(Laptop t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "INSERT INTO MayTinh (maMay, tenMay, tenCpu, ram, cardManHinh, giaNhap,giaXuat, dungLuongPin, kichThuocMan, xuatXu, loaiMay, rom, trangThai) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getMaMay());
			pst.setString(2, t.getTenMay());
			pst.setString(3, t.getTenCpu());
			pst.setString(4, t.getRam());
			pst.setString(5, t.getCardManHinh());
			pst.setDouble(6, t.getGiaNhap());
			pst.setDouble(7, t.getGiaXuat());
			pst.setString(8, t.getDungLuongPin());
			pst.setDouble(9, t.getKichThuoc());
			pst.setString(10, t.getXuatXu());
			pst.setString(11, "Laptop");
			pst.setString(12, t.getRom());
			pst.setInt(13, 0);
			ketQua = pst.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
		}
		return ketQua;
	}

	@Override
	public int update(Laptop t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			// String sql = "INSERT INTO MayTinh (maMay, tenMay, soLuong, tenCpu, ram,
			// cardManHinh, gia, dungLuongPin, dungLuongPin, dungLuongPin, loaiMay, rom)
			// VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			String sql = "UPDATE MayTinh SET maMay=?, tenMay=?,tenCpu=?, ram=?, cardManHinh=?, giaNhap=?,giaXuat=?, dungLuongPin=?, kichThuocMan=?, xuatXu=?, loaiMay = ?, rom = ? WHERE maMay=? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getMaMay());
			pst.setString(2, t.getTenMay());
			pst.setString(3, t.getTenCpu());
			pst.setString(4, t.getRam());
			pst.setString(5, t.getCardManHinh());
			pst.setDouble(6, t.getGiaNhap());
			pst.setDouble(7, t.getGiaXuat());
			pst.setString(8, t.getDungLuongPin());
			pst.setDouble(9, t.getKichThuoc());
			pst.setString(10, t.getXuatXu());
			pst.setString(11, "Laptop");
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
	public int delete(Laptop t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE MayTinh SET trangthai=? where maMay=? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.isTrangThai() == true ? 1 : 0);
			pst.setString(2, t.getMaMay());
			ketQua = pst.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public ArrayList<Laptop> selectAll() {
		ArrayList<Laptop> ketQua = new ArrayList<Laptop>();
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
				double kichThuocMan = rs.getDouble("kichThuocMan");
				String dungLuongPin = rs.getString("dungLuongPin");
				String rom = rs.getString("rom");
				String xuatXu = rs.getString("xuatXu");
				boolean trangThai = rs.getInt("trangThai") == 1 ? true : false;
				Laptop mt = new Laptop(maMay, tenMay, soLuong, giaNhap, giaXuat, tenCpu, ram, xuatXu, cardManHinh, rom, trangThai,
						kichThuocMan, dungLuongPin);
				ketQua.add(mt);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public Laptop selectById(String t) {
		Laptop kq = null;
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
				double kichThuocMan = rs.getDouble("kichThuocMan");
				String dungLuongPin = rs.getString("dungLuongPin");
				String rom = rs.getString("rom");
				String xuatXu = rs.getString("xuatXu");
				boolean trangThai = rs.getInt("trangThai") == 1 ? true : false;
				kq = new Laptop(maMay, tenMay, soLuong, giaNhap, giaXuat, tenCpu, ram, xuatXu, cardManHinh, rom, trangThai,
						kichThuocMan, dungLuongPin);
			}
			JDBCUtil.closeConnection(con);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kq;
	}

	public boolean isLaptop(String id) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM MayTinh WHERE maMay= ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			String tl = null;
			while (rs.next()) {
				tl = rs.getString("loaiMay");
			}
			if (tl.equals("Laptop")) {
				return true;
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
