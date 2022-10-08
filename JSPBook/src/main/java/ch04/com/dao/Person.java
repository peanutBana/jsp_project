package ch04.com.dao;

public class Person {
	private int id = 20181004;
	private String name = "홍길순";
	
	public Person() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {		// return 하지 않는 메소드는 void로 정의
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
