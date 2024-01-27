package com.allxone.coinmarket.auth.UserDetail;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IMultiUserDetailService extends UserDetailsService{
	UserDetails loadUserByEmail(String var1) throws UsernameNotFoundException;

    UserDetails loadUserByPhone(String var1) throws UsernameNotFoundException;
    
    UserDetails loadUserByUserName(String var1) throws UsernameNotFoundException;
}
