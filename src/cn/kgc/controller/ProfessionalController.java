package cn.kgc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cn.kgc.bean.PageBean;
import cn.kgc.dto.EChartsBarDto;
import cn.kgc.dto.EChartsPieDto;
import cn.kgc.dto.EChartsStepLineDto;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.Professional;
import cn.kgc.service.impl.ProfessionalServiceImpl;
import cn.kgc.service.intf.ProfessionalService;
import cn.kgc.utils.ProfessionalUtils;
import cn.kgc.utils.StringUtils;

public class ProfessionalController extends CoreController {
	private static final Logger logger = LoggerFactory.getLogger(ProfessionalController.class); 
	ProfessionalService professionalService = new ProfessionalServiceImpl();
	
	public ProfessionalController() {
		super.setController(this);
	}
	public void mainTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> feilds = req.getParameterMap();
		PageBean<Professional> pageBean = null;
		try {
			pageBean = new PageBean<>(professionalService.getCount(feilds));
			if(feilds.get("page") != null && StringUtils.isNotEmpty(feilds.get("page")[0])) {
				pageBean.setCurrentPage(Integer.parseInt(feilds.get("page")[0]));
			} else {
				pageBean.setCurrentPage(1);
			}
			
			pageBean = professionalService.query(pageBean,feilds);
			
		} catch (ServiceException e) {
			logger.error("[ProfessionalController:mainTable]" + e.getMessage());
		}
		req.setAttribute("pageBean", pageBean);
		req.setAttribute("maps", feilds);
		req.setAttribute("columnNames", ProfessionalUtils.colName);
		req.setAttribute("fields", ProfessionalUtils.fields);
		
		req.getRequestDispatcher("professional.jsp").forward(req, resp);
		
	}
	
	public void dels(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idArrStr = req.getParameter("data");
		
		List<String> idArr = JSONArray.parseArray(idArrStr,String.class);
		
		try {
			professionalService.deletes(idArr);
			resp.getWriter().print("删除成功");
		} catch (ServiceException e) {
			logger.error("[ProfessionalController:dels]" + e.getMessage());
			resp.getWriter().print(e.getMessage());
		}
		
	}
	
	public void disable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idArrStr = req.getParameter("data");
		
		List<String> idArr = JSONArray.parseArray(idArrStr,String.class);
		
		String msg = "";
		try {
			professionalService.disable(idArr);
			msg = "停用成功";
		} catch (ServiceException e) {
			logger.error("[ProfessionalController:disable]" + e.getMessage());
			msg = e.getMessage();
		}
		resp.getWriter().print(msg);
	}
	
	
	public void enable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idArrStr = req.getParameter("data");
		
		List<String> idArr = JSONArray.parseArray(idArrStr,String.class);
		
		int status = 0;
		try {
			status = professionalService.enable(idArr);
		} catch (ServiceException e) {
			logger.error("[ProfessionalController:enable]" + e.getMessage());
		}
		
		resp.getWriter().print(status);
	}
	
	public void pro_add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = professionalService.getNewId();

		req.setAttribute("pro", new Professional(id));
		req.setAttribute("selectMap", ProfessionalUtils.selectMap);
		req.setAttribute("command", "add");
		req.getRequestDispatcher("pro_modify.jsp").forward(req, resp);
	}
	
	
	public void pro_modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Professional pro;
		try {
			pro = professionalService.query(id);
			req.setAttribute("pro", pro);
			req.setAttribute("selectMap", ProfessionalUtils.selectMap);
			req.setAttribute("command", "modify");
			req.getRequestDispatcher("pro_modify.jsp").forward(req, resp);
		} catch (ServiceException e) {
			logger.error("[ProfessionalController:pro_modify]" + e.getMessage());
			req.setAttribute("msg", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}

	}
	
	
	public void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String command = req.getParameter("command");
		String data = req.getParameter("data");
		
		Professional pro = JSON.parseObject(data, Professional.class);
		int status = 0;
		try {
			if("add".equals(command)) {
					status = professionalService.add(pro);
			} else if("modify".equals(command)) {
				status = professionalService.modify(pro);
			}
		} catch (ServiceException e) {
			logger.error("[ProfessionalController:save]" + e.getMessage());
		}
		
		resp.getWriter().println(status);
	}
	
	public void statisticalQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		if("stepLine".equals(type)) {
			List<String> legendData = new ArrayList<>();
			legendData.add("Step Start");
			legendData.add("Step Middle");
			legendData.add("Step End");
			
			List<String> xAxisName = new ArrayList<>();
			xAxisName.add("Mon");
			xAxisName.add("Tue");
			xAxisName.add("Wed");
			xAxisName.add("Thu");
			xAxisName.add("Fri");
			xAxisName.add("Sat");
			xAxisName.add("Sun");
			
			List<String> snames = new ArrayList<>();
			snames.add("Step Start");
			snames.add("Step Middle");
			snames.add("Step End");
			
			List<String> stepNames = new ArrayList<>();
			stepNames.add("Start");
			stepNames.add("Middle");
			stepNames.add("End");
			
			List<List<String>> datas = new ArrayList<>();
			List<String> temp = new ArrayList<>();
			temp.add("120");
			temp.add("120");
			temp.add("120");
			temp.add("120");
			temp.add("120");
			temp.add("120");
			temp.add("120");
			datas.add(temp);
			
			temp = new ArrayList<>();
			temp.add("220");
			temp.add("220");
			temp.add("220");
			temp.add("220");
			temp.add("220");
			temp.add("220");
			temp.add("220");
			datas.add(temp);
			
			temp = new ArrayList<>();
			temp.add("320");
			temp.add("320");
			temp.add("320");
			temp.add("320");
			temp.add("320");
			temp.add("320");
			temp.add("320");
			datas.add(temp);
			EChartsStepLineDto eChartsStepLineDto = new EChartsStepLineDto("测试", legendData, xAxisName, snames, stepNames, datas);
			resp.getWriter().println(JSON.toJSON(eChartsStepLineDto));
			
		} else if("pie".equals(type)) {
			List<Map<String, String>> data = new ArrayList<>();
			Map<String, String> tempMap = new HashMap<>();
			tempMap.put("value", "200");
			tempMap.put("name", "广告");
			data.add(tempMap);
			
			tempMap = new HashMap<>();
			tempMap.put("value", "300");
			tempMap.put("name", "联盟");
			data.add(tempMap);
			
			tempMap = new HashMap<>();
			tempMap.put("value", "400");
			tempMap.put("name", "部落");
			data.add(tempMap);
			EChartsPieDto eChartsPieDto = new EChartsPieDto("123", data);
			resp.getWriter().println(JSON.toJSON(eChartsPieDto));
		} else if("bar".equals(type)) {
			List<String> xAxisData = new ArrayList<>();
			xAxisData.add("衬衫");
			xAxisData.add("羊毛衫");
			xAxisData.add("雪纺衫");
			xAxisData.add("裤子");
			xAxisData.add("高跟鞋");
			xAxisData.add("袜子");
			List<String> data = new ArrayList<>();
			data.add("100");
			data.add("200");
			data.add("150");
			data.add("300");
			data.add("120");
			data.add("110");
			EChartsBarDto eChartsBarDto = new EChartsBarDto("ECharts 入门示例", "销量", xAxisData, "销量", data);
			resp.getWriter().println(JSON.toJSON(eChartsBarDto));
		}
	}
}
