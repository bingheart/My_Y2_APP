package service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import dao.UserMapper;
import entity.AppCategory;
import entity.AppInfo;
import entity.AppVersion;
import entity.DataDictionary;
import entity.DevUser;
import entity.InquiryParameter;

@Service("userService")
public class Dev_userService {
	
	@Resource(name="userMapper")
	private UserMapper dev_userMapper;
	public DevUser userLogin(String devCode,String devPassword) {
		
		return dev_userMapper.userLogin(devCode, devPassword);
		
	}
	
	public List<AppCategory> appCategoryList() {
		return dev_userMapper.appCategoryList();
	}
	
	public List<DataDictionary> dataDictionaryList(){
		return dev_userMapper.dataDictionaryList();
	}
	
	
	public List<AppInfo> querySoftwareName(InquiryParameter inquiryParameter){
		
		List<AppInfo> list=dev_userMapper.querySoftwareName(inquiryParameter);
		
		for (AppInfo appInfo : list) {
			
			appInfo.setStatusName(dev_userMapper.statusName(appInfo.getStatus()));
			appInfo.setVersionNo(dev_userMapper.versionNoName(appInfo.getId()));
			appInfo.setCategoryLevel1Name(dev_userMapper.categoryLevel1(appInfo.getCategoryLevel1()));
			appInfo.setCategoryLevel2Name(dev_userMapper.categoryLevel1(appInfo.getCategoryLevel2()));
			appInfo.setCategoryLevel3Name(dev_userMapper.categoryLevel1(appInfo.getCategoryLevel3()));
		}
		
		return list;

	}
	
	
	public List<AppInfo> appInfoList(Integer devid,Integer currentPageNo){
		
		List<AppInfo> list=dev_userMapper.appInfoList(devid, currentPageNo);
		
		
		for (AppInfo appInfo : list) {
			
			appInfo.setStatusName(dev_userMapper.statusName(appInfo.getStatus()));
			appInfo.setVersionNo(dev_userMapper.versionNoName(appInfo.getId()));
			appInfo.setCategoryLevel1Name(dev_userMapper.categoryLevel1(appInfo.getCategoryLevel1()));
			appInfo.setCategoryLevel2Name(dev_userMapper.categoryLevel1(appInfo.getCategoryLevel2()));
			appInfo.setCategoryLevel3Name(dev_userMapper.categoryLevel1(appInfo.getCategoryLevel3()));
		}
		
		return list;
	}
	
	public List<AppCategory> queryCategoryLevel2(){
		return dev_userMapper.queryCategoryLevel2();
	}
	
	public List<AppCategory> queryCategoryLevel3(){
		return dev_userMapper.queryCategoryLevel3();
	}
	
	public List<AppCategory> queryCategoryLevelID2(Integer pid){
		return dev_userMapper.queryCategoryLevelID2(pid);
	}
	
	public int count(Integer id) {
		return dev_userMapper.count(id);
	}

	public List<AppVersion> appVersion(Integer id) {
		
		List<AppVersion> list = dev_userMapper.appversion(id);
	
		for (AppVersion appVersion : list) {
			appVersion.setPublishStatusName(dev_userMapper.statusName(appVersion.getStatus()));
		}
		
		return list;
	}
	
	public int myCount(InquiryParameter inquiryParameter) {
		
		return dev_userMapper.myCount(inquiryParameter);
	}
	
	
	public int delapp(String id) {
		dev_userMapper.deleteAppinfodb(id);
		return dev_userMapper.delapp(id);
	}
	
	public int addAppVersion(AppVersion appVersion) {
		return dev_userMapper.addAppVersion(appVersion);
	}
	
	public AppVersion SelectappVersionID(Integer appId,String versionNo) {
		
		
		return dev_userMapper.SelectappVersionID(appId, versionNo);
	}
	
	public int UpdateVersionId(Integer versionId,Integer id,String supportROM) {
		
		return dev_userMapper.UpdateVersionId(versionId, id,supportROM);
	}
	
	public List<DataDictionary> datadictionarylist(){
		return dev_userMapper.datadictionarylist();
	}
	
	public int InsertAppInfo(AppInfo appInfo) {
		return dev_userMapper.InsertAppInfo(appInfo);
	}
	
	public AppVersion SelectVersion(Integer id) {
		return dev_userMapper.SelectVersion(id);
	}
	
	public int  updateAppversion(AppVersion appVersion) {
		return dev_userMapper.updateAppversion(appVersion);
	}
	
	public int appInfoID(AppInfo appInfo) {
		return dev_userMapper.appInfoID(appInfo);
	}
	
	public int updateAppId(Integer appId,Integer id) {
		return dev_userMapper.updateAppId(appId, id);
	}
	public AppInfo  selectAppInfoList(int id){
		return dev_userMapper.selectAppInfoList(id);
	}
	
	public int updateAppInfodb(AppInfo appInfo) {
		return dev_userMapper.updateAppInfodb(appInfo);
	}
	
	public AppInfo SelectAppInfo(int id) {
		
		AppInfo appInfo = dev_userMapper.SelectAppInfo(id);
		appInfo.setStatusName(dev_userMapper.statusName(appInfo.getStatus()));
		appInfo.setVersionNo(dev_userMapper.versionNoName(appInfo.getId()));
		appInfo.setCategoryLevel1Name(dev_userMapper.categoryLevel1(appInfo.getCategoryLevel1()));
		appInfo.setCategoryLevel2Name(dev_userMapper.categoryLevel1(appInfo.getCategoryLevel2()));
		appInfo.setCategoryLevel3Name(dev_userMapper.categoryLevel1(appInfo.getCategoryLevel3()));
		return appInfo;
	}
	
	public int OnTheShelf(AppInfo appInfo) {
		return dev_userMapper.OnTheShelf(appInfo);
	}
	public int Dismount(AppInfo appInfo) {
		return dev_userMapper.Dismount(appInfo);
	}
	
	public int delfile(Integer id,String logoPicPath) {
		return dev_userMapper.delfile(id,logoPicPath);
	}
	public int delFile(@Param("id") Integer id,@Param("apkLocPath") String apkLocPath) {
		return dev_userMapper.delFile(id, apkLocPath);
	}
}
