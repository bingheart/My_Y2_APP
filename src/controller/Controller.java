package controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.regexp.internal.recompile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.AppCategory;
import entity.AppInfo;
import entity.AppVersion;
import entity.DataDictionary;
import entity.DevUser;
import entity.InquiryParameter;
import service.Dev_userService;
import ulter.UploadHandleServlet;

@org.springframework.stereotype.Controller
@RequestMapping("/user")
public class Controller {
	
	@Resource(name="userService")
	private Dev_userService dev_userService;
	//登录页面
	@RequestMapping("/login")
	public String Login(HttpSession session) {
		session.removeAttribute("devUserSession");
		return "devlogin";
	}
	//登录方法
	@RequestMapping("/dologin")
	public String doLogin(HttpServletRequest request,HttpSession session) {
		
		String userCode = request.getParameter("devCode");
		String userPassword = request.getParameter("devPassword");
		DevUser user = dev_userService.userLogin(userCode, userPassword);
		session.setAttribute("devUserSession", user);
		if (user!=null) {
			
			return "redirect:list";
			
		}else {
			return "devlogin";
		}
		
		
	}
	//主页面
	@RequestMapping("/main")
	public String Indes(HttpSession session) {
		
		return "developer/main";
	}
	//查询
	@RequestMapping("/Select")
	private String Select(HttpServletRequest request,HttpSession session,@RequestParam(value="currentPageNo",required=false)Integer currentPageNo) {


		
		
		if (session.getAttribute("devUserSession")==null) {
			
			return "redirect:login";
			
		}

		DevUser user = (DevUser) session.getAttribute("devUserSession");
		InquiryParameter inquiryParameter =null;
		
		if (currentPageNo==0) {
			inquiryParameter= new InquiryParameter();
		}else {
			inquiryParameter = (InquiryParameter) session.getAttribute("inquiryParameter");
		}

		
		if (request.getParameter("querySoftwareName")!=null&&request.getParameter("querySoftwareName")!="") {
			String xmString = null;
			try {
				 xmString = new String(request.getParameter("querySoftwareName").toString().getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			inquiryParameter.setSoftwareName(xmString);//乱码
		}
		
		
		if (request.getParameter("queryStatus")!=null&&request.getParameter("queryStatus")!="") {
			inquiryParameter.setStatus(Integer.valueOf(request.getParameter("queryStatus")));
		}
		if (request.getParameter("queryFlatformId")!=null&&request.getParameter("queryFlatformId")!="") {
			inquiryParameter.setFlatformId(Integer.valueOf(request.getParameter("queryFlatformId")));
		}
		if (request.getParameter("queryCategoryLevel1")!=null&&request.getParameter("queryCategoryLevel1")!="") {
			inquiryParameter.setCategoryLevel1(Integer.valueOf(request.getParameter("queryCategoryLevel1")));
		}
		if (request.getParameter("queryCategoryLevel2")!=null&&request.getParameter("queryCategoryLevel2")!="") {
			inquiryParameter.setCategoryLevel2(Integer.valueOf(request.getParameter("queryCategoryLevel2")));
		}
		if (request.getParameter("queryCategoryLevel3")!=null&&request.getParameter("queryCategoryLevel3")!="") {
			inquiryParameter.setCategoryLevel3(Integer.valueOf(request.getParameter("queryCategoryLevel3")));
		}
		
	
		
			if (0==currentPageNo||request.getParameter("currentPageNo")==null) {
				currentPageNo=1;
			}
			session.setAttribute("currentPageNo", currentPageNo);

			currentPageNo=(currentPageNo-1)*5;
			inquiryParameter.setCurrentPageNo(currentPageNo);
			inquiryParameter.setDevId(user.getId());
			List<AppInfo> appInfos =dev_userService.querySoftwareName(inquiryParameter);
			session.setAttribute("inquiryParameter", inquiryParameter);
			session.setAttribute("appInfoList", appInfos);
			
			int coun = dev_userService.myCount(inquiryParameter);
			session.setAttribute("totalCount",coun);
			
			coun = (coun%5)==0 ? (coun/5):(coun/5)+1;
			session.setAttribute("coun",coun);
	


		return "developer/appinfolist";
	}
	
	//显示全部
	@RequestMapping("/list")
	private String List(HttpSession session,HttpServletRequest request,@RequestParam(value="currentPageNo",required=false)Integer currentPageNo) {
		
		
		if (session.getAttribute("devUserSession")==null) {
			
			return "redirect:login";
			
		}else {
			
			
			DevUser user = (DevUser) session.getAttribute("devUserSession");
			
			if (null==request.getParameter("currentPageNo")) {
				currentPageNo=1;
			}
			session.setAttribute("currentPageNo", currentPageNo);
			
			currentPageNo=(currentPageNo-1)*5;
			
			
			if (session.getAttribute("Input")==null) {
				
				List<AppInfo> appInfos = dev_userService.appInfoList(user.getId(),currentPageNo);
				session.setAttribute("appInfoList", appInfos);
			}
			
			int coun = dev_userService.count(user.getId());
			session.setAttribute("totalCount",coun);
			
			coun = (coun%5)==0 ? (coun/5):(coun/5)+1;
			session.setAttribute("coun",coun);
			List<AppCategory> appCategories =dev_userService.appCategoryList();
			
			session.setAttribute("appCategories",appCategories);
			List<DataDictionary> dataDictionaries = dev_userService.dataDictionaryList();
			session.setAttribute("dataDictionaries", dataDictionaries);
			return "developer/appinfolist";
			
		}
	
	}
	

	
	@RequestMapping("/appversionadd")
	public String appversionadd(@RequestParam Integer id,HttpServletRequest request,HttpSession session) {
		
		List<AppVersion> appVersionList = dev_userService.appVersion(id);
		
		request.setAttribute("appVersionList", appVersionList);
		
		session.setAttribute("id", id);
		
		return "developer/appversionadd";
	}
	
	//查看版本
	@RequestMapping("appview")
	public String appview(Integer id,HttpSession session,HttpServletRequest request) {
		List<AppVersion> appVersionList = dev_userService.appVersion(id);
		
		request.setAttribute("appVersionList", appVersionList);
		AppInfo appInfo=dev_userService.SelectAppInfo(id);
		session.setAttribute("appInfo", appInfo);
		return "developer/appinfoview";
	}
	
	
	
	//修改版本
	@RequestMapping("appinfomodify")
	public String appinfomodify(HttpServletRequest request,HttpSession session) {
		
		int id = Integer.valueOf(request.getParameter("id"));
		AppInfo appInfo = dev_userService.selectAppInfoList(id);
		session.setAttribute("appInfo", appInfo);
		
		
		return "developer/appinfomodify";
	}
	
	@RequestMapping("appinfomodifysave")
	public String appinfomodifysave(HttpServletRequest request,HttpServletResponse response,HttpSession session,@RequestParam(value="attach") MultipartFile attach) {
		
		AppInfo appInfo = new AppInfo();
		appInfo.setSoftwareName(request.getParameter("softwareName"));
		appInfo.setAPKName(request.getParameter("APKName"));
		appInfo.setSupportROM(request.getParameter("supportROM"));
		appInfo.setInterfaceLanguage(request.getParameter("interfaceLanguage"));
		appInfo.setSoftwareSize(new BigDecimal(request.getParameter("softwareSize")));
		appInfo.setDownloads(Integer.valueOf(request.getParameter("downloads")));
		appInfo.setFlatformId(Integer.valueOf(request.getParameter("flatformId")));
		appInfo.setCategoryLevel1(Integer.valueOf(request.getParameter("categoryLevel1")));
		appInfo.setCategoryLevel2(Integer.valueOf(request.getParameter("categoryLevel2")));
		appInfo.setCategoryLevel3(Integer.valueOf(request.getParameter("categoryLevel3")));
		appInfo.setStatus(1);
		appInfo.setAppInfo(request.getParameter("appInfo"));
		 String apkLocPath = null;
		if (!attach.isEmpty()) {
			String path =request.getSession().getServletContext().getRealPath("statics"+File.separator+"upload");
			String oldFileName= attach.getOriginalFilename();
			String preflx = FilenameUtils.getExtension(oldFileName);
			String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000)+"."+preflx;
			File targetFile = new File(path,fileName);
			if(!targetFile.exists()) {
				targetFile.mkdirs();
			}
			try {
				attach.transferTo(targetFile);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			apkLocPath =path+File.separator+fileName;
			appInfo.setLogoLocPath(apkLocPath);
			appInfo.setLogoPicPath(apkLocPath);
		}
		Date date = new Date();
		
		if (session.getAttribute("devUserSession")==null) {
			return "redirect:login";
		}
		
		DevUser user = (DevUser) session.getAttribute("devUserSession");
		appInfo.setModifyBy(user.getId());
		appInfo.setModifyDate(date);
		appInfo.setId(Integer.valueOf(request.getParameter("id")));
		
		if (dev_userService.updateAppInfodb(appInfo)>=0) {
			
			return "redirect:appinfomodify?id="+request.getParameter("id");
		}
		
		
		return "";
	}
	
	
	
	@RequestMapping("/appversionmodify")
	public String appversionmodify(HttpServletRequest request,HttpSession session,Integer aid) {
		
		List<AppVersion> appVersionList = dev_userService.appVersion(aid);
		
		request.setAttribute("appVersionList", appVersionList);
		
		AppVersion appVersion =dev_userService.SelectVersion(aid);
		request.setAttribute("appVersion", appVersion);
		session.setAttribute("aid", aid);
		session.setAttribute("id", appVersion.getId());
		return "developer/appversionmodify";
	}
	
	//修改版本信息
	@RequestMapping("appversionmodifysave")
	public String appversionmodifysave(HttpServletRequest request,HttpServletResponse response,HttpSession session,@RequestParam(value="attach") MultipartFile attach) throws ServletException, IOException {
		
		if ( session.getAttribute("devUserSession")==null) {
			return "redirect:login";
		}
		
		 AppVersion appVersion = new AppVersion();
		
		 appVersion.setVersionNo(request.getParameter("versionNo"));
		 appVersion.setVersionSize(new BigDecimal(request.getParameter("versionSize")));
		 appVersion.setPublishStatus(1);
		 appVersion.setVersionInfo(request.getParameter("versionInfo"));
		 String apkLocPath = null;
		 String apkFileName = null;
		if (!attach.isEmpty()) {
			String path =request.getSession().getServletContext().getRealPath("statics"+File.separator+"upload");
			String oldFileName= attach.getOriginalFilename();
			String preflx = FilenameUtils.getExtension(oldFileName);
			String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000)+"."+preflx;
			File targetFile = new File(path,fileName);
			if(!targetFile.exists()) {
				targetFile.mkdirs();
			}
			try {
				attach.transferTo(targetFile);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			apkLocPath =path+File.separator+fileName;
			apkFileName =oldFileName;
		}
		appVersion.setApkFileName(apkFileName);
		appVersion.setApkLocPath(apkLocPath);
		appVersion.setDownloadLink(apkLocPath);
		Date date =new  Date();
		DevUser user = (DevUser) session.getAttribute("devUserSession");
		

		
		appVersion.setModifyBy(user.getId());
		appVersion.setModifyDate(date);
		appVersion.setId((Integer)session.getAttribute("id"));
		if (dev_userService.updateAppversion(appVersion)>=0) {
			return "redirect:appversionmodify?aid="+session.getAttribute("aid");
		}
		
		return "redirect:appversionmodify?aid="+session.getAttribute("aid");
	}
	
	//添加版本信息
	@RequestMapping("/addversionsave")
	public String addversionsave(HttpServletRequest request,HttpServletResponse response,HttpSession session,@RequestParam(value="a_downloadLink") MultipartFile attach) throws ServletException, IOException {
		
		if ( session.getAttribute("devUserSession")==null) {
			return "redirect:login";
		}
		
		String apkLocPath = null;
		 String apkFileName = null;
		if (!attach.isEmpty()) {
			String path =request.getSession().getServletContext().getRealPath("statics"+File.separator+"upload");
			String oldFileName= attach.getOriginalFilename();
			String preflx = FilenameUtils.getExtension(oldFileName);
			String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000)+"."+preflx;
			File targetFile = new File(path,fileName);
			if(!targetFile.exists()) {
				targetFile.mkdirs();
			}
			try {
				attach.transferTo(targetFile);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			apkLocPath =path+File.separator+fileName;
			apkFileName =oldFileName;
		}
		
		Integer id = (Integer) session.getAttribute("id");//获取版本ID
		String versionNo = request.getParameter("versionNo");//版本号
		BigDecimal versionSize = new BigDecimal(request.getParameter("versionSize"));//获取文件大小
		String versionInfo = request.getParameter("versionInfo");//获取版本叙述信息
		Date date = new Date();  
		
		AppVersion appVersion = new AppVersion();
		appVersion.setDownloadLink(apkLocPath);
		appVersion.setCreatedBy(id);
		appVersion.setAppId(id);
		appVersion.setCreationDate(date);
		appVersion.setVersionNo(versionNo);
		appVersion.setVersionSize(versionSize);
		appVersion.setVersionInfo(versionInfo);
		appVersion.setApkFileName(apkFileName);
		appVersion.setApkLocPath(apkLocPath);
		appVersion.setPublishStatus(1);
		appVersion.setModifyBy(id);
		appVersion.setModifyDate(date);
		if (dev_userService.addAppVersion(appVersion)>=0) {
			
			AppVersion appVersion2 = dev_userService.SelectappVersionID(id, versionNo);
			dev_userService.UpdateVersionId(appVersion2.getId(), id,appVersion2.getVersionNo());
			request.getRequestDispatcher("appversionadd?id="+id).forward(request, response);
		}
		
		
		return "developer/appversionadd?id="+id;
	}
	

	
	
	
	
	@RequestMapping("/appinfoadd")
	public String appinfoadd() {
				
		return "developer/appinfoadd";
	}
	
	
	@RequestMapping(value="user/categorylevellist",method=RequestMethod.GET,produces="categorylevellist/json;charset=UTF-8")
	@ResponseBody
	public String Name(HttpServletRequest request) {
		
		Integer pInteger = Integer.valueOf(request.getParameter("pid"));

		List<AppCategory> list =dev_userService.queryCategoryLevelID2(pInteger);

		return JSONArray.toJSONString(list);
	}

	@RequestMapping(value="user/delapp",method=RequestMethod.GET,produces="categorylevellist/json;charset=UTF-8")
	@ResponseBody
	public Object delapp(@RequestParam String id) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("delResult", "false");
		String delResult = null;
		int dep=dev_userService.delapp(id);
		if (dep>=1) {
			resultMap.put("delResult", "true");
		}
		return  JSONArray.toJSONString(delResult);
	}
	
	
	@RequestMapping(value="user/datadictionarylist",method=RequestMethod.GET,produces="datadictionarylist/json;charset=UTF-8")
	@ResponseBody
	public String datadictionarylist(HttpServletRequest request) {
		
	
		List<DataDictionary> list = dev_userService.datadictionarylist();

		return JSONArray.toJSONString(list);
	}
	
	@RequestMapping(value="user/categorylevellist0",method=RequestMethod.GET,produces="categorylevellist0/json;charset=UTF-8")
	@ResponseBody
	public String categorylevellist(HttpServletRequest request) {
		
	
		List<AppCategory> list = dev_userService.appCategoryList();

		return JSONArray.toJSONString(list);
	}
	
	@RequestMapping("appinfoaddsave")
	public String appinfoaddsave(HttpServletRequest request,HttpSession session,@RequestParam(value="a_logoPicPath") MultipartFile a_logoPicPath) {
		
		if (session.getAttribute("devUserSession")==null) {
			
			return "redirect:login";
			
		}
		
		AppInfo appInfo = new AppInfo();
		appInfo.setSoftwareName(request.getParameter("softwareName"));
		appInfo.setAPKName(request.getParameter("APKName"));
		appInfo.setSupportROM(request.getParameter("supportROM"));
		appInfo.setInterfaceLanguage(request.getParameter("interfaceLanguage"));
		appInfo.setSoftwareSize(new BigDecimal(request.getParameter("softwareSize")));
		Date date = new Date();
		appInfo.setUpdateDate(date);
		DevUser user = (DevUser) session.getAttribute("devUserSession");
		appInfo.setDevId(user.getId());
		appInfo.setAppInfo(request.getParameter("appInfo"));
		appInfo.setStatus(1);
		appInfo.setOnSaleDate(date);
		appInfo.setOffSaleDate(date);
		appInfo.setFlatformId(Integer.valueOf(request.getParameter("flatformId")));
		appInfo.setDownloads(Integer.valueOf(request.getParameter("downloads")));
		appInfo.setCategoryLevel1(Integer.valueOf(request.getParameter("categoryLevel1")));
		appInfo.setCategoryLevel2(Integer.valueOf(request.getParameter("categoryLevel2")));
		appInfo.setCategoryLevel3(Integer.valueOf(request.getParameter("categoryLevel3")));
		 String apkLocPath = null;
		 String fileName=null;
		 String path=null;
		if (!a_logoPicPath.isEmpty()) {
			path =request.getSession().getServletContext().getRealPath("statics"+File.separator+"upload");
			System.out.println(path);
			String oldFileName= a_logoPicPath.getOriginalFilename();
			String preflx = FilenameUtils.getExtension(oldFileName);
			 fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000)+"."+preflx;
			File targetFile = new File(path,fileName);
			if(!targetFile.exists()) {
				targetFile.mkdirs();
			}
			try {
				a_logoPicPath.transferTo(targetFile);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			apkLocPath =path+"/"+fileName;
		}
		
		
		appInfo.setLogoLocPath(apkLocPath);
		appInfo.setLogoPicPath(apkLocPath);
		
		if( dev_userService.InsertAppInfo(appInfo)>=0) {

				return "redirect:list";

		}
		
		
		return "/appinfoadd";
	}
	
//	@RequestMapping(value="user/apkexist",method=RequestMethod.GET,produces="datadictionarylist/json;charset=UTF-8")
//	@ResponseBody
//	public String apkexist(HttpServletRequest request) {
//		
//	
//		List<DataDictionary> list = dev_userService.datadictionarylist();
//
//		return JSONArray.toJSONString(list);
//	}
	@RequestMapping(value="user/OnTheShelf",method=RequestMethod.GET,produces="OnTheShelf/json;charset=UTF-8")
	@ResponseBody
	public Object OnTheShelf(@RequestParam Integer appId) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("delResult", "false");
		String delResult = null;
		AppInfo appInfo = new AppInfo();
		appInfo.setId(appId);
		Date date = new Date();
		appInfo.setStatus(4);
		appInfo.setOnSaleDate(date);
		int dep=dev_userService.OnTheShelf(appInfo);
		
		if (dep>=1) {
			resultMap.put("delResult", "true");
		}
		return  JSONArray.toJSONString(delResult);
	}
	
