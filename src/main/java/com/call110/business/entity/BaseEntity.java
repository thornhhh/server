package com.call110.business.entity;

import javax.persistence.Transient;

import com.call110.common.pageable.Page;

public class BaseEntity {
	@Transient
	private Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
