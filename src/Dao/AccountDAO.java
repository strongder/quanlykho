package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Account;
import Util.JDBCUtil;

public class AccountDAO implements DAOInterface<Account> {

	public static AccountDAO getInstance() {
		return new AccountDAO();
	}

	@Override
	public int insert(Account t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "INSERT INTO Account (fullName, userName, password, roleName, status, email) VALUES (?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getFullName());
			pst.setString(2, t.getUsername());
			pst.setString(3, t.getPassword());
			pst.setString(4, t.getRole());
			pst.setInt(5, 0);
			pst.setString(6, t.getEmail());
			ketQua = pst.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int update(Account t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE Account SET fullName=?, password=?, roleName=?, email=? WHERE userName=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getFullName());
			pst.setString(2, t.getPassword());
			pst.setString(3, t.getRole());
			pst.setString(5, t.getUsername());
			pst.setString(4, t.getEmail());
			ketQua = pst.executeUpdate();
			
			JDBCUtil.closeConnection(con);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int delete(Account t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "DELETE FROM Account WHERE userName=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getUsername());
			ketQua = pst.executeUpdate();
			
			JDBCUtil.closeConnection(con);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ketQua;
	}

	
	public int deleteTrangThai(String t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "Update Account SET status = 1 WHERE userName=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t);
			ketQua = pst.executeUpdate();
			JDBCUtil.closeConnection(con);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ketQua;
	}
	@Override
	public ArrayList<Account> selectAll() {
		ArrayList<Account> ketQua = new ArrayList<Account>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM Account";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String fullName = rs.getString("fullName");
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				String role = rs.getString("roleName");
				boolean status = rs.getInt("status")==1?true:false;
				String email = rs.getString("email");
				Account acc = new Account(fullName, userName, password, role, status, email);
				ketQua.add(acc);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public Account selectById(String t) {
		Account acc = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM Account WHERE userName=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String fullName = rs.getString("fullName");
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				String role = rs.getString("roleName");
				boolean status = rs.getInt("status")==1?true:false;;
				String email = rs.getString("email");
				acc = new Account(fullName, userName, password, role, status, email);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return acc;
	}

	public int updatePassword(String email, String password) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE Account SET password=? WHERE email=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, password);
			pst.setString(2, email);

			ketQua = pst.executeUpdate();
			JDBCUtil.closeConnection(con);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ketQua;
	}

	public int getSL() {
		int ketQua = 0 ;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT count(username) as SL FROM account WHERE status = 0";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               ketQua = rs.getInt("SL");
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;

	}
	public int changePassByEmail(String pass, String email) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE Account SET  password=? WHERE email=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,pass);
			pst.setString(2, email);
			ketQua = pst.executeUpdate();
			JDBCUtil.closeConnection(con);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ketQua;
	}
}