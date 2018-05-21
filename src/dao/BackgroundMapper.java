package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;

import entity.AppCategory;
import entity.AppInfo;
import entity.AppVersion;
import entity.BackendUser;
import entity.DataDictionary;
import entity.InquiryParameter;

@Controller("devbackgroundMapper")
public interface BackgroundMapper {
	
	public BackendUser backendLogin(@Param("userName") String userName,@Param("userPassword")String userPassword);

	public List<AppCategory> appCategoryList(@Param("parentId") Integer parentId);
	
	public List<DataDictionary> selectTypeName(@Param("typeName") String typeName);
	
	public List<AppInfo>appInfoList(@Param("currentPageNo") Integer currentPageNo);
	
	public String statusName(@Param("status") Integer status);
	
	public String versionNoName(@Param("id") Integer id);
	
	public String categoryLevel1(@Param("categoryLevel1") Integer categoryLevel1);
	
	public int count();
	
	public List<AppInfo> querySoftwareName(InquiryParameter inquiryParameter);
	
	public int myCount(InquiryParameter inquiryParameter);
	
	public AppInfo selectAid(@Param("aid") Integer aid);
	
	public AppVersion selectVid(@Param("vid") Integer vid);
	
	public int updateStatus(@Param("status") Integer status,@Param("id") Integer id);
	
	public int updatePublishStatus(@Param("publishStatus") Integer status,@Param("id") Integer id);
}
