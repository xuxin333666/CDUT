package cn.kgc.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import cn.kgc.exception.ServiceException;
import cn.kgc.service.impl.StudentServiceImpl;
import cn.kgc.service.intf.StudentService;



@WebServlet("/admin/permissions/student/saveUpload")
public class SaveFileUploadStudentController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SaveFileUploadStudentController.class); 
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StudentService studnetService = new StudentServiceImpl();
		String id = req.getParameter("id");
		String photoUrl = req.getParameter("photoUrl");
		
		int status = 0;
		try {
			status = studnetService.addPhotoUrl(id,photoUrl);
			resp.getWriter().println(status);
		} catch (ServiceException e) {
			logger.error("[SaveFileUploadStudentController:doPost]" + e.getMessage());
			resp.getWriter().println(e.getMessage());
		}
		
	}
	
	

}
