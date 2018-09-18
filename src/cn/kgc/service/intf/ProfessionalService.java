package cn.kgc.service.intf;

import java.util.List;
import java.util.Map;

import cn.kgc.bean.PageBean;
import cn.kgc.dto.EChartsBarDto;
import cn.kgc.dto.EChartsPieDto;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.Professional;

public interface ProfessionalService {

	int getCount(Map<String, String[]> feilds) throws ServiceException;

	PageBean<Professional> query(PageBean<Professional> pageBean, Map<String, String[]> feilds) throws ServiceException;

	String getNewId();

	int add(Professional pro) throws ServiceException;

	int modify(Professional pro) throws ServiceException;

	Professional query(String id) throws ServiceException;

	int deletes(List<String> idArr) throws ServiceException;

	int enable(List<String> idArr) throws ServiceException;

	int disable(List<String> idArr) throws ServiceException;

	List<Professional> query() throws ServiceException;

	EChartsPieDto studentNumInProByPie() throws ServiceException;

	EChartsBarDto studentNumInProByBar() throws ServiceException;

}
