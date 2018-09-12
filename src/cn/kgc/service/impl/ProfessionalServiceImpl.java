package cn.kgc.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kgc.bean.PageBean;
import cn.kgc.dao.impl.ProfessionalDaoImpl;
import cn.kgc.dao.intf.ProfessionalDao;
import cn.kgc.exception.DaoException;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.Professional;
import cn.kgc.service.intf.ProfessionalService;
import cn.kgc.utils.ProfessionalUtils;

public class ProfessionalServiceImpl implements ProfessionalService {
	ProfessionalDao professionalDao = new ProfessionalDaoImpl();
	private static final Logger logger = LoggerFactory.getLogger(ProfessionalServiceImpl.class); 
	@Override
	public int getCount(Map<String, String[]> feilds) throws ServiceException {
		try {
			return professionalDao.getCount(feilds);
		} catch (DaoException e) {
			logger.error("[ProfessionalServiceImpl:getCount]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public PageBean<Professional> query(PageBean<Professional> pageBean, Map<String, String[]> feilds) throws ServiceException {
		List<Professional> professionals;
		try {
			professionals = professionalDao.query(pageBean,feilds);
			professionals = ProfessionalUtils.listfilter(true, professionals);
			pageBean.setDataList(professionals);
			return pageBean;
		} catch (DaoException e) {
			logger.error("[ProfessionalServiceImpl:query]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

}
