package com.call110.business.entity;
import java.io.Serializable;
import java.util.List;
public class Role extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
    private String name;
    private List<Permission> permissions;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}