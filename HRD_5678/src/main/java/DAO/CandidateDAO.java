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
			
			String sql = "SELECT V_NAME, SUBSTR(V_JUMIN,1,2)+1900||'년'||SUBSTR(V_JUMIN,3,2)||'월'||SUBSTR(V_JUMIN,5,2)||'일생' 생년월일, '만 '||TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), TO_DATE(19||SUBSTR(V_JUMIN,1,6),'YYYYMMDD')) / 12)||'세' 나이, DECODE(SUBSTR(V_JUMIN, 7, 1), '1', '남자', '2', '여자', '3', '남자', '4', '여자', '5', '남자', '6', '여자') 성별, M_NO 후보번호 ,SUBSTR(V_TIME, 1, 2)||':'||SUBSTR(V_TIME, 3, 2) 투표시간, DECODE(V_CONFIRM, 'N', '미확인', 'Y', '확인') 유권자확인 ";
			sql += "FROM TBL_VOTE_202005 ";
			sql += "WHERE V_AREA ='제1투표장'";
			
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Vote vote = new Vote();
				vote.setV_name(rs.getString(1));
				vote.setV_birth(rs.getString(2));
				vote.setV_age(rs.getString(3));
				vote.setV_sex(rs.getString(4));
				vote.setM_no(rs.getString(5));
				vote.setV_time(rs.getString(6));
				vote.setV_confirm(rs.getString(7));
				
				list.add(vote);
			}
			
			request.setAttribute("list", list);
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "check.jsp";
	}
	
	public String rank(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Candidate> list = new ArrayList<Candidate>();
		
		try {
			conn = getConnection();
			
			String sql = "select m.m_no 후보번호, m.m_name 성명, count(v.m_no) 총투표건수 ";
			sql += "from tbl_member_202005 m join tbl_vote_202005 v ";
			sql += "on m.m_no = v.m_no and v.v_confirm = 'Y' ";
			sql += "group by (m.m_no, m.m_name, v.m_no) ";
			sql += "order by count(v.m_no) desc";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Candidate candidate = new Candidate();
				candidate.setM_no(rs.getString(1));
				candidate.setM_name(rs.getString(2));
				candidate.setCount(rs.getString(3));
				
				list.add(candidate);
			}
			
			request.setAttribute("list", list);
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "rank.jsp";
	}
}
