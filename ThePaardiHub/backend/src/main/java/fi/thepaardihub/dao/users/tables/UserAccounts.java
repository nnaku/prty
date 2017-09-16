package fi.thepaardihub.dao.users.tables;

import javax.persistence.*;

@Entity
@Table(name = "useraccounts")
public class UserAccounts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UserProfiles_ID", nullable = false)
	private int userProfilesId;
	@Column(name = "UserName", nullable = false)
	private String userName;
	@Column(name = "PasswordHash", nullable = false)
	private String passwordHash;
	@Column(name = "UserRoles_ID", nullable = false)
	private int userRolesId = 1;

	@OneToOne(optional = false)
	@JoinColumn(name = "UserProfiles_ID", insertable = false, updatable = false)
	private UserProfiles userProfiles;

	@ManyToOne(optional = false)
	@JoinColumn(name = "UserRoles_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private UserRoles userRoles;

	public UserAccounts() {}
	public UserAccounts(int userProfilesId, String userName, String passwordHash, int userRolesId) {
		super();
		this.userProfilesId = userProfilesId;
		this.userName = userName;
		this.passwordHash = passwordHash;
		this.userRolesId = userRolesId;
	}

	public UserAccounts(int userProfilesId, String userName, String passwordHash) {
		super();
		this.userProfilesId = userProfilesId;
		this.userName = userName;
		this.passwordHash = passwordHash;
	}

	public UserProfiles getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(UserProfiles userProfiles) {
		this.userProfiles = userProfiles;
	}

	public int getUserProfilesId() {
		return userProfilesId;
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

	public void setUserRolesId(int userRolesId) {
		this.userRolesId = userRolesId;
	}

	@Override
	public String toString() {
		return "UserAccounts [userProfilesId=" + userProfilesId + ", userName=" + userName + ", passwordHash="
				+ passwordHash + ", userRolesId=" + userRolesId + "]";
	}

}
