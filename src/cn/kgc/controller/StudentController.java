package cn.kgc.controller;

import java.io.IOException;
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
import cn.kgc.dto.treeNode;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.Group;
import cn.kgc.model.Student;
import cn.kgc.service.impl.StudentServiceImpl;
import cn.kgc.service.intf.StudentService;
import cn.kgc.utils.StringUtils;
import cn.kgc.utils.StudentUtils;


public class StudentController extends CoreController {
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class); 
	StudentService studnetService = new StudentServiceImpl();
	
	public StudentController() {
		super.setController(this);
	}
	public void pro_add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String gid = req.getParameter("gid");
		Student student;
		try {
			student = studnetService.getNewStudentByGid(gid);
			
			req.setAttribute("student", student);
			req.setAttribute("selectMap", StudentUtils.selectMap);
			req.setAttribute("command", "add");
			req.getRequestDispatcher("pro_modify.jsp").forward(req, resp);
			
		} catch (ServiceException e) {
			logger.error("[StudentController:pro_add]" + e.getMessage());
			req.setAttribute("msg", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
		
	}
	
	public void pro_fileUpload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Student student;
		try {
			student = studnetService.query(id);
			
			req.setAttribute("student", student);
			req.getRequestDispatcher("fileUpload.jsp").forward(req, resp);
		} catch (ServiceException e) {
			logger.error("[StudentController:pro_fileUpload]" + e.getMessage());
			req.setAttribute("msg", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}

	}
	
	public void pro_modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Student student;
		try {
			student = studnetService.query(id);
			
			req.setAttribute("student", student);
			req.setAttribute("selectMap", StudentUtils.selectMap);
			req.setAttribute("command", "modify");
			req.getRequestDispatcher("pro_modify.jsp").forward(req, resp);
		} catch (ServiceException e) {
			logger.error("[StudentController:pro_modify]" + e.getMessage());
			req.setAttribute("msg", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}

	}
	
	public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idArrStr = req.getParameter("data");
		
		List<String> idArr = JSONArray.parseArray(idArrStr,String.class);
		
		int status = 0;
		try {
			status = studnetService.regist(idArr);
		} catch (ServiceException e) {
			logger.error("[StudentController:regist]" + e.getMessage());
		}
		
		resp.getWriter().print(status);
	}
	
	public void report(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idArrStr = req.getParameter("data");
		
		List<String> idArr = JSONArray.parseArray(idArrStr,String.class);
		
		int status = 0;
		try {
			status = studnetService.report(idArr);
		} catch (ServiceException e) {
			logger.error("[StudentController:report]" + e.getMessage());
		}
		
		resp.getWriter().print(status);
	}
	
	public void saveUpload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String photoUrl = req.getParameter("photoUrl");
		
		int status = 0;
		try {
			status = studnetService.addPhotoUrl(id,photoUrl);
			resp.getWriter().println(status);
		} catch (ServiceException e) {
			logger.error("[StudentController:saveUpload]" + e.getMessage());
			resp.getWriter().println(e.getMessage());
		}
		
	}
	
	public void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String command = req.getParameter("command");
		String gid = req.getParameter("gid");
		String data = req.getParameter("data");
		
		Student student = JSON.parseObject(data, Student.class);
		
		student.setGroup(new Group(gid));
		
		int status = 0;
		try {
			if("add".equals(command)) {
				status = studnetService.add(student);
			} else if("modify".equals(command)) {
				status = studnetService.modify(student);
			}
			resp.getWriter().println(status);
		} catch (ServiceException e) {
			logger.error("[StudentController:save]" + e.getMessage());
			resp.getWriter().println(e.getMessage());
		}
		
	}
	
	public void mainTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> feilds = req.getParameterMap();
		String gid = req.getParameter("gid");
		String pid = req.getParameter("pid");
		PageBean<Student> pageBean = null;
		try {
			pageBean = new PageBean<>(studnetService.getCount(feilds));
			if(StringUtils.isNotEmpty(req.getParameter("page"))) {
				pageBean.setCurrentPage(Integer.parseInt(req.getParameter("page")));
			} else {
				pageBean.setCurrentPage(1);
			}
			
			pageBean = studnetService.query(pageBean,feilds);
			
			
		} catch (ServiceException e) {
			logger.error("[StudentController:mainTable:学生查询失败]" + e.getMessage());
		}
		
		List<treeNode> proNodes = null;
		try {
			proNodes = studnetService.createTreeNode(gid,pid);
		} catch (ServiceException e) {
			logger.error("[StudentController:mainTable:树构建失败]" + e.getMessage());
		}
		
		req.setAttribute("pageBean", pageBean);
		req.setAttribute("maps", feilds);
		req.setAttribute("selectMap", StudentUtils.selectMap);
		req.setAttribute("columnNames", StudentUtils.colName);
		req.setAttribute("fields", StudentUtils.fields);
		
		req.setAttribute("nodes", JSONArray.toJSON(proNodes));
		
		req.getRequestDispatcher("student.jsp").forward(req, resp);
	}
	
	public void student_regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> feilds = req.getParameterMap();
		String gid = req.getParameter("gid");
		String pid = req.getParameter("pid");
		PageBean<Student> pageBean = null;
		try {
			pageBean = new PageBean<>(studnetService.getCount(feilds));
			if(StringUtils.isNotEmpty(req.getParameter("page"))) {
				pageBean.setCurrentPage(Integer.parseInt(req.getParameter("page")));
			} else {
				pageBean.setCurrentPage(1);
			}
			
			pageBean = studnetService.query(pageBean,feilds);
			
			
		} catch (ServiceException e) {
			logger.error("[StudentController:student_regist:学生查询失败]" + e.getMessage());
		}
		
		List<treeNode> proNodes = null;
		try {
			proNodes = studnetService.createTreeNode(gid,pid);
		} catch (ServiceException e) {
			logger.error("[StudentController:student_regist:树构建失败]" + e.getMessage());
		}
		
		req.setAttribute("pageBean", pageBean);
		req.setAttribute("maps", feilds);
		req.setAttribute("selectMap", StudentUtils.selectMap);
		req.setAttribute("columnNames", StudentUtils.colName_regist);
		req.setAttribute("fields", StudentUtils.fields_regist);
		
		req.setAttribute("nodes", JSONArray.toJSON(proNodes));
		
		req.getRequestDispatcher("student_regist.jsp").forward(req, resp);
	}
	
	public void student_report(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> feilds = req.getParameterMap();
		String gid = req.getParameter("gid");
		String pid = req.getParameter("pid");
		PageBean<Student> pageBean = null;
		try {
			pageBean = new PageBean<>(studnetService.getCount(feilds));
			if(StringUtils.isNotEmpty(req.getParameter("page"))) {
				pageBean.setCurrentPage(Integer.parseInt(req.getParameter("page")));
			} else {
				pageBean.setCurrentPage(1);
			}
			
			pageBean = studnetService.query(pageBean,feilds);
			
			
		} catch (ServiceException e) {
			logger.error("[StudentController:student_report:学生查询失败]" + e.getMessage());
		}
		
		List<treeNode> proNodes = null;
		try {
			proNodes = studnetService.createTreeNode(gid,pid);
		} catch (ServiceException e) {
			logger.error("[StudentController:student_report:树构建失败]" + e.getMessage());
		}
		
		req.setAttribute("pageBean", pageBean);
		req.setAttribute("maps", feilds);
		req.setAttribute("selectMap", StudentUtils.selectMap);
		req.setAttribute("columnNames", StudentUtils.colName_report);
		req.setAttribute("fields", StudentUtils.fields_report);
		
		req.setAttribute("nodes", JSONArray.toJSON(proNodes));
		
		req.getRequestDispatcher("student_report.jsp").forward(req, resp);
	}
	
	public void unreport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idArrStr = req.getParameter("data");
		
		List<String> idArr = JSONArray.parseArray(idArrStr,String.class);
		
		String msg = "";
		try {
			studnetService.unReport(idArr);
			msg = "已确认未报到";
		} catch (ServiceException e) {
			logger.error("[StudentController:unreport]" + e.getMessage());
			msg = e.getMessage();
		}
		resp.getWriter().print(msg);
	}
	
	public void unregist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idArrStr = req.getParameter("data");
		
		List<String> idArr = JSONArray.parseArray(idArrStr,String.class);
		
		String msg = "";
		try {
			studnetService.unRegist(idArr);
			msg = "已确认未注册";
		} catch (ServiceException e) {
			logger.error("[StudentController:unregist]" + e.getMessage());
			msg = e.getMessage();
		}
		resp.getWriter().print(msg);
	}
}
