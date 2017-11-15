package fi.thepaardihub.dao.users.tables;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

@Entity
@Table(name = "useraccounts")
public class UserAccounts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "UserName", nullable = false)
	private String userName;
	@Column(name = "PasswordHash", nullable = false)
	private String passwordHash;
	@Column(name = "FirstName")
	private String firstName;
	@Column(name = "LastName")
	private String lastName;
	@Id
	@Column(name = "Email", nullable = false)
	private String email;
	@Column(name = "UserRoles_ID", nullable = false)
	private int userRolesId = 1;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "UserRoles_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private UserRoles userRoles;

	public UserAccounts() {
	}

	public UserAccounts(String userName, String passwordHash, String firstName, String lastName, String email) {
		super();
		this.userName = userName;
		this.passwordHash = passwordHash;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public int getUserRolesId() {
		return userRolesId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRoles getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(UserRoles userRoles) {
		this.userRoles = userRoles;
	}

	public void setUserRolesId(int userRolesId) {
		this.userRolesId = userRolesId;
	}

	public Map <String,Object> toMap() {
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("username", this.userName);
		jsonMap.put("firstname", this.firstName);
		jsonMap.put("lastname", this.lastName);
		jsonMap.put("email", this.email);
		return jsonMap;
	}
}
