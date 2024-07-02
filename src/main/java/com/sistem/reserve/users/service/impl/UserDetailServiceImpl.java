package com.sistem.reserve.users.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sistem.reserve.users.repository.IUsersRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

  @Autowired
  private IUsersRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var users = repository.findByName(username);
    List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

    users.getRoles()
        .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName().name()))));
    users.getRoles().stream().flatMap(role -> role.getPermitsSet().stream())
        .forEach(permit -> authorityList.add(new SimpleGrantedAuthority(permit.getName().toString())));

    return new User(users.getName(), users.getPassword(), users.isEnabled(), users.isAccountNoExpired(),
        users.isCredentialNoExpired(), users.isAccountNoLocked(), authorityList);
  }
}
