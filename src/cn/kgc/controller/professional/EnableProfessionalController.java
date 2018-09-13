package cn.kgc.controller.professional;

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
import cn.kgc.service.impl.ProfessionalServiceImpl;
import cn.kgc.service.intf.ProfessionalService;



@WebServlet("/admin/permissions/professional/enable")
public class EnableProfessionalController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(EnableProfessionalController.class); 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProfessionalService professionalService = new ProfessionalServiceImpl();
		
		String idArrStr = req.getParameter("data");
		
		List<String> idArr = JSONArray.parseArray(idArrStr,String.class);
		
		int status = 0;
		try {
			status = professionalService.enable(idArr);
		} catch (ServiceException e) {
			logger.error("[EnableProfessionalController:doPost]" + e.getMessage());
		}
		
		resp.getWriter().print(status);
	}
	
	

}
