package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Util.JDBCUtil;


public class LoginDAO {
	public static LoginDAO getInstance()
	{
		return new LoginDAO();
	}
	public boolean authen(String username, String password) {
		boolean kq = false;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM account WHERE username = ? AND password = ? AND Status = 0";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                kq = true;
            } else {
                kq =false;
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return kq;
    }
}
