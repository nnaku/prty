package fi.thepaardihub.dao.users.tables;

import java.util.Set;

import javax.persistence.*;




@Entity
@Table(name="userroles")
public class UserRoles {
	
	@Id
	@Column(name="ID")
	private int id;
	@Column(name="RoleName")
	private String roleName;
	
	@OneToMany(mappedBy="userRoles", targetEntity=UserAccounts.class,fetch=FetchType.LAZY)
	private Set<UserAccounts> userAccounts;
	
	public UserRoles() {}

	public UserRoles(int id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}

	public int getId() {
		return id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "UserRoles [id=" + id + ", roleName=" + roleName + ", userAccounts=" + userAccounts + "]";
	}
	
	
	

}
