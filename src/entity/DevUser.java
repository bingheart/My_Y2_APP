package entity;

import java.util.Date;

public class DevUser {
	private Integer id;//涓婚敭id
	private String devCode;//寮�鍙戣�呭笎鍙�(绯荤粺鐧诲綍璐﹀彿)
	private String devName;//寮�鍙戣�呭悕绉�
	private String devPassword;//寮�鍙戣�呭瘑鐮�
	private String devEmail;//寮�鍙戣�呴偖绠�
	private String devInfo;	//寮�鍙戣�呯畝浠�
	private Integer createdBy;//鍒涘缓鑰�
	private Date creationDate;//鍒涘缓鏃堕棿
	private Integer modifyBy;//鏇存柊鑰�
	private Date modifyDate;//鏇存柊鏃堕棿
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDevCode() {
		return devCode;
	}
	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}
	public String getDevName() {
		return devName;
	}
	public void setDevName(String devName) {
		this.devName = devName;
	}
	public String getDevPassword() {
		return devPassword;
	}
	public void setDevPassword(String devPassword) {
		this.devPassword = devPassword;
	}
	public String getDevEmail() {
		return devEmail;
	}
	public void setDevEmail(String devEmail) {
		this.devEmail = devEmail;
	}
	public String getDevInfo() {
		return devInfo;
	}
	public void setDevInfo(String devInfo) {
		this.devInfo = devInfo;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
