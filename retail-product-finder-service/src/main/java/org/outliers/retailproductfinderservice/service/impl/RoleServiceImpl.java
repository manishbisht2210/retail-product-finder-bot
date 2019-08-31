/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.service.impl;

import java.util.List;
import java.util.Optional;
import org.outliers.retailproductfinderservice.objects.model.Role;
import org.outliers.retailproductfinderservice.objects.model.RoleName;
import org.outliers.retailproductfinderservice.repository.RoleRepository;
import org.outliers.retailproductfinderservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

  private RoleRepository roleRepository;

  @Autowired
  public RoleServiceImpl(
      RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public void save(Role role) {
    roleRepository.saveAndFlush(role);
  }

  @Override
  public List<Role> findAll() {
    return roleRepository.findAll();
  }

  @Override
  public Optional<Role> findByName(RoleName roleName) {
    return roleRepository.findByName(roleName);
  }
}
