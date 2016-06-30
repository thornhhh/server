package com.call110.business.vo;

public class UserVO {
	String 
		uid,
		name,//真实姓名
		nickname,//昵称
		phone,//手机号
		rid,//简历ID
		rname,
		appearanceRate,//露面率
		aboutRate,//相关率
		reportCount,//被举报数
		isDel
	;
	

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getName() {
		return name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getAppearanceRate() {
		return appearanceRate;
	}

	public void setAppearanceRate(String appearanceRate) {
		this.appearanceRate = appearanceRate;
	}

	public String getAboutRate() {
		return aboutRate;
	}

	public void setAboutRate(String aboutRate) {
		this.aboutRate = aboutRate;
	}

	public String getReportCount() {
		return reportCount;
	}

	public void setReportCount(String reportCount) {
		this.reportCount = reportCount;
	}
}
