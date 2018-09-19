package cn.kgc.service.intf;

import java.util.List;
import java.util.Set;

import cn.kgc.dto.treeNode;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.Perms;
import cn.kgc.model.User;

public interface PermsService {

	List<Perms> getMenu() throws ServiceException;

	Set<String> getPerms(User user) throws ServiceException;

	List<treeNode> getTreeNodes(String pid) throws ServiceException;

}
