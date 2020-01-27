package mainApp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"user"
})
@XmlRootElement(name = "credential")
public class Credential {
	
	@XmlElement(required = true)
	protected List<Credential.User> user;
	
    public List<Credential.User> getUser() {
        if (user == null) {
        	user = new ArrayList<Credential.User>();
        }
        return this.user;
    }
    
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = {
		"userID",
	    "login",
	    "password",
	    "mail",
	    "disable",
	    "role",
	    "credentialId"
	})
	public static class User {
		@XmlElement(required = true)
		public int userID;
		@XmlElement(required = true)
		public String login;
		@XmlElement(required = true)
		public String password;
		@XmlElement(required = true)
		public String mail;
		@XmlElement(required = true)
		public boolean disable;
		@XmlElement(required = true)
		public Credential.User.Role role;
		@XmlElement(required = true)
		public int credentialId;
		
		public User()
		{
			
		}
		
		public User(int userID, String login, String password, String mail, boolean disable, Role role) 
		{
			this.userID = userID;
			this.login = login;
			this.password = password;
			this.mail = mail;
			this.disable = disable;
			this.role = role;
		}
		
		public User(String login, String password, String mail, boolean disable, Role role)
		{
			this.login = login;
			this.password = password;
			this.mail = mail;
			this.disable = disable;
			this.role = role;
		}
		
		@XmlAccessorType(XmlAccessType.FIELD)
	    @XmlType(name = "", propOrder = {
	    	"roleID",
	        "code",
	        "description",
	        "right"
	    })
		public static class Role{
			@XmlElement(required = true)
			public int roleID;
			@XmlElement(required = true)
			public String code;
			@XmlElement(required = true)
			public String description;
			@XmlElement(required = true)
			public Credential.User.Role.Right right;
			
			public Role() {}
			
			public Role(int roleID, String code, String description, Right right) {
				this.roleID = roleID;
				this.code = code;
				this.description = description;
				this.right = right;
			}
			
			public Role(String code, String description, Right right) {
				this.code = code;
				this.description = description;
				this.right = right;
			}
			
			@XmlAccessorType(XmlAccessType.FIELD)
		    @XmlType(name = "", propOrder = {
		    	"rightId",
		    	"edit",
		        "read",
		        "create",
		        "delete",
		        "export",
		        "save"
		    })
			public static class Right{
				@XmlElement(required = true)
				public int rightId;
				@XmlElement(required = true)
				public boolean edit;
				@XmlElement(required = true)
				public boolean read;
				@XmlElement(required = true)
				public boolean create;
				@XmlElement(required = true)
				public boolean delete;
				@XmlElement(required = true)
				public boolean export;
				@XmlElement(required = true)
				public boolean save;
				
				public Right() {}
				
				public Right(boolean edit, boolean read, boolean create, boolean delete, boolean export, boolean save) {
					this.edit = edit;
					this.read = read;
					this.create = create;
					this.delete = delete;
					this.save = save;
				}
			}
		}

	}
}
