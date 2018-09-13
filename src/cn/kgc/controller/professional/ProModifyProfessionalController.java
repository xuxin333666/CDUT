package cn.kgc.controller.professional;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kgc.exception.ServiceException;
import cn.kgc.model.Professional;
import cn.kgc.service.impl.ProfessionalServiceImpl;
import cn.kgc.service.intf.ProfessionalService;
import cn.kgc.utils.ProfessionalUtils;



@WebServlet("/admin/permissions/professional/pro_modify")
public class ProModifyProfessionalController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(ProModifyProfessionalController.class); 
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProfessionalService professionalService = new ProfessionalServiceImpl();
		String id = req.getParameter("id");
		Professional pro;
		try {
			pro = professionalService.query(id);
			req.setAttribute("pro", pro);
			req.setAttribute("selectMap", ProfessionalUtils.selectMap);
			req.setAttribute("command", "modify");
			req.getRequestDispatcher("pro_modify.jsp").forward(req, resp);
		} catch (ServiceException e) {
			logger.error("[ProModifyProfessionalController:doPost]" + e.getMessage());
			req.setAttribute("msg", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}

	}
	
	

}
