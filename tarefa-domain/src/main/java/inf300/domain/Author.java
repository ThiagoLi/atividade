package inf300.domain;

import java.util.Date;

public class Author {
	private String fname;
	private String mname;
	private String lname;
	private Date birthdate;
	private String bio;
    
    
	public Author(String fname, String mname, String lname, Date birthdate, String bio) {
		super();
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.birthdate = birthdate;
		this.bio = bio;
	}
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
    
    
    
}
