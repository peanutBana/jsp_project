package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import DTO.ToDo;
import DTO.User;

public class ToDoListDAO {
	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	   


	   // DB와 연결 수행 메소드
	   public Connection open() {
			Connection conn = null;
			try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(JDBC_URL, "todo", "todo1234");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return conn; //데이터 베이스의 연결 객체를 리턴
		}
	   
	   public ArrayList<ToDo> getList() throws Exception{
		   ArrayList<ToDo> toDoList = new ArrayList<>();
		   String sql = "select todo_id, todo_title, user_id, todo_memo from todo where is_finished = 'n'";
		   
		   try(
				   Connection conn = open();
	    	       PreparedStatement pstmt = conn.prepareStatement(sql); // 쿼리문 등록 -> 컴파일
				   ResultSet rs = pstmt.executeQuery();  
		   ){
			   while(rs.next()) {
				   ToDo td = new ToDo();
				   td.setTodoId(rs.getInt(1));
				   td.setTodoTitle(rs.getString(2));
				   td.setUserId(rs.getInt(3));
				   td.setTodoMemo(rs.getString(4));
				   
				   toDoList.add(td);
			   }
			   return toDoList;
		   }
	   }
	   
	   public ArrayList<ToDo> getListFin() throws Exception{
		   ArrayList<ToDo> toDoList = new ArrayList<>();
		   String sql = "select todo_id, todo_title, user_id, todo_memo from todo where is_finished = 'y'";
		   
		   try(
				   Connection conn = open();
	    	       PreparedStatement pstmt = conn.prepareStatement(sql); // 쿼리문 등록 -> 컴파일
				   ResultSet rs = pstmt.executeQuery();  
		   ){
			   while(rs.next()) {
				   ToDo td = new ToDo();
				   td.setTodoId(rs.getInt(1));
				   td.setTodoTitle(rs.getString(2));
				   td.setUserId(rs.getInt(3));
				   td.setTodoMemo(rs.getString(4));
				   
				   toDoList.add(td);
			   }
			   return toDoList;
		   }
	   }
	   
	   public void insertTodo(ToDo td) throws Exception{
		   String sql = "INSERT INTO TODO (TODO_TITLE, IS_FINISHED) VALUES(?, 'n')";
		   
		   try(
					Connection conn = open();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					){
				pstmt.setString(1,td.getTodoTitle());
				pstmt.executeUpdate();
			}
	   }
}
