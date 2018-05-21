package service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.BackgroundMapper;
import entity.AppCategory;
import entity.AppInfo;
import entity.AppVersion;
import entity.BackendUser;
import entity.DataDictionary;
import entity.InquiryParameter;

@Service("backgroundService")

public class BackgroundService {

	@Autowired
	BackgroundMapper dev_backgroundMapper;
	
	public BackendUser backendLogin( String userName,String userPassword) {
		return  dev_backgroundMapper.backendLogin(userName, userPassword);
	}
	
	public List<AppCategory> appCategoryList(Integer parentId) {
		return dev_backgroundMapper.appCategoryList(parentId);
	}
	
	public List<DataDictionary> selectTypeName( String typeName){
		return dev_backgroundMapper.selectTypeName(typeName);
	}
	
	public List<AppInfo>appInfoList( Integer currentPageNo){
		
		List<AppInfo> list=dev_backgroundMapper.appInfoList(currentPageNo);
		
		
		for (AppInfo appInfo : list) {
			
			appInfo.setStatusName(dev_backgroundMapper.statusName(appInfo.getStatus()));
			appInfo.setVersionNo(dev_backgroundMapper.versionNoName(appInfo.getId()));
			appInfo.setCategoryLevel1Name(dev_backgroundMapper.categoryLevel1(appInfo.getCategoryLevel1()));
			appInfo.setCategoryLevel2Name(dev_backgroundMapper.categoryLevel1(appInfo.getCategoryLevel2()));
			appInfo.setCategoryLevel3Name(dev_backgroundMapper.categoryLevel1(appInfo.getCategoryLevel3()));
		}
		
		
		return list;
	}
	
	public int count() {
		return dev_backgroundMapper.count();
	}
	
	public List<AppInfo> querySoftwareName(InquiryParameter inquiryParameter){
	List<AppInfo> list=dev_backgroundMapper.querySoftwareName(inquiryParameter);

		for (AppInfo appInfo : list) {
			
			appInfo.setStatusName(dev_backgroundMapper.statusName(appInfo.getStatus()));
			appInfo.setVersionNo(dev_backgroundMapper.versionNoName(appInfo.getId()));
			appInfo.setCategoryLevel1Name(dev_backgroundMapper.categoryLevel1(appInfo.getCategoryLevel1()));
			appInfo.setCategoryLevel2Name(dev_backgroundMapper.categoryLevel1(appInfo.getCategoryLevel2()));
			appInfo.setCategoryLevel3Name(dev_backgroundMapper.categoryLevel1(appInfo.getCategoryLevel3()));
		}
		
		
		return list;
	}
	
	public int myCount(InquiryParameter inquiryParameter) {
		return dev_backgroundMapper.myCount(inquiryParameter);
	}
	public AppInfo selectAid(Integer aid) {
		
		AppInfo appInfo = dev_backgroundMapper.selectAid(aid);
		
		
		appInfo.setStatusName(dev_backgroundMapper.statusName(appInfo.getStatus()));
		appInfo.setVersionNo(dev_backgroundMapper.versionNoName(appInfo.getId()));
		appInfo.setCategoryLevel1Name(dev_backgroundMapper.categoryLevel1(appInfo.getCategoryLevel1()));
		appInfo.setCategoryLevel2Name(dev_backgroundMapper.categoryLevel1(appInfo.getCategoryLevel2()));
		appInfo.setCategoryLevel3Name(dev_backgroundMapper.categoryLevel1(appInfo.getCategoryLevel3()));
		
		return appInfo;
	}
	
	public AppVersion selectVid(Integer vid) {
		
		return dev_backgroundMapper.selectVid(vid);
	}
	
	public int updateStatus(Integer status,Integer id) {
		return dev_backgroundMapper.updateStatus(status, id);
	}
	
	public int updatePublishStatus(Integer status,Integer id) {
		return dev_backgroundMapper.updatePublishStatus(status, id);
	}
	
}
