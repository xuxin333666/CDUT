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



@WebServlet("/admin/permissions/professional/disable")
public class DisableProfessionalController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(DisableProfessionalController.class); 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProfessionalService professionalService = new ProfessionalServiceImpl();
		
		String idArrStr = req.getParameter("data");
		
		List<String> idArr = JSONArray.parseArray(idArrStr,String.class);
		
		String msg = "";
		try {
			professionalService.disable(idArr);
			msg = "Õ£”√≥…π¶";
		} catch (ServiceException e) {
			logger.error("[DisableProfessionalController:doPost]" + e.getMessage());
			msg = e.getMessage();
		}
		resp.getWriter().print(msg);
	}
	
	

}
