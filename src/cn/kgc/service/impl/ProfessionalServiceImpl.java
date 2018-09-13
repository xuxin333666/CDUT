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
import cn.kgc.utils.StringUtils;

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
			professionals = ProfessionalUtils.listfilter(professionals);
			pageBean.setDataList(professionals);
			return pageBean;
		} catch (DaoException e) {
			logger.error("[ProfessionalServiceImpl:query]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public String getNewId() {
		return StringUtils.createTimeRandomId();
	}

	@Override
	public int add(Professional pro) throws ServiceException {
		try {
			return professionalDao.insert(pro);
		} catch (DaoException e) {
			logger.error("[ProfessionalServiceImpl:add]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public int modify(Professional pro) throws ServiceException {
		try {
			return professionalDao.update(pro);
		} catch (DaoException e) {
			logger.error("[ProfessionalServiceImpl:modify]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Professional query(String id) throws ServiceException {
		try {
			return professionalDao.query(id);
		} catch (DaoException e) {
			logger.error("[ProfessionalServiceImpl:query]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public int deletes(List<String> idArr) throws ServiceException {
		try {
			return professionalDao.deletes(idArr);
		} catch (DaoException e) {
			logger.error("[ProfessionalServiceImpl:deletes]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public int enable(List<String> idArr) throws ServiceException {
		try {
			return professionalDao.updates(idArr,"01");
		} catch (DaoException e) {
			logger.error("[ProfessionalServiceImpl:enable]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public int disable(List<String> idArr) throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
