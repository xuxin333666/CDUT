package cn.kgc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kgc.bean.PageBean;
import cn.kgc.dao.impl.GroupDaoImpl;
import cn.kgc.dao.impl.ProfessionalDaoImpl;
import cn.kgc.dao.impl.StudentDaoImpl;
import cn.kgc.dao.intf.GroupDao;
import cn.kgc.dao.intf.ProfessionalDao;
import cn.kgc.dao.intf.StudentDao;
import cn.kgc.dto.EChartsBarDto;
import cn.kgc.dto.EChartsPieDto;
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
		int status = 0;
		List<String> errorList = checkGroupExist(idArr);
		try {
			status = professionalDao.deletes(idArr);
		} catch (DaoException e) {
			logger.error("[ProfessionalServiceImpl:deletes]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		if(errorList.size() != 0 ) {
			StringBuilder sb = new StringBuilder();
			sb.append("如下专业无法删除，请检查是否含有班级：\n");
			for (String id : errorList) {
				sb.append("专业编号： " + id + "\n");
			}
			throw new ServiceException(sb.toString());
		}
		return status;
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
		int status = 0;
		List<String> errorList = checkGroupExist(idArr);
		try {
			status = professionalDao.updates(idArr, "02");
		} catch (DaoException e) {
			logger.error("[ProfessionalServiceImpl:disable]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		
		if(errorList.size() != 0 ) {
			StringBuilder sb = new StringBuilder();
			sb.append("如下专业无法停用，请检查是否含有班级：\n");
			for (String id : errorList) {
				sb.append("专业编号： " + id + "\n");
			}
			throw new ServiceException(sb.toString());
		}
		return status;
	}

	@Override
	public List<Professional> query() throws ServiceException {
		try {
			return professionalDao.query("status","01");
		} catch (DaoException e) {
			logger.error("[ProfessionalServiceImpl:query]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
	
	private List<String> checkGroupExist(List<String> idArr) throws ServiceException {
		List<String> errorList = new ArrayList<>();
		Map<String, String[]> maps = new HashMap<>();
		GroupDao groupDao = new GroupDaoImpl();
		
		for (String id : idArr) {
			maps.put("p.id", new String[]{id});
			try {
				int count = groupDao.getCount(maps);
				if(count != 0) {
					errorList.add(id);
				}
			} catch (DaoException e) {
				logger.error("[ProfessionalServiceImpl:checkGroupExist]" + e.getMessage());
				throw new ServiceException(e.getMessage());
			}
		}
		idArr.removeAll(errorList);
		return errorList;
	}

	@Override
	public EChartsPieDto studentNumInProByPie() throws ServiceException {
		StudentDao studentDao = new StudentDaoImpl();
		List<Map<String, String>> data = new ArrayList<>();
		Map<String, String> tempMap = new HashMap<>();
		Map<String, String[]> feilds = null;
		try {
			List<Professional> pros = professionalDao.query();
			int count = 0;
			for (Professional professional : pros) {
				count = 0;
				tempMap = new HashMap<>();
				if("01".equals(professional.getStatus())) {
					feilds = new HashMap<>();
					feilds.put("p.id", new String[]{professional.getId()});
					count = studentDao.getCount(feilds);
					tempMap.put("name", professional.getName());
				} else {
					tempMap.put("name", professional.getName() + "(未启用)");
				}
				tempMap.put("value", count + "");
				data.add(tempMap);
			}
		} catch (DaoException e) {
			logger.error("[ProfessionalServiceImpl:studentNumInProByPie]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return new EChartsPieDto("各专业学生人数饼图", data);
	}

	@Override
	public EChartsBarDto studentNumInProByBar() throws ServiceException {
		List<String> xAxisData = new ArrayList<>();
		
		List<String> legendNames = new ArrayList<>();
		legendNames.add("人数");
		legendNames.add("男生人数");
		legendNames.add("女生人数");
		
		List<String> snames = new ArrayList<>();
		List<List<String>> datas = new ArrayList<>();
		List<String> maleCount = new ArrayList<>();
		List<String> femaleCount = new ArrayList<>();
		List<String> count = new ArrayList<>();
		snames.add("人数");
		snames.add("男生人数");
		snames.add("女生人数");
		try {
			String proName = null;
			String gender = null;
			List<Map<String, Object>> values = professionalDao.statisticalQuery("select p.name,s.gender,count(1) as c FROM t_student AS s RIGHT JOIN ( t_group as g RIGHT JOIN t_professional as p on g.pro_id = p.id) on s.group_id = g.id group by s.gender,p.name order by p.name,s.gender+0");
			for (int i=0;i<values.size();i++) {
				proName = (String)values.get(i).get("name");
				if(!xAxisData.contains(proName)) {
					xAxisData.add(proName);
					gender = (String)values.get(i).get("gender");
					String malec = "0";
					String femalec = "0";
					if("01".equals(gender)) {
						malec = values.get(i).get("c") + "";
						if(i+1<values.size() && proName.equals(values.get(i+1).get("name"))) {
							femalec = values.get(i+1).get("c") + "";
						}
					} else if("02".equals(gender)) {
						femalec = values.get(i).get("c") + "";
					} else if(gender == null) {
						if(i+1<values.size() && proName.equals(values.get(i+1).get("name"))) {
							xAxisData.remove(xAxisData.size()-1);
							continue;
						}
					}
					maleCount.add(malec);
					femaleCount.add(femalec);
					count.add((Integer.parseInt(malec) + Integer.parseInt(femalec)) + "");
				}
			}
			datas.add(count);
			datas.add(maleCount);
			datas.add(femaleCount);
			return new EChartsBarDto("各专业学生人数统计柱状图", legendNames, xAxisData, snames, datas);
		} catch (DaoException e) {
			logger.error("[ProfessionalServiceImpl:studentNumInProByBar]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		
		
	}
	

}
