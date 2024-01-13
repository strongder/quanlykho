package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.NhaCungCap;
import Util.JDBCUtil;

public class NhaCungCapDAO implements DAOInterface<NhaCungCap> {

    public static NhaCungCapDAO getInstance() {
        return new NhaCungCapDAO();
    }

    @Override
    public int insert(NhaCungCap t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO NhaCungCap (maNhaCungCap, tenNhaCungCap, Sdt, diaChi, trangThai) VALUES (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaNCC());
            pst.setString(2, t.getTenNhaCungCap());
            pst.setString(3, t.getSdt());
            pst.setString(4, t.getDiaChi());
            pst.setInt(5, 1);
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
           
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(NhaCungCap t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE NhaCungCap SET maNhaCungCap=?, tenNhaCungCap=?, Sdt=?, diaChi=? WHERE maNhaCungCap=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaNCC());
            pst.setString(2, t.getTenNhaCungCap());
            pst.setString(3, t.getSdt());
            pst.setString(4, t.getDiaChi());
            pst.setString(5, t.getMaNCC());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;

    }

    @Override
    public int delete(NhaCungCap t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Update NhaCungCap Set trangThai = 1 WHERE maNhaCungCap=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaNCC());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
    public int deleteTrangThai(String maNCC) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Update NhaCungCap Set trangThai = 1 WHERE maNhaCungCap=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,maNCC);
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<NhaCungCap> selectAll() {
        ArrayList<NhaCungCap> ketQua = new ArrayList<NhaCungCap>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM NhaCungCap";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maNhaCungCap = rs.getString("maNhaCungCap");
                String tenNhaCungCap = rs.getString("tenNhaCungCap");
                String sdt = rs.getString("Sdt");
                String diaChi = rs.getString("diaChi");
                boolean trangThai = rs.getInt("trangThai")== 1 ? true:false;
                NhaCungCap ncc = new NhaCungCap(maNhaCungCap, tenNhaCungCap, sdt, diaChi, trangThai);
                ketQua.add(ncc);
            }JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public NhaCungCap selectById(String t) {
        NhaCungCap ketQua = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM NhaCungCap WHERE maNhaCungCap=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maNhaCungCap = rs.getString("maNhaCungCap");
                String tenNhaCungCap = rs.getString("tenNhaCungCap");
                String sdt = rs.getString("Sdt");
                String diaChi = rs.getString("diaChi");
                boolean trangThai = rs.getInt("trangThai")== 1 ? true:false;
                ketQua = new NhaCungCap(maNhaCungCap, tenNhaCungCap, sdt, diaChi, trangThai);
            }JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public String selectIdByName(String t) {
        String ketQua = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT maNhaCungCap FROM NhaCungCap WHERE tenNhaCungCap=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maNhaCungCap = rs.getString("maNhaCungCap");
                ketQua = maNhaCungCap;
            }JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

	public int  getSL() {
		int ketQua = 0 ;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT count(maNhaCungCap) as SL FROM NhaCungCap WHERE trangThai = 0 ";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               ketQua = rs.getInt("SL");
            }JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;

	}
}

