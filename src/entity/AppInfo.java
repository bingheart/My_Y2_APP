package entity;

import java.math.BigDecimal;
import java.util.Date;

public class AppInfo {
	private Integer id;//涓婚敭id
	private String softwareName;//杞欢鍚嶇О
	private String APKName;//APK鍚嶇О
	private String supportROM;//鏀寔ROM
	private String interfaceLanguage;//鐣岄潰璇█
	private Date updateDate;//鏇存柊鏃ユ湡
	private BigDecimal softwareSize;//杞欢澶у皬锛堝崟浣嶏細M锛�
	private Integer devId;//寮�鍙戣�卛d
	private String appInfo;//搴旂敤绠�浠�
	private Integer status;//app鐘舵�乮d
	private Date onSaleDate;//涓婃灦鏃堕棿
	private Date offSaleDate;//涓嬫灦鏃堕棿
	private Integer categoryLevel3;//鎵�灞炰笁绾у垎绫籭d
	private Integer downloads;//涓嬭浇閲忥紙鍗曚綅锛氭锛�
	private Integer flatformId;//app鎵�灞炲钩鍙癷d
	private Integer createdBy;//鍒涘缓鑰�
	private Date creationDate;//鍒涘缓鏃堕棿
	private Integer modifyBy;//鏇存柊鑰�
	private Date modifyDate;//鏇存柊鏃堕棿
	
	private String statusName;//app鐘舵�佸悕绉�
	private String flatformName;//app鎵�灞炲钩鍙板悕绉�
	private String categoryLevel3Name;//鎵�灞炰笁绾у垎绫诲悕绉�
	private String devName;//寮�鍙戣�呭悕绉�
	
	private Integer categoryLevel1;//鎵�灞炰竴绾у垎绫籭d
	private Integer categoryLevel2;//鎵�灞炰簩绾у垎绫籭d
	
	private String categoryLevel1Name;//鎵�灞炰竴绾у垎绫诲悕绉�
	private String categoryLevel2Name;//鎵�灞炰簩绾у垎绫诲悕绉�
	
	private String logoPicPath;//LOGO鍥剧墖URL璺緞
	private String logoLocPath;//LOGO鍥剧墖鐨勬湇鍔″櫒瀛樺偍璺緞
	
	private Integer versionId;//鏈�鏂扮殑鐗堟湰id
	private String versionNo;//鏈�鏂扮殑鐗堟湰鍙�
		
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public Integer getVersionId() {
		return versionId;
	}
	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}
	public String getLogoLocPath() {
		return logoLocPath;
	}
	public void setLogoLocPath(String logoLocPath) {
		this.logoLocPath = logoLocPath;
	}
	public String getLogoPicPath() {
		return logoPicPath;
	}
	public void setLogoPicPath(String logoPicPath) {
		this.logoPicPath = logoPicPath;
	}
	public Integer getCategoryLevel3() {
		return categoryLevel3;
	}
	public void setCategoryLevel3(Integer categoryLevel3) {
		this.categoryLevel3 = categoryLevel3;
	}
	public String getCategoryLevel3Name() {
		return categoryLevel3Name;
	}
	public void setCategoryLevel3Name(String categoryLevel3Name) {
		this.categoryLevel3Name = categoryLevel3Name;
	}
	public String getCategoryLevel1Name() {
		return categoryLevel1Name;
	}
	public void setCategoryLevel1Name(String categoryLevel1Name) {
		this.categoryLevel1Name = categoryLevel1Name;
	}
	public String getCategoryLevel2Name() {
		return categoryLevel2Name;
	}
	public void setCategoryLevel2Name(String categoryLevel2Name) {
		this.categoryLevel2Name = categoryLevel2Name;
	}
	public Integer getCategoryLevel1() {
		return categoryLevel1;
	}
	public void setCategoryLevel1(Integer categoryLevel1) {
		this.categoryLevel1 = categoryLevel1;
	}
	public Integer getCategoryLevel2() {
		return categoryLevel2;
	}
	public void setCategoryLevel2(Integer categoryLevel2) {
		this.categoryLevel2 = categoryLevel2;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getFlatformName() {
		return flatformName;
	}
	public void setFlatformName(String flatformName) {
		this.flatformName = flatformName;
	}
	
	public String getDevName() {
		return devName;
	}
	public void setDevName(String devName) {
		this.devName = devName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSoftwareName() {
		return softwareName;
	}
	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}
	public String getAPKName() {
		return APKName;
	}
	public void setAPKName(String aPKName) {
		APKName = aPKName;
	}
	public String getSupportROM() {
		return supportROM;
	}
	public void setSupportROM(String supportROM) {
		this.supportROM = supportROM;
	}
	public String getInterfaceLanguage() {
		return interfaceLanguage;
	}
	public void setInterfaceLanguage(String interfaceLanguage) {
		this.interfaceLanguage = interfaceLanguage;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public BigDecimal getSoftwareSize() {
		return softwareSize;
	}
	public void setSoftwareSize(BigDecimal softwareSize) {
		this.softwareSize = softwareSize;
	}
	public Integer getDevId() {
		return devId;
	}
	public void setDevId(Integer devId) {
		this.devId = devId;
	}
	public String getAppInfo() {
		return appInfo;
	}
	public void setAppInfo(String appInfo) {
		this.appInfo = appInfo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getOnSaleDate() {
		return onSaleDate;
	}
	public void setOnSaleDate(Date onSaleDate) {
		this.onSaleDate = onSaleDate;
	}
	public Date getOffSaleDate() {
		return offSaleDate;
	}
	public void setOffSaleDate(Date offSaleDate) {
		this.offSaleDate = offSaleDate;
	}
	
	public Integer getDownloads() {
		return downloads;
	}
	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
	}
	public Integer getFlatformId() {
		return flatformId;
	}
	public void setFlatformId(Integer flatformId) {
		this.flatformId = flatformId;
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
