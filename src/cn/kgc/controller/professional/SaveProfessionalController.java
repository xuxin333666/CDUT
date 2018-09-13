package cn.kgc.controller.professional;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import cn.kgc.exception.ServiceException;
import cn.kgc.model.Professional;
import cn.kgc.service.impl.ProfessionalServiceImpl;
import cn.kgc.service.intf.ProfessionalService;



@WebServlet("/admin/permissions/professional/save")
public class SaveProfessionalController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SaveProfessionalController.class); 
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProfessionalService professionalService = new ProfessionalServiceImpl();
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
			logger.error("[SaveProfessionalController:doPost]" + e.getMessage());
		}
		
		resp.getWriter().println(status);
	}
	
	

}
