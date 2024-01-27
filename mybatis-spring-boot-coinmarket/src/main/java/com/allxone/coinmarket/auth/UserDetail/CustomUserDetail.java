package com.allxone.coinmarket.auth.UserDetail;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.allxone.coinmarket.model.Users;

public class CustomUserDetail implements UserDetails{
	private String email;
	
	private String username;

	private String password;

	private boolean isActive;

	private Collection<? extends GrantedAuthority> authorities;
	
	public CustomUserDetail(Users user, Collection<? extends GrantedAuthority> authorities) {
		this.username=user.getUsername();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.isActive = user.getIsActive();
		this.authorities = authorities;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	
	

	public boolean isActive() {
		return isActive;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}
	
}
