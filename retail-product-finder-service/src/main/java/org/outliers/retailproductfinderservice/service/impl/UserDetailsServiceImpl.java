/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.service.impl;

import org.outliers.retailproductfinderservice.objects.model.User;
import org.outliers.retailproductfinderservice.repository.UserRepository;
import org.outliers.retailproductfinderservice.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private UserRepository userRepository;

  @Autowired
  public UserDetailsServiceImpl(
      UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String userName)
      throws UsernameNotFoundException {
    User user = userRepository.findByUserName(userName)
        .orElseThrow(() ->
            new UsernameNotFoundException("User Not Found with -> username or email : " + userName)
        );
    return UserPrinciple.build(user);
  }
}