	@RequestMapping(value="user/Dismount",method=RequestMethod.GET,produces="OnTheShelf/json;charset=UTF-8")
	@ResponseBody
	public Object Dismount(@RequestParam Integer appId) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("delResult", "false");
		String delResult = null;
		AppInfo appInfo = new AppInfo();
		appInfo.setId(appId);
		Date date = new Date();
		appInfo.setStatus(5);
		appInfo.setOffSaleDate(date);
		int dep=dev_userService.Dismount(appInfo);
		
		if (dep>=1) {
			resultMap.put("delResult", "true");
		}
		return  JSONArray.toJSONString(delResult);
	}

	@RequestMapping(value="user/delfile",method=RequestMethod.GET,produces="delfile/json;charset=UTF-8")
	@ResponseBody
	public Object delfile(@RequestParam Integer id) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("delResult", "false");
		String delResult = null;
		
		int dep=dev_userService.delfile(id, null);
		
		if (dep>=1) {
			resultMap.put("delResult", "true");
		}
		return  JSONArray.toJSONString(delResult);
	}
	
	
	@RequestMapping(value="user/delFile",method=RequestMethod.GET,produces="delFile/json;charset=UTF-8")
	@ResponseBody
	public Object delFile(@RequestParam Integer id) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("delResult", "false");
		String delResult = null;
		
		int dep=dev_userService.delFile(id, null);
		
		if (dep>=1) {
			resultMap.put("delResult", "true");
		}
		return  JSONArray.toJSONString(delResult);
	}
	
	
	
}
