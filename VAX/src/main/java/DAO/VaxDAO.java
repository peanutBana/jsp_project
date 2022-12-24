package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VaxDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//데이터베이스 연결
	public static Connection getConnection() throws Exception {
	      Class.forName("oracle.jdbc.OracleDriver");
	      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");
	      return con;
	   }
	
	public int insert(HttpServletRequest request, HttpServletResponse response) {
		String resrvNo = request.getParameter("resrvNo");
		String jumin = request.getParameter("jumin");
		String vCode = request.getParameter("vCode");
		String hospCode = request.getParameter("hospCode");
		String resvDate = request.getParameter("resvDate");
		String resvTime = request.getParameter("resvTime");
		int result = 0;
		
		try {
			conn = getConnection();
			
			String sql = "insert into tbl_vaccresv_202108";
			sql += " values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, resrvNo);
			ps.setString(2, jumin);
			ps.setString(3, vCode);
			ps.setString(4, hospCode);
			ps.setString(5, resvDate);
			ps.setString(6, resvTime);
			
			result = ps.executeUpdate();
			System.out.println(result);
			
			conn.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String list(HttpServletRequest request, HttpServletResponse response) {
		return "list.jsp";
		
	}
	
	public String statics(HttpServletRequest request, HttpServletResponse response) {
		return "statics.jsp";
		
	}
}
