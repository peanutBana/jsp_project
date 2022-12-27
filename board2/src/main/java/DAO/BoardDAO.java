package DAO;

import java.sql.*;
import java.util.*;

import DTO.Board;

public class BoardDAO {
   final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
   final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";

   // DB와 연결 수행 메소드
   public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "test", "test1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn; //데이터 베이스의 연결 객체를 리턴
	}

   
   // 게시판 리스트 가져오기
   public ArrayList<Board> getList() throws Exception {
	   ArrayList<Board> boardList = new ArrayList<>(); // 데이터 저장할 배열
	   String sql = "select board_no, title, user_id, to_char(reg_date, 'yyyy.mm.dd') reg_date, views from board";
      // 리소스 자동 닫기(try-with-resource)
      // try 괄호안에 변수명을 적으면 알아서 적용이 된다
      try(
				Connection conn = open();
    	        PreparedStatement pstmt = conn.prepareStatement(sql); // 쿼리문 등록 -> 컴파일
				ResultSet rs = pstmt.executeQuery();	     // 쿼리문 실행 -> 데이터 베이스 결과 저장
		) {
         while (rs.next()) {
            Board b = new Board();
            b.setBoard_no(rs.getInt(1));
            b.setTitle(rs.getString(2));
            b.setUser_id(rs.getString(3));
            b.setReg_date(rs.getString(4));
            b.setViews(rs.getInt(5));
            
            boardList.add(b);
         }
         //메소드 타입 자체가 List이기 때문에 리턴도 List타입으로 해준다
         return boardList;
      }
   }

	public Board getView(int board_no) throws Exception {
		Board b = new Board();
		String sql = "select board_no, title, user_id, to_char(reg_date, 'yyyy.mm.dd') reg_date, views, content from board where board_no = ?";
		
		try(
				Connection conn = open();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
			pstmt.setInt(1, board_no);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				b.setBoard_no(rs.getInt(1));
				b.setTitle(rs.getString(2));
				b.setUser_id(rs.getString(3));
				b.setReg_date(rs.getString(4));
				b.setViews(rs.getInt(5));
				b.setContent(rs.getString(6));
			}
			
			return b;
		}
	}
	
   
  //조회수 증가
	public void updateViews(int board_no) throws Exception {
		String sql = "update board set views = (views + 1) where board_no = ?";
		
		try (
			Connection conn = open();
			PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
			pstmt.setInt(1, board_no);
			pstmt.executeUpdate();
		}
	}
	
	public void insertBoard(Board b) throws Exception {
		String sql = "INSERT INTO BOARD (BOARD_NO, USER_ID, TITLE, CONTENT, REG_DATE, VIEWS)\n"
				+ "VALUES(BOARD_SEQ.nextval, ?, ?, ?, sysdate, 0)";
		
		try(
				Connection conn = open();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setString(1,b.getUser_id());
			pstmt.setString(2,b.getTitle());
			pstmt.setString(3, b.getContent());
			pstmt.executeUpdate();
		}
		
	}
	
	public Board getViewForEdit(int board_no) throws Exception {
		Board b = new Board();
		String sql = "select board_no, title, user_id, to_char(reg_date, 'yyyy.mm.dd') reg_date, views, content from board where board_no = ?";
		
		try(
				Connection conn = open();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
			pstmt.setInt(1, board_no);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				b.setBoard_no(rs.getInt(1));
				b.setTitle(rs.getString(2));
				b.setUser_id(rs.getString(3));
				b.setReg_date(rs.getString(4));
				b.setViews(rs.getInt(5));
				b.setContent(rs.getString(6));
			}
			return b;
		}
	}
	//게시판 글 수정
	public void updateBoard(Board  b) throws Exception {
		String sql = "update board set title = ?, user_id = ?, content = ? where board_no = ?";
		
		try (
			Connection conn = open();
			PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getUser_id());
			pstmt.setString(3, b.getContent());
			pstmt.setInt(4, b.getBoard_no());
			pstmt.executeUpdate();
			
			//수정된 글이 없을 경우
			if(pstmt.executeUpdate()!= 1) {
				throw new Exception("DB에러");
			}
		}
	}
	//게시글 삭제
	public void deleteBoard(int board_no) throws Exception{
		String sql = "delete from board where board_no = ?";

		try (
				Connection conn = open();
				PreparedStatement pstmt = conn.prepareStatement(sql);
					) {
				pstmt.setInt(1, board_no);
				
				
				//수정된 글이 없을 경우
				if(pstmt.executeUpdate()!= 1) {
					throw new Exception("삭제된 글이 없습니다!");
				}
			}
		
	}
}