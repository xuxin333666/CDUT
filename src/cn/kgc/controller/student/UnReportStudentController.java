package cn.kgc.controller.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;

import cn.kgc.exception.ServiceException;
import cn.kgc.service.impl.StudentServiceImpl;
import cn.kgc.service.intf.StudentService;



@WebServlet("/admin/permissions/student/unregist")
public class UnReportStudentController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(UnReportStudentController.class); 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StudentService studnetService = new StudentServiceImpl();
		
		String idArrStr = req.getParameter("data");
		
		List<String> idArr = JSONArray.parseArray(idArrStr,String.class);
		
		String msg = "";
		try {
			studnetService.unRegist(idArr);
			msg = "ÒÑÈ·ÈÏÎ´×¢²á";
		} catch (ServiceException e) {
			logger.error("[UnReportStudentController:doPost]" + e.getMessage());
			msg = e.getMessage();
		}
		resp.getWriter().print(msg);
	}
	
	

}
