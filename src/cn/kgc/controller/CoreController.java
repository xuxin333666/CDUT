package cn.kgc.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kgc.filter.ControllerSelectFilter;
import cn.kgc.utils.StringUtils;

public class CoreController {
	
	private static final Logger logger = LoggerFactory.getLogger(CoreController.class); 
	protected ControllerSelectFilter filter;
	private CoreController controller;
	
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String url = req.getRequestURI();
		String methodName = StringUtils.parseURLCommand(url);
		try {
			Method method = controller.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(controller, req,resp);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[CoreController:execute:∑¥…‰“Ï≥£]" + e.getMessage());
			req.setAttribute("msg", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
	}

	public CoreController getController() {
		return controller;
	}

	public void setController(CoreController controller) {
		this.controller = controller;
	}

	public ControllerSelectFilter getFilter() {
		return filter;
	}

	public void setFilter(ControllerSelectFilter filter) {
		this.filter = filter;
	};
}
