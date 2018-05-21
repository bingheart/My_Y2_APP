package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;

import entity.AppCategory;
import entity.AppInfo;
import entity.AppVersion;
import entity.DataDictionary;
import entity.DevUser;
import entity.InquiryParameter;


@Controller("userMapper")
public interface UserMapper {

	public DevUser userLogin(@Param("userName") String userName,@Param("userPassword")String userPassword);
	
	public List<AppCategory>  appCategoryList(); 
	
	public List<DataDictionary> dataDictionaryList();
	
	public List<AppInfo> appInfoList(@Param("devId") Integer devid,@Param("currentPageNo") Integer currentPageNo);
	
	public List<AppCategory> queryCategoryLevel2();
	
	public List<AppCategory> queryCategoryLevel3();
	
	public List<AppCategory> queryCategoryLevelID2(@Param("pid") Integer pid);
	
	public String statusName(@Param("status") Integer status);
	
	public String versionNoName(@Param("id") Integer id);
	
	public String categoryLevel1(@Param("categoryLevel1") Integer categoryLevel1);
	
	public Integer count(@Param("id") Integer id);
	
	public List<AppVersion> appversion(@Param("appId") Integer id);
	
	public List<AppInfo> querySoftwareName(InquiryParameter inquiryParameter);
	
	public int myCount(InquiryParameter inquiryParameter);
	
	public int delapp(@Param("Id")String id);
	
	public int deleteAppinfodb(@Param("appId")String id);
	
	public int addAppVersion(AppVersion appVersion);
	
	public AppVersion SelectappVersionID(@Param("appId") Integer appId,@Param("versionNo")String versionNo);
	
	public int UpdateVersionId(@Param("versionId") Integer versionId,@Param("id") Integer id,@Param("supportROM")String supportROM);

	public List<DataDictionary> datadictionarylist();
	
	public int InsertAppInfo(AppInfo appInfo);
	
	public AppVersion SelectVersion(@Param("id")Integer id);
	
	public int  updateAppversion(AppVersion appVersion);
	
	public int appInfoID(AppInfo appInfo);
	
	public int updateAppId(@Param("appId") Integer appId,@Param("id") Integer id);
	
	public AppInfo selectAppInfoList(int id);
	
	public int updateAppInfodb(AppInfo appInfo);
	
	public AppInfo SelectAppInfo(int id);
	
	public int OnTheShelf(AppInfo appInfo);
	
	public int Dismount(AppInfo appInfo);
	
	public int delfile(@Param("id") Integer id,@Param("logoPicPath") String logoPicPath);
	
	public int delFile(@Param("id") Integer id,@Param("downloadLink") String apkLocPath);
}