package com.ericsson.csp.tsc.admin.controller.pojo.tool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
/**
 * 
 * angular-shiro response data.
{
    info : {
        authc : {
            principal : {
                // the Suject/User principal, for example
                "login":"edegas",
                "apiKey":"*******"
            },
            credentials : {
                // the Subject/User credentials, for example
                "name" : "Edgar Degas",
                "email":"degas@mail.com"
            }
        },
        authz : {
            // list of the Subject/User roles, for example
            roles:["GUEST"],
            // list of the Subject/User permissions, for example
            permissions:["newsletter:read","book:*"]
        }
    }
}
 * @author Administrator
 *
 */
public class AngularShiroPojo {
	private Map<String, Object> info = new HashMap<String, Object>();
	private Map<String, Map<String, String>> authc = new HashMap<String, Map<String, String>>();
	private Map<String, Set<String>> authz = new HashMap<String, Set<String>>();
	
	private static final String ROLES_NAME = "roles";
	private static final String PERMISSIONS_NAME = "permissions";
	private static final String AUTHC_NAME = "authc";
	private static final String AUTHZ_NAME = "authz";
	private static final String PRINCIPAL_NAME = "principal";
	private static final String CREDENTIALS_NAME = "credentials";
	
	public AngularShiroPojo() {
		init();
	}
	
	private void init() {
		authz.put(ROLES_NAME, new HashSet<String>());
		authz.put(PERMISSIONS_NAME, new HashSet<String>());
		authc.put(PRINCIPAL_NAME, new HashMap<String, String>());
		authc.put(CREDENTIALS_NAME, new HashMap<String, String>());
		info.put(AUTHC_NAME, authc);
		info.put(AUTHZ_NAME, authz);
	}
	
	public void setRoles(Set<String> roles) {
		authz.get(ROLES_NAME).addAll(roles);
	}
	
	public Set<String> getRoles() {
		return authz.get(ROLES_NAME);
	}
	
	public void setPermitions(Set<String> permitions) {
		authz.get(PERMISSIONS_NAME).addAll(permitions);
	}
	
	public Set<String> getPermitions() {
		return authz.get(PERMISSIONS_NAME);
	}
	
	public void setPrincipal(Map<String, String> map) {
		authc.get(PRINCIPAL_NAME).putAll(map);
	}
	
	public Map<String, String> getPrincipal() {
		return authc.get(PRINCIPAL_NAME);
	}
	
	public void setCredentials(Map<String, String> map) {
		authc.get(CREDENTIALS_NAME).putAll(map);
	}
	
	public Map<String, String> getCredentials() {
		return authc.get(CREDENTIALS_NAME);
	}
	
	@Override
	public String toString() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("info", info);
		return JSON.toJSONString(map);
	}
}
