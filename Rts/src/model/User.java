package model;

public class User {

	InsulinPumpSystem insulinSystem;
	
	
	public User(InsulinPumpSystem insulinSystem) {
		
		this.insulinSystem = insulinSystem;
	}
	public User() {	
		
	}
	public void EndSystem() {
		System.exit(0);
	}
	

}
