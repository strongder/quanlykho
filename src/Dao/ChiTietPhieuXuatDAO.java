package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.ChiTietPhieu;
import Util.JDBCUtil;

public class ChiTietPhieuXuatDAO implements DAOInterface<ChiTietPhieu> {

    public static ChiTietPhieuXuatDAO getInstance() {
        return new ChiTietPhieuXuatDAO();
    }

    @Override
    public int insert(ChiTietPhieu t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO ChiTietPhieuXuat (maPhieu, maMay, soLuong, donGia) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaPhieu());
            pst.setString(2, t.getMaMay());
            pst.setInt(3, t.getSoLuong());
            pst.setDouble(4, t.getDonGia());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(ChiTietPhieu t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE ChiTietPhieuXuat SET maPhieu=?, maMay=?, soLuong=?, donGia = ?  WHERE maPhieu=? AND maMay=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaPhieu());
            pst.setString(2, t.getMaMay());
            pst.setInt(3, t.getSoLuong());
            pst.setDouble(4, t.getDonGia());
            pst.setString(5, t.getMaPhieu());
            pst.setString(6, t.getMaMay());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(ChiTietPhieu t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM ChiTietPhieuXuat WHERE maPhieu=?";
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

    public ArrayList<ChiTietPhieu> selectByMaPhieu(String t) {
        ArrayList<ChiTietPhieu> ketQua = new ArrayList<ChiTietPhieu>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ChiTietPhieuXuat WHERE maPhieu=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maPhieu = rs.getString("maPhieu");
                String maMay = rs.getString("maMay");
                int soLuong = rs.getInt("soLuong");
                double donGia = rs.getDouble("donGia");
                ChiTietPhieu ctp = new ChiTietPhieu(maPhieu, maMay, soLuong, donGia);
                ketQua.add(ctp);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<ChiTietPhieu> selectAll() {
        ArrayList<ChiTietPhieu> ketQua = new ArrayList<ChiTietPhieu>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ChiTietPhieuXuat";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maPhieu = rs.getString("maPhieu");
                String maMay = rs.getString("maMay");
                int soLuong = rs.getInt("soLuong");
                double donGia = rs.getDouble("donGia");
                ChiTietPhieu ctp = new ChiTietPhieu(maPhieu, maMay, soLuong, donGia);
                ketQua.add(ctp);
            }JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ChiTietPhieu selectById(String t) {
        ChiTietPhieu ketQua = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ChiTietPhieuXuat WHERE maPhieu=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maPhieu = rs.getString("maPhieu");
                String maMay = rs.getString("maMay");
                int soLuong = rs.getInt("soLuong");
                double donGia = rs.getDouble("donGia");
                ketQua = new ChiTietPhieu(maPhieu, maMay, soLuong, donGia);
            }JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
    public int soLuongXuat(String maMay)
    {	
    	int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT sum(soLuong) FROM ChiTietPhieuXuat WHERE maMay=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, maMay);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            	ketQua = rs.getInt(1);
            }JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
}
