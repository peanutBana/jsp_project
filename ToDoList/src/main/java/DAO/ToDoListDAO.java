package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	   
	   public ArrayList<User> readUserList() throws Exception{
		   ArrayList<User> userList = new ArrayList<>();
		   String sql = "select user_id, user_name from td_user";
		   
		   try(
				   Connection conn = open();
	    	       PreparedStatement pstmt = conn.prepareStatement(sql); // 쿼리문 등록 -> 컴파일
				   ResultSet rs = pstmt.executeQuery(); 
				   ){
			   while(rs.next()) {
				   User user = new User();
				   user.setUserId(rs.getInt(1));
				   user.setUserName(rs.getString(2));
				   userList.add(user);
			   }
			   
			   return userList;
		   }		   
	   }
	   
	   public ArrayList<ToDo> getList() throws Exception{
		   ArrayList<ToDo> toDoList = new ArrayList<>();
		   String sql = "select todo_id, todo_title, user_id, todo_memo ";
				  sql += "from todo where is_finished = 'n'";
		   
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
	   
	   public ToDo getView(int todoId) throws Exception{
		   ToDo td = new ToDo();
		   String sql = "select todo_id, todo_title, user_id, todo_memo, is_finished";
		   sql += " from todo";
		   sql += " where todo_id = ?";
		   
		   try (
			   Connection conn = open();
			   PreparedStatement pstmt = conn.prepareStatement(sql);			   
		   ){
			   pstmt.setInt(1, todoId);
			   ResultSet rs = pstmt.executeQuery();
			   while(rs.next()) {
				   td.setTodoId(rs.getInt(1));
				   td.setTodoTitle(rs.getString(2));
				   td.setUserId(rs.getInt(3));
				   td.setTodoMemo(rs.getString(4));
				   td.setIsFinished(rs.getString(5));
			   }
			   return td;
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
		   String sql = "INSERT INTO TODO (TODO_ID, TODO_TITLE, USER_ID, IS_FINISHED) VALUES(todo_seq.nextval, ?, ?, 'n')";
		   
		   try(
					Connection conn = open();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					){
				pstmt.setString(1,td.getTodoTitle());
				pstmt.setInt(2,td.getUserId());
				pstmt.executeUpdate();
			}
	   } 
	   
	   public void updateTodo(ToDo td) throws Exception{
		   String sql ="update todo set"; 
		   sql += " todo_title= ?, todo_memo= ? ,is_finished = ?";
		   sql += " where todo_id= ?";
			try (
				Connection conn = open();
				PreparedStatement pstmt = conn.prepareStatement(sql);
					) {
				pstmt.setString(1, td.getTodoTitle());
				pstmt.setString(2, td.getTodoMemo());
				pstmt.setString(3, td.getIsFinished());
				pstmt.setInt(4, td.getTodoId());
				pstmt.executeUpdate();
			}
	   }
	   
	   public void deleteTodo(int todoId) throws Exception{
		   String sql = "delete from todo where todo_id = ?";
		   
		   try (
					Connection conn = open();
					PreparedStatement pstmt = conn.prepareStatement(sql);
						) {
					pstmt.setInt(1, todoId);
					
					//수정된 글이 없을 경우
					if(pstmt.executeUpdate()!= 1) {
						throw new Exception("삭제된 글이 없습니다!");
					}
				}
		}
}
