package logical;

public class Usuario {
	private String userName;
	private String passcode;
	
	public Usuario(String userName, String passcode) {
		super();
		this.userName = userName;
		this.passcode = passcode;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasscode() {
		return passcode;
	}
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}
	
	
}
