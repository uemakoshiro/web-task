package entity;

public class User {
	private String loginId;
	private String name;
	private String role;
	
	public User() {}
	
	public User(String id, String name, String role) {
		this.loginId = id;
		this.name = name;
		this.role = role;
	}
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}