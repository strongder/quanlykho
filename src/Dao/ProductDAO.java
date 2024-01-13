package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Product;
import Util.JDBCUtil;

public class ProductDAO implements DAOInterface<Product> {

    public static ProductDAO getInstance() {
        return new ProductDAO();
    }

    @Override
    public int insert(Product t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Product t) {
        return 0;
    }

    @Override
    public int delete(Product t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM Maytinh WHERE maMay=? ";
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
    public ArrayList<Product> selectAll() {
        ArrayList<Product> ketQua = new ArrayList<Product>();
        Connection con = null;
        try {
             con = JDBCUtil.getConnection();
            String sql = "SELECT maMay,tenMay,soLuong,giaNhap,giaXuat,tenCpu,ram,xuatXu,cardManHinh,rom,trangThai FROM maytinh";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maMay = rs.getString("maMay");
                String tenMay = rs.getString("tenMay");
                int soLuong = rs.getInt("soLuong");
                double giaXuat= rs.getDouble("giaXuat");
                double giaNhap = rs.getDouble("giaNhap");
                String tenCpu = rs.getString("tenCpu");
                String ram = rs.getString("ram");
                String xuatXu = rs.getString("xuatXu");
                String cardManHinh = rs.getString("cardManHinh");
                String rom = rs.getString("rom");
                boolean trangThai = rs.getInt("trangThai")==1?true:false;
                Product mt = new Product(maMay, tenMay, soLuong, giaNhap,giaXuat, tenCpu, ram, xuatXu, cardManHinh, rom, trangThai);
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
    public Product selectById(String t) {
        Product ketQua = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT maMay,tenMay,soLuong,giaNhap,giaXuat,tenCpu,ram,xuatXu,cardManHinh,rom,trangThai FROM maytinh WHERE maMay = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maMay = rs.getString("maMay");
                String tenMay = rs.getString("tenMay");
                int soLuong = rs.getInt("soLuong");
                double giaXuat= rs.getDouble("giaXuat");
                double giaNhap = rs.getDouble("giaNhap");
                String tenCpu = rs.getString("tenCpu");
                String ram = rs.getString("ram");
                String xuatXu = rs.getString("xuatXu");
                String cardManHinh = rs.getString("cardManHinh");
                String rom = rs.getString("rom");
                boolean trangThai = rs.getInt("trangThai")==1?true:false;
                ketQua = new Product(maMay, tenMay, soLuong, giaNhap, giaXuat, tenCpu, ram, xuatXu, cardManHinh, rom, trangThai);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    public int updateSoLuong(String maMay, int soluong) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            //String sql = "INSERT INTO Product (maMay, tenMay, soLuong, tenCpu, ram, cardManHinh, gia, dungLuongPin, dungLuongPin, dungLuongPin, loaiMay, rom) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            String sql = "UPDATE maytinh SET soLuong=? WHERE maMay=? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, soluong);
            pst.setString(2, maMay);
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public int deleteTrangThai(String maMay){
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            //String sql = "INSERT INTO Product (maMay, tenMay, soLuong, tenCpu, ram, cardManHinh, gia, dungLuongPin, dungLuongPin, dungLuongPin, loaiMay, rom) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            String sql = "UPDATE maytinh SET trangThai=1 WHERE maMay=? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, maMay);
            ketQua = pst.executeUpdate();
             JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

  
        
    public int getSl() {
        int soluong = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT count(maMay) as SL FROM maytinh WHERE trangThai = 0";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                soluong = rs.getInt("SL");
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return soluong;
    }
}

