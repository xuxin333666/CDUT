package cn.kgc.controller.professional;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kgc.bean.PageBean;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.Professional;
import cn.kgc.service.impl.ProfessionalServiceImpl;
import cn.kgc.service.intf.ProfessionalService;
import cn.kgc.utils.StringUtils;



@WebServlet("/admin/permissions/professional/mainTable")
public class ProfessionalController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(ProfessionalController.class); 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProfessionalService professionalService = new ProfessionalServiceImpl();
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
			logger.error("[ProfessionalController:doPost]" + e.getMessage());
		}
		req.setAttribute("pageBean", pageBean);
		req.setAttribute("maps", feilds);
		req.setAttribute("columnNames", new String[]{"序号","专业编号","专业名称","专业代码","专业英文名","创建日期","学制","总学分","教师人数","状态"});
		req.setAttribute("fields", new String[]{"id","name","code","nameEn","date","eductionalSystme","totalScore","teatherCount","status"});
		
		req.getRequestDispatcher("professional.jsp").forward(req, resp);
	}
	
	

}
