package com.noriental.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.context.WebApplicationContext;

import com.noriental.security.usermanage.domain.Group;
import com.noriental.security.usermanage.domain.Permission;
import com.noriental.security.usermanage.service.GroupService;
import com.noriental.security.usermanage.service.PermissionService;
import com.noriental.security.utils.PermissionUtils;

/**
 *
 * @author Wang Beichen
 * @date 2014-1-16
 * @version 1.0
 */
public class ContextWrapper {

	private WebApplicationContext wac = null;
	
    //更新用户权限信息
	private GroupService groupService;
	
	private PermissionService permissionService;
	
	private PermissionUtils permissionUtils;
	
	
	public ContextWrapper(WebApplicationContext wac) {
		this.setWac(wac);
		permissionService = (PermissionService) wac.getBean("permissionService");
		permissionUtils = (PermissionUtils) wac.getBean("permissionUtils");
		groupService = (GroupService) wac.getBean("groupService");
		List<Group> groupList = groupService.find(null);
		for(int i=0; i<groupList.size(); i++) {
		    Group g = groupList.get(i);
		    List<Permission> permissList = permissionService.findByGroupId(g.getId());
		    permissionUtils.setPermission(g.getId(), permissList);
		}
	}
	public Map<String, Object> getMetadata() throws Exception {
		Map<String, Object> metaMap = new HashMap<String, Object>();
        
        return metaMap;
	}

	public void setWac(WebApplicationContext wac) {
		this.wac = wac;
	}

	public WebApplicationContext getWac() {
		return wac;
	}
}
