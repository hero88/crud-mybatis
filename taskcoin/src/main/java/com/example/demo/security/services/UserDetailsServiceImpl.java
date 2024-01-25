package com.example.demo.security.services;

import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserMapper userMapper;

  @Autowired
  RoleMapper roleMapper;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userMapper.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User Not Found with username: " + username);
    }
    Set<Role> roles = roleMapper.findByUsername(username);
    user.setRoles(roles);
    return UserDetailsImpl.build(user);
  }

}
