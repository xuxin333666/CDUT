package cn.kgc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.controller.CoreController;
import cn.kgc.utils.ControllerUtils;

@WebFilter("/*")
public class ControllerSelectFilter implements Filter {
	

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		String url = request.getRequestURI();
		CoreController controller = null;
		try {
			controller = ControllerUtils.getController(url);
			controller.setFilter(this);
			controller.execute(request, response);
		} catch (Exception e) {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
