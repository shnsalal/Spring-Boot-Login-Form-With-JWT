package com.messaging.main.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;


@Entity
public class User {
	@Id
//	@GeneratedValue(generator = "UUID")
	private Integer id;
	
	@NotNull
	@Email(message = "Please provide a valid email")
	@NotEmpty(message = "Please provide an email")
	@Size(min=5, message = "Username should be greater than 5")
	@Column(unique = true)
	private String username;
	
	@NotNull
	@Size(min=5, message = "Password should be greater than 5")
    @NotEmpty(message = "Please provide a password")
    private String password;
	
	private boolean active;
	
	@JsonIgnoreProperties("users")
	@ManyToMany(fetch = FetchType.EAGER,
			cascade = {CascadeType.DETACH, CascadeType.MERGE,
					CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
			)
	private Set<Role> roles; 
	
	public void setId(int id) {
		this.id =  id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
