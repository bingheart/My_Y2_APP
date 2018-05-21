package  entity;

import java.util.Date;

public class BackendUser {
	private Integer id;//涓婚敭id
	private String userCode;//鐢ㄦ埛缂栫爜锛堢櫥褰曡处鍙凤級
	private String userName;//鐢ㄦ埛鍚嶇О
	private String userPassword;//鐢ㄦ埛瀵嗙爜
	private Integer userType;//鐢ㄦ埛瑙掕壊绫诲瀷id
	private Integer createdBy;//鍒涘缓鑰�
	private Date creationDate;//鍒涘缓鏃堕棿
	private Integer modifyBy;//鏇存柊鑰�
	private Date modifyDate;//鏇存柊鏃堕棿
	private String userTypeName;//鐢ㄦ埛瑙掕壊绫诲瀷鍚嶇О
	
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
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
