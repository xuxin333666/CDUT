package cn.kgc.controller.professional;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.model.Professional;
import cn.kgc.service.impl.ProfessionalServiceImpl;
import cn.kgc.service.intf.ProfessionalService;
import cn.kgc.utils.ProfessionalUtils;



@WebServlet("/admin/permissions/professional/pro_add")
public class ProAddProfessionalController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProfessionalService professionalService = new ProfessionalServiceImpl();
		String id = professionalService.getNewId();

		req.setAttribute("pro", new Professional(id));
		req.setAttribute("selectMap", ProfessionalUtils.selectMap);
		req.setAttribute("command", "add");
		req.getRequestDispatcher("pro_modify.jsp").forward(req, resp);
	}
	
	

}
