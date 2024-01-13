package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Model.Phieu;
import Model.PhieuNhap;
import Util.JDBCUtil;

public class PhieuNhapDAO implements DAOInterface<PhieuNhap> {

    public static PhieuNhapDAO getInstance() {
        return new PhieuNhapDAO();
    }

    @Override
    public int insert(PhieuNhap t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO PhieuNhap (maPhieu, thoiGianTao, nguoiTao,maNhaCungCap, tongTien) VALUES (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaPhieu());
            pst.setObject(2, t.getThoiGianTao());
            pst.setString(3, t.getNguoiTao());
            pst.setString(4, t.getMaNhaCungCap());
            pst.setDouble(5, t.getTongTien());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(PhieuNhap t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE PhieuNhap SET maPhieu=?, thoiGianTao=?, nguoiTao=?, maNhaCungCap=?, tongTien = ? WHERE maPhieu=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaPhieu());
            pst.setObject(2, t.getThoiGianTao());
            pst.setString(3, t.getNguoiTao());
            pst.setString(4, t.getMaNhaCungCap());
            pst.setDouble(5, t.getTongTien());
            pst.setString(6, t.getMaPhieu());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(PhieuNhap t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM PhieuNhap WHERE maPhieu=?";
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
	            String sql = "Select count(maPhieu) as quantity from PhieuNhap";
	            PreparedStatement pst = con.prepareStatement(sql);
	            ResultSet rs = pst.executeQuery();
	            
	            while (rs.next())
	            {
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
    public ArrayList<PhieuNhap> selectAll() {
        ArrayList<PhieuNhap> ketQua = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM PhieuNhap ORDER BY thoiGianTao DESC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maPhieu = rs.getString("maPhieu");
                LocalDateTime thoiGianTao = (LocalDateTime) rs.getObject("thoigiantao");
                String nguoiTao = rs.getString("nguoiTao");
                String maNhaCungCap = rs.getString("maNhaCungCap");
                double tongTien = rs.getDouble("tongTien");
                PhieuNhap p = new PhieuNhap(maPhieu, thoiGianTao, nguoiTao, tongTien, maNhaCungCap );
                ketQua.add(p);
            }JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }


    @Override
    public PhieuNhap selectById(String t) {
        PhieuNhap ketQua = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM PhieuNhap WHERE maPhieu=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            	String maPhieu = rs.getString("maPhieu");
                LocalDateTime thoiGianTao = (LocalDateTime) rs.getObject("thoigiantao");
                String nguoiTao = rs.getString("nguoiTao");
                String maNhaCungCap = rs.getString("maNhaCungCap");
                double tongTien = rs.getDouble("tongTien");
                ketQua = new PhieuNhap(maPhieu, thoiGianTao, nguoiTao, tongTien, maNhaCungCap );
            }JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }


}
