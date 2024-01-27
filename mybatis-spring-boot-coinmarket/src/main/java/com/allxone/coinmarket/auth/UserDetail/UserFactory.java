package com.allxone.coinmarket.auth.UserDetail;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.allxone.coinmarket.mapper.RolesMapper;
import com.allxone.coinmarket.mapper.UserRoleMapper;
import com.allxone.coinmarket.model.Roles;
import com.allxone.coinmarket.model.RolesExample;
import com.allxone.coinmarket.model.UserRole;
import com.allxone.coinmarket.model.UserRoleExample;
import com.allxone.coinmarket.model.Users;

public final class UserFactory {
	
	private UserRoleMapper userRoleMapper;
	
	private RolesMapper roleMapper;
	
	private static UserFactory instance;
	
	public static synchronized UserFactory getInstance () {
        if (instance == null) {
            instance = new UserFactory();
        }
        return instance;
    }

    private UserFactory() {
    }
    
    static CustomUserDetail create(Users user) {
    	return new CustomUserDetail(user, mapToGrantedAuthorities(UserFactory.getInstance().getAuthorities(user)));
    }
    
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Roles> list) {
        return list.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
    }
    
    public List<Roles> getAuthorities(Users user){
    	UserRoleExample userRoleExample = new UserRoleExample();
    	userRoleExample.createCriteria().andUserIdEqualTo(user.getId());
    	List<UserRole> tmpUserRole = userRoleMapper.selectByExample(userRoleExample);
    	LinkedList<UserRole> userRoles = new LinkedList<UserRole>(tmpUserRole);
    	
    	List<Roles> tmpRoles = getRoles();
    	LinkedList<Roles> roles = new LinkedList<>(tmpRoles);
    	while (userRoles.size()>0) {
			UserRole userRole = userRoles.pop();
			for (Roles role : tmpRoles) {
				if(userRole.getRoleId() == role.getId()) {
					roles.add(role);
				}
			}
		}
    	return roles;
    }

    public List<Roles> getRoles(){
    	RolesExample example = new RolesExample();
    	example.createCriteria().andIdIsNotNull();
    	return roleMapper.selectByExample(example);
    }
    
    
    
    public String chooseUsername(String username) {
    	return "";
    }
    
	public void setUserRoleMapper(UserRoleMapper userRoleMapper) {
		this.userRoleMapper = userRoleMapper;
	}

	public void setRoleMapper(RolesMapper roleMapper) {
		 this.roleMapper=  roleMapper;
	}

}
