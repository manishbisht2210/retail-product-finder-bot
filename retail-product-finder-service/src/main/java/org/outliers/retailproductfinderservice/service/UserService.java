/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.service;

import java.util.List;
import java.util.Optional;
import org.outliers.retailproductfinderservice.objects.model.User;

public interface UserService {

  boolean existsByUserName(String userName);

  boolean existsByEmail(String email);

  void save(User user);

  List<User> findAll();

  Optional<User> findById(String userId);

  Optional<User> findByUserName(String userName);
}
