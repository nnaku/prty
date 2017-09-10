package fi.thepaardihub.dao.users.tables;

import javax.persistence.*;

@Entity
@Table(name = "userprofiles")
public class UserProfiles {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private int id;
	@Column(name = "FirstName", nullable = false)
	private String firstName;
	@Column(name = "LastName", nullable = false)
	private String lastName;
	@Column(name = "NickName", nullable = false)
	private String nickName;
	@Column(name = "Email", nullable = false)
	private String email;

	@OneToOne(optional = false, cascade = CascadeType.ALL, mappedBy = "userProfiles", targetEntity = UserAccounts.class)
	private UserAccounts userAccounts;

	public UserProfiles() {}
	public UserProfiles(int id, String firstName, String lastName, String nickName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.email = email;
	}

	public UserAccounts getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(UserAccounts userAccounts) {
		this.userAccounts = userAccounts;
	}

	public int getId() {
		return id;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserProfiles [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", nickName="
				+ nickName + ", email=" + email + "]";
	}

}
