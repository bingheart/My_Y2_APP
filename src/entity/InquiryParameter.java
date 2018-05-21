package entity;

public class InquiryParameter {
	
	private String softwareName;//获取名字
	
	private Integer status;//获取状态
	
	private Integer devId;
	
	private Integer flatformId;//获取平台
	
	private Integer categoryLevel1;//获取一级菜单
	
	private Integer categoryLevel2;//获取二级菜单
	
	private Integer categoryLevel3;//获取三级菜单

	private Integer currentPageNo;
	

	public Integer getDevId() {
		return devId;
	}

	public void setDevId(Integer devId) {
		this.devId = devId;
	}



	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getCategoryLevel3() {
		return categoryLevel3;
	}

	public void setCategoryLevel3(Integer categoryLevel3) {
		this.categoryLevel3 = categoryLevel3;
	}

	public Integer getFlatformId() {
		return flatformId;
	}

	public void setFlatformId(Integer integer) {
		this.flatformId = integer;
	}

	public Integer getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(Integer currentPageNo) {
		this.currentPageNo = currentPageNo;
	}




}
