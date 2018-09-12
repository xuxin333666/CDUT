package cn.kgc.service.intf;

import java.util.Map;

import cn.kgc.bean.PageBean;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.Professional;

public interface ProfessionalService {

	int getCount(Map<String, String[]> feilds) throws ServiceException;

	PageBean<Professional> query(PageBean<Professional> pageBean, Map<String, String[]> feilds) throws ServiceException;

	String getNewId();

}
