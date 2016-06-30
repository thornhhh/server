package com.call110.business.entity;
import java.io.Serializable;
public class EmployeeRole implements Serializable {
	private static final long serialVersionUID = 1L;
    private Long eid;
    private Long rid;

    public Long getEid() {
        return eid;
    }
    public void setEid(Long eid) {
        this.eid = eid;
    }
    public Long getRid() {
        return rid;
    }
    public void setRid(Long rid) {
        this.rid = rid;
    }
}