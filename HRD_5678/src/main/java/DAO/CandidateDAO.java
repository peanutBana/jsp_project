package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Candidate;
import DTO.Vote;

public class CandidateDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//데이터베이스 연결
	public static Connection getConnection() throws Exception {
	      Class.forName("oracle.jdbc.OracleDriver");
	      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");
	      return con;
	   }
	
	public String result(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Candidate> list = new ArrayList<Candidate>();
		
		try {
			conn = getConnection();
			String sql = "SELECT A.M_NO AS 후보번호, A.M_NAME AS 성명, B.P_NAME AS 소속정당, DECODE(A.P_SCHOOL, '1', '고졸', '2', '학사', '3', '석사', '4', '박사') AS 학력, SUBSTR(A.M_JUMIN, 1, 6) || '-' || SUBSTR(A.M_JUMIN,7, 13) AS 주민번호, A.M_CITY AS 지역구, B.P_TEL1|| '-' || B.P_TEL2 || '-' ||(SUBSTR(B.P_TEL3, 4)) || (SUBSTR(B.P_TEL3, 4)) || (SUBSTR(B.P_TEL3,4)) || (SUBSTR(B.P_TEL3, 4)) AS 대표전화";
			sql += " FROM TBL_MEMBER_202005 A JOIN TBL_PARTY_202005 B";
			sql += " ON(A.P_CODE = B.P_CODE)";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Candidate candidate = new Candidate();
				candidate.setM_no(rs.getString(1));
				candidate.setM_name(rs.getString(2));
				candidate.setParty(rs.getString(3));
				candidate.setP_school(rs.getString(4));
				candidate.setM_jumin(rs.getString(5));
				candidate.setM_city(rs.getString(6));
				candidate.setTel(rs.getString(7));
				
				list.add(candidate);
			}
			request.setAttribute("list", list);
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "result.jsp";
	}
	public int insert(HttpServletRequest request, HttpServletResponse response) {
		String v_jumin = request.getParameter("v_jumin");
		String v_name = request.getParameter("v_name");
		String m_no = request.getParameter("m_no");
		String v_time = request.getParameter("v_time");
		String v_area = request.getParameter("v_area");
		String v_confirm = request.getParameter("v_confirm");
		int result = 0;
		
		try {
			conn = getConnection();
			
			String sql = "insert into tbl_vote_202005";
			sql += " values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, v_jumin);
			ps.setString(2, v_name);
			ps.setString(3, m_no);
			ps.setString(4, v_time);
			ps.setString(5, v_area);
			ps.setString(6, v_confirm);
			
			result = ps.executeUpdate();
			System.out.println(result);
			
			conn.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String check(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Vote> list = new ArrayList<Vote>();
		
		try {
			conn = getConnection();
			
			String sql = "select ";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
		return "check.jsp";
		
	}
	
}
