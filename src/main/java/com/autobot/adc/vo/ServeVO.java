package com.autobot.adc.vo;

public class ServeVO {

	private String serveName;

	private String serveAddr;

	private String serveRemark;

	private String nameCreate;

	private String nameModified;

	public String getServeName() {
		return serveName;
	}

	public void setServeName(String serveName) {
		this.serveName = serveName == null ? null : serveName.trim();
	}

	public String getServeAddr() {
		return serveAddr;
	}

	public void setServeAddr(String serveAddr) {
		this.serveAddr = serveAddr == null ? null : serveAddr.trim();
	}

	public String getServeRemark() {
		return serveRemark;
	}

	public void setServeRemark(String serveRemark) {
		this.serveRemark = serveRemark == null ? null : serveRemark.trim();
	}

	public String getNameCreate() {
		return nameCreate;
	}

	public void setNameCreate(String nameCreate) {
		this.nameCreate = nameCreate == null ? null : nameCreate.trim();
	}

	public String getNameModified() {
		return nameModified;
	}

	public void setNameModified(String nameModified) {
		this.nameModified = nameModified == null ? null : nameModified.trim();
	}

}