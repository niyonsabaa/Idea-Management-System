package com.flyhub.ideamanagementsystem.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "access_pemissions")
public class Permissions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = Role.class, fetch = FetchType.LAZY)

	@JoinColumn(name = "role_id", nullable = false)

	@OnDelete(action = OnDeleteAction.CASCADE)
	private Role role;

	private String moduleName;
	private boolean canCreate;
	private boolean canDelete;
	private boolean canUpdate;
	private boolean canRead;
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public boolean isCanCreate() {
		return canCreate;
	}
	public void setCanCreate(boolean canCreate) {
		this.canCreate = canCreate;
	}
	public boolean isCanDelete() {
		return canDelete;
	}
	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}
	public boolean isCanUpdate() {
		return canUpdate;
	}
	public void setCanUpdate(boolean canUpdate) {
		this.canUpdate = canUpdate;
	}
	public boolean isCanRead() {
		return canRead;
	}
	public void setCanRead(boolean canRead) {
		this.canRead = canRead;
	}
	
	public Permissions() {		
	}
	public Permissions(Role role, String moduleName, boolean canCreate, boolean canDelete, boolean canUpdate,
			boolean canRead) {		
		this.role = role;
		this.moduleName = moduleName;
		this.canCreate = canCreate;
		this.canDelete = canDelete;
		this.canUpdate = canUpdate;
		this.canRead = canRead;
	}	
}
