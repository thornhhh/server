package com.call110.business.entity;
import java.io.Serializable;
public class RolePermission extends BaseEntity  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long pid;
    private Long rid;

    public Long getPid() {
        return pid;
    }
    public void setPid(Long pid) {
        this.pid = pid;
    }
    public Long getRid() {
        return rid;
    }
    public void setRid(Long rid) {
        this.rid = rid;
    }
}