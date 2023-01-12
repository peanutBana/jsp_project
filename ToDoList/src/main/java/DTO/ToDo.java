package DTO;

public class ToDo {
	private int todoId;
	private String todoTitle;
	private int userId;
	private String todoMemo;
	private String isFinished;
	
	public String getIsFinished() {
		return isFinished;
	}
	public void setIsFinished(String isFinished) {
		this.isFinished = isFinished;
	}
	public int getTodoId() {
		return todoId;
	}
	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}
	public String getTodoTitle() {
		return todoTitle;
	}
	public void setTodoTitle(String todoTitle) {
		this.todoTitle = todoTitle;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTodoMemo() {
		return todoMemo;
	}
	public void setTodoMemo(String todoMemo) {
		this.todoMemo = todoMemo;
	}
	
}
