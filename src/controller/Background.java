package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import entity.AppCategory;
import entity.AppInfo;
import entity.AppVersion;
import entity.BackendUser;
import entity.DataDictionary;
import entity.InquiryParameter;
import service.BackgroundService;

@Controller
@RequestMapping("background")
public class Background {
	@Autowired
	BackgroundService dev_backgroundService;
	
	@RequestMapping("login")
	public String Login() {
		return "backendlogin";
	}
	
	@RequestMapping("dologin")
	public String doLogin(HttpServletRequest request,HttpSession session) {
		String name = request.getParameter("userCode");
		String mima = request.getParameter("userPassword");
		BackendUser user=dev_backgroundService.backendLogin(name, mima);
		if (user!=null) {
			session.setAttribute("userSession", user);
			
			return "redirect:list";
		}else {
			return "backendlogin";
		}
		
	}
	
	@RequestMapping("main")
	public String main(HttpSession session) {
		
		if (session.getAttribute("userSession")==null) {
			return "redirect:login";
		}
		
		return "backend/main";
	}
	
	@RequestMapping("/Select")
	public String Select(HttpServletRequest request,HttpSession session,@RequestParam(value="currentPageNo",required=false)Integer currentPageNo) {
		
		if (session.getAttribute("userSession")==null) {
			
			return "redirect:login";
			
		}
		
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
		
		if (currentPageNo==0||request.getParameter("currentPageNo")==null) {
			currentPageNo=1;
		}
		session.setAttribute("currentPageNo", currentPageNo);
		currentPageNo=(currentPageNo-1)*5;
		inquiryParameter.setCurrentPageNo(currentPageNo);
	
		List<AppInfo> appInfos =dev_backgroundService.querySoftwareName(inquiryParameter);
		session.setAttribute("inquiryParameter", inquiryParameter);
		session.setAttribute("appInfoList", appInfos);
		
		currentPageNo=(currentPageNo-1)*5;
		inquiryParameter.setCurrentPageNo(currentPageNo);
		session.setAttribute("inquiryParameter", inquiryParameter);
		session.setAttribute("appInfoList", appInfos);
		
		int coun = dev_backgroundService.myCount(inquiryParameter);
		
		session.setAttribute("totalCount",coun);
		
		coun = (coun%5)==0 ? (coun/5):(coun/5)+1;
		session.setAttribute("totalPageCount",coun);
		
		
		return "backend/applist";
	}
	
	
	@RequestMapping("list")
	public String list(HttpSession session,HttpServletRequest request,@RequestParam(value="currentPageNo",required=false)Integer currentPageNo){
		
		List<DataDictionary> dataDictionaries = dev_backgroundService.selectTypeName("所属平台");
		session.setAttribute("flatFormList", dataDictionaries);
		session.setAttribute("categoryLevel1List", dev_backgroundService.appCategoryList(0));
		
		if (request.getParameter("currentPageNo")==null) {
			currentPageNo=1;
		}
		session.setAttribute("currentPageNo", currentPageNo);
		Integer totalCount = dev_backgroundService.count();
		session.setAttribute("totalCount", totalCount);
		Integer totalPageCount = totalCount%5==0?totalCount/5:(totalCount/5)+1;
		session.setAttribute("totalPageCount", totalPageCount);
		List<AppInfo> appInfos = dev_backgroundService.appInfoList(currentPageNo);
		session.setAttribute("appInfoList", appInfos);

		return "backend/applist";
	}
	
	@RequestMapping(value="datadictionarylist",method=RequestMethod.GET,produces="datadictionarylist/json;charset=UTF-8")
	@ResponseBody
	public String datadictionarylist(HttpServletRequest request) {
		
		Integer id= Integer.valueOf(request.getParameter("pid"));
		
		List<AppCategory> list = dev_backgroundService.appCategoryList(id);

		return JSONArray.toJSONString(list);
	}
	
	@RequestMapping("check")
	public String check(HttpServletRequest request,HttpSession session,Integer aid,Integer vid) {
		
		AppInfo appInfo = dev_backgroundService.selectAid(aid);
		request.setAttribute("appInfo", appInfo);
		
		AppVersion appVersion = dev_backgroundService.selectVid(vid);
		request.setAttribute("appVersion", appVersion);
		
		return "backend/appcheck";
	}
	@RequestMapping("checksave")
	public String checksave(HttpServletRequest request) {
		
		Integer aid = Integer.valueOf(request.getParameter("id"));
		AppInfo appInfo = dev_backgroundService.selectAid(aid);
		
		Integer status=Integer.valueOf(request.getParameter("status"));
		
		if (dev_backgroundService.updateStatus(status, aid)>=0&&dev_backgroundService.updatePublishStatus(status, appInfo.getVersionId())>=0) {
			
			System.out.println("89898989");
		}else {
			
		}
		
		return "";
	}

}
