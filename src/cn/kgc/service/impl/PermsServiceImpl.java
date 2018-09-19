package cn.kgc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kgc.dao.impl.PermsDaoImpl;
import cn.kgc.dao.intf.PermsDao;
import cn.kgc.dto.treeNode;
import cn.kgc.exception.DaoException;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.Perms;
import cn.kgc.model.User;
import cn.kgc.service.intf.PermsService;

public class PermsServiceImpl implements PermsService {
	private PermsDao permsDao = new PermsDaoImpl();
	private static final Logger logger = LoggerFactory.getLogger(PermsServiceImpl.class); 
	@Override
	public List<Perms> getMenu() throws ServiceException {
		try {
			return permsDao.query();
		} catch (DaoException e) {
			logger.error("[PermsServiceImpl:getMenu]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public Set<String> getPerms(User user) throws ServiceException {
		try {
			return permsDao.queryPermsName(user);
		} catch (DaoException e) {
			logger.error("[PermsServiceImpl:getPerms]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<treeNode> getTreeNodes(String pid) throws ServiceException {
		try {
			List<Perms> perms = permsDao.queryPermsName(pid);
			List<treeNode> Nodes = new ArrayList<>();
			int count = 1;
			treeNode node = new treeNode();
			node.setText("<span style='color:orange' data-pid='"+ pid +"' onclick='addTreeClick(\""+ pid +"\")'>新建资源</span>");
			Nodes.add(node);
			for (Perms perm : perms) {
				node = new treeNode();
				if("03".equals(perm.getType())) {
					node.setText("<span style='color:orange'>"+ perm.getName() +"（按钮）</span>");
				} else {
					node.setText("<span data-num='"+ count++ +"' data-id='"+ perm.getId() +"' data-pid='"+ pid +"' >"+ perm.getName() +"</span>");
					List<treeNode> subNodes = new ArrayList<>();
					treeNode subNode = new treeNode();
					subNode.setText("<span style='color:orange' data-pid='"+ pid +"' onclick='addTreeClick(\""+ pid +"\")'>新建资源</span>");
					subNodes.add(subNode);
					node.setNodes(subNodes);
				}
				Nodes.add(node);
				
			}
			return Nodes;
		} catch (DaoException e) {
			logger.error("[PermsServiceImpl:getTreeNodes]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}


}